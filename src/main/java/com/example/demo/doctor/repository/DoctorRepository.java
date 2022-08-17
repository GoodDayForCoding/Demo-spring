package com.example.demo.doctor.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.AppointmentVo;
import com.example.demo.vo.DiagnosisVo;
import com.example.demo.vo.DiseaseDataVo;
import com.example.demo.vo.DiseaseVo;
import com.example.demo.vo.DoctorVo;
import com.example.demo.vo.MedicineDataVo;
import com.example.demo.vo.PrescriptionVo;
import com.example.demo.vo.TreatmentVo;

@Repository
public class DoctorRepository {

	@Autowired
	private SqlSession sqlSession;

	public List<DoctorVo> getTreatList(Long no) {
		return sqlSession.selectList("doctor.getTreatList", no);
	}

	public List<DiseaseDataVo> getModalDiease(String name, int page) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("page", page);
		
		return sqlSession.selectList("doctor.getDiease", map);
	}

	public List<MedicineDataVo> getModalPrescription(String name, int page) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("page", page);
		
		return sqlSession.selectList("doctor.getMedicine", map);
	}

	public List<MedicineDataVo> getModalTreatment(String name, int page) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("page", page);
		
		return sqlSession.selectList("doctor.getTreatment", map);
	}

	public Boolean doctorSuccess(DiagnosisVo diagnosisVo) {
		return sqlSession.insert("doctor.insertDiagnosis", diagnosisVo) == 1;
	}

	public Boolean insertPrescription(PrescriptionVo prescriptionVo) {
		return sqlSession.insert("doctor.insertPrescription", prescriptionVo) == 1;
	}

	public Boolean insertDisease(DiseaseVo diseaseVo) {
		return sqlSession.insert("doctor.insertDisease", diseaseVo) == 1;
	}

	public Boolean insertTreatment(TreatmentVo treatmentVo) {
		return sqlSession.insert("doctor.insertTreatment", treatmentVo) == 1;		
	}

	public Boolean updateAppointment(AppointmentVo appointmentVo) {
		return sqlSession.update("doctor.updateAppointment", appointmentVo) == 1;
	}
	
	
	
}
