package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cheekat.dao.UserIndexImgDao;
import com.xd.cheekat.pojo.UserIndexImg;
import com.xd.cheekat.util.DateUtil;

@Service
public class UserIndexImgServiceImpl implements UserIndexImgService{

	@Autowired
	UserIndexImgDao userIndexImgDao;
	
	@Override
	public List<UserIndexImg> getUserIndexImgList(long usreId,
			int start, int count) {
		// TODO Auto-generated method stub
		return userIndexImgDao.getUserIndexImgList(usreId,start,count);
	}

	@Override
	public int addUserIndexImg(long user_id, String path) {
		// TODO Auto-generated method stub
		UserIndexImg userIndexImg = new UserIndexImg();
		userIndexImg.setUserId(user_id);
		userIndexImg.setImg(path);
		userIndexImg.setCreateTime(DateUtil.getNowTime());
		return userIndexImgDao.insertSelective(userIndexImg);
	}

	@Override
	public int delUserIndexImg(long userId, String[] imgpath) {
		// TODO Auto-generated method stub
		return userIndexImgDao.delUserIndexImg(userId,imgpath);
	}

}
