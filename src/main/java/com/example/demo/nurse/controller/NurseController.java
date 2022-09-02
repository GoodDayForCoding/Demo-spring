package com.example.demo.nurse.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	
	@GetMapping("/patientByNo/{no}")
	public ResponseEntity<JsonResult> patientDetails(@PathVariable("no") Long no) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(nurseService.findPatientByNo(no)));
	}
	
	@GetMapping("/patientByHospitalNo/{no}")
	public ResponseEntity<JsonResult> patientListByHospitalNo(@PathVariable("no") Long no) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(nurseService.findPatientListByHospitalNo(no)));
	}
	
	@GetMapping("/patientByName/{name}")
	public ResponseEntity<JsonResult> patientDetails(@PathVariable("name") String name) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(nurseService.findPatientByName(name)));
	}
	
	@PostMapping("/patient")
	public ResponseEntity<JsonResult> firstPatientAdd(@RequestBody Map<String, Object> param) {
		Boolean result = nurseService.addPatient(param);
		
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(result));
	}
	
	@GetMapping("/appointment")
	public ResponseEntity<JsonResult> appointmentList() {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(nurseService.findAppointmentList()));
	}
	
	@GetMapping("/appointmentByDate/{date}")
	public ResponseEntity<JsonResult> appointmentDetailsByDate(@PathVariable("date") String date) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(nurseService.findAppointmentByDate(date)));
	}
	
	@GetMapping("/appointmentByPatientNo/{no}")
	public ResponseEntity<JsonResult> appointmentDetails(@PathVariable("no") Long no) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(nurseService.findAppointmentByPatientNo(no)));
	}
	
	@GetMapping("/appointmentByPatientName/{patientName}")
	public ResponseEntity<JsonResult> appointmentDetails(@PathVariable("patientName") String patientName) {
	
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(nurseService.findAppointmentByPatientName(patientName)));
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
	
	@PatchMapping("/{no}")
	public ResponseEntity<JsonResult> appointmentRemove(@PathVariable("no") Long no) {
		Boolean result = nurseService.removeAppointment(no);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(result ? no : null));
	}
	
	@GetMapping("/payment/{no}")
	public ResponseEntity<JsonResult> paymentDetail(@PathVariable("no") Long no) {
	
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(nurseService.findPaymentByPatientNo(no)));
	}
}
