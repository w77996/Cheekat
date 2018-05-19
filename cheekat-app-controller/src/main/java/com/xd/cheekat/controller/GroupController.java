package com.xd.cheekat.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import RespCode.RespCode;

import com.xd.cheekat.pojo.Group;
import com.xd.cheekat.pojo.GroupDetails;
import com.xd.cheekat.pojo.UserInfo;
import com.xd.cheekat.service.FriendService;
import com.xd.cheekat.service.GroupDetailsService;
import com.xd.cheekat.service.GroupService;
import com.xd.cheekat.service.UserInfoService;
import com.xd.cheekat.util.DateUtil;
import com.xd.cheekat.util.ImUtils;
import com.xd.cheekat.util.JsonUtils;

@RestController
public class GroupController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private GroupDetailsService groupDetailsService;
	
	@RequestMapping(value="/open/getGroupDetailInfo")
	public String getGroupDetailInfo(@RequestParam String userId,@RequestParam String groupId){
		
		UserInfo user = userInfoService.selectByPrimaryKey(Long.parseLong(userId));
		if(null == user){
			return JsonUtils.writeJson(0, RespCode.ERROR_USER_NOT_EXIST.getCode(), RespCode.ERROR_USER_NOT_EXIST.getMsg());	
		}
		//获取groupId下的用户信息
		List<Map<String,Object>> userList = groupDetailsService.getUserGroupByImId(groupId);

		return JsonUtils.writeJson(1, "获取成功", userList, "object");
	}
	
	/**
	 * c查询附近的人
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/open/addGroup", produces = "text/html;charset=UTF-8")
	public String addGroup(HttpServletRequest request) {
		String groupName = request.getParameter("groupName");
		String userId = request.getParameter("userId");
		String members = request.getParameter("members");
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		System.out.println("groupName "+groupName+" members "+members);
	try {
		groupName = new String(groupName.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("groupName "+groupName);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		boolean isFriend = true;
		if(!StringUtils.isBlank(userId) && !StringUtils.isBlank(members) && !StringUtils.isBlank(groupName)) {
			String[] member = members.split(",");
			if(member.length >= 2) {
			for(int i = 0; i < member.length; i++) {
				List<Map<String,Object>> list = friendService.getFriendByTwoId(Long.parseLong(userId), Long.parseLong(member[i]));
				if(list.size() > 0) {
					for(Map<String,Object> map : list) {
						int status = (Integer)map.get("status");
						if(status != 2) {
							isFriend = false;
						}
					}
				}
			}
			if(isFriend) {
				String[] ids = userId.concat(",").concat(members).split(",");
				List<UserInfo> userList = userInfoService.getUserByIds(ids);
				String memberNames = "";
				String admin = "";
				for(UserInfo user:userList) {
					if(user.getUserId() == Long.parseLong(userId)) {
						admin = user.getUserName();
					}
					String[] memberStr = members.split(",");
					for(int i = 0; i < memberStr.length; i++) {
						if(user.getUserId() == Long.parseLong(memberStr[i])) {
							if(memberNames.equals("")) {
								memberNames = user.getUserName();
							}else {
								memberNames = memberNames.concat(",").concat(user.getUserName());
							}
						}
					}
				}
				Group group = groupService.getGroupByNameAndAdminId(groupName,Long.parseLong(userId));
				if(group != null) {
					return JsonUtils.writeJson(0,33, "该群已存在");
				}
				String imGroupId = "";
				if(!admin.equals("") && !memberNames.equals("")) {
					imGroupId = ImUtils.addGroup(groupName, memberNames.split(","), admin);
					if(imGroupId.equals("")) {
						return JsonUtils.writeJson(0,27, "环信群创建失败");
					}
				}else {
					return JsonUtils.writeJson(0,26, "群成员不存在");
				}				
				group = new Group();
				group.setGroupName(groupName);
				group.setCreateTime(DateUtil.getNowDate());
				group.setImGroupId(imGroupId);
				group.setAdminId(Long.parseLong(userId));
				groupService.addGroup(group);
				Group group2 = groupService.getGroupByNameAndAdminId(groupName,Long.parseLong(userId));
				GroupDetails gd = new GroupDetails();
				gd.setMemberId(Long.parseLong(userId));
				gd.setIsAdmin(1);
				gd.setJoinTime(DateUtil.getNowTime());
				gd.setGroupId(group2.getGroupId());
				groupDetailsService.addGroupDetails(gd);
				for(int i = 0; i < member.length; i++) {
					GroupDetails gd2 = new GroupDetails();
					gd2.setMemberId(Long.parseLong(member[i]));
					gd2.setIsAdmin(0);
					gd2.setJoinTime(DateUtil.getNowTime());
					gd2.setGroupId(group2.getGroupId());
					groupDetailsService.addGroupDetails(gd2);
				}
				returnStr = JsonUtils.writeJson("创建成功",1);
			}else {
				returnStr = JsonUtils.writeJson(0,7, "存在与你不是互关的成员");
			}
			}else {
				returnStr = JsonUtils.writeJson(0,32, "群组成员不能小于3个");
			}
		}
		return returnStr;
	}
	
	@RequestMapping(value = "/open/delMember", produces = "text/html;charset=UTF-8")
	public String delMember(HttpServletRequest request) {
		String member = request.getParameter("member");
		String userId = request.getParameter("userId");
		String groupId = request.getParameter("groupId");
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		if(!StringUtils.isBlank(userId) && !StringUtils.isBlank(member) && !StringUtils.isBlank(groupId)) {
			Group group = groupService.getGroupById(Long.parseLong(groupId));
			if(group != null) {
			GroupDetails gd = groupDetailsService.getGroupDetailsByGroupIdAndUserId(Long.parseLong(groupId), Long.parseLong(userId));
			System.out.println(gd.toString());
			if(gd != null) {
				if(gd.getIsAdmin() == 0) {
					returnStr = JsonUtils.writeJson(0, 14, "你不是群主");
				}else {
					GroupDetails gd1 = groupDetailsService.getGroupDetailsByGroupIdAndUserId(Long.parseLong(groupId), Long.parseLong(member));
					if(gd1 != null) {
						UserInfo user = userInfoService.selectByPrimaryKey(Long.parseLong(member));
						if(user != null) {
						boolean isSuccess = ImUtils.delSingleMember(group.getImGroupId(), user.getUserName());
						if(isSuccess) {
							groupDetailsService.removeGroupDetails(gd1.getGroupId(),Long.parseLong(member));
						    returnStr = JsonUtils.writeJson("删除成功",1);
						}else {
							returnStr = JsonUtils.writeJson(0, 29, "环信群成员删除失败");
						}
						}else {
							returnStr = JsonUtils.writeJson(0, 26, "成员账号不存在");
						}
					}else {
						returnStr = JsonUtils.writeJson(0, 15, "成员ID不存在");
					}
				}
			}else {
				returnStr = JsonUtils.writeJson(0, 13, "群主ID不存在");
			}
		}else {
			returnStr = JsonUtils.writeJson(0, 25, "群ID不存在");
		}
		}
		return returnStr;
	}
	
	@RequestMapping(value = "/open/addMember")
	public String addMember(HttpServletRequest request) {
		String member = request.getParameter("member");
		String userId = request.getParameter("userId");
		String groupId = request.getParameter("groupId");
		String returnStr = JsonUtils.writeJson(0, RespCode.ERROR_PARAM_EMTY.getCode(), RespCode.ERROR_PARAM_EMTY.getMsg());
		if(!StringUtils.isBlank(userId) && !StringUtils.isBlank(member) && !StringUtils.isBlank(groupId)) {
			Group group = groupService.getGroupById(Long.parseLong(groupId));
			if(group != null) {
			GroupDetails gd = groupDetailsService.getGroupDetailsByGroupIdAndUserId(Long.parseLong(groupId), Long.parseLong(userId));
			if(gd != null) {
					GroupDetails gd1 = groupDetailsService.getGroupDetailsByGroupIdAndUserId(Long.parseLong(groupId), Long.parseLong(member));
					if(gd1 == null) {
						UserInfo user = userInfoService.selectByPrimaryKey(Long.parseLong(member));
						if(user != null) {
						boolean isSuccess = ImUtils.addSingleMember(group.getImGroupId(), user.getUserName());
						if(isSuccess) {
							gd1 = new GroupDetails();
							gd1.setGroupId(Long.parseLong(groupId));
							gd1.setIsAdmin(0);
							gd1.setJoinTime(DateUtil.getNowTime());
							gd1.setMemberId(Long.parseLong(member));
							groupDetailsService.addGroupDetails(gd1);
						    returnStr = JsonUtils.writeJson("添加成功",1);
						}else {
							returnStr = JsonUtils.writeJson(0, 31, "环信群成员添加失败");
						}
						}else {
							returnStr = JsonUtils.writeJson(0, 26, "成员账号不存在");
						}
					}else {
						returnStr = JsonUtils.writeJson(0, 30, "成员已经存在");
					}
			}else {
				returnStr = JsonUtils.writeJson(0, 15, "邀请人不存在");
			}
		}else {
			returnStr = JsonUtils.writeJson(0, 25, "群ID不存在");
		}
		}
		return returnStr;
	}
	
	@RequestMapping(value = "/open/updateGroup", produces = "text/html;charset=UTF-8")
	public String updateGroup(HttpServletRequest request) {
		String groupName = request.getParameter("groupName");
		String userId = request.getParameter("userId");
		String groupId = request.getParameter("groupId");
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		if(!StringUtils.isBlank(userId) && !StringUtils.isBlank(groupId) && !StringUtils.isBlank(groupName)) {
			Group group = groupService.getGroupById(Long.parseLong(groupId));
			if(group != null) {
				GroupDetails gd = groupDetailsService.getGroupDetailsByGroupIdAndUserId(Long.parseLong(groupId), Long.parseLong(userId));
				if(gd != null) {
//					if(gd.getIsAdmin() == 1) {
						boolean isSuccess = ImUtils.updateGroup(groupName, group.getImGroupId());
						if(!isSuccess) {
							return JsonUtils.writeJson(0, 28, "修改失败");
						}
						group.setGroupName(groupName);
						groupService.editGroup(group);
						returnStr = JsonUtils.writeJson("修改成功",1);
//					}else {
//						returnStr = JsonUtils.writeJson(0, 14, "无权限修改");
//					}
				}else {
					returnStr = JsonUtils.writeJson(0, 13, "群ID不存在");
				}
			}else {
				returnStr = JsonUtils.writeJson(0, 25, "群ID不存在");
			}
		}
		return returnStr;
	}

	/**
	 * 获取用户群聊列表
	 * <p>Title: getUserGroup</p>  
	 * <p>Description: </p>  
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/open/getGroupList")
	public String getUserGroup(@RequestParam String userId){
		
		long user_id = Long.parseLong(userId);
		//获取User下的群组
		List<Map<String,Object>> list = groupService.getUserGroup(user_id);
		if(null == list || 0 == list.size()){
			return  JsonUtils.writeJson(1, "获取成功,无群组", null, "object");
		}

		List<Map<String,Object>> resultList = new ArrayList<>();
		for(int i = 0;i < list.size();i++){
			Map<String,Object> result = new HashMap<String,Object>();
			long groupId = (long) list.get(i).get("group_id");
			result.put("groupId",groupId);
			result.put("groupName",list.get(i).get("group_name"));
			result.put("im_group_id",list.get(i).get("im_group_id"));
			result.put("creatTime", list.get(i).get("create_time"));
			result.put("isAdmin",list.get(i).get("is_admin")+"");
			//获取groupId下的用户信息
			List<Map<String,Object>> userList = groupDetailsService.getUserGroupDetails(groupId);
			result.put("user",userList);
			resultList.add(result);
		}
		return  JsonUtils.writeJson(1, "获取成功", resultList, "object");
	}
	/**
	 * 用户退出群聊
	 * <p>Title: exitGroup</p>  
	 * <p>Description: </p>  
	 * @param userId
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value="/open/exitGroup",produces = "text/html;charset=UTF-8")
	public String exitGroup(@RequestParam String userId,@RequestParam String groupId,@RequestParam String isAdmin){
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		if(StringUtils.isBlank(userId)||StringUtils.isBlank(groupId)||StringUtils.isBlank(isAdmin)){
			return returnStr;
		}
		long user_id = Long.parseLong(userId);
		long group_id = Long.parseLong(groupId);
		int is_Admin = Integer.parseInt(isAdmin);


		GroupDetails groupDetails = groupDetailsService.getUserGroupDetailsIsAdmin(user_id,group_id,is_Admin);
		if(null == groupDetails){
			return JsonUtils.writeJson(0, 35, "权限错误");
		}
		Group group = groupService.getGroupById(group_id);
		if(null == group){
			return JsonUtils.writeJson(1, 0, "退出失败");
		}
		UserInfo user = userInfoService.selectByPrimaryKey(user_id);
		if(null == user){
			return JsonUtils.writeJson(1, 0, "退出失败");
		}
		List<Map<String,Object>> userList = groupDetailsService.getUserGroupDetails(group_id);
		if(userList.size() ==  3){
			boolean isDel = ImUtils.deleteGroup(group.getImGroupId());
			if(isDel){
				int i = groupService.deleteGroup(group_id);
				if(i > 0){
					
					return  JsonUtils.writeJson(1, "退出成功", null, "object");
				}
			}
			return JsonUtils.writeJson(1, 0, "退出失败");
			//删除这个群
			
		}else if(userList.size() > 3){
			//删除用户在群中的信息
			if(is_Admin == 1){
				//作为管理员
				String userName =  groupDetailsService.deleteUserAdminFromGroup(user_id,group_id);
				if(null != userName){
					ImUtils.transferAdmin(group.getImGroupId(),userName);
					return  JsonUtils.writeJson(1, "退出成功", null, "object");
				}else {
					return JsonUtils.writeJson(1, 0, "退出失败");
				}
			}else if(is_Admin == 0){
				//不作为管理员
				int i = groupDetailsService.deleteUserFromGroup(user_id,group_id);
				if(i > 0){
					ImUtils.delSingleMember(group.getImGroupId(),user.getUserName());
					return  JsonUtils.writeJson(1, "退出成功", null, "object");
				}else {
					return JsonUtils.writeJson(1, 0, "退出失败");
				}
			}
		}
		
		return JsonUtils.writeJson(0, 0, "未知错误");
	}

}
