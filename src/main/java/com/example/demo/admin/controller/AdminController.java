package com.example.demo.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.admin.service.AdminService;
import com.example.demo.dto.JsonResult;
import com.example.demo.vo.HospitalVo;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("")
	public ResponseEntity<JsonResult> index(
			@RequestParam(value = "kw", required = true, defaultValue = "") String keyword) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(adminService.getHospitalList(keyword)));
	}

	@GetMapping("/{no}")
	public ResponseEntity<JsonResult> index(@PathVariable("no") Long no) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(adminService.getHospitalOne(no)));
	}

	@PostMapping("")
	public ResponseEntity<JsonResult> join(@RequestBody HospitalVo hospitalVo) {
		hospitalVo.setIsActive(1);
		adminService.hopitalJoin(hospitalVo);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(hospitalVo));
	}

	@DeleteMapping("/{no}")
	public ResponseEntity<JsonResult> remove(@PathVariable("no") Long no) {
		Boolean result = adminService.hospitalRemove(no);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(result ? no : null));
	}

	@PutMapping("/{no}")
	public ResponseEntity<JsonResult> update(@PathVariable("no") Long no, @RequestBody HospitalVo hospitalVo) {
		Boolean result = adminService.hospitalUpdate(hospitalVo);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(result ? no : null));
	}

	@GetMapping("/diseasedata")
	public ResponseEntity<JsonResult> diseaseData(
			@RequestParam(value = "kw", required = true, defaultValue = "") String keyword) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(adminService.getDiseaseDataList(keyword)));
	}

	@GetMapping("/medicinedata")
	public ResponseEntity<JsonResult> medicineData(
			@RequestParam(value = "kw", required = true, defaultValue = "") String keyword) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(adminService.getMedicineDataList(keyword)));
	}
}
