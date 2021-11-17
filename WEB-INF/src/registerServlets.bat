javac -d ..\classes user\dao\*.java user\modele\User.java
javac -d ..\classes -cp ..\classes;C:\MesAppli\TOMCAT9\apache-tomcat-9.0.44\lib\servlet-api.jar auth\AuthServlet.java user\RegisterServlet.java                                                                                                                                                   
cd ../../../../bin                                                           
catalina.bat run