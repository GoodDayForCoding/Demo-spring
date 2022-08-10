package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CalendarVo {

	private String date;
	private String day;
	private int isHoliday;
}
