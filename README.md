# Transportation system
Client–server model that simulates the system of passenger bus transportation. 
-------------------------------------------------------------------------------------------------------------
To create database:
mysql -u root -p < transportation_system.sql
-------------------------------------------------------------------------------------------------------------
To compile:
javac -classpath .;antlr-2.7.6.jar;commons-collections-3.1.jar;dom4j-1.6.1.jar;commons-logging.jar;
hibernate3.jar;javassist-3.4.GA.jar;jta-1.1.jar;log4j-1.2.15.jar;mysqlJDBC-3.1.13.jar;slf4j-api-1.5.3.jar;
ejb3-persistence.jar;hibernate-annotations.jar;hibernate-commons-annotations.jar;javaee.jar;
slf4j-log4j12-1.5.3.jar;jsp-api.jar;servlet-api.jar com\interaction\*.java com\objects\*.java utils\*.java 
com\forms\*.java com\web\*.java CompanyMemberMain.java

Or use project management tools such as Apache Maven etc. All required libraries placed in lib folder.
