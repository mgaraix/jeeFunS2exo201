–-- compiler TestUserJDBC.java
javac -d ..\classes user\dao\*.java user\modele\User.java 
javac -cp ..\classes;junit-4.12.jar;hamcrest-core-1.3.jar -d ..\classes Test*.java 
java -cp ..\classes;junit-4.12.jar;hamcrest-core-1.3.jar;..\lib\sqlite-jdbc-3.23.1.jar org.junit.runner.JUnitCore TestUserJDBC