package com.prac.simple.entity.resp;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginResp {
	
	private String id;
	
	private String username;
	
	private List<String> roles;
	
	private String token;

}
