<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.Board" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/kognise/water.css@latest/dist/light.min.css">
<link rel="stylesheet" href="./css/Style.css">
<title>管理画面</title>
</head>
<body>
	<button id="btn" class="btn">TOPへ戻る</button><br>

	<% ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list"); %>
	<% SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); %>
	<% for(int i = 0;i < list.size();i++){
		Board b = list.get(i);
	%>
		<%=b.getId() + " "%>
		投稿者：<%=b.getName() %>
		投稿時間：<%=sdf.format(b.getPosttime()) %>
		<% if(b.getEditingtime() != null){ %>
			編集時間：<%=sdf.format(b.getEditingtime()) %>
		<% } %>
		<div id="buttons">
			<form action="/board/Edit" data-ajax="false" method="get">
				<input type="hidden" id="edit" name="key" value="<%=b.getId() %>">
				<button type="submit" class="btn2">編集</button>
			</form>

			<form action="/board/Delete" data-ajax="false" method="get">
				<input type="hidden" id="delete" name="key" value="<%=b.getId() %>">
				<button type="submit" class="btn3">削除</button>
			</form>
		</div>
		<br>
		<%=b.getContent() %><br>
		<!-- ファイルに対する処理 -->
		<% if(b.getFile() != null){ %>
			<!-- 拡張子の取得 -->
			<% String ext = b.getFile().substring(b.getFile().lastIndexOf('.')); %>
			<!-- 拡張子を判定 -->
			<% if(ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".png") ||
					ext.equals(".gif")) {%>
				<img src="${pageContext.request.contextPath}/upload/<%=b.getFile() %>">
			<% }else{ %>
				<a href="${pageContext.request.contextPath}/upload/<%=b.getFile() %>"><%=b.getFile() %></a>
			<% } %>
		<% } %>
		<br>
		<hr>
	<% } %>

	<!-- JavaScript/jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script>
	$(function(){
		$('#btn').click(function(){		//トップに遷移
			window.location.href="http://localhost:8080/board/Top"
		})
	});
	</script>

</body>
</html>