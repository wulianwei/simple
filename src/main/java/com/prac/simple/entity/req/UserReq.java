package com.prac.simple.entity.req;

import lombok.Data;
import lombok.ToString;

@Data	
@ToString
public class UserReq {
	
	private Integer pageSize;
	
	private Integer pageNum;
	
	private String id;

    private String username;

    private String password;

    private String email;

    private String salt;

    private String enabled;
    
    private String roleIds; //"role1,role2"

}
