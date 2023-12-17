package com.example.paradiseppk.service;

import com.example.paradiseppk.dto.MemberDto;
import com.example.paradiseppk.entity.Member;
import com.example.paradiseppk.mapper.MemberMapper;
import com.example.paradiseppk.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepository;
    
    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = memberRepository.save(MemberMapper.mapToMember(memberDto));
        return MemberMapper.mapToMemberDto(member);
    }
    
    @Override
    public List<MemberDto> getMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberDto> memberDtos = members.stream()
        .map((product) -> (MemberMapper.mapToMemberDto(product)))
        .collect(Collectors.toList());
        return memberDtos;
    }
    
    @Override
    public List<MemberDto> searchMembers(String query){
        List<Member> members = memberRepository.findByNama(query);
        List<MemberDto> memberDtos = members.stream()
                .map((product) -> (MemberMapper.mapToMemberDto(product)))
                .collect(Collectors.toList());
        
        return memberDtos;
    }
    
    @Override
    public MemberDto getMember(Long id) {
        Member member = memberRepository.getReferenceById(id);
        return MemberMapper.mapToMemberDto(member);
    }
    
    @Override
    public MemberDto updateMember(MemberDto memberDto) {
        Member member = memberRepository.save(MemberMapper.mapToMember(memberDto));
        return MemberMapper.mapToMemberDto(member);
    }
    
    @Override
    public boolean deleteMember(Long id) {
        memberRepository.deleteById(id);
        return true;
    }
}
