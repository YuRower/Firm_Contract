<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
      <link href="resources/style.css" rel="stylesheet" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Firm Manager Home</title>
    </head>
    <body>
        <div align="center">
            <h1>Firm List</h1>
            <h3><a href="newFirm">New Firm</a></h3>
            <table border="1">
                <th>No</th>
                <th>Name</th>
                <th>Shef</th>
                <th>Address</th>
                <th>Action</th>
                 
                <c:forEach var="info_firm" items="${firmList}" varStatus="status">
                <tr>
                    <td>${info_firm.id}</td>
                    <td >${info_firm.name}</td>
                    <td>${info_firm.shef}</td>
                    <td>
                      <a href="findByLetter?l=${info_firm.address}" id = "firstLet">
                    ${info_firm.address}
                    
                    </a>
                    </td>
                    <td>
                        <a href="editFirm?id=${info_firm.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteFirm?id=${info_firm.id}">Delete</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                         <a href="allContracts?id=${info_firm.id}">All Contracts</a>
                    </td>  
                </tr>
                </c:forEach>             
            </table>
        </div>
    </body>
</html>