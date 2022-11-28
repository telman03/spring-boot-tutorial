package com.dailycodebuffer.tutorial.Repository;

import com.dailycodebuffer.tutorial.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
