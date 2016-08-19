# singleSignOn
Single Sign On project

1. Instructions to install and configure prerequisites

1.1 Following programs must be installed on the test machine
    - mysql
    - JDK 1.8.0
    - maven
    - ldap

1.2 Mysql must be running at the background

1.3 Ldap must be running at the background
make sure that you are running at ldap://localhost:" + 10389
and UserDn and password not set

2. Instructions to create database and prepare source code to build

2.1 Run the sql script in /Scripts/Create.sql file

2.2 Run the ldid script in /Scripts/Create.ldif file or (/Scripts/CreateWithDC.ldif if you already have dc=example,dc=com in your ldap server)

2.3 Open Source/singleSignOn/auth-server/src/main/resources/application.properties file

Set these 3 fields with your credentials and deployment address

spring.datasource.url = jdbc:mysql://localhost:{mysql port}/ssoproject
spring.datasource.username = {mysql username}
spring.datasource.password = {mysql password}

2.4 Go to /Source/singleSignOn directory

    - Do the following in sequence, in four different terminal windows (wait for successful build of previous one):
	* In first terminal window:
		> Go under eureka directory with cd command
  		> Run mvn spring-boot:run command
	* In second terminal window:
		> Go under auth-server directory with cd command
  		> Run mvn spring-boot:run command
	* In third terminal window:
		> Go under login directory with cd command
  		> Run mvn spring-boot:run command
	* In fourth terminal window:
		> Go under client1 directory with cd command
  		> Run mvn spring-boot:run command
	* In fifth terminal window:
		> Go under client2 directory with cd command
  		> Run mvn spring-boot:run command
    - Wait until all the programs are start
    - Open web browser and go to http://localhost:8084 for first client
    - Open web browser and go to http://localhost:8085 for second client

2.5 For login, use
username: user password: password or
username: suser password: password2

2.6 For testing the application from browser;

Scenario 1: try to login with credentials
    - You will be redirected to clients

Scenario2: try to logout from one of your clients using logout button
    - You will be redirected to login page when you refresh the other clients page

3. Assumptions and missing requirements

3.1 User must use same browser for all clients (tested with Safari browser)

3.2 Project only tested at Unix based systems (Mac OS)

3.3 System only makes Authenticate. Authorization step is missing

3.4 User interface tests are missing

3.5 Automate deployment and testing step is missing
