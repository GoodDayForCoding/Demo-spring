package com.example.demo.common.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.AttendanceScheduleVo;


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
		public List<Map<String, Object>> visitedRecordList(int hospital, String email, int page, String role) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospital", hospital);
			map.put("email", email);
			map.put("page", page);
			map.put("role", role);
			return sqlSession.selectList("common.findPatientByDoctor", map);
		}


		public int visitedRecordList(int hospital, String name, String role) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospital", hospital);
			map.put("email", name);
			map.put("role", role);
			
			return sqlSession.selectOne("common.findRecordCount", map);
		}

		public int visitedRecord(int hospital, String date) {	// 날짜선택시 Total
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospital", hospital);
			map.put("date", date);
			
			return sqlSession.selectOne("common.findvisitedRecordCount", map);
		}

		public int visitedRecordDetail(int hospital, String name) {	// 환자선택시 Total
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospital", hospital);
			map.put("name", name);
			
			return sqlSession.selectOne("common.findRecordDetailCount", map);
		}

		public List<Map<String, Object>> visitedRecord(int hospital, String date, int page) {	// 날짜 선택시 데이터
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospital", hospital);
			map.put("date", date);
			map.put("page", page);
			
			return sqlSession.selectList("common.findPatientDate", map);
		}

		public List<Map<String, Object>> visitedRecordDetail(int hospital, String name, int no, int detailPage) {	 // 환자 선택시 데이터
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospital", hospital);
			map.put("name", name);
			map.put("no", no);
			map.put("page", detailPage);
			
			return sqlSession.selectList("common.findPatientDetail", map);
		}

		
		/*************** 진료 내역 end ******************/
		
}
