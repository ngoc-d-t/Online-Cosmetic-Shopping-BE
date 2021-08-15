package com.ngocdt.tttn.entity;

import java.util.List;

import javax.persistence.*;

import com.ngocdt.tttn.enums.ROLE;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Role")
@Getter
@Setter
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int roleID;
	
	@Column(unique = true)
	@Enumerated(EnumType.STRING)
	private ROLE roleName;

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public ROLE getRoleName() {
		return roleName;
	}

	public void setRoleName(ROLE roleName) {
		this.roleName = roleName;
	}
}
