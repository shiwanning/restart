package com.restart.dao;

import com.restart.bean.Member;

import java.util.List;

public interface MemberDao {
     List<Member> findMemberByUserName(Long UserName);
}
