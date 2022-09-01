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

	
	public Long insertPatient(PatientVo patientVo) {
		
		
		Long result = (long) sqlSession.insert("nurse.insertPatient", patientVo);
		

		return result;
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

	public List<AppointmentVo> selectAppointmentList() {
		
		return sqlSession.selectList("nurse.findAllAppointments");
	}

	public NurseVo selectAppointmentByPatientNo(Long no) {
		
		return sqlSession.selectOne("nurse.findAppointmentByPatientNo", no);
	}


	public List<NurseVo> selectAppointmentByPatientName(String patientName) {
		return sqlSession.selectList("nurse.findAppointmentByPatientName", patientName);
	}


	public PatientVo selectPatientByNo(Long no) {
		return sqlSession.selectOne("nurse.findPatientByNo", no);
	}


	public List<PatientVo> selectPatientByName(String name) {
		return sqlSession.selectList("nurse.findPatientByName", name);
	}


	public List<AppointmentVo> selectAppointmentByDate(String date) {
		return sqlSession.selectList("nurse.findAppointmentByDate", date);
	}


	public Boolean deleteAppointment(Long no) {
		return sqlSession.update("nurse.deleteAppointment", no) == 1;
	}


	public List<PatientVo> selectPatientListByHospitalNo(Long no) {
		
		return sqlSession.selectList("nurse.findAllPatientByHospitalNo", no);
	}

}
