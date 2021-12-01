package com.edu.mapper.demo1;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Demo1Mapper {

    @Insert("insert into demo_user (username,age,sal) values (#{username},#{age},#{sal})")
    int addDemo1(@Param("username") String username,@Param("age") int age,@Param("sal") Double sal);
}
