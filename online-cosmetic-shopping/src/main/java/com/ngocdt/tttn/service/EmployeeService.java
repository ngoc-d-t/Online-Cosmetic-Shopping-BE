package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService extends GenericService<EmployeeDTO, Integer>{
    List<EmployeeDTO> showAllRoleAdminEmployees();
    List<EmployeeDTO> showAllShippers();
}
