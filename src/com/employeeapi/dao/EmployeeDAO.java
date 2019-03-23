package com.employeeapi.dao;

import java.io.*;

import com.employeeapi.vo.EmployeeVO;

public class EmployeeDAO extends GenericDAO<EmployeeVO, Serializable> {
	public EmployeeDAO() {
		super(EmployeeVO.class);
	}
}
