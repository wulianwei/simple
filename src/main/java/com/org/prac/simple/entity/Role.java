package com.org.prac.simple.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Role implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String name;

    private String description;
    
}