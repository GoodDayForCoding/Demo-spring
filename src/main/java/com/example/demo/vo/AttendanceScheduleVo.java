package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AttendanceScheduleVo {

	private Long No;
	private String startDate;
	private String endDate;
	private String status;
	private String remark;
	private int isConfirmed;
	private Long hospitalNo;
	private Long employeeNo;
}
