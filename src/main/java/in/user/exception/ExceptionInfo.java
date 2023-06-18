package in.user.exception;

import java.time.LocalDateTime;
public class ExceptionInfo {
	private String code;
	private String msg;
	private LocalDateTime date;
	
	public ExceptionInfo() {
		
	}
	public ExceptionInfo(String code, String msg, LocalDateTime date) {
		this.code = code;
		this.msg = msg;
		this.date = date;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "ExceptionInfo [code=" + code + ", msg=" + msg + ", date=" + date + "]";
	}
	
	

}

