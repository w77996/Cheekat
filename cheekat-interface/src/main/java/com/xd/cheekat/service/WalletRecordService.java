package com.xd.cheekat.service;

import com.xd.cheekat.pojo.WalletRecord;

public interface WalletRecordService {

	WalletRecord getWallerOrderByRecordSN(String out_trade_no);

	boolean editUserWalletPayElse(String out_trade_no, long from_uid,
			int type, Double money, Double fee);

	void editWalletOrderPayStatus(String out_trade_no, int pay_status);

	String addWalletRecordOrder(long userId, String money, int payType,
			int orderType);

}
