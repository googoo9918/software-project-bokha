package com.app.domain.welfare;

import com.app.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikesRepository extends JpaRepository<UserLikes,Long> {

    Optional<UserLikes> findByMemberAndWelfareId(Member member,String welfareId);

}
