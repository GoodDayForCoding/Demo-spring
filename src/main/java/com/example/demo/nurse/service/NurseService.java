package com.example.demo.nurse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.nurse.repository.NurseRepository;
import com.example.demo.vo.PatientVo;


@Service
public class NurseService {

	@Autowired
	private NurseRepository nurseRepository;

	public List<PatientVo> getHospitalList() {
		return nurseRepository.findAllPatients();
	}

	public PatientVo getHospitalOne(Long no) {
		return nurseRepository.findPatientByNo(no);
	}

	@Transactional
	public Boolean patientJoin(PatientVo patientVo) {
		nurseRepository.insertPatient(patientVo);

		
		return nurseRepository.insertPatient(patientVo);
	}


	@Transactional
	public Boolean patientUpdate(PatientVo patientVo) {
		nurseRepository.updatePatient(patientVo);

		return nurseRepository.updatePatient(patientVo);
	}
}
