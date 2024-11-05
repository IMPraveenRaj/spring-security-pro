<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registration form</title>
</head>
<body>

<h1 align="center">Register Here</h1>
<hr/>

<div align="center">
 <form:form action="process-registration" method="POST" modelAttribute="reg">
   
  Username:  <form:input path="username" name="user"/>
  <br/>
  <br/>
  Password:  <form:password  path="password" name="pass" />
  <br/>
  <br/>
  <input type="submit" value="register">
  
 
 </form:form>
 
 </div>

</body>
</html>