package com.xd.cheekat.controller;

import java.io.File;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import RespCode.RespCode;

import com.xd.cheekat.common.Constant;
import com.xd.cheekat.pojo.UserInfo;
import com.xd.cheekat.service.UserInfoService;
import com.xd.cheekat.util.DateUtil;
import com.xd.cheekat.util.FileUtil;
import com.xd.cheekat.util.ImUtils;
import com.xd.cheekat.util.JsonUtils;
import com.xd.cheekat.util.PropertiesUtil;

@RestController
public class UserController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/getUserId")
	public String getUserById(@RequestParam long userId){
		UserInfo userInfo =userInfoService.selectByPrimaryKey(userId);
		System.out.println(userInfo.toString());
		return JsonUtils.writeJson(1, RespCode.STATU_OK.getMsg(), userInfo, "object");
	}
	
	@RequestMapping(value = "/setInvisible")
	public String setInvisible(@RequestParam long userId,@RequestParam int invisible){
		UserInfo userInfo = userInfoService.selectByPrimaryKey(userId);
		
		if(null == userInfo){
			return JsonUtils.writeJson(0, RespCode.ERROR_USER_NOT_EXIST.getCode(),RespCode.ERROR_USER_NOT_EXIST.getMsg());
		}
		
		int i = userInfoService.updateByPrimaryKeySelective(userInfo);
		
		if(0 < i){
			JsonUtils.writeJson("设置成功", 1);
		}
		return JsonUtils.writeJson(0, RespCode.ERROR_SYS_BUSY.getCode(),RespCode.ERROR_SYS_BUSY.getMsg());
	}
	
	/**
     * 设置user信息
     * <p>Title: editUser</p>
     * <p>Description: </p>
     *
     * @param userId
     * @param headImg
     * @param userName
     * @param birth
     * @param height
     * @param sex
     * @return
     */
    @RequestMapping(value = "/open/editUser", method = RequestMethod.POST)
    @ResponseBody
    public String editUser(@RequestParam String userId, @RequestParam String headImg, @RequestParam String userName, @RequestParam String birth, @RequestParam String height, @RequestParam String sex, HttpServletRequest request) {
        String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
      
        long user_id = Long.parseLong(userId);
        UserInfo user = userInfoService.selectByPrimaryKey(user_id);
        if (null == user) {
            return JsonUtils.writeJson(0, 4, "用户不存在");
        }
        Properties properties =  new PropertiesUtil().getProperites("config.properties");
        String context_path = properties.getProperty("context_path", "");
        //获取绝对路径
       // String httpPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";

        String filePathName= request.getSession().getServletContext().getRealPath("/")+"/"+Constant.HEAD_IMG_PATH;;//存放路径
        System.out.println(filePathName);
        File file = new File(filePathName);
        if (!file.exists()) {
            file.mkdirs();
        }
        // System.out.println(filePathName);
        String fileName = System.currentTimeMillis() + String.valueOf((int) ((Math.random() * 9 + 1) * 100000)) + ".jpg";
        boolean isSuccess = FileUtil.CreateImgBase64(headImg, filePathName + fileName);
        if (!isSuccess) {
            return JsonUtils.writeJson(0, 24, "图片上传失败");
        }
        user.setBirth(birth);
        user.setHeadImg(context_path+Constant.HEAD_IMG_PATH  + fileName);
        user.setHeight(Integer.parseInt(height));
        user.setSex(Integer.parseInt(sex));
        user.setUserId(user_id);
        user.setNickName(userName);
        int i = userInfoService.editUser(user);
        UserInfo userResult = userInfoService.selectByPrimaryKey(Long.parseLong(userId));
        if (0 < i) {
            return JsonUtils.writeJson(1, "请求成功", userResult, "object");
        }
        return JsonUtils.writeJson(0, 0, "修改失败");
    }
    
    /**
     * 新添加用户信息
     *
     * @param headImg
     * @param userName
     * @param birth
     * @param height
     * @param sex
     * @param type
     * @param phone
     * @param openId
     * @param request
     * @return
     */
    @RequestMapping(value = "/open/registUser", method = RequestMethod.POST)
    @ResponseBody
    public String registUser(@RequestParam String headImg, @RequestParam String userName, @RequestParam String birth, @RequestParam String height, @RequestParam String sex, @RequestParam String type, @RequestParam(required = false) String phone, @RequestParam(required = false) String openId, HttpServletRequest request) {
        String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
        if (StringUtils.isBlank(headImg) || StringUtils.isBlank(userName) || StringUtils.isBlank(birth) || StringUtils.isBlank(height) || StringUtils.isBlank(sex)) {
            return returnStr;
        }


        if(Constant.LOGIN_TYPE_PHONE == Integer.parseInt(type)){
            //通过手机查找用户是否存在
            if(StringUtils.isBlank(phone)){
                return JsonUtils.writeJson(0, 0, "参数为空");
            }
            UserInfo user = userInfoService.getUserByUserName(phone);
            if(null != user){
                return JsonUtils.writeJson(1, 4,"用户已存在");
            }
        }else if(Constant.LOGIN_TYPE_WECHAT == Integer.parseInt(type)){
            //判断参数
            if(StringUtils.isBlank(openId)){
                return JsonUtils.writeJson(0, 0, "参数为空");
            }
            UserInfo user = userInfoService.getUserByUserName(openId);
            if(null != user){
                return JsonUtils.writeJson(0,4,"用户已存在");
            }
            //查询user是否存在
            UserInfo wechatUser = userInfoService.getUserByOpenId(openId);
            if(null != wechatUser){
                return JsonUtils.writeJson(0,40,"微信已被绑定");
            }
        }

        //获取绝对路径
       // String httpPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";

        Properties properties =  new PropertiesUtil().getProperites("config.properties");
        String context_path = properties.getProperty("context_path", "");

        String filePathName= request.getSession().getServletContext().getRealPath("/")+"/"+Constant.HEAD_IMG_PATH;;//存放路径
        System.out.println(filePathName);
        File file = new File(filePathName);
        if (!file.exists()) {
            file.mkdirs();
        }
        System.out.println("filePathName"+filePathName);
        String fileName = System.currentTimeMillis() + String.valueOf((int) ((Math.random() * 9 + 1) * 100000)) + ".jpg";
        boolean isSuccess = FileUtil.CreateImgBase64(headImg, filePathName + fileName);
        if (!isSuccess) {
            return JsonUtils.writeJson(0, 24, "图片上传失败");
        }
        int login_type = Integer.parseInt(type);
        boolean addImSuccess = false;
        if (Constant.LOGIN_TYPE_PHONE == login_type) {
            addImSuccess = ImUtils.authRegister(phone, "123456", phone);
        } else if (Constant.LOGIN_TYPE_WECHAT == login_type) {
            addImSuccess = ImUtils.authRegister(openId, "123456", openId);
        } else {
            return JsonUtils.writeJson(0, 0, "参数为空");
        }
        if (!addImSuccess) {
            return JsonUtils.writeJson(0, 34, "注册失败");
        }
        UserInfo user = new UserInfo();
        user.setBirth(birth);
        user.setHeadImg(context_path+Constant.HEAD_IMG_PATH + fileName);
        user.setHeight(Integer.parseInt(height));
        user.setSex(Integer.parseInt(sex));
        user.setNickName(userName);
        user.setCreateTime(DateUtil.getNowTime());
        UserInfo userResult = null;
        if (Constant.LOGIN_TYPE_PHONE == login_type) {
            user.setUserName(phone);

            userInfoService.addNewUser(user);
            userResult = userInfoService.getUserByUserName(phone);
        } else if (Constant.LOGIN_TYPE_WECHAT == login_type) {
            user.setUserName(openId);
            userInfoService.addNewUser(user);
            userResult = userInfoService.getUserByUserName(openId);
        } else {
            return JsonUtils.writeJson(0, 0, "参数为空");
        }
        walletService.addNewUser(userResult.getUserId());
        return JsonUtils.writeJson(1, "请求成功", userResult, "object");

    }

}
