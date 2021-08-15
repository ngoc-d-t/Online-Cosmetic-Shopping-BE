package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Role;
import com.ngocdt.tttn.enums.ROLE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(ROLE roleName);
}
