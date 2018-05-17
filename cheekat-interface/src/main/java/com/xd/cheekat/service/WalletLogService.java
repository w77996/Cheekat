package com.xd.cheekat.service;

import java.util.List;

import com.xd.cheekat.pojo.WalletLog;

public interface WalletLogService {
	
	List<WalletLog> getWalletLogByUserId(long userId);

}
