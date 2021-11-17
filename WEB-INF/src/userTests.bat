javac -d ..\classes user\dao\*.java user\modele\User.java user\UserTest.java
java -cp ..\classes;..\lib\sqlite-jdbc-3.23.1.jar user.UserTest