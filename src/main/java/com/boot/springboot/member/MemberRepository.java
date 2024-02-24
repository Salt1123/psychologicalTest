package com.boot.springboot.member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    List<MemberEntity> findByUserid(String userid);


    @Modifying
    @Transactional
    @Query(value = "UPDATE jyson_user SET pw = :pw WHERE userid = :userid", nativeQuery = true)
    void updatePasswordByUserId(@Param("userid") String userid, @Param("pw") String pw);
}
