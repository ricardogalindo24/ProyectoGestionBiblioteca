package com.autozone.models;

public class ObjectAccion {
	private Object obj;
	private String action;
	
	public ObjectAccion(Object obj, String action) {
		super();
		this.obj = obj;
		this.action = action;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	
}