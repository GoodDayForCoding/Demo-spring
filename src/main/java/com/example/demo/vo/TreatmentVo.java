package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TreatmentVo {

	private Long no;
	private String medicineName;
	private String treat;
	private String dose;
	private Long diagnosisNo;
}
