package com.xd.cheekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xd.cheekat.common.Constant;
import com.xd.cheekat.dao.WalletRecordDao;
import com.xd.cheekat.pojo.WalletLog;
import com.xd.cheekat.pojo.WalletRecord;
import com.xd.cheekat.util.DateUtil;
import com.xd.cheekat.util.PayCommonUtil;
@Service
public class WalletRecordServiceImpl implements WalletRecordService{

	
	@Autowired
	private WalletRecordDao walletRecordDao;
	
	@Override
	public WalletRecord getWallerOrderByRecordSN(String out_trade_no) {
		// TODO Auto-generated method stub
		return walletRecordDao.getWallerOrderByRecordSN(out_trade_no);
	}

	@Transactional
	@Override
	public boolean editUserWalletPayElse(String out_trade_no, long from_uid,
			int type, Double money, Double fee) {
		// TODO Auto-generated method stub
		String date = DateUtil.getNowTime();
		WalletRecord walletRecord = new WalletRecord();
		walletRecord.setPayStatus(Constant.PAY_STATUS_SUCCESS);
		walletRecord.setPayTime(date);
		 walletRecordDao.updateWalletRecordByRecordSn(walletRecord);
		//更新日志
		WalletLog walletLog = new WalletLog();
		walletLog.setRecordSn(out_trade_no);
		walletLog.setUserId(from_uid);
		walletLog.setType(type);
		walletLog.setChangeMoney(money);
		walletLog.setMoney(fee);
		walletLog.setCreateTime(date);
		return true;
	}

	@Override
	public void editWalletOrderPayStatus(String out_trade_no, int pay_status) {
		// TODO Auto-generated method stub
		WalletRecord walletRecord = new WalletRecord();
		walletRecord.setRecordSn(out_trade_no);
		walletRecord.setPayStatus(pay_status);
		walletRecord.setPayTime(DateUtil.getNowTime());
		walletRecordDao.updateWalletRecordByRecordSn(walletRecord);
		
	}

	@Override
	public String addWalletRecordOrder(long userId, String money, int payType,
			int orderType) {
		// TODO Auto-generated method stub
		String record_sn = PayCommonUtil.createOutTradeNo();
		WalletRecord walletRecord = new WalletRecord();
		walletRecord.setFromUid(userId);
		walletRecord.setRecordSn(record_sn);
		walletRecord.setType(orderType);
		walletRecord.setPayType(payType);
		walletRecord.setMoney(Double.parseDouble(money));
		int i = walletRecordDao.insertSelective(walletRecord);
		if(0 < i){
			return record_sn;
		}
		return null;
	}

}
