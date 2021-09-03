package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.dto.EmployeeDTO;
import com.ngocdt.tttn.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value="select e.employeeID, e.address,e.birthday, e.fullname, e.phoneNumber, e.sex from (select a.employeeID from (select roleID from Role where roleName ='ROLE_SHIPPER') r inner join (select employeeID, roleID from account) a on r.roleID = a.roleID) b inner join Employee e on e.employeeID = b.employeeID",nativeQuery = true)
    List<Employee> findAllShipper();
    @Query(value="select e.employeeID, e.address,e.birthday, e.fullname, e.phoneNumber, e.sex from (select a.employeeID from (select roleID from Role where roleName ='ROLE_ADMIN') r inner join (select employeeID, roleID from account) a on r.roleID = a.roleID) b inner join Employee e on e.employeeID = b.employeeID",nativeQuery = true)
    List<Employee> findAllRoleAdminEmployee();
}
