package com.example.demo.nurse.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.AppointmentVo;
import com.example.demo.vo.NurseVo;
import com.example.demo.vo.PatientVo;


@Repository
public class NurseRepository {
	
	@Autowired
	private SqlSession sqlSession;

	
	public Boolean insertPatient(PatientVo patientVo) {
		return sqlSession.insert("nurse.insertPatient", patientVo) == 1;
	}


	public Boolean updatePatient(PatientVo patientVo) {
		return sqlSession.update("nurse.updatePatient", patientVo) == 1;		
	}

	public Boolean insertAppointment(AppointmentVo appointmentVo) {
		
		return sqlSession.insert("nurse.insertAppointment", appointmentVo) == 1;
	}

	public Boolean updateAppointment(AppointmentVo appointmentVo) {
		
		return sqlSession.update("nurse.updateAppointment", appointmentVo) == 1;
	}

	public List<PatientVo> selectPatientList() {
		
		return sqlSession.selectList("nurse.findAllPatients");
	}

	public PatientVo selectPatient(Long no) {

		return sqlSession.selectOne("nurse.findPatientByNo", no);
	}

	public List<NurseVo> selectAppointmentList() {
		
		return sqlSession.selectList("nurse.findAllAppointments");
	}

	public NurseVo selectAppointment(Long no) {
		
		return sqlSession.selectOne("nurse.findAppointmentByNo", no);
	}

}
