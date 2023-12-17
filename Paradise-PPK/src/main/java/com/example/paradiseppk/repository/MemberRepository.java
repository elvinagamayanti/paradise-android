package com.example.paradiseppk.repository;

import com.example.paradiseppk.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends PagingAndSortingRepository<Member, Long>, CrudRepository<Member, Long>, JpaRepository<Member, Long> {
    List<Member> findByNama(@Param("nama") String nama);
    @Query("SELECT m FROM Member m WHERE m.nim = :nim")
    List<Member> findByNim(@Param("nim") String nim);
}
