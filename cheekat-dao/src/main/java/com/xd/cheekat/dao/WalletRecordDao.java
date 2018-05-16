package com.xd.cheekat.dao;

import com.xd.cheekat.pojo.WalletRecord;

public interface WalletRecordDao {
    int deleteByPrimaryKey(Long walletRecordId);

    int insert(WalletRecord record);

    int insertSelective(WalletRecord record);

    WalletRecord selectByPrimaryKey(Long walletRecordId);

    int updateByPrimaryKeySelective(WalletRecord record);

    int updateByPrimaryKey(WalletRecord record);

	WalletRecord getWallerOrderByRecordSN(String recordSn);

	void updateWalletRecordByRecordSn(WalletRecord walletRecord);
}