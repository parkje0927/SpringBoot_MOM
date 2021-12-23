package com.example.alarmandlogin.repository;

import com.example.alarmandlogin.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void saveOrUpdate(Member member);

    Member findByMbId(Long mbId);
}
