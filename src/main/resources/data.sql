Insert into ROLE (NAME) values ('ADMIN');
Insert into ROLE (NAME) values ('MANAGER');
Insert into ROLE (NAME) values ('USER');
Insert into EMPLOYEE (EMAIL,EMP_FULL_NAME,EMPNAME,ISACTIVE,JOB_TITLE,PASSWORD,PHONE_NO,PSWD_EXPIRY,USER_EXPIRY) 
values ('admin@ntuc.com','ADMIN','admin',1,'Admin','$2a$10$63VF0be1Q2Z4QF5Vb7gnZOai8tTpawnJAEl8DEtyKeMGAjDzxtesi','98765432',to_date('10-DEC-23','DD-MON-RR'),to_date('10-DEC-23','DD-MON-RR'));
Insert into EMPLOYEES_ROLES (EMPLOYEEID,ROLEID) values (1,1);
Insert into CUST_STATE (ISDRIVER,NAME) values (0,'Cat A');
Insert into VEH_STATUS (IS_ACTIVE,NAME) values (1,'Class A');
Insert into DAILYRATE (CUSTCATID,DAYRATE,VEHCLASSID) values (1,80,1);
Insert into VEHICLE (DAILY_RATE,VEH_BRAND,VEH_LIC_PLATE,VEH_MODEL,VEHSTTSID) values (66,'Jaguar','XE1A','XE',1);
Insert into CUSTOMER (ADDR1,ADDR2,CITY,CUSTNAME,DATEUPD,DOB,EMAIL,ISACTIVE,NRIC,PHONENO,CUSTSTATEID) values ('123 Rambutan Road','No road','Singapore','Charlie Cha',to_date('01-JAN-21','DD-MON-RR'),to_date('25-DEC-80','DD-MON-RR'),'charlie@gmail.co',1,'S7654321A','98765432',1);

/* need to set once at application.properties: */
/* spring.datasource.initialization-mode=always */