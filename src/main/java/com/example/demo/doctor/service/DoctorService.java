package com.example.demo.doctor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.ognl.ASTBitNegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.doctor.repository.DoctorRepository;
import com.example.demo.vo.AppointmentVo;
import com.example.demo.vo.DiagnosisVo;
import com.example.demo.vo.DiseaseDataVo;
import com.example.demo.vo.DiseaseVo;
import com.example.demo.vo.DoctorVo;
import com.example.demo.vo.MedicineDataVo;
import com.example.demo.vo.PrescriptionVo;
import com.example.demo.vo.TreatmentVo;

@Service
public class DoctorService {
	private int PAGE_SIZE = 10;

	@Autowired
	private DoctorRepository doctorRepository;
	

	public List<DoctorVo> getTreatList(Long no) {
		return doctorRepository.getTreatList(no);
	}
	
	@Transactional
	public Boolean doctorSuccess(DoctorVo doctorVo) {
		Boolean status = false;
		// 진단서입력
		DiagnosisVo diagnosisVo = new DiagnosisVo();
		diagnosisVo.setAppointmentNo(doctorVo.getAppointmentNo());
		diagnosisVo.setOpinion(doctorVo.getDiagnosisOpinion());
		diagnosisVo.setPatientNo(doctorVo.getPatientNo());
		doctorRepository.doctorSuccess(diagnosisVo); //diagnosis;
		
		// 처방전입력
		PrescriptionVo prescriptionVo = new PrescriptionVo(); 
		prescriptionVo.setDiagnosisNo(diagnosisVo.getNo());
		prescriptionVo.setMedicineName(doctorVo.getPrescriptionName());
		prescriptionVo.setDose(doctorVo.getPrescriptionDose());
		prescriptionVo.setRemark(doctorVo.getPrescriptionRemark());
		prescriptionVo.setDosingDays(doctorVo.getPrescriptionDays());
		prescriptionVo.setDosingPrequency(doctorVo.getPrescriptionPrequency());
		doctorRepository.insertPrescription(prescriptionVo);
		
		// 질병입력
		DiseaseVo diseaseVo = new DiseaseVo();
		diseaseVo.setDiseaseName(doctorVo.getDiseaseName());
		diseaseVo.setDiagnosisNo(diagnosisVo.getNo());
		doctorRepository.insertDisease(diseaseVo);
		
		// 치료입력(없을수도있음)
		TreatmentVo treatmentVo = new TreatmentVo();
		treatmentVo.setMedicineName(doctorVo.getTreatmentName());
		treatmentVo.setTreat(doctorVo.getTreat());
		treatmentVo.setDose(doctorVo.getTreatDose());
		treatmentVo.setDiagnosisNo(diagnosisVo.getNo());
		doctorRepository.insertTreatment(treatmentVo);
		
		// 예약 update
		AppointmentVo appointmentVo = new AppointmentVo();
		appointmentVo.setNo(doctorVo.getAppointmentNo());
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
