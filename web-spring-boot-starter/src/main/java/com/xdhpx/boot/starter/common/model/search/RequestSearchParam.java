package com.xdhpx.boot.starter.common.model.search;

import java.io.Serializable;

/**
 * @ClassName: RequestSearchParam
 * @Description: 通用查询条件封装类
 * @author 郝瑞龙
*/
public class RequestSearchParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final String EQ="eq";
	public static final String GT="gt";
	public static final String GE="ge";
	public static final String LT="lt";
	public static final String LE="le";
	public static final String LIKE="like";
	public static final String NE="ne";
	public static final String ISNULL="isNull";
	public static final String ISNOTNULL="isNotNull";
	private String key;
	private Object value;
	private String operator = "eq";
	
	public RequestSearchParam() { }
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getOperator() {
		if(this.operator.equals(EQ))
			return EQ;
		else if(this.operator.equals(GT)){
			return GT;
		}else if(this.operator.equals(GE)){
			return GE;
		}else if(this.operator.equals(LT)){
			return LT;
		}else if(this.operator.equals(LE)){
			return LE;
		}else if(this.operator.equals(LIKE)){
			return LIKE;
		}else if(this.operator.equals(NE)){
			return NE;
		}else if(this.operator.equals(ISNULL)){
			return ISNULL;
		}else if(this.operator.equals(ISNOTNULL)){
			return ISNOTNULL;
		}else return EQ;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public RequestSearchParam(String key, Object value, String operator) {
		super();
		this.key = key;
		this.value = value;
		this.operator = operator;
	}
	
}
