package com.asd.mapper.session;

import com.asd.model.session.Session;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SessionMapper {

	@Select("SELECT * FROM SESSION WHERE ID = #{id}")
	@Results(value = {
			@Result(property = "user", column = "user_id", one = @One(select = "com.asd.mapper.user.UserMapper.findById"))
	})
	Session findById(@Param("id") int id);
}
