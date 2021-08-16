package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.AccountDTO;
import com.ngocdt.tttn.entity.Account;
import com.ngocdt.tttn.entity.Employee;
import com.ngocdt.tttn.entity.Role;
import com.ngocdt.tttn.enums.ROLE;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.exception.ConflictException;
import com.ngocdt.tttn.repository.AccountRepository;
import com.ngocdt.tttn.repository.CustomerRepository;
import com.ngocdt.tttn.repository.EmployeeRepository;
import com.ngocdt.tttn.repository.RoleRepository;
import com.ngocdt.tttn.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepo;
    private final RoleRepository roleRepo;
    private final EmployeeRepository employeeRepo;
    private final CustomerRepository customerRepo;
    private final PasswordEncoder encoder;
    @Override
    @ModelAttribute("email")
    @Transactional
    public AccountDTO update(AccountDTO dto, HttpServletRequest request) {
        Account accountCurrent = accountRepo.findByEmail(request.getAttribute("email").toString()).get();
        Account account = AccountDTO.toEntity(dto);
        Employee employee = employeeRepo.findById(accountCurrent.getEmployee().getEmployeeID()).orElseThrow(
                () -> new BadRequestException("Employee not found."));
        account.setEmployee(employee);
        Role role = new Role();
        role.setRoleName(ROLE.ROLE_ADMIN);
        account.setRole(role);
        return AccountDTO.toDTO(accountRepo.save(account));
    }

    @Override
    @Transactional
    public AccountDTO create(AccountDTO dto) {
        System.out.println("employee: "+dto.getEmployeeID());
        Account account = AccountDTO.toEntity(dto);
        Employee employee = employeeRepo.findById(dto.getEmployeeID()).orElseThrow(
                () -> new BadRequestException("Employee not found."));
        account.setEmployee(employee);
        Role role = roleRepo.findByRoleName(ROLE.ROLE_ADMIN).orElseThrow(()-> new ConflictException("Role not found."));
        account.setRole(role);
        account.setAccountID(0);
        account.setPassword(encoder.encode(account.getPassword()));
        return AccountDTO.toDTO(accountRepo.save(account));
    }

    @Override
    public void delete(HttpServletRequest request) {

    }

}
