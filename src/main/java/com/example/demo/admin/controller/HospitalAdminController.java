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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.admin.service.HospitalAdminService;
import com.example.demo.dto.JsonResult;
import com.example.demo.vo.EmployeeVo;

@RestController
@RequestMapping("/api/hospitaladmin")
public class HospitalAdminController {

	@Autowired
	private HospitalAdminService hospitalAdminService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("")
	public ResponseEntity<JsonResult> index(
			@RequestParam(value = "kw", required = true, defaultValue = "") String keyword,
			@RequestParam(value = "hn", required = true, defaultValue = "") Long hospitalNo) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(JsonResult.success(hospitalAdminService.getEmployeeList(keyword, hospitalNo)));
	}

	@GetMapping("/{no}")
	public ResponseEntity<JsonResult> index(@PathVariable("no") Long no) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(hospitalAdminService.getEmployeeOne(no)));
	}

	@PostMapping("")
	public ResponseEntity<JsonResult> join(@RequestBody EmployeeVo employeeVo) {
		employeeVo.setPassword(bCryptPasswordEncoder.encode(employeeVo.getPassword()));
		hospitalAdminService.employeeJoin(employeeVo);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(employeeVo));
	}

	@DeleteMapping("/{no}")
	public ResponseEntity<JsonResult> remove(@PathVariable("no") Long no) {
		Boolean result = hospitalAdminService.employeeRemove(no);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(result ? no : null));
	}

	@PutMapping("/{no}")
	public ResponseEntity<JsonResult> update(@PathVariable("no") Long no, @RequestBody EmployeeVo employeeVo) {
		employeeVo.setPassword(bCryptPasswordEncoder.encode(employeeVo.getPassword()));
		Boolean result = hospitalAdminService.employeeUpdate(employeeVo);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(result ? no : null));
	}
}
