<%@page import="com.numberlist.vo.NumberListVo"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<NumberListVo> list = (List<NumberListVo>)request.getAttribute("list");
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello Numbers</title>
<style>
	body {background-color: #373737;border: double;margin: 10px;}
	form {background-color: #BBBB99;border: double;margin: 10px;}
	input {background-color: #FFFFBB;border: double;margin: 1px;}
	a {color: #F0F0F0;}
	

</style>
</head>
<body>
	<h3>전화번호부</h3>
	
	<jsp:include page="/WEB-INF/views/includes/header.jsp">
	<jsp:param value="" name="s"/>
	</jsp:include>
	
	<form action="<%=request.getContextPath() %>/" method="POST">
		<label for="search">검색어</label>
		<input type="hidden" name="a" value="search" /> 
		<input type="text" name="search" id="search" />
		<input type="submit" value="검색">
	</form>
	</tr>
	</br>
	<table border="3">
		<tr>
			<th>이름</th>
			<td>휴대전화</td>
			<td>전화번호</td>
			<td>도구</td>
		</tr>
		<%
	for (NumberListVo vo : list) {
	%>
		<tr>
			<th><%=vo.getNumberName() %></th>
			<td><%=vo.getNumberHp() %></td>
			<td><%=vo.getNumberTel() %></td>		
			<td colspan="2">
				<!-- 삭제 폼 -->
				<form action="<%=request.getContextPath()%>/"
					method="POST">
					<input type="hidden" name="a" value="delete" /> 
					<input type="hidden" name="no" value="<%=vo.getNumberId() %>" />
					<input type="submit" value="삭제" />
				</form>
			</td>	
		</tr>	
		<%
		}
		%>
		</table>
		</br>
			<a href="<%=request.getContextPath() %>/?a=insert">새 주소 추가</a>
</body>
</html>