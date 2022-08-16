package com.example.demo.doctor.service;

import java.util.List;

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
	
}
