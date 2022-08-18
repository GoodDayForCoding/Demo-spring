package com.example.demo.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.service.CommonService;
import com.example.demo.dto.JsonResult;
import com.example.demo.vo.AttendanceScheduleVo;

@RestController
@RequestMapping("/api/common")
public class CommonController {

	@Autowired
	private CommonService commonService;
	
	@GetMapping("/attendanceSchedule")
	public ResponseEntity<JsonResult> atttendanceScheduleList() {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(commonService.findAttendanceScheduleList()));
	}
	
	@GetMapping("/attendanceScheduleByNo/{no}")
	public ResponseEntity<JsonResult> attendanceScheduleDetails(@PathVariable("no") Long no) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(commonService.findAttendanceScheduleByNo(no)));
	}
	
	@PostMapping("attendanceSchedule")
	public ResponseEntity<JsonResult> attendanceScheduleAdd(@RequestBody AttendanceScheduleVo attendanceScheduleVo){
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(attendanceScheduleVo));
	}
	
	@PutMapping("attendanceSchedule/{no}")
	public ResponseEntity<JsonResult> attendanceScheduleUpdate(@PathVariable("no") Long no) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(commonService.updateAttendanceSchedule(no)));
	}
	
	@DeleteMapping("attendanceSchedule/{no}")
	public ResponseEntity<JsonResult> attendanceScheduleDelete(@PathVariable("no") Long no) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(commonService.deleteAttendanceSchedule(no)));
	}
	
}
