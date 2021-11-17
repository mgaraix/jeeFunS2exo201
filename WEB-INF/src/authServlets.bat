----  authServlet.bat
--– compiler AuthServlet.java                                                       
javac -d ..\classes -cp ..\classes;C:\MesAppli\TOMCAT9\apache-tomcat-9.0.44\lib\servlet-api.jar auth\AuthServlet.java                                             
--- relancer tomcat
cd ../../../../bin                                                           
catalina.bat run