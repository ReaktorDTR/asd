package com.asd.mapper.user;

import com.asd.model.user.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GroupMapper {

	@Select("SELECT * FROM USER_GROUP WHERE ID = #{id}")
	Group findById(@Param("id") int id);

}
