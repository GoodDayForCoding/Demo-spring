package com.example.demo.nurse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.JsonResult;
import com.example.demo.nurse.service.NurseService;
import com.example.demo.vo.AppointmentVo;
import com.example.demo.vo.PatientVo;

@RestController
@RequestMapping("/api/nurse")
public class NurseController {

	@Autowired
	private NurseService nurseService;
	
	@GetMapping("/patient")
	public ResponseEntity<JsonResult> patientList() {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(nurseService.findPatientList()));
	}
	
	@GetMapping("/patient/{no}")
	public ResponseEntity<JsonResult> patientDetails(@PathVariable("no") Long no) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(nurseService.findPatient(no)));
	}
	
	@PostMapping("/patient")
	public ResponseEntity<JsonResult> patientAdd(@RequestBody PatientVo patientVo) {
		nurseService.addPatient(patientVo);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(patientVo));
	}
	
	@GetMapping("/appointment")
	public ResponseEntity<JsonResult> appointmentList() {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(nurseService.findAppointmentList()));
	}
	
	@GetMapping("/appointment/{no}")
	public ResponseEntity<JsonResult> appointmentDetails(@PathVariable("no") Long no) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(nurseService.findAppointment(no)));
	}
	
	@PostMapping("/appointment")
	public ResponseEntity<JsonResult> appointmentAdd(@RequestBody AppointmentVo appointmentVo) {
		nurseService.addAppointment(appointmentVo);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(appointmentVo));
	}
	
	@PutMapping("/appointment/{no}")
	public ResponseEntity<JsonResult> appointmentModify(@PathVariable("no") Long no, @RequestBody AppointmentVo appointmentVo) {
		Boolean result = nurseService.modifyApointment(appointmentVo);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(result ? no : null));
	}
}
