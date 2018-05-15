package com.xd.cheekat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xd.cheekat.pojo.UserIndexImg;

public interface UserIndexImgDao {
    int deleteByPrimaryKey(Long userImgId);

    int insert(UserIndexImg record);

    int insertSelective(UserIndexImg record);

    UserIndexImg selectByPrimaryKey(Long userImgId);

    int updateByPrimaryKeySelective(UserIndexImg record);

    int updateByPrimaryKey(UserIndexImg record);

	List<UserIndexImg> getUserIndexImgList(@Param("userId")long userId,@Param("start")int start,
			@Param("count")int count);

	int delUserIndexImg(@Param("usreId")long userId, @Param("imgpath")String[] imgpath);
}