package com.example.demo.doctor.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.DiseaseDataVo;
import com.example.demo.vo.DoctorVo;
import com.example.demo.vo.MedicineDataVo;

@Repository
public class DoctorRepository {

	@Autowired
	private SqlSession sqlSession;

	public List<DoctorVo> getTreatList(Long no) {
		return sqlSession.selectList("doctor.getTreatList", no);
	}

	public List<DiseaseDataVo> getModalDiease(String name) {
		return sqlSession.selectList("doctor.getDiease", name);
	}

	public List<MedicineDataVo> getModalPrescription(String name) {
		return sqlSession.selectList("doctor.getMedicine", name);
	}

	public List<MedicineDataVo> getModalTreatment(String name) {
		return sqlSession.selectList("doctor.getTreatment", name);
	}
	
}
