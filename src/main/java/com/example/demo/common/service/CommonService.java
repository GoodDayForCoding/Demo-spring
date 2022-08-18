package com.example.demo.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.repository.CommonRepository;
import com.example.demo.vo.AttendanceScheduleVo;

@Service
public class CommonService {
	
	@Autowired
	private CommonRepository commonRepository;
	
	public List<AttendanceScheduleVo> findAttendanceScheduleList() {
		
		return commonRepository.selectAttendanceScheduleList();
	}


}
