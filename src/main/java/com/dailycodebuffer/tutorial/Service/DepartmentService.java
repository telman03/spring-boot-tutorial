package com.dailycodebuffer.tutorial.Service;

import com.dailycodebuffer.tutorial.Entity.Department;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> fetchDepartmentList();

    public Department fetchDepartmentList(Long departmentId);
}
