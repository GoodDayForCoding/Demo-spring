package com.example.demo.nurse.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.PatientVo;


@Repository
public class NurseRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public List<PatientVo> findAllPatients() {
		return sqlSession.selectList("nurse.findAllPatients");
	}

	public PatientVo findPatientByNo(Long no) {
		return sqlSession.selectOne("nurse.findPatientByNo", no);
	}

	public Boolean insertPatient(PatientVo patientVo) {
		return sqlSession.insert("nurse.insertPatient", patientVo) == 1;
	}


	public Boolean updatePatient(PatientVo patientVo) {
		return sqlSession.update("nurse", patientVo) == 1;		
	}

}
