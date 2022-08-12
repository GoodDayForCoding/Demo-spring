package com.example.demo.nurse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.nurse.repository.NurseRepository;
import com.example.demo.vo.AppointmentVo;
import com.example.demo.vo.PatientVo;


@Service
public class NurseService {

	@Autowired
	private NurseRepository nurseRepository;

	
	public Boolean addAppointment(AppointmentVo appointmentVo) {
		
		return nurseRepository.insertAppointment(appointmentVo);
	}

	public Boolean modifyApointment(AppointmentVo appointmentVo) {

		return nurseRepository.updateAppointment(appointmentVo);
	}

	public List<PatientVo> findPatientList() {

		return nurseRepository.selectPatientList();
	}

	public PatientVo findPatient(Long no) {

		return nurseRepository.selectPatient(no);
	}

	public Boolean addPatient(PatientVo patientVo) {
		
		return nurseRepository.insertPatient(patientVo);
	}

	public List<AppointmentVo> findAppointmentList() {

		return nurseRepository.selectAppointmentList();
	}

	public AppointmentVo findAppointment(Long no) {

		return nurseRepository.selectAppointment(no);
	}

	
}
