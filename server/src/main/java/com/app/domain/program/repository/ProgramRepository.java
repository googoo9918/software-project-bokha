package com.app.domain.program.repository;

import com.app.domain.member.entity.Member;
import com.app.domain.program.entity.Program;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query(value = "select * from program where member_id = :memberId", nativeQuery = true)
    Page<Program> findAllByMember(long memberId, Pageable pageable);

    Optional<Program> findByServIdAndMemberMemberId(String servId, long memberId);

    @Query(value = "select p.serv_id from program p where member_id = :memberId",nativeQuery = true)
    List<String> findByMember(long memberId);
}
