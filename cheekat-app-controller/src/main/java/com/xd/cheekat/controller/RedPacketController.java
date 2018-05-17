package com.xd.cheekat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xd.cheekat.common.Constant;
import com.xd.cheekat.pojo.Group;
import com.xd.cheekat.pojo.RedPacket;
import com.xd.cheekat.pojo.UserInfo;
import com.xd.cheekat.pojo.Wallet;
import com.xd.cheekat.pojo.WalletRecord;
import com.xd.cheekat.service.GroupService;
import com.xd.cheekat.service.RedPacketService;
import com.xd.cheekat.service.UserInfoService;
import com.xd.cheekat.service.WalletRecordService;
import com.xd.cheekat.service.WalletService;
import com.xd.cheekat.util.ImUtils;
import com.xd.cheekat.util.JsonUtils;
import com.xd.cheekat.util.PayCommonUtil;
import com.xd.cheekat.util.WxPayUtil;

@RestController
public class RedPacketController {

	
	 @Autowired
	    private UserInfoService userService;

	    @Autowired
	    private WalletRecordService walletRecordService;

	    @Autowired
	    private RedPacketService redPacketService;

	    @Autowired
	    private WalletService walletService;

	    @Autowired
	    private GroupService groupService;


	    /**
	     * 抢红包
	     *
	     * @throws
	     * @Title: getRedPacket
	     * @Description: TODO
	     * @param: @param user_id
	     * @param: @param redpacket_id
	     * @param: @return
	     * @return: String
	     */
	    @RequestMapping(value = "/open/getRedPacket", produces = "text/html;charset=UTF-8")
	    public String userGetRedPacket(@RequestParam String userId,
	                               @RequestParam String redpacketId) {
	        // 查询领取的用户信息
	        UserInfo user = userService.selectByPrimaryKey(Long.parseLong(userId));
	        if (null == user) {
	            return JsonUtils.writeJson(0, 4, "用户不存在");
	        }
	        // 查询红包信息
	        RedPacket redPacket = redPacketService.getRedPacketById(Long.parseLong(redpacketId));
	        if (null == redPacket) {
	            return JsonUtils.writeJson(0, 16, "红包id不存在");
	        }
	        WalletRecord walletRecord = walletRecordService.getWallerOrderByRecordSN(redPacket.getRecordSn());
	        System.out.println("wallerTecord "+walletRecord.toString());
	        if (walletRecord.getMoney().compareTo(redPacket.getMoney()) != 0) {
	            return JsonUtils.writeJson(0, 18, "红包领取失败");
	        }
	        if(Constant.REDPACKET_INVALID == redPacket.getStatus()){
	        	return JsonUtils.writeJson(0, 18, "红包失效");
	        }
	        if (redPacket.getStatus() == Constant.FETCH_SUCCESS) {
	            if (redPacket.getPublishId() == user.getUserId()) {
	                //查询被领取人的信息
	                UserInfo acceptUser = userService.selectByPrimaryKey(redPacket.getAcceptId());
	                Map<String, Object> map = new HashMap<>();
	                map.put("user", acceptUser);
	                map.put("redpacket", redPacket);
	                return JsonUtils.writeJson(1, "请求成功", map, "object");
	            }
	            return JsonUtils.writeJson(0, 17, "红包被领取");
	        }
	        if (redPacket.getPublishId() == user.getUserId()) {
	            return JsonUtils.writeJson(0, 36, "不能领取自己的红包");
	        }
	        if(redPacket.getStatus() == Constant.FETCH_WAIT){
	        	 //更新红包状态
		        boolean isRedPacket = redPacketService.editRedPacketFetchStatus(redPacket.getRecordSn(), user.getUserId(), Constant.FETCH_SUCCESS);
		        if (false == isRedPacket) {
		            return JsonUtils.writeJson(0, 18, "红包领取失败");
		        }
		        //更新状态
		        Wallet wallet = walletService.findWalletByUserId(user.getUserId());
		        Double total_fee = wallet.getMoney() + redPacket.getMoney();
		        boolean isWalletSuccess = walletService.editUserWalletFetchBalance(redPacket.getRecordSn(), user.getUserId(), Constant.LOG_FETCH_REDPACKET, redPacket.getMoney(), total_fee);
		        if (false == isWalletSuccess) {
		            return JsonUtils.writeJson(0, 18, "红包领取失败");
		        }
		        RedPacket redPacket2 = redPacketService.getRedPacketById(Long.parseLong(redpacketId));
		        if (null != redPacket2) {
		            return JsonUtils.writeJson(1, "领取成功", redPacket2, "object");
		        } else {
		            return JsonUtils.writeJson(0, 18, "红包领取失败");
		        }
	        }
	        return  JsonUtils.writeJson(0, 0, "参数错误");
	       
	    }

	    /**
	     * 支付红包
	     *
	     * @throws
	     * @Title: payRedPacket
	     * @Description: TODO
	     * @param: @param userId
	     * @param: @param payType
	     * @param: @param money
	     * @param: @param type
	     * @param: @param request
	     * @param: @return
	     * @return: String
	     */
	    @RequestMapping(value = "/open/payRedPacket", produces = "text/html;charset=UTF-8")
	    public String payRedPacket(@RequestParam String payType, @RequestParam String money,
	                               @RequestParam String type, @RequestParam String to, @RequestParam String to_id,@RequestParam String userName, HttpServletRequest request) {
	        System.out.println("进入");
	      
	        String result = JsonUtils.writeJson(0, 0, "参数错误");
	        int pay_type = Integer.parseInt(payType);
	        int taskType = Integer.parseInt(type);
	        Double price = Double.parseDouble(money);
	        UserInfo user = userService.getUserByUserName(userName);
	        if (null == user) {
	            return JsonUtils.writeJson(0, 4, "用户不存在");
	        }
	        if (Constant.PAY_TYPE_WECHAT == pay_type) {
	            if (Constant.ORDER_TYPE_REDPACKET == taskType) {
	                //微信支付发红包
	                String record_sn = walletRecordService.addWalletRecordOrder(user.getUserId(), money, Constant.PAY_TYPE_WECHAT, Constant.ORDER_TYPE_REDPACKET);
	                if (null == record_sn) {
	                    return JsonUtils.writeJson(0, 19, "订单生成失败");
	                }
	                //生成红包
	                boolean isRedPacketSuccess = redPacketService.addRedpacketRecord(record_sn, String.valueOf(user.getUserId()), money, to, to_id);
	                if (false == isRedPacketSuccess) {
	                    return JsonUtils.writeJson(0, 22, "红包发送失败");
	                }
	                //请求微信prepay发送给手机
	                SortedMap<Object, Object> map = WxPayUtil.getPreperIdFromWX(record_sn, PayCommonUtil.getIpAddress(request), Constant.APP_NAME + Constant.REDPACKET, price);
	                if (null == map) {
	                    return JsonUtils.writeJson(0, 19, "订单生成失败");
	                }
	                return JsonUtils.writeJson(1, "请求成功", map, "object");
	            }
	        } else if (Constant.PAY_TYPE_BALANCE == pay_type) {
	            if (Constant.ORDER_TYPE_REDPACKET == taskType) {
	                Wallet wallet = walletService.findWalletByUserId(user.getUserId());
	                if (null == wallet) {
	                    return JsonUtils.writeJson(0, 0, "参数错误");
	                }
	                //余额支付发红包
	                if (wallet.getMoney().compareTo(new Double(money)) < 0) {
	                    return JsonUtils.writeJson(0, 21, "余额不足");
	                }
	                //生成订单
	                String record_sn = walletRecordService.addWalletRecordOrder(user.getUserId(), money, Constant.PAY_TYPE_BALANCE, Constant.ORDER_TYPE_REDPACKET);
	                if (null == record_sn) {
	                    return JsonUtils.writeJson(0, 22, "红包发送失败");
	                }
	                //生成红包，待支付
	                boolean isRedPacketSuccess = redPacketService.addRedpacketRecord(record_sn, String.valueOf(user.getUserId()), money, to, to_id);
	                if (false == isRedPacketSuccess) {
	                    return JsonUtils.writeJson(0, 22, "红包发送失败");
	                }
	                //修改金额,更新订单支付状态，插入余额记录
	                Double total_fee = wallet.getMoney() - Double.parseDouble(money);
	                String changemoney = "-" + money;
	                boolean isWalletSuccess = walletService.editUserWalletPayBalance(record_sn, user.getUserId(), Constant.LOG_AWARD_REDPACKET, Double.parseDouble(changemoney), total_fee);
	                if (false == isWalletSuccess) {
	                    return JsonUtils.writeJson(0, 22, "红包发送失败");
	                } else {
	                    System.out.println(record_sn);
	                    //修改红包支付状态
	                    redPacketService.editRedPacketPayStatus(record_sn,Constant.PAY_STATUS_SUCCESS);
	                    //判断to类型是群发还是个人红包
	                    RedPacket redPacket = redPacketService.getRedPacketByRecordSN(record_sn);
	                    UserInfo fromUser = userService.selectByPrimaryKey(user.getUserId());
	                    //个人,直接获取个人Id并发送至环信
	                    if (Constant.TO_TYPE_PRIVATE == redPacket.getToType()) {
	                        UserInfo toUser = userService.getUserByUserName(redPacket.getToId());
	                        ImUtils.sendTextMessage("users", new String[]{toUser.getUserName()}, "WtwdRedPacketTxt:好友" + fromUser.getNickName()  + "发布了一个红包，点击查看:" + redPacket.getRedpacketId(),fromUser.getUserName());
	                        //ImUtils.sendTextMessage("users", new String[]{fromUser.getUser_name()}, "WtwdRedPacketTxt:" + fromUser.getNick_name()  + "发布了一个红包，点击查看:" + redPacket.getRedpacket_id());
	                    } else if (Constant.TO_TYPE_GROUP == redPacket.getToType()) {
	                        //群发，获取群成员的名称，并发送
	                        Group group = groupService.getGroupByImId(Long.parseLong(redPacket.getToId()));
	                        if (null !=group) {
	                            ImUtils.sendTextMessage("chatgroups", new String[]{group.getImGroupId()}, "WtwdRedPacketTxt:好友" + fromUser.getNickName()  + "发布了一个红包，点击查看:" + redPacket.getRedpacketId(),fromUser.getUserName());

	                        }
	                    }
	                    return JsonUtils.writeJson("红包发送成功", 1);
	                }
	            }
	        }
	        return JsonUtils.writeJson(0, 0, "参数错误");
	    }
}
