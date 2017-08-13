package com.asd.mapper.user;

import com.asd.model.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

	@Select("SELECT * FROM USERS WHERE ID = #{id}")
	@Results(value = {
			@Result(property = "location", column = "location_id", one = @One(select = "com.asd.mapper.location.LocationMapper.findById")),
			@Result(property = "group", column = "user_group_id", one = @One(select = "com.asd.mapper.user.GroupMapper.findById"))
	})
	User findById(@Param("id") int id);
}
