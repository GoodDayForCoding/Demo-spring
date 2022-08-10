package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DiagnosisVo {

	private Long no;
	private String diseaseNumber;
	private String diseaseName;
	private String opinion;
	private Long patientNo;
	private Long appointmentNo;
}
