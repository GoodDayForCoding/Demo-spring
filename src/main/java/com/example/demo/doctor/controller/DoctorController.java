package com.example.demo.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.doctor.service.DoctorService;
import com.example.demo.dto.JsonResult;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("")
	public ResponseEntity<JsonResult> index(){
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(null));
	}
	
	@GetMapping("/{no}")
	public ResponseEntity<JsonResult> index(@PathVariable("no") Long no){
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(doctorService.getTreatList(no)));
	}
	
	@GetMapping("/diseasemodal/{name}")
	public ResponseEntity<JsonResult> modalDiease(@PathVariable("name") String name){
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(doctorService.getModalDiease(name)));
	}
	
	@GetMapping("/prescriptionmodal/{name}")
	public ResponseEntity<JsonResult> modalPrescription(@PathVariable("name") String name){
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(doctorService.getModalPrescription(name)));
	}
	
	@GetMapping("/treatmentmodal")
	public ResponseEntity<JsonResult> modalTreatment(@RequestParam(value="name", required=true, defaultValue="") String name){
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(doctorService.getModalTreatment(name)));
	}
}
