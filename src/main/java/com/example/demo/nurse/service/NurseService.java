package com.example.demo.nurse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.nurse.repository.NurseRepository;
import com.example.demo.vo.AppointmentVo;
import com.example.demo.vo.NurseVo;
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
	
	@Transactional
	public Boolean addPatient(Map<String, Object> param) {
		PatientVo patientVo = new PatientVo();
		AppointmentVo appointmentVo = new AppointmentVo();
		System.out.println(param);		
		String name = (String) param.get("name");
		String rrn = (String) param.get("rrn");
		String address = (String) param.get("address");
		String phoneNumber = (String) param.get("phoneNumber");
		int gender =  Integer.parseInt(String.valueOf(param.get("gender")));
		int hasInsurance = Integer.parseInt(String.valueOf(param.get("hasInsurance")));
		String regDate = (String) param.get("regDate");
		//Long hospitalNo = (long) param.get("hospitalNo");
		//Long employeeNo = (long) param.get("employeeNo");
		//int status = (int) param.get("status");
		 
		Long hospitalNo = Long.valueOf((String) param.get("hospitalNo"));
		Long employeeNo = Long.valueOf((String) param.get("employeeNo"));
		String remarks = (String) param.get("remarks");
		int status = Integer.valueOf((String) param.get("status"));

		
		patientVo.setName(name);
		patientVo.setRrn(rrn);
		patientVo.setAddress(address);
		patientVo.setPhoneNumber(phoneNumber);
		patientVo.setGender((int)1);
		patientVo.setHasInsurance((int)1);
		patientVo.setRegDate(regDate);
		patientVo.setHospitalNo(hospitalNo);

		
		Long patientNo = nurseRepository.insertPatient(patientVo);
		

		appointmentVo.setStatus(status);
		appointmentVo.setDate(regDate);
		appointmentVo.setRemarks(remarks);
		appointmentVo.setPatientNo(patientNo);
		appointmentVo.setEmployeeNo(employeeNo);
		appointmentVo.setHospitalNo(hospitalNo);
		nurseRepository.insertAppointment(appointmentVo);
		
		return true;
	}

	public List<AppointmentVo> findAppointmentList() {

		return nurseRepository.selectAppointmentList();
	}

	public NurseVo findAppointmentByPatientNo(Long no) {

		return nurseRepository.selectAppointmentByPatientNo(no);
	}

	public List<NurseVo> findAppointmentByPatientName(String patientName) {
		
		return nurseRepository.selectAppointmentByPatientName(patientName);
	}

	public PatientVo findPatientByNo(Long no) {
		return nurseRepository.selectPatientByNo(no);
	}

	public List<PatientVo> findPatientByName(String name) {
		return nurseRepository.selectPatientByName(name);
	}

	
}
