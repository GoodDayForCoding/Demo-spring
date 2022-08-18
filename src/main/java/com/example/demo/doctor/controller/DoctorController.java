package com.example.demo.doctor.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.doctor.service.DoctorService;
import com.example.demo.dto.JsonResult;
import com.example.demo.vo.DoctorVo;

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
	
	@PostMapping("")
	public ResponseEntity<JsonResult> success(@RequestBody DoctorVo doctorVo){
		doctorService.doctorSuccess(doctorVo);		
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(doctorVo));		
	}
	
	
	
	@GetMapping("/diseasemodal")
	public ResponseEntity<JsonResult> modalDiease(@RequestParam(value="name", required=true, defaultValue="") String name,
												  @RequestParam(value="no", required = true, defaultValue = "1") int page, Model model){
		
		Map<String, Object> map = doctorService.getModalDiease(name, page);
		model.addAttribute("map", map);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(model));
	}
	
	@GetMapping("/prescriptionmodal")
	public ResponseEntity<JsonResult> modalPrescription(@RequestParam(value="name", required=true, defaultValue="") String name,
			  											@RequestParam(value="page", required = true, defaultValue = "0") int page, Model model){
		Map<String, Object> map = doctorService.getModalPrescription(name, page);
		model.addAttribute("map", map);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(model));
	}
	
	@GetMapping("/treatmentmodal")
	public ResponseEntity<JsonResult> modalTreatment(@RequestParam(value="name", required=true, defaultValue="") String name,
			  										 @RequestParam(value="page", required = true, defaultValue = "0") int page, Model model){
		Map<String, Object> map = doctorService.getModalTreatment(name, page);
		model.addAttribute("map", map);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(model));
	}
}
