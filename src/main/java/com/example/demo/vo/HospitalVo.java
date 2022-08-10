package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class HospitalVo {

	private Long no;
	private String name;
	private String address;
	private String phoneNumber;
	private String url;
	private int isActive;
}
