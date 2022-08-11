package com.example.demo.doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.doctor.repository.DoctorRepository;
import com.example.demo.vo.DiseaseDataVo;
import com.example.demo.vo.DoctorVo;
import com.example.demo.vo.MedicineDataVo;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	public List<DoctorVo> getTreatList(Long no) {
		return doctorRepository.getTreatList(no);
	}

	public List<DiseaseDataVo> getModalDiease(String name) {
		return doctorRepository.getModalDiease(name);
	}

	public List<MedicineDataVo> getModalPrescription(String name) {
		return doctorRepository.getModalPrescription(name);
	}

	public List<MedicineDataVo> getModalTreatment(String name) {
		return doctorRepository.getModalTreatment(name);
	}
	
}
