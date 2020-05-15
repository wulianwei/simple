package com.org.prac.simple.entity.resp;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MenuResp {
	
	private String id;
	
	private String title;

    private String type;

    private String url;
    
    private Byte orders;
    
    private List<MenuResp> children = new ArrayList<MenuResp>();

}
