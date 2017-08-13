package com.asd.mapper.location;

import com.asd.model.location.Country;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CountryMapper {

	@Select("SELECT * FROM COUNTRY WHERE ID = #{id}")
	Country findById(@Param("id") int id);

}
