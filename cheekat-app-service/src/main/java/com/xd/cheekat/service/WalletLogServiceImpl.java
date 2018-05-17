package com.xd.cheekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cheekat.dao.WalletLogDao;
import com.xd.cheekat.pojo.WalletLog;

@Service
public class WalletLogServiceImpl implements WalletLogService{

	@Autowired
	private WalletLogDao walletLogDao;
	
	@Override
	public List<WalletLog> getWalletLogByUserId(long userId) {
		// TODO Auto-generated method stub
		return walletLogDao.getWalletLogByUserId(userId);
	}

}
