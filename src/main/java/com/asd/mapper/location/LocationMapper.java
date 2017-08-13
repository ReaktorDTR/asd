package com.asd.mapper.location;

import com.asd.model.location.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LocationMapper {

	@Select("SELECT * FROM LOCATION WHERE ID = #{id}")
	@Results(value = {
			@Result(property = "country", column = "country_id", one = @One(select = "com.asd.mapper.location.CountryMapper.findById"))
	})
	Location findById(@Param("id") int id);

}
