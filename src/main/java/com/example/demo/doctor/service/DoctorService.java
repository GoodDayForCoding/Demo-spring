package com.example.demo.doctor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.doctor.repository.DoctorRepository;
import com.example.demo.vo.AppointmentVo;
import com.example.demo.vo.DiagnosisVo;
import com.example.demo.vo.DiseaseDataVo;
import com.example.demo.vo.DoctorVo;
import com.example.demo.vo.MedicineDataVo;

@Service
public class DoctorService {
	private int PAGE_SIZE = 10;

	@Autowired
	private DoctorRepository doctorRepository;
	

	public List<DoctorVo> getTreatList(Long no) {
		return doctorRepository.getTreatList(no);
	}
	
	public Object getTreatCount(Long no) {
		return doctorRepository.getTreatCount(no);
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Boolean doctorSuccess(HashMap<String, Object> param) {
		Boolean status = false;
				
		Map<String, Object> diagnosisMap = (Map<String, Object>)param.get("diagnosis");
		// 진단서입력
		// 진단서 번호를 받아와야해서 vo 사용
		DiagnosisVo diagnosisVo = new DiagnosisVo();		
		diagnosisVo.setAppointmentNo(Long.parseLong(diagnosisMap.get("appointmentNo").toString()));
		diagnosisVo.setOpinion(diagnosisMap.get("opinion").toString());
		diagnosisVo.setPatientNo(Long.parseLong(diagnosisMap.get("patientNo").toString())); 
		doctorRepository.doctorSuccess(diagnosisVo); //diagnosis;
		
		// System.out.println(param);
		Long diagnosisNo = diagnosisVo.getNo();
				
		
		List<Object> diseasesList = (List<Object>)param.get("disease");
		if(diseasesList != null) {
			doctorRepository.insertDisease(diseasesList, diagnosisNo);
		}	
		
		List<Object> treatmentList = (List<Object>)param.get("treatment");
		if(treatmentList != null) {
			doctorRepository.insertTreatment(treatmentList, diagnosisNo);			
		}
		
		List<Object> prescriptionList = (List<Object>)param.get("prescription");		
		if(prescriptionList != null) {
			doctorRepository.insertPrescription(prescriptionList, diagnosisNo);			
		}

		// 예약 update
		AppointmentVo appointmentVo = new AppointmentVo();
		appointmentVo.setNo(Long.parseLong(diagnosisMap.get("appointmentNo").toString()));
		appointmentVo.setStatus(4);
		status =doctorRepository.updateAppointment(appointmentVo);
		
		return status;
	}
	
	 
	
	
	public Map<String, Object> getModalPrescription(String name, int currentPage) {
		
		int totalCount = doctorRepository.prescriptionCount(name);
		
		int page = (currentPage - 1) * PAGE_SIZE;
		
		List<MedicineDataVo> list = doctorRepository.getModalPrescription(name, page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		
		return map;
	}

	public Map<String, Object> getModalTreatment(String name, int currentPage) {
		int totalCount = doctorRepository.treatmentCount(name);
		
		int page = (currentPage - 1) * PAGE_SIZE;
		
		List<MedicineDataVo> list = doctorRepository.getModalTreatment(name, page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		
		
		return map;
	}

	public Map<String, Object> getModalDiease(String keyword, int currentPage) {

		int totalCount = doctorRepository.dieaseTotalCount(keyword);

		int page = (currentPage - 1) * PAGE_SIZE;
		
		List<DiseaseDataVo> list = doctorRepository.getModalDiease(keyword, page);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("list", list);
		map.put("totalCount", totalCount);
		return map;
	}

}
