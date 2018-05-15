package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import com.xd.cheekat.pojo.UserIndexImg;

public interface UserIndexImgService {

	List<UserIndexImg> getUserIndexImgList(long usreId, int start,
			int count);

	int addUserIndexImg(long user_id, String path);

	int delUserIndexImg(long userId, String[] imgpath);

}
