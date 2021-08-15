package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.EmployeeDTO;
import com.ngocdt.tttn.entity.Employee;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.EmployeeRepository;
import com.ngocdt.tttn.service.EmployeeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepo;

    @Override
    public List<EmployeeDTO> showAll() {
        return employeeRepo.findAll().stream().map(EmployeeDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO showOne(Integer id) {
        return EmployeeDTO.toDTO(employeeRepo.findById(id).orElse(null));
    }

    @Override
    public EmployeeDTO update(EmployeeDTO dto) {
        if(!employeeRepo.existsById(dto.getEmployeeID()))
            throw new BadRequestException("Bad request.");
        Employee empl= EmployeeDTO.toEntity(dto);
        return EmployeeDTO.toDTO(employeeRepo.save(empl));
    }

    @Override
    public EmployeeDTO create(EmployeeDTO dto) {
        Employee empl = EmployeeDTO.toEntity(dto);
        empl.setEmployeeID(0);
        return EmployeeDTO.toDTO(employeeRepo.save(empl));
    }

    @Override
    public void delete(Integer id) {
        if(!employeeRepo.existsById(id))
            throw new BadRequestException("Bad request.");
        employeeRepo.deleteById(id);
    }
}
