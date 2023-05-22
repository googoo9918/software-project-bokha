package com.app.domain.program.repository;

import com.app.domain.program.entity.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query(value = "select * from program where member_id = :memberId", nativeQuery = true)
    Page<Program> findAllByMember(long memberId, Pageable pageable);

    Optional<Program> findByServIdAndMemberMemberId(String servId, long memberId);
}
