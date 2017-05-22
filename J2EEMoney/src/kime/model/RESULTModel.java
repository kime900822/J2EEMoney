/**
* FileName: RESULTModel.java
* 接口返回类
*
* @author 赵舟浩
    * @Date    2017-05-22
* @version 1.00
*/

package kime.model;

import java.util.List;

public class RESULTModel<T> {
	private String success;
	private String code;
	private String message;
	private String systemTime;
	private List<T> data;
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
