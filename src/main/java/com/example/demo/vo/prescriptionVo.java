package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class prescriptionVo {

	private Long no;
	private String medicineNumber;
	private String medicineName;
	private int dose;
	private int dosingPrequency;
	private int dosingDays;
	private String usage;
	private Long diagnosisNo;
}
