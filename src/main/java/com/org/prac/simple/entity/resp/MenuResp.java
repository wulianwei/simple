package com.org.prac.simple.entity.resp;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MenuResp {
	
	private String title;

    private String type;

    private String url;
    
    private List<MenuResp> children;

}
