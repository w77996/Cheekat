package com.xd.cheekat.service;

import com.xd.cheekat.pojo.Wallet;

public interface WalletService {

	void addNewUser(Long userId);

	Wallet findWalletByUserId(long userId);

	boolean editUserWalletPayBalance(String out_trade_no, long from_uid,
			int type, Double money, Double fee);

	boolean editUserWalletFetchBalance(String recordSn, Long userId,
			int logType, Double money, Double total_fee);

}
