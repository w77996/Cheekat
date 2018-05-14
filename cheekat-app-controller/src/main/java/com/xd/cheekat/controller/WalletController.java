package com.xd.cheekat.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xd.cheekat.pojo.UserInfo;
import com.xd.cheekat.service.UserInfoService;
import com.xd.cheekat.util.JsonUtils;

@RestController
public class WalletController {

	@Autowired
	private UserInfoService userInfoService;
	
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
		/*Wallet wallet = walletService.findWalletByUserId(user_id);*/
		Map<String, Object> result = new HashMap<String, Object>();
		//result.put("balance",wallet.getMoney().doubleValue());
		return JsonUtils.writeJson(1, "请求成功", result, "object");
	}
}
