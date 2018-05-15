package com.xd.cheekat.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xd.cheekat.common.Constant;
import com.xd.cheekat.pojo.UserInfo;
import com.xd.cheekat.pojo.Wallet;
import com.xd.cheekat.service.UserInfoService;
import com.xd.cheekat.service.WalletService;
import com.xd.cheekat.util.JsonUtils;
import com.xd.cheekat.util.PayCommonUtil;
import com.xd.cheekat.util.WxPayUtil;

@RestController
public class WalletController {

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private WalletService walletService;
	
	/**
	 * 获取余额
	 * @Title:           getBalance
	 * @Description:     TODO
	 * @param:           @param userId
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping(value = "/open/getBalance")
	public String getBalance(@RequestParam String userId){
		if (StringUtils.isBlank(userId)) {
			return JsonUtils.writeJson(0, 0, "参数错误");
		}
		
		long user_id = Long.parseLong(userId);
		UserInfo user = userInfoService.selectByPrimaryKey(user_id);
		if(null == user){
			return JsonUtils.writeJson(0, 4, "用户不存在");
		}
		Wallet wallet = walletService.findWalletByUserId(user_id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("balance",wallet.getMoney().doubleValue());
		return JsonUtils.writeJson(1, "请求成功", result, "object");
	}
	
	/**
	 * 充值
	 * 
	 * @Title: rechargMoney
	 * @Description: TODO
	 * @param: @param user_id
	 * @param: @param money
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	@RequestMapping(value = "/open/recharge",produces = "text/html;charset=UTF-8")
	public String rechargeMoney(@RequestParam String userId,
			@RequestParam String payType, @RequestParam String money,
			@RequestParam String type, HttpServletRequest request) {
		if (StringUtils.isBlank(userId) || StringUtils.isBlank(payType)
				|| StringUtils.isBlank(money) || StringUtils.isBlank(type)) {
			return JsonUtils.writeJson(0, 0, "参数错误");
		}
		String result = JsonUtils.writeJson(0, 0, "参数错误");
		int pay_type = Integer.parseInt(payType);
		int taskType = Integer.parseInt(type);
		long user_id = Long.parseLong(userId);
		double price = Double.parseDouble(money);
		UserInfo user = userInfoService.selectByPrimaryKey(user_id);
		if (null == user) {
			return JsonUtils.writeJson(0, 4, "用户不存在");
		}

//		if (Constant.PAY_TYPE_WECHAT == pay_type) {
//			 if (Constant.ORDER_TYPE_TRADE == taskType) {
//				// 微信支付充值
//				String record_sn = walletRecordService.addWalletRecordOrder(user_id,money,Constant.PAY_TYPE_WECHAT,Constant.ORDER_TYPE_TRADE);
//				if (null == record_sn) {
//					return JsonUtils.writeJson(0, 19, "订单生成失败");
//				}
//				SortedMap<Object, Object> map = WxPayUtil.getPreperIdFromWX(
//						record_sn, PayCommonUtil.getIpAddress(request),
//						Constant.APP_NAME + Constant.RECHARGE, price);
//				if (null == map) {
//					return JsonUtils.writeJson(0, 19, "订单生成失败");
//				}
//				return JsonUtils.writeJson(1, "请求成功", map, "object");
//			} else {
//				return JsonUtils.writeJson(0, 0, "参数错误");
//			}
//		}
		return JsonUtils.writeJson(0, 0, "参数错误");
	}
}
