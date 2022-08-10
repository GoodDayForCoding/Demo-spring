package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EmployeeVo {

	private Long no;
	private String name;
	private String rrn;
	private String email;
	private String password;
	private int gender;
	private String address;
	private String phoneNumber;
	private String role;
	private String licenseNumber;
	private String regDate;
	private Long hospitalNo;
}
