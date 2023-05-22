package com.app.domain.member.service;

import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.constant.Status;
import com.app.domain.member.dto.MemberDto;
import com.app.domain.member.entity.Member;
import com.app.domain.member.repository.MemberRepository;
import com.app.global.config.JasyptConfig;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.error.exception.BusinessException;
import com.app.global.error.exception.EntityNotFoundException;
import com.app.global.jwt.dto.JwtTokenDto;
import com.app.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final TokenManager tokenManager;

    private final JasyptConfig jasyptConfig;

    /**
     * 회원가입(카카오 로그인 시 사용)
     */
    public Member registerMember(Member member) {
        validateDuplicateMember(member); //동일 이메일있는지 확인
        return memberRepository.save(member);
    }

    /**
     * 회원가입(사이트 자체 회원가입)
     */
    public Member createMember(Member member){
        validateDuplicateMember(member); //동일 이메일있는지 확인

        member.setMemberType(MemberType.DEFAULT); //자체 회원가입
        member.setRole(Role.USER);
        member.setPassword(encryptPassword(member.getPassword())); //비밀번호 암호화

        return memberRepository.save(member);
    }

    /**
     * 로그인
     */
    public MemberDto.LoginResponse login(Member member){
        JwtTokenDto jwtTokenDto;
        Optional<Member> optionalMember = findMemberByEmail(member.getEmail()); //이메일로 회원 검색

        if(optionalMember.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS); //존재 안하면 예외 처리
        }

        Member findMember = optionalMember.get();

        if(findMember.getStatus() == Status.DELETE){
            throw new EntityNotFoundException(ErrorCode.MEMBER_WITHDRAWN);
        }

        if(member.getPassword().equals("aaaa1111!")){
            if(!findMember.getPassword().equals(member.getPassword())){
                throw new AuthenticationException(ErrorCode.WRONG_PASSWROD); //비밀번호 일치 하지 않으면 예외처리
            }
        }else if(!decryptPassword(findMember.getPassword()).equals(member.getPassword())){
            throw new AuthenticationException(ErrorCode.WRONG_PASSWROD); //비밀번호 일치 하지 않으면 예외처리
        }

        jwtTokenDto = tokenManager.createJwtTokenDto(findMember.getMemberId(), findMember.getRole()); //토큰 생성

        findMember.updateRefreshToken(jwtTokenDto); //토큰값 설정
        memberRepository.save(findMember); //db에 리프레쉬 토큰 업데이트

        return MemberDto.LoginResponse.of(jwtTokenDto, findMember.getRole());
    }

    /**
     * 회원 목록 조회
     */
    @Transactional(readOnly = true)
    public Page<Member> findMembers(int page, int size){
        Page<Member> findAllMember = memberRepository.findAll(
                PageRequest.of(page, size, Sort.by("memberId").descending())
        );
        return findAllMember;
    }

    /**
     * 회원 수정
     */
    public Member updateMember(Member member){
        Member preMember = findVerifiedMemberByMemberId(member.getMemberId());

        if(member.getPassword() != null){
            String password = encryptPassword(member.getPassword());
            preMember.setPassword(password); //암호화된 비밀번호 설정
        }

        Optional.ofNullable(member.getAge())
                .ifPresent(age-> preMember.setAge(age));

        Optional.ofNullable(member.getRegion())
                .ifPresent(region->preMember.setRegion(region));

        return memberRepository.save(preMember);
    }

    /**
     * 회원 삭제
     */
    public Member deleteMember(Long memberId){
        Member member = findVerifiedMemberByMemberId(memberId);
        member.setStatus(Status.DELETE);

        return memberRepository.save(member);
    }
    /**
     * 로그인 회원 정보 조회
     */
    @Transactional(readOnly = true)
    public Member getLoginMember(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String accessToken = authorizationHeader.split(" ")[1];

        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        Long memberId = Long.valueOf( (Integer) tokenClaims.get("memberId"));
        return findVerifiedMemberByMemberId(memberId);
    }
    /**
     * 로그인한 회원 Role 조회(USER OR counselor)
     * */
    @Transactional(readOnly = true)
    public Role getLoginRole(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String accessToken = authorizationHeader.split(" ")[1];

        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        String role = (String) tokenClaims.get("role");
        if(role.equals("USER")) {
            return (Role) Enum.valueOf(Role.class, role);
        }else{
            return (Role) Enum.valueOf(Role.class, "ADMIN");
        }
    }
    /**
     * 회원 중복 확인(있으면 예외)
     */
    private void validateDuplicateMember(Member member) {
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
        if(optionalMember.isPresent()) {
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_MEMBER);
            //동일 이메일 있는 경우 에러 처리
        }
    }

    /**
     * 회원 존재 확인(없으면 예외)
     */
    @Transactional(readOnly = true)
    public Member findVerifiedMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    }

    @Transactional(readOnly = true)
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Member findMemberByRefreshToken(String refreshToken) {
        Member member = memberRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
        LocalDateTime tokenExpirationTime = member.getTokenExpirationTime();
        if(tokenExpirationTime.isBefore(LocalDateTime.now())) {
            throw new AuthenticationException(ErrorCode.REFRESH_TOKEN_EXPIRED); //refresh 토큰이 만료됐을 경우
        }
        return member;
    }

    public Member findMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    }

    /**
     * 비밀번호 암호화
     */
    public String encryptPassword(String password){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword(jasyptConfig.getPassword());
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");

        return encryptor.encrypt(password);
    }

    /**
     * 비밀번호 복호화
     */
    public String decryptPassword(String password){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword(jasyptConfig.getPassword());
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");

        return encryptor.decrypt(password);
    }
}
