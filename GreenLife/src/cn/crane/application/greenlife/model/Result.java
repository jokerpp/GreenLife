package cn.crane.application.greenlife.model;

import java.io.Serializable;


/**
 * 接口返回数据基类
 * 
 * @author Administrator
 * 
 */
public class Result implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String TAG = "";
	public static final String STATE_SUCCESS = "0";
	private String resultNumber;
	private String resultMessage = "";
//	请求页码	pageIndex		Int
//	每页记录	pageSize		Int
//	数据总量	total		Int
//	返回数据量	size		Int
	private Integer pageIndex;
	private Integer pageSize;
	private Integer total;
	private Integer size;

	public Result() {
		TAG = getClass().getSimpleName();
	}
	public String getResultNumber() {
		return resultNumber;
	}

	public void setResultNumber(String resultNumber) {
		this.resultNumber = resultNumber;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public boolean isSuccess() {
		return STATE_SUCCESS.equalsIgnoreCase(resultNumber);
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
	
	

}
