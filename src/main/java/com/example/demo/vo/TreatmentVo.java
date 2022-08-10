package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TreatmentVo {

	private Long no;
	private String medicineNumber;
	private String medicineName;
	private String usage;
	private int dose;
	private Long diagnosisNo;
}
