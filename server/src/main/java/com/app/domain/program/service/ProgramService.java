package com.app.domain.program.service;


import com.app.domain.member.entity.Member;
import com.app.domain.program.entity.Program;
import com.app.global.error.ErrorCode;
import com.app.domain.program.repository.ProgramRepository;
import com.app.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ProgramService {
    private final ProgramRepository programRepository;

    public Program saveProgram(Program program, Member member){
        program.setMember(member);
        validateDuplicateSaveProgram(program, member);
        return programRepository.save(program);
    }

    @Transactional(readOnly = true)
    public Page<Program> searchSavePrograms(Long memberId, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("program_id").descending());
        Page<Program> programPage = programRepository.findAllByMember(memberId, pageable);
        return programPage;
    }

    /**
     * 프로그램 중복 검사(있으면 예외)
     * @param program
     */
    private void validateDuplicateSaveProgram(Program program, Member member){
        Optional<Program> optionalProgram = programRepository.findByServIdAndMemberMemberId(program.getServId(), member.getMemberId());
        if(optionalProgram.isPresent()){
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_PROGRAM);
        }
    }

}
