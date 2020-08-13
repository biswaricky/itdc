package com.dxc.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Admin 
{
	@Id
	private int adminid;
	private String name;
	private String pass;
	
	public Admin()
	{
		
	}

	public Admin(int adminid, String name, String pass) {
		super();
		this.adminid = adminid;
		this.name = name;
		this.pass = pass;
	}

	public int getAdminid() {
		return adminid;
	}

	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Admin [adminid=" + adminid + ", name=" + name + ", pass=" + pass + "]";
	}
	
}
