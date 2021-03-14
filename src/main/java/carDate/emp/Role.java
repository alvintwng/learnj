package carDate.emp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	@Column(name = "ROLEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	
	@Column(length = 15, nullable = false, unique = true)
	private String name;
	
	public Role() {	}
	
	public Role(String name) {
		this.name = name;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* This is must to show data on the html page*/
	@Override
	public String toString() {
		return name;
	}

}

/*
 -- REM https://www.baeldung.com/spring-boot-data-sql-and-schema-sql
Insert into ROLE (NAME) values ('ADMIN');
Insert into ROLE (NAME) values ('MANAGER');
Insert into ROLE (NAME) values ('USER');

 * */
 