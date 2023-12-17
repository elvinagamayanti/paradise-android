package com.example.paradiseppk.mapper;

import com.example.paradiseppk.dto.MemberDto;
import com.example.paradiseppk.entity.Member;

public class MemberMapper {
    public static Member mapToMember(MemberDto memberDto){
        return Member.builder()
                .id(memberDto.getId())
                .nim(memberDto.getNim())
                .nama(memberDto.getNama())
                .angkatan(memberDto.getAngkatan())
                .divisi(memberDto.getDivisi())
                .build();
    }
    public static MemberDto mapToMemberDto(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .nim(member.getNim())
                .nama(member.getNama())
                .angkatan(member.getAngkatan())
                .divisi(member.getDivisi())
                .build();
    }
}
