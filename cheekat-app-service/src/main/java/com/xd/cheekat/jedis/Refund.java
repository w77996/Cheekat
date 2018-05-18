package com.xd.cheekat.jedis;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xd.cheekat.common.Constant;
import com.xd.cheekat.pojo.Mission;
import com.xd.cheekat.pojo.RedPacket;
import com.xd.cheekat.pojo.Wallet;
import com.xd.cheekat.pojo.WalletRecord;
import com.xd.cheekat.service.MissionService;
import com.xd.cheekat.service.RedPacketService;
import com.xd.cheekat.service.WalletLogService;
import com.xd.cheekat.service.WalletRecordService;
import com.xd.cheekat.service.WalletService;
@Component
public class Refund {
	
	
	@Resource
    private WalletRecordService walletRecordService;
	
	@Autowired
	private WalletLogService walletLogService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private MissionService missionService;
	
	@Autowired
	private RedPacketService redPacketService;
    
    private static Refund Refund;
    
    @PostConstruct
    public void init() {
    	Refund = this;
    	Refund.walletRecordService = this.walletRecordService;
    	Refund.walletService = this.walletService;
    	Refund.walletLogService = this.walletLogService;
    	Refund.missionService = this.missionService;
    	Refund.redPacketService = this.redPacketService;
    }
    

    public static WalletRecord getWallerOrderByRecordSN(String sn) {
    	return Refund.walletRecordService.getWallerOrderByRecordSN(sn);
    }
    
    public static void refundMission(String sn){
    	Mission mission =Refund. missionService.getMissionByRecordSN(sn);
		mission.setStatus(Constant.MISSION_TYPE_INVALID);
		Refund.missionService.editMission(mission);
		
		Wallet wallet = Refund.walletService.findWalletByUserId(mission.getPublishId()); 
		
		Double total_fee = wallet.getMoney()+mission.getMoney();
		String changemoney = ""+mission.getMoney();
		Refund.walletService.refund(mission.getRecordSn(),mission.getPublishId(),Constant.LOG_REFUND_TASK,Double.parseDouble(changemoney),total_fee);

    }
    public static void refundRedPacket(String sn){
    	RedPacket redPacket = Refund.redPacketService.getRedPacketByRecordSN(sn);
		redPacket.setStatus(Constant.REDPACKET_INVALID);
		Refund.redPacketService.editRedPacket(redPacket);
		
		Wallet wallet = Refund.walletService.findWalletByUserId(redPacket.getPublishId()); 
		
		Double total_fee = wallet.getMoney()+redPacket.getMoney();
		String changemoney = ""+redPacket.getMoney();
		Refund.walletService.refund(redPacket.getRecordSn(),redPacket.getPublishId(),Constant.LOG_REFUND_TASK,Double.parseDouble(changemoney),total_fee);
		
    }
	
}
