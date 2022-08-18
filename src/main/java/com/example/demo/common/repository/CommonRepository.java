package com.example.demo.common.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.AttendanceScheduleVo;
import com.example.demo.vo.DoctorVo;


@Repository
public class CommonRepository {
	
		@Autowired
		private SqlSession sqlSession;
		
		public List<AttendanceScheduleVo> selectAttendanceScheduleList() {
				
			return sqlSession.selectList("common.findAllAttendanceSchedule");
		}

		public AttendanceScheduleVo selectAttendanceScheduleByNo() {
			
			return sqlSession.selectOne("common.findAttendanceScheduleByNo");
		}

		public Boolean insertAttendanceSchedule(AttendanceScheduleVo attendanceScheduleVo) {
			
			return sqlSession.insert("common.insertAttendanceSchedule", attendanceScheduleVo) == 1;
		}

		public Boolean updateAttendanceSchedule(Long no) {

			return sqlSession.update("common.updateAttendanceSchedule", no) == 1;
		}
		
		public Boolean deleteAttendanceSchedule(Long no) {
			
			return sqlSession.delete("common.deleteAttendanceSchedule", no) == 1;
		}

		
		/*************** 진료 내역 start ******************/
		public List<DoctorVo> visitedRecordList(int hospital, String email, int page) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospital", hospital);
			map.put("email", email);
			map.put("page", page);
			return sqlSession.selectList("common.findPatientByDoctor", map);
		}

		public List<DoctorVo> visitedRecordList(int hospital, int page) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospital", hospital);
			map.put("page", page);
			
			return sqlSession.selectList("common.findPatient", map);
		}

		public int visitedRecordList(int hospital, String name, String role) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospital", hospital);
			map.put("email", name);
			map.put("role", role);
			
			return sqlSession.selectOne("common.findRecordCount", map);
		}
		
		/*************** 진료 내역 end ******************/
		
}
