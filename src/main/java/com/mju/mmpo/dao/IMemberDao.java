package com.mju.mmpo.dao;

import com.mju.mmpo.domain.Member;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Allen李
 * @date
 */
public interface IMemberDao {
    @Select("select * from member where id=#{id}")
    public Member selectById(String id) throws Exception;

    @Select("select * from member")
    public List<Member> findAll() throws  Exception;
}
