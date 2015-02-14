package com.jiaoyiping.util.annotation;

public class UserServiceImpl {
	
	private UserDaoImpl userDaoImpl;
	public UserDaoImpl getUserDaoImpl() {
		return userDaoImpl;
	}
	@Resource(name="userDaoImpl")
	public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}
	public UserDaoImpl1 getUserDaoImpl1() {
		return userDaoImpl1;
	}
	@Resource(name="userDaoImpl1")
	public void setUserDaoImpl1(UserDaoImpl1 userDaoImpl1) {
		this.userDaoImpl1 = userDaoImpl1;
	}
	
	private UserDaoImpl1 userDaoImpl1;
	
	public void getUser(){
		System.out.println("--getUser()--");
		System.out.println("调用方法之前");
		this.userDaoImpl.show();
		System.out.println("调用方法之后");
	}
	public void getUsesr1(){
		System.out.println("--getUser1()--");
		System.out.println("调用方法之前");
		this.userDaoImpl1.show();
		System.out.println("调用方法之后");
	}

}
