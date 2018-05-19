package com.xd.cheekat.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.xd.cheekat.dao.MessageDao;
import com.xd.cheekat.pojo.Message;

@Service
public class MessageServiceImpl implements MessageService{

	
	@Autowired
	private MessageDao messageDao;
	
	@Override
	public int deleteByPrimaryKey(Long messageId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Message record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Message record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Message selectByPrimaryKey(Long messageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Message record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Message record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Message getLastMessage(int i) {
		// TODO Auto-generated method stub
		return messageDao.getLastMessage(i);
	}

}
