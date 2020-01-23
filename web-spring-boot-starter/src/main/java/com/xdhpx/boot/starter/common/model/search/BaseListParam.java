package com.xdhpx.boot.starter.common.model.search;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: BaseListParam
 * @Description: 通用查询参数封装类
 * @author 郝瑞龙
*/
public class BaseListParam  implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private int pageNo = 1;
    private int pageSize = 10;
    private List<RequestOrderParam> orders;
    private List<RequestSearchParam> searchParamList;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<RequestOrderParam> getOrders() {
		return orders;
	}

	public void setOrders(List<RequestOrderParam> orders) {
		this.orders = orders;
	}

	public List<RequestSearchParam> getSearchParamList() {
		return searchParamList;
	}

	public void setSearchParamList(List<RequestSearchParam> searchParamList) {
		this.searchParamList = searchParamList;
	}


}
