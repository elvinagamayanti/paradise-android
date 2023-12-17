package com.example.paradiseppk.service;

import com.example.paradiseppk.dto.MemberDto;

import java.util.List;

public interface MemberService {
    public MemberDto createMember(MemberDto memberDto);
    public List<MemberDto> getMembers();
    public List<MemberDto> searchMembers(String query);
    public MemberDto updateMember(MemberDto memberDto);
    public boolean deleteMember(Long id);
    public MemberDto getMember(Long id);
}
