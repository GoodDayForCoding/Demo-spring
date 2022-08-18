package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PatientVo {

	private Long no;
	private String name;
	private String rrn;
	private String address;
	private String phoneNumber;
	private int gender;
	private int hasInsurance;
	private String regDate;
	private Long hospitalNo;
}
