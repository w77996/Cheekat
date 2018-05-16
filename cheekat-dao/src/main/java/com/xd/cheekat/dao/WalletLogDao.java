package com.xd.cheekat.dao;

import com.xd.cheekat.pojo.WalletLog;

public interface WalletLogDao {
    int deleteByPrimaryKey(Long logId);

    int insert(WalletLog record);

    int insertSelective(WalletLog record);

    WalletLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(WalletLog record);

    int updateByPrimaryKey(WalletLog record);
}