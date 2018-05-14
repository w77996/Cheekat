package com.xd.cheekat.service;

import com.xd.cheekat.pojo.Wallet;

public interface WalletService {

	void addNewUser(Long userId);

	Wallet findWalletByUserId(long userId);

}
