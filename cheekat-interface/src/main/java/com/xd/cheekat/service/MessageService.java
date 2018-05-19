package com.xd.cheekat.service;

import com.xd.cheekat.pojo.Message;

public interface MessageService {
    int deleteByPrimaryKey(Long messageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long messageId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

	Message getLastMessage(int i);
}