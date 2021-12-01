package com.edu.mapper.demo2;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Demo2Mapper {

    @Insert("insert into demo2_city (city) values (#{city})")
    int addDemo2(@Param("city") String city);
}
