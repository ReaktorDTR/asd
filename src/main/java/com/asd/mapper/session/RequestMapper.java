package com.asd.mapper.session;

import com.asd.model.session.Request;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RequestMapper {

	@Select("SELECT * FROM REQUEST WHERE ID = #{id}")
	@Results(value = {
			@Result(property = "session", column = "session_id", one = @One(select = "com.asd.mapper.session.SessionMapper.findById"))
	})
	Request findById(@Param("id") int id);

	@Select("SELECT * FROM REQUEST LIMIT #{limit}")
	@Results(value = {
			@Result(property = "session", column = "session_id", one = @One(select = "com.asd.mapper.session.SessionMapper.findById"))
	})
	List<Request> getRequests(@Param("limit") Integer limit);
}
