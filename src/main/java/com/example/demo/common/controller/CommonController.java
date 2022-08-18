package com.example.demo.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.service.CommonService;
import com.example.demo.dto.JsonResult;

@RestController
@RequestMapping("/api/common")
public class CommonController {

	@Autowired
	private CommonService commonService;
	
	@GetMapping("/attendanceSchedule")
	public ResponseEntity<JsonResult> atttendanceScheduleList() {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(commonService.findAttendanceScheduleList()));
	}
}
