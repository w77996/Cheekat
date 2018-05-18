package com.xd.cheekat.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xd.cheekat.common.Constant;
import com.xd.cheekat.pojo.Group;
import com.xd.cheekat.pojo.Mission;
import com.xd.cheekat.pojo.UserInfo;
import com.xd.cheekat.pojo.Wallet;
import com.xd.cheekat.service.FriendService;
import com.xd.cheekat.service.GroupService;
import com.xd.cheekat.service.MissionService;
import com.xd.cheekat.service.UserInfoService;
import com.xd.cheekat.service.WalletRecordService;
import com.xd.cheekat.service.WalletService;
import com.xd.cheekat.util.DateUtil;
import com.xd.cheekat.util.ImUtils;
import com.xd.cheekat.util.JsonUtils;
import com.xd.cheekat.util.PayCommonUtil;
import com.xd.cheekat.util.WxPayUtil;

@RestController
public class MissionController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserInfoService userService;
	
	@Autowired
	private MissionService missionService;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private WalletRecordService walletRecordService;
	
	@Autowired
	private GroupService groupService;
	
	/*@Autowired
	private JedisClient jedisClient;*/
	
	/**
	 * 查询所有的任务
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/open/getAllMission", produces = "text/html;charset=UTF-8")
	public String getAllMission(HttpServletRequest request) {
		String start = request.getParameter("start");
		String count = request.getParameter("count");
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		if(!StringUtils.isBlank(start) && !StringUtils.isBlank(count)) {
			/*List<Mission> mList = missionService.getExpiredMission();
			for(Mission mission:mList) {
				mission.setStatus(4);
				missionService.editMission(mission);
				Wallet wallet = walletService.findWalletByUserId(mission.getPublish_id());
					if(null == wallet){
						return JsonUtils.writeJson(0, 0, "参数错误");
					}
					//修改金额,更新订单支付状态，插入余额记录
					Double total_fee = wallet.getMoney()+mission.getMoney();
					String changemoney = ""+mission.getMoney();
					boolean isWalletSuccess = walletService.refund(mission.getRecord_sn(),mission.getPublish_id(),Constants.LOG_REFUND_TASK,Double.parseDouble(changemoney),total_fee);
					if(false == isWalletSuccess){
						return JsonUtils.writeJson(0, 22, "余额更新失败");
					}
			}*/
			List<Map<String,Object>> list = missionService.getAllMissionLimit(Integer.parseInt(start), Integer.parseInt(count));
			returnStr = JsonUtils.writeJson(1, "获取成功", list, "object");
		}
		return returnStr;
	}
	
	@RequestMapping(value = "/open/getMyMission", produces = "text/html;charset=UTF-8")
	public String getMyMission(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		if(!StringUtils.isBlank(userId)) {
			UserInfo user = userService.selectByPrimaryKey(Long.parseLong(userId));
		    if(user != null) {
		    	List<Map<String,Object>> list = missionService.getMyMission(Long.parseLong(userId));			    
				returnStr = JsonUtils.writeJson(1, "获取成功", list, "object");
		    }else {
		    	returnStr = JsonUtils.writeJson(0, 4, "用户不存在");			
		    }
		}
		return returnStr;
	}
	
	@RequestMapping(value = "/open/acceptMission", produces="application/json;charset=UTF-8")
	public String acceptMission(HttpServletRequest request) {
		String userId =  request.getParameter("userId");
		String missionId = request.getParameter("missionId");
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		if(!StringUtils.isBlank(userId) && !StringUtils.isBlank(missionId)) {
			UserInfo user = userService.selectByPrimaryKey(Long.parseLong(userId));
		    if(user != null) {
		    	Mission mission = missionService.getMissionById(Long.parseLong(missionId));
		    	if(mission != null) {
		    		if(mission.getSex() == user.getSex() || mission.getSex() == 3) {
		    			if(mission.getPublishId() != user.getUserId()) {
		    			if(mission.getAcceptId() == null) {
		    				mission.setAcceptId(user.getUserId());
			    			mission.setStatus(1);
			    			mission.setAcceptTime(DateUtil.getNowTime());
			 			    missionService.editMission(mission);
			 			    Mission resultMission = missionService.getMissionById(Long.parseLong(missionId));
			 			    //删除redis中的key
			 			    
			 			    returnStr = JsonUtils.writeJson(1, "领取成功", resultMission, "object");
		    			}else {
		    				returnStr = JsonUtils.writeJson(0, 9, "该任务已被领取");
		    			}
		    			}else {
		    				returnStr = JsonUtils.writeJson(0, 11, "不能领取自己的任务");
		    			}
		    			
		    		}else {
		    			returnStr = JsonUtils.writeJson(0, 6, "该任务不适合你");
		    		}
		    	}else {
		    		returnStr = JsonUtils.writeJson(0, 8, "任务不存在");
		    	}
		    }else {
		    	returnStr = JsonUtils.writeJson(0, 4, "用户不存在");			
		    }
		   }
		return returnStr;
	}
	
	@RequestMapping(value = "/open/completeMission", produces="application/json;charset=UTF-8")
	public String completeMission(HttpServletRequest request) {
		String userId =  request.getParameter("userId");
		String missionId = request.getParameter("missionId");
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		if(!StringUtils.isBlank(userId) && !StringUtils.isBlank(missionId)) {
			UserInfo user = userService.selectByPrimaryKey(Long.parseLong(userId));
		    if(user != null) {
		    	Mission mission = missionService.getMissionById(Long.parseLong(missionId));
		    	if(mission != null) {
		    		if(mission.getAcceptId() == Long.parseLong(userId) || mission.getPublishId() == Long.parseLong(userId)) {
		    			if(mission.getAcceptId() == Long.parseLong(userId)) {
		    				mission.setStatus(2);
		    			}else if(mission.getPublishId() == Long.parseLong(userId) && mission.getStatus() == 2) {
		    				mission.setStatus(3);
		    				mission.setFinishTime(DateUtil.getNowTime());
		    			}else {
		    				returnStr = JsonUtils.writeJson(0, 12, "无法确认完成");
		    			}
		 			    missionService.editMission(mission);
		 			   Wallet wallet = walletService.findWalletByUserId(mission.getAcceptId());
						if(null == wallet){
							return JsonUtils.writeJson(0, 0, "参数错误");
						}
		 			    Double total_fee = wallet.getMoney()+mission.getMoney();
						String changemoney = ""+mission.getMoney();
						boolean isWalletSuccess = walletService.editUserWalletPayBalance(mission.getRecordSn(),mission.getAcceptId(),Constant.LOG_FETCH_TASK,Double.parseDouble(changemoney),total_fee);
						if(false == isWalletSuccess){
							return JsonUtils.writeJson(0, 22, "余额更新失败");
						}
		 			    returnStr = JsonUtils.writeJson("设置成功", 1);
		    		}else {
		    			returnStr = JsonUtils.writeJson(0, 10, "无权限操作");
		    		}
		    	}else {
		    		returnStr = JsonUtils.writeJson(0, 8, "任务不存在");
		    	}
		    }else {
		    	returnStr = JsonUtils.writeJson(0, 4, "用户不存在");			
		    }
		   }
		return returnStr;
	}
	
	@RequestMapping(value = "/open/closeMission", produces="application/json;charset=UTF-8")
	public String closeMission(HttpServletRequest request) {
		String userId =  request.getParameter("userId");
		String missionId = request.getParameter("missionId");
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		if(!StringUtils.isBlank(userId) && !StringUtils.isBlank(missionId)) {
			UserInfo user = userService.selectByPrimaryKey(Long.parseLong(userId));
		    if(user != null) {
		    	Mission mission = missionService.getMissionById(Long.parseLong(missionId));
		    	if(mission != null) {
		    		if(mission.getPublishId() == Long.parseLong(userId)) {
                        if(mission.getStatus() == Constant.MISSION_TYPE_WATI_FETCH) {
		    				mission.setStatus(Constant.MISSION_TYPE_INVALID);
		    			}else {
		    				returnStr = JsonUtils.writeJson(0, 12, "无法关闭");
		    			}                        
		 			    missionService.editMission(mission);
		 			    Wallet wallet = walletService.findWalletByUserId(Long.parseLong(userId));
						if(null == wallet){
							return JsonUtils.writeJson(0, 0, "参数错误");
						}
						//修改金额,更新订单支付状态，插入余额记录
						Double total_fee = wallet.getMoney()+mission.getMoney();
						String changemoney = ""+mission.getMoney();
						boolean isWalletSuccess = walletService.refund(mission.getRecordSn(),Long.parseLong(userId),Constant.LOG_REFUND_TASK,Double.parseDouble(changemoney),total_fee);
//						if(false == isWalletSuccess){
//							return JsonUtils.writeJson(0, 22, "余额更新失败");
//						}
		 			    returnStr = JsonUtils.writeJson("设置成功", 1);
		    		}else {
		    			returnStr = JsonUtils.writeJson(0, 10, "无权限操作");
		    		}
		    	}else {
		    		returnStr = JsonUtils.writeJson(0, 8, "任务不存在");
		    	}
		    }else {
		    	returnStr = JsonUtils.writeJson(0, 4, "用户不存在");			
		    }
		   }
		return returnStr;
	}

	
	@RequestMapping(value = "/open/publishMission", produces="application/json;charset=UTF-8")
	public String publishMission(HttpServletRequest request) {
		String userId =  request.getParameter("userId");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		String sex = request.getParameter("sex");
		String money = request.getParameter("money");
		String address = request.getParameter("address");
		String startTime = request.getParameter("startTime");
		String to = request.getParameter("to");//0是发给所有人,1传用户,2是传群
		String anonymous = request.getParameter("anonymous");
		String pay_type = request.getParameter("payType");
		String toId = request.getParameter("toId"); //发送到群
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		if(!StringUtils.isBlank(userId) && !StringUtils.isBlank(content)&& !StringUtils.isBlank(type)&& !StringUtils.isBlank(sex)&& !StringUtils.isBlank(money)&& !StringUtils.isBlank(address) && !StringUtils.isBlank(startTime) && !StringUtils.isBlank(pay_type) && !StringUtils.isBlank(to)) {
			if(Integer.parseInt(to) > 0 && StringUtils.isBlank(toId)) {
				return returnStr;
			}
			UserInfo user = userService.selectByPrimaryKey(Long.parseLong(userId));
			String record_sn = "";
		    if(user != null) {
				if (Constant.PAY_TYPE_WECHAT == Integer.parseInt(pay_type)) {
					//微信支付发红包
					record_sn = walletRecordService.addWalletRecordOrder(Long.parseLong(userId),money,Constant.PAY_TYPE_WECHAT,Constant.ORDER_TYPE_TASK);
					if(null == record_sn){
						return JsonUtils.writeJson(0, 19, "订单生成失败");
					}
					SortedMap<Object, Object> map =  WxPayUtil.getPreperIdFromWX(record_sn, PayCommonUtil.getIpAddress(request),Constant.APP_NAME+Constant.TASK, Double.parseDouble(money));
					if(null == map){
						return JsonUtils.writeJson(0, 19, "订单生成失败");
					}
					Mission mission = new Mission();
			    	mission.setAddress(address);
			    	mission.setContent(content);
			    	mission.setCreateTime(DateUtil.getNowTime());
			    	mission.setMoney(Double.parseDouble(money));
			    	mission.setPublishId(user.getUserId());
			    	mission.setSex(Integer.parseInt(sex));
			    	mission.setStartTime(startTime);
			    	mission.setStatus(5);//等待微信返回支付成功
			    	mission.setType(Integer.parseInt(type));
			    	mission.setToType(Integer.parseInt(to));
			    	mission.setToId(toId);
			    	mission.setRecordSn(record_sn);
			    	mission.setAnonymous(Integer.parseInt(anonymous));
			    	System.out.println(mission.toString());
			    	missionService.addMission(mission);
			    	//jedisClient.set(record_sn, "mission");
			}else if(Constant.PAY_TYPE_BALANCE == Integer.parseInt(pay_type)){
					Wallet wallet = walletService.findWalletByUserId(Long.parseLong(userId));
					if(null == wallet){
						return JsonUtils.writeJson(0, 0, "参数错误");
					}
					//余额支付发红包
					if(wallet.getMoney().compareTo(new Double(money)) < 0){
						return JsonUtils.writeJson(0, 21, "余额不足");
					}
					//生成订单
					record_sn = walletRecordService.addWalletRecordOrder(Long.parseLong(userId),money,Constant.PAY_TYPE_BALANCE,Constant.ORDER_TYPE_TASK);
					if(null == record_sn){
						return JsonUtils.writeJson(0, 22, "订单生成失败");
					}
					//修改金额,更新订单支付状态，插入余额记录
					Double total_fee = wallet.getMoney()-Double.parseDouble(money);
					String changemoney = "-"+money;
					boolean isWalletSuccess = walletService.editUserWalletPayBalance(record_sn,Long.parseLong(userId),Constant.LOG_AWARD_TASK,Double.parseDouble(changemoney),total_fee);
					if(false == isWalletSuccess){
						return JsonUtils.writeJson(0, 22, "订单生成失败");
					}
					Mission mission = new Mission();
			    	mission.setAddress(address);
			    	mission.setContent(content);
			    	mission.setCreateTime(DateUtil.getNowTime());
			    	mission.setMoney(Double.parseDouble(money));
			    	mission.setPublishId(user.getUserId());
			    	mission.setSex(Integer.parseInt(sex));
			    	mission.setStartTime(startTime);
			    	mission.setStatus(0);
			    	mission.setType(Integer.parseInt(type));
			    	mission.setToType(Integer.parseInt(to));
			    	mission.setToId(toId);
			    	mission.setRecordSn(record_sn);
			    	mission.setAnonymous(Integer.parseInt(anonymous));
			    	System.out.println(mission.toString());
			    	missionService.addMission(mission);
			    	Mission mission2 = missionService.getMissionByPubIdAndCreateTime(user.getUserId(),mission.getCreateTime(),mission.getContent(),mission.getStartTime());
			    	if(Integer.parseInt(to) == 0){//如果发给所有人
			    	List<Map<String,Object>> fList = friendService.getUserFriends(Long.parseLong(userId));
			    	if(fList.size() > 20) {//每次只能发送给20个人
			    		int count = fList.size() / 20;
			    		for(int i = 0; i < count; i++) {
			    			String userNames = "";
			    			for(int j = 20*i; j < 20*(i+1); j++) {
			    				Map<String,Object> map = fList.get(j);
			    				if(userNames.equals("")) {		    					
				    				userNames = (String)map.get("user_name");
				    			}else {
				    				userNames = userNames.concat(",").concat((String)map.get("user_name"));
				    			}
			    			}
			    			ImUtils.sendTextMessage("users", userNames.split(","), "WtwdMissionTxt:好友"+user.getNickName()+"发布了一个任务，点击查看:"+mission2.getMissionId(),user.getUserName());
			    		}
			    		int mod = fList.size() % 20;
			    		String userNames = "";
			    		for(int i = count*20; i < count*20+mod; i++) {
			    			Map<String,Object> map = fList.get(i);
		    				if(userNames.equals("")) {		    					
			    				userNames = (String)map.get("user_name");
			    			}else {
			    				userNames = userNames.concat(",").concat((String)map.get("user_name"));
			    			}
			    		}
			    		ImUtils.sendTextMessage("users", userNames.split(","), "WtwdMissionTxt:好友"+user.getNickName()+"发布了一个任务，点击查看:"+mission2.getMissionId(),user.getUserName());
			    	}else if(fList.size() > 0) {//每次只能发送给20个人
			    		String userNames = "";
			    		for(Map<String,Object> map : fList) {
			    			if(userNames.equals("")) {
			    				userNames = (String)map.get("user_name");
			    			}else {
			    				userNames = userNames.concat(",").concat((String)map.get("user_name"));
			    			}
			    		}
			    		ImUtils.sendTextMessage("users", userNames.split(","), "WtwdMissionTxt:好友"+user.getNickName()+"发布了一个任务，点击查看:"+mission2.getMissionId(),user.getUserName());
			    	}
			    }else if(Integer.parseInt(to) == 1) {//发给个人
			    	UserInfo toUser = userService.getUserByUserName(toId);
			    	if(toUser != null) {
			    		ImUtils.sendTextMessage("users", new String[]{toUser.getUserName()}, "WtwdMissionTxt:好友"+user.getNickName()+"发布了一个任务，点击查看:"+mission2.getMissionId(),user.getUserName());
			    	}		    	
			    }else {//发群
			    	Group group = groupService.getGroupByImId(Long.parseLong(toId));
			    	if(group != null) {
			    		ImUtils.sendTextMessage("chatgroups", new String[]{group.getImGroupId()}, "WtwdMissionTxt:好友"+user.getNickName()+"发布了一个任务，点击查看:"+mission2.getMissionId(),user.getUserName());
			    	}		    	
			    }
			}		    	
		    	returnStr = JsonUtils.writeJson("发布成功", 1);
		    }else {
		    	returnStr = JsonUtils.writeJson(0, 4, "用户不存在");			
		    }
		   }
		return returnStr;
	}

	@RequestMapping(value = "/open/getMission",produces = "text/html;charset=UTF-8")
	public String getMission(@RequestParam String missionId){

		Mission m = missionService.getMissionById(Long.parseLong(missionId));
		List<Mission> list = new ArrayList<>();

		try {
			Date date = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(m.getStartTime());
			m.setStartTime(date.getTime()+"");
			Date date2 = sdf.parse(m.getCreateTime());
			m.setCreateTime(date2.getTime()+"");
			Date date3 = sdf.parse(m.getAcceptTime());
			m.setAcceptTime(date3.getTime()+"");
			list.add(m);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return JsonUtils.writeJson(1, "获取成功", list, "object");
	}
}
