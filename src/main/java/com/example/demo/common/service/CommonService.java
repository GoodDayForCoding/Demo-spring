package com.example.demo.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.repository.CommonRepository;
import com.example.demo.vo.AttendanceScheduleVo;
import com.example.demo.vo.DoctorVo;

@Service
public class CommonService {
	
	@Autowired
	private CommonRepository commonRepository;
	
	public List<AttendanceScheduleVo> findAttendanceScheduleList() {
		
		return commonRepository.selectAttendanceScheduleList();
	}

	public AttendanceScheduleVo findAttendanceScheduleByNo(Long no) {
		
		return commonRepository.selectAttendanceScheduleByNo();
	}

	public Boolean addAttendanceSchedule(AttendanceScheduleVo attendanceScheduleVo) {
		// TODO Auto-generated method stub
		return commonRepository.insertAttendanceSchedule(attendanceScheduleVo);
	}

	public Boolean updateAttendanceSchedule(Long no) {
		// TODO Auto-generated method stub
		return commonRepository.updateAttendanceSchedule(no);
	}
	
	public Boolean deleteAttendanceSchedule(Long no) {
		// TODO Auto-generated method stub
		return commonRepository.deleteAttendanceSchedule(no);
	}

	
	/*************** 진료 내역 start ******************/
	public Map<String, Object> visitedRecordList(int hospital, String name, String role, int currentPage) {
		
		int totalCount = commonRepository.visitedRecordList(hospital, name, role);
		int page = (currentPage - 1) * 10;
		
		List<DoctorVo> list;
		if(role == "의사") {
			list = commonRepository.visitedRecordList(hospital, name, page);
		} else {
			list = commonRepository.visitedRecordList(hospital, page);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", totalCount);
		map.put("data", list);
		
		return map;
	}

	/*************** 진료 내역 end ******************/
}
