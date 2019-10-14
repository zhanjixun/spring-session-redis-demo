package com.zhanjixun.web.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserDTO implements Serializable {

	private Integer id;
	private String name;
	private String userName;
	private String password;

}