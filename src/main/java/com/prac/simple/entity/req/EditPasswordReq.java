package com.prac.simple.entity.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EditPasswordReq {
	
	private String oldPassword;
	
	private String newPassword;

}
