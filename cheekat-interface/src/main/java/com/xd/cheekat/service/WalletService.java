package com.xd.cheekat.service;

import com.xd.cheekat.pojo.Wallet;

public interface WalletService {

	void addNewUser(Long userId);

	Wallet findWalletByUserId(long userId);

	void editUserWalletPayBalance(String out_trade_no, long from_uid,
			int type, Double money, Double fee);

}
