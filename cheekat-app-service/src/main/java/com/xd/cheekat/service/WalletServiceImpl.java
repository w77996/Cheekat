package com.xd.cheekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cheekat.dao.WalletDao;
import com.xd.cheekat.pojo.Wallet;
@Service
public class WalletServiceImpl implements WalletService{

	@Autowired
	private WalletDao walletDao;
	
	@Override
	public void addNewUser(Long userId) {
		// TODO Auto-generated method stub
		Wallet wallet = new Wallet();
		wallet.setUserId(userId);
		walletDao.insertSelective(wallet);
	}

	@Override
	public Wallet findWalletByUserId(long userId) {
		// TODO Auto-generated method stub
		return walletDao.findWalletByUserId(userId);
	}

	@Override
	public void editUserWalletPayBalance(String out_trade_no, long from_uid,
			int type, Double money, Double fee) {
		// TODO Auto-generated method stub
		
	}

}
