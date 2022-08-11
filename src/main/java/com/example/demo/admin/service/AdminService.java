package com.example.demo.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.admin.repository.AdminRepository;
import com.example.demo.vo.EmployeeVo;
import com.example.demo.vo.HospitalVo;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public List<HospitalVo> getHospitalList() {
		return adminRepository.findAll();
	}

	@Transactional
	public Boolean hopitalJoin(HospitalVo hospitalVo) {
		adminRepository.insertHospital(hospitalVo);

		EmployeeVo employeeVo = new EmployeeVo();
		employeeVo.setName("관리자");
		employeeVo.setEmail(hospitalVo.getName() + "@medireco.com");
		employeeVo.setPassword("admin");
		employeeVo.setAddress(hospitalVo.getAddress());
		employeeVo.setPhoneNumber(hospitalVo.getPhoneNumber());
		employeeVo.setHospitalNo(hospitalVo.getNo());

		return adminRepository.insertHospitalAdmin(employeeVo);
	}
	
	@Transactional
	public Boolean hospitalRemove(Long no) {
		adminRepository.updateIsActive(no);
		return adminRepository.deleteHospitalAdmin(no);
	}
}
