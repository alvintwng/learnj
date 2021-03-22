git

sqlScript

# git
``` console 
antw@Mac-mini SumProj % git remote add origin https://.../learnj.git
antw@Mac-mini SumProj % git checkout branch3

Mar 15, 2021
antw@Mac-mini mJCap7 % git clone https://github.com/alvintwng/learnj.git
antw@Mac-mini mJCap7 % cd learnj

antw@Mac-mini learnj % pwd             
/Users/antw/ntuc/mJCap7/learnj

antw@Mac-mini learnj % git checkout -b branch4
antw@Mac-mini learnj % git add .
antw@Mac-mini learnj % git commit -m "transfer to /Users/antw/ntuc/mJCap7/learnj"
```

# sqlScript
``` sql
insert into ROLE (NAME)  values  ('ADMIN');
insert into ROLE (NAME)  values  ('MANAGER');
insert into ROLE (NAME)  values  ('USER');

insert into EMPLOYEE (EMAIL, EMP_FULL_NAME, EMPNAME, ISACTIVE, JOB_TITLE, PASSWORD, PHONE_NO, PSWD_EXPIRY, USER_EXPIRY)
            VALUES ('root@carDate.com', 'Simon Sridhar', 'root', 1, 'Director', '$2y$12$B6v4.Xt3A/Ru850pmXwzjunyTZJkcv3kTOekHB2.vU/jmh31I9XiG',
                    '+9198989897', '27-MAY-2021', '27-JUL-2021');
Insert into EMPLOYEE (EMAIL, EMP_FULL_NAME, EMPNAME, ISACTIVE, JOB_TITLE, PASSWORD, PHONE_NO, PSWD_EXPIRY, USER_EXPIRY) 
            values ('ntuc@ntuc.com',    'ntuc',          'ntuc', 1, 'Manager',  '$2y$12$HhTBWJSyOmHzJV54PwvgeeSOpsVb03sN1x9evySaF3Q0Kc8TXQygq',
                    '88888888',   '27-MAY-2021',  '27-JUL-21');


               
insert into VEH_STATUS (NAME)  values  ('FREE');
insert into VEH_STATUS (NAME)  values  ('HIRED');
```