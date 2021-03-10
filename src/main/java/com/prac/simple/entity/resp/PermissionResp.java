package com.prac.simple.entity.resp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PermissionResp {

	private String id;

    private String pid;

    private String title;

    private String type;

    private String url;

    private String description;

    private Integer orders;
    	
	private String mainMenu;
	
	private String subMenu;
	
	private String mainMenuName;
	
	private String subMenuName;
	
}
