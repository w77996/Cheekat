package RespCode;

public enum RespCode {
	STATU_OK(1, "请求成功！"),
	STATU_FAIL(0, "请求失败！"),
	
	ERROR_PARAM_EMTY(0,"参数为空"),
	ERROR_SYS_BUSY(1,"系统繁忙"),
	ERROR_USER_ALREAD_EXIST(2,"该用户已经存在"),
	ERROR_PASSWORD_ERRORT(3,"密码错误"),
	ERROR_USER_NOT_EXIST(4,"用户不存在"),
	;
	


	RespCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private int code;
	private String msg;

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
