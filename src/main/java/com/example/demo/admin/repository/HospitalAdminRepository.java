package com.example.demo.admin.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.EmployeeVo;

@Repository
public class HospitalAdminRepository {

	@Autowired
	private SqlSession sqlSession;

	public List<EmployeeVo> findAll(Map<String, Object> map) {
		return sqlSession.selectList("hospitalAdmin.findAll", map);
	}

	public EmployeeVo findByNo(Long no) {
		return sqlSession.selectOne("hospitalAdmin.findByNo", no);
	}

	public boolean insertEmployee(EmployeeVo employeeVo) {
		return sqlSession.insert("hospitalAdmin.insertEmployee", employeeVo) == 1;
	}

	public Boolean deleteEmployeeAdmin(Long no) {
		return sqlSession.delete("hospitalAdmin.deleteEmployee", no) == 1;
	}

	public Boolean updateEmployeeAdmin(EmployeeVo employeeVo) {
		return sqlSession.update("hospitalAdmin.updateEmployee", employeeVo) == 1;
	}
}
