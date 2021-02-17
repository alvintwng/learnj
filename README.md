git

sqlScript

# git
``` console 
antw@Mac-mini SumProj % git remote add origin https://github.com/alvintwng/sumProj.git
git branch -M main
git push -u origin main
fatal: remote origin already exists.
Enumerating objects: 30, done.
Counting objects: 100% (30/30), done.
Delta compression using up to 4 threads
Compressing objects: 100% (25/25), done.
Writing objects: 100% (30/30), 55.24 KiB | 13.81 MiB/s, done.
Total 30 (delta 0), reused 0 (delta 0)
remote: 
remote: Create a pull request for 'main' on GitHub by visiting:
remote:      https://github.com/alvintwng/learnj/pull/new/main
remote: 
To https://github.com/alvintwng/learnj.git
 * [new branch]      main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.


antw@Mac-mini SumProj %       
antw@Mac-mini SumProj % 
antw@Mac-mini SumProj % git status
On branch main
Your branch is up to date with 'origin/main'.

nothing to commit, working tree clean

antw@Mac-mini SumProj % ls
HELP.md		mvnw		mvnw.cmd	pom.xml		src		target
antw@Mac-mini SumProj % echo "# sumProj" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/alvintwng/sumProj.git
git push -u origin main 
Reinitialized existing Git repository in /Users/antw/ntuc/mJCap7/SumProj/.git/
[main 91b3437] first commit
 1 file changed, 1 insertion(+)
 create mode 100644 README.md
fatal: remote origin already exists.
Enumerating objects: 4, done.
Counting objects: 100% (4/4), done.
Delta compression using up to 4 threads
Compressing objects: 100% (2/2), done.
Writing objects: 100% (3/3), 282 bytes | 282.00 KiB/s, done.
Total 3 (delta 1), reused 0 (delta 0)
remote: Resolving deltas: 100% (1/1), completed with 1 local object.
To https://github.com/alvintwng/learnj.git
   2f6ec94..91b3437  main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.


antw@Mac-mini SumProj % ls
HELP.md		README.md	mvnw		mvnw.cmd	pom.xml		src		target


antw@Mac-mini SumProj % cat readme.md 
# sumProj

antw@Mac-mini SumProj % git status
On branch main
Your branch is up to date with 'origin/main'.

antw@Mac-mini SumProj % git checkout -b branch2
Switched to a new branch 'branch2'
antw@Mac-mini SumProj % git status
On branch branch2
nothing to commit, working tree clean
antw@Mac-mini SumProj % vim readme.md

antw@Mac-mini sumproj % git branch -v

antw@Mac-mini sumproj % git add .  
antw@Mac-mini sumproj % git commit -m "employees pojo crud"  
antw@Mac-mini sumproj % git push -u origin branch2

antw@Mac-mini sumproj % git add .
antw@Mac-mini sumproj % git commit -m "vehicle pojo crud"
antw@Mac-mini sumproj % git push -u origin branch2  
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