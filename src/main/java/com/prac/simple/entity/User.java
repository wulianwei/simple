package com.prac.simple.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String username;

    private String password;

    private String email;

    private String salt;

    private String enabled;

    private Date createtime;

    private Date lasttime;
    
    private List<String> roles;

}