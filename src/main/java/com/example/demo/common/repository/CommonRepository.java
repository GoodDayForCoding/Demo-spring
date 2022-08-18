package com.example.demo.common.repository;

import java.util.List;

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
}
