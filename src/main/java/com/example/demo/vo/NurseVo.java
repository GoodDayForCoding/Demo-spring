package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NurseVo {

	private Long appointmentNo;			
	private String appointmentDate; 		
	private String appointmentRemark;
	private int appointmentStatus;
	private String patientName;
	private int patientAge;
	private boolean patientGender;
	private String doctorName;
}
