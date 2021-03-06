package com.prac.simple.entity.req;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PermissionReq {	
	private String id;

    private String pid;

    private String title;

    private String type;

    private String url;

    private String description;

    private Integer orders;
    
	private String roleId;
	
	private String mainMenu;
	
	private String subMenu;
	private List<String> ids;
}
