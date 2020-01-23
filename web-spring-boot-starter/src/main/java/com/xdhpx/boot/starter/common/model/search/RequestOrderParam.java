package com.xdhpx.boot.starter.common.model.search;

import java.io.Serializable;

/**
  * @ClassName: RequestOrderParam
  * @Description: 通用查询顺序封装类
  * @author 郝瑞龙
 */
public class RequestOrderParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public RequestOrderParam(){}
	public RequestOrderParam(String sort,String order){
		this.order = order;
		this.sort = sort;
	}
	private String sort;
	private String order = "asc";
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}
