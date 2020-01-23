package com.xdhpx.boot.starter.web.entity;

import java.util.Date;

public class User {
	
	/**主键ID**/
    private String id;
    
    /**用户名**/
    private String userName;
    
    /**密码**/
    private String password;
    
    /**性别：1男,2女**/
    private Byte sex;
    
    /**年龄**/
    private int age;
    
    /**创建时间**/
    private Date createTime;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
    public String toString() {
        return "User ["
        		+ "id=" + id 
        		+ ", userName=" + userName  
        		+", password=" + password 
        		+", sex=" + sex 
        		+ ", age=" + age 
        		+ ", createTime=" + createTime
                + "]";
    }
	
}