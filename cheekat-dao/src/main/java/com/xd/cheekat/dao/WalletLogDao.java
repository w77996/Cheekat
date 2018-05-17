package com.xd.cheekat.dao;

import java.util.List;

import com.xd.cheekat.pojo.WalletLog;

public interface WalletLogDao {
    int deleteByPrimaryKey(Long logId);

    int insert(WalletLog record);

    int insertSelective(WalletLog record);

    WalletLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(WalletLog record);

    int updateByPrimaryKey(WalletLog record);

	List<WalletLog> getWalletLogByUserId(long userId);
}