package com.xd.cheekat.dao;

import com.xd.cheekat.pojo.Wallet;

public interface WalletDao {
    int deleteByPrimaryKey(Long walletId);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Long walletId);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);

	Wallet findWalletByUserId(long userId);
}