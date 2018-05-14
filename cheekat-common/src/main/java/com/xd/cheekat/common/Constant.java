package com.xd.cheekat.common;

public class Constant {
	/**
	 * 该类不能被实例化
	 */
	private Constant() {
		throw new IllegalAccessError();
	}
	
	/**
	 * 原始编码
	 */
	public static final String ENCOD = "ISO-8859-1";
	
	/**
	 * 解析编码：UTF-8
	 */
	public static final String ENCODING = "UTF-8";
	
	/**
	 * 解析编码：GB2312
	 */
	public static final String ENCODING_2312 = "GB2312";
	
	/**
	 * 当前登录用户
	 */
	public static final String CURRENT_USER = "currentUser";
	
	/**
	 * 页面空格
	 */
	public static final String NBSP = "&nbsp;";
	
	/**
	 * 时间格式 : 年-月-日
	 */
	public static final String FORMAT1 = "yyyy-MM-dd";
	
	/**
	 * 时间格式 : 年-月-日
	 */
	public static final String FORMAT1_ = "yyyyMMdd";

	/**
	 * 时间格式 : 年-月-日 时:分      24小时制  HH小写为12小时制
	 */
	public static final String FORMAT2 = "yyyy-MM-dd HH:mm";

	/**
	 * 时间格式 : 年-月-日 时:分:秒   24小时制
	 */
    public static final String FORMAT3 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Double的默认值
	 */
	public static final String DOUBLE_DEFAULT = "0.0";
	
	/**
	 * Integer默认值
	 */
	public static final String INTEGER_DEFAULT = "0";
	
	/**
	 * 分隔符  “-”
	 */
	public static final String GE_ZI_FU = "-";
	
	/**
	 * 分隔符  “-”
	 */
	public static final String GE_ZI_FU_XIA = "_";
	
	/**
	 * 路径分隔负 “/”
	 */
	public static final String XIE_GAN = "/";
	
	/**
	 * 路径分隔负 “\\”
	 */
	public static final String XIE_GE = "\\";
	
	/**
	 * String的默认值空  “”
	 */
	public static final String KONG = "";
	
	/**
	 * 成功标志
	 */
	public static final String SUCCESS = "success";
	
	/**
	 * 出错标志
	 */
	public static final String FAILURE = "failure";

	/**
	 * 出错标志
	 */
	public static final String ERROR = "error";
	
	/**
	 * 404页面
	 */
	public static final String PAGE_404 = "/pages/error/404/html";
	
	public static final String SYS_NAME = "CCIC-SY系统：";
	
	public static final String COMPRESS_ZIP = "zip";
	
	public static final String IMAGE_JPG = "jpg";
	// 二维码尺寸
	public static final int QRCODE_SIZE = 300;
	// LOGO宽度
	public static final int QRCODE_WIDTH = 60;
	// LOGO高度
	public static final int QRCODE_HEIGHT = 60;
	
	/**
     * 微信支付相关信息
     */
     public static final  String APPID = "wx592c35b4382852c7";//服务号的应用号
    // public static final  String MCH_ID = "1503248441";//商户号
     public static final  String MCH_ID = "1503248441";//商户号
    // public static final  String API_KEY = "";//API密钥
//     public static final  String API_KEY = "966ac0178cd1dc45a8b9e597794c5ff0";//API密钥
     public static final  String API_KEY = "emIs5XG6HlnDt6JvF7zET1PKs3NyAg03";//API密钥
     public static final  String SIGN_TYPE = "MD5";//签名加密方式

     //微信统一下单连接
     public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
     
 	public static final String HEAD_IMG_PATH="/upload/head/image/";
 	public static final String USER_IMG_PATH="/upload/user/image/";
     
 	public static final int LOGIN_TYPE_PHONE = 1;//手机登录
	public static final int LOGIN_TYPE_WECHAT = 2;//微信登录
	public static final int USER_INVISIBLE = 1;//用户隐身
	public static final int USER_VISIBLE = 0;//用户在线

}
