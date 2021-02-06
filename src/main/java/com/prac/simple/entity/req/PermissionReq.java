package com.prac.simple.entity.req;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PermissionReq {
	private String id;
	private String roleId;
	private List<String> ids;
}
