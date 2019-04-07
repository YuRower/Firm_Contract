<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>New/Edit Contact</title>
   </head>
   <body>
      <div align="center">
         <h1>All Contracts</h1>
         <h3><a href="newContract">New Contract</a></h3>
         <table border="1">
            <th>idFirm</th>
            <th>Numbered</th>
            <th>Named</th>
            <th>Sumd</th>
            <th>DataStart</th>
            <th>DataFinish</th>
            <th>Avans</th>
            <th>Action</th>
            <c:forEach var="info_Contract" items="${contractList}" varStatus="status">
               <tr>
                  <td>${info_Contract.idFirm}</td>
                  <td>${info_Contract.numbered}</td>
                  <td > ${info_Contract.named}</td>
                  <td>${info_Contract.sumd}</td>
                  <td>${info_Contract.dataStart}</td>
                  <td>${info_Contract.dataFinish}</td>
                  <td>${info_Contract.avans}</td>
                  <td>
                     <a href="editContract?id=${info_Contract.id}">Edit</a>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <a href="deleteContract?id=${info_Contract.id}">Delete</a>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                  </td>
               </tr>
                     <c:set var = "idFirm" scope = "request" value = "${info_Contract.idFirm}"/>
            </c:forEach>
         </table>
         <form action="findByDate">
            Data Start: <input type="date" name="dayS">
            Data Finish: <input type="date" name="dayE">
           <input type="hidden" name="id" value =  "${idFirm}"> 
            <input type="submit">
         </form>
      </div>
   </body>
</html>