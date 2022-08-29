package com.example.demo.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.repository.CommonRepository;
import com.example.demo.vo.AttendanceScheduleVo;

@Service
public class CommonService {
	
	@Autowired
	private CommonRepository commonRepository;
	
	public List<AttendanceScheduleVo> findAttendanceScheduleList(int hospital, String sdate, String endDate) {		
		return commonRepository.selectAttendanceScheduleList(hospital, sdate, endDate);
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
	public Map<String, Object> visitedRecordList(int hospital, long no, String role, int currentPage) {
		
		int totalCount = commonRepository.visitedRecordList(hospital, no, role);
		int page = (currentPage - 1) * 10;
		
		List<Map<String, Object>> list = commonRepository.visitedRecordList(hospital, no, page, role);;
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", totalCount);
		map.put("data", list);
		
		return map;
	}
	
	public Map<String, Object> visitedRecord(int hospital, String date, int currentPage){
		int total = commonRepository.visitedRecord(hospital, date);
		
		int page = (currentPage - 1) * 10;
				
		List<Map<String, Object>> list = commonRepository.visitedRecord(hospital, date, page);
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", total);
		map.put("date", date);
		map.put("data", list);
		
		return map;
	}
	
	public Map<String, Object> visitedRecordDetail(int hospital, String name, int no, int currentPage){
		int total = commonRepository.visitedRecordDetail(hospital, name);
		
		int detailPage = (currentPage - 1) * 10;	
	
		List<Map<String, Object>> list = commonRepository.visitedRecordDetail(hospital, name,no, detailPage);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", total);
		map.put("data", list);
		
		return map;
	}


	/*************** 진료 내역 end ******************/
}
