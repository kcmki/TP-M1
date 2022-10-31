<<<<<<< HEAD
/* TP 3 */
/*BELAISSAOUI Mohamed Elmekki 191931063467*/
/*1---------------------------------------------*/
conn system/mekki123

select count(*) as "nombre d'instance" from DICT;
DESC DICT;

/*2---------------------------------------------*/
select * from DICT where TABLE_NAME= 'ALL_TAB_COLUMNS';
DESC ALL_TAB_COLUMNS;
/*  */
select * from DICT where TABLE_NAME= 'USER_USERS';
DESC USER_USERS;
/*  */
select * from DICT where TABLE_NAME= 'ALL_CONSTRAINTS';
DESC ALL_CONSTRAINTS;
/*  */
select * from DICT where TABLE_NAME= 'USER_TAB_PRIVS';
DESC USER_TAB_PRIVS;
/*   */

/*3---------------------------------------------*/
SELECT username FROM USER_USERS;

/*4---------------------------------------------*/

DESC ALL_TAB_COLUMNS;
DESC USER_TAB_COLUMNS;

/*5---------------------------------------------*/

conn DBAINTERVENTION/mekki123
SELECT TABLE_NAME  FROM USER_TABLES;
=======
>>>>>>> 5048c148621aa39621549f3870ef5cd44a913ff2

/*6---------------------------------------------*/

SELECT *  FROM ALL_TABLES WHERE OWNER='DBAINTERVENTION';
SELECT *  FROM ALL_TABLES WHERE OWNER='SYSTEM';

/*7---------------------------------------------*/

SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME IN ('VEHICULE','INTERVENTIONS');

/*8---------------------------------------------*/

 select constraint_name,table_name,constraint_TYPE,r_constraint_name from user_constraints;

/*9---------------------------------------------*/
select constraint_name,table_name,constraint_TYPE,r_constraint_name from user_constraints where table_name in ('INTERVENTIONS');

/*10--------------------------------------------*/
select constraint_name,table_name,constraint_TYPE,r_constraint_name from all_constraints where table_name in ('INTERVENTIONS');
select * from user_tab_columns where table_name ='INTERVENTIONS';
/*11--------------------------------------------*/
select * from dba_tab_privs where grantee='ADMIN';
select * from dba_sys_privs where grantee='ADMIN';
select * from dba_tab_privs where grantee IN (SELECT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE grantee='ADMIN');
/*12--------------------------------------------*/
select * from dba_role_privs where grantee='ADMIN';
/*13--------------------------------------------*/
select object_name from all_objects where owner='ADMIN';
/*14--------------------------------------------*/
select owner from all_tables WHERE table_name='INTERVENTIONS';
/*15--------------------------------------------*/
select bytes/1024 KO from DBA_segments where segment_name='INTERVENTIONS';
/*16--------------------------------------------*/
select table_name from all_tables where owner='DBAINTERVENTION';
select constraint_name,constraint_type,table_name FROM ALL_CONSTRAINTS WHERE OWNER='DBAINTERVENTION';
select TABLE_NAME,COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE OWNER='DBAINTERVENTION';