package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AppointmentVo {

	private Long no;
	private int status;
	private String date;
	private String remarks;
	private Long employeeNo;
	private Long patientNo;
	private Long hospitalNo;
}
