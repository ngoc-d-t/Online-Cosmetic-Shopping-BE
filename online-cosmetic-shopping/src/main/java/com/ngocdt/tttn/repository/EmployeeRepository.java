package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
