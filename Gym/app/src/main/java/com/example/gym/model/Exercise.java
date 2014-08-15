package com.example.gym.model;


import java.util.UUID;

public class Exercise {
	
	private UUID mId;
	private String mName;
	private String mExlink;
	private String mYlink;
	

	
	public String getExlink() {
		return mExlink;
	}
	public void setExlink(String exlink) {
		mExlink = exlink;
	}
	public String getYlink() {
		return mYlink;
	}
	public void setYlink(String ylink) {
		mYlink = ylink;
	}

	
	
	public Exercise(){
		mId = UUID.randomUUID();
	}
	public UUID getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

}
