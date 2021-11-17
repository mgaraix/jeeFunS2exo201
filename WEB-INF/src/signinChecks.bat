javac -d ..\classes -cp ..\classes;junit-4.12.jar;hamcrest-core-1.3.jar utils\*.java auth\SigninCheck.java
java -cp ..\classes;junit-4.12.jar;hamcrest-core-1.3.jar;..\lib\sqlite-jdbc-3.23.1.jar org.junit.runner.JUnitCore auth.SigninCheck                                            


