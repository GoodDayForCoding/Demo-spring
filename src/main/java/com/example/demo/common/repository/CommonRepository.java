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
}
