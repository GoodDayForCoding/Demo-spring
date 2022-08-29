package com.example.demo.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.repository.HospitalAdminRepository;
import com.example.demo.vo.EmployeeVo;

@Service
public class HospitalAdminService {

	@Autowired
	private HospitalAdminRepository hospitalAdminRepository;

	public List<EmployeeVo> getEmployeeList(String keyword, Long hospitalNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("hospitalNo", hospitalNo);
		return hospitalAdminRepository.findAll(map);
	}

	public EmployeeVo getEmployeeOne(Long no) {
		return hospitalAdminRepository.findByNo(no);
	}

	public Boolean employeeJoin(EmployeeVo employeeVo) {
		return hospitalAdminRepository.insertEmployee(employeeVo);
	}

	public Boolean employeeRemove(Long no) {
		return hospitalAdminRepository.deleteEmployeeAdmin(no);
	}

	public Boolean employeeUpdate(EmployeeVo employeeVo) {
		return hospitalAdminRepository.updateEmployeeAdmin(employeeVo);
	}
}
