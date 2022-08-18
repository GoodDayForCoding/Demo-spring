package com.example.demo.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.admin.repository.AdminRepository;
import com.example.demo.vo.DiseaseDataVo;
import com.example.demo.vo.EmployeeVo;
import com.example.demo.vo.HospitalVo;
import com.example.demo.vo.MedicineDataVo;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public List<HospitalVo> getHospitalList(String keyword) {
		return adminRepository.findAll(keyword);
	}

	public HospitalVo getHospitalOne(Long no) {
		return adminRepository.findByNo(no);
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

	@Transactional
	public Boolean hospitalUpdate(HospitalVo hospitalVo) {
		adminRepository.updateHospital(hospitalVo);

		EmployeeVo employeeVo = new EmployeeVo();
		employeeVo.setEmail(hospitalVo.getName() + "@medireco.com");
		employeeVo.setAddress(hospitalVo.getAddress());
		employeeVo.setPhoneNumber(hospitalVo.getPhoneNumber());
		employeeVo.setHospitalNo(hospitalVo.getNo());

		return adminRepository.updateHospitalAdmin(employeeVo);
	}

	public List<DiseaseDataVo> getDiseaseDataList(String keyword) {
		return adminRepository.findAllDiseaseData(keyword);
	}

	public List<MedicineDataVo> getMedicineDataList(String keyword) {
		return adminRepository.findAllMedicineData(keyword);
	}
}
