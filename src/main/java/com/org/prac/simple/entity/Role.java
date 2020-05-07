package com.org.prac.simple.entity;

import java.io.Serializable;

public class Role implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String name;

    private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    
}