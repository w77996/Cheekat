package com.xd.cheekat.service;

import org.springframework.stereotype.Service;

import com.xd.cheekat.pojo.WalletRecord;
@Service
public class WalletRecordServiceImpl implements WalletRecordService{

	@Override
	public WalletRecord getWallerOrderByRecordSN(String out_trade_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editUserWalletPayElse(String out_trade_no, long from_uid,
			int type, Double money, Double fee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void editWalletOrderPayStatus(String out_trade_no, int pay_status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String addWalletRecordOrder(long userId, String money, int payType,
			int orderType) {
		// TODO Auto-generated method stub
		return null;
	}

}
