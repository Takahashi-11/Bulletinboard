<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.Board" %>
<%@page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/kognise/water.css@latest/dist/light.min.css">
<link rel="stylesheet" href="./css/Style.css">
<title>投稿内容の編集</title>
</head>
<body>
	<% Board b = (Board)request.getAttribute("board"); %>

	<button id="btn" class="btn">TOPへ</button>

	<form id="post" action="/board/EditResult" method="get">
		内容<br>
		<input type="hidden" id="edit" name="id" value="<%=b.getId() %>">
		<textarea name="text" id="content" rows="8" cols="80"></textarea><br>
		<input type="submit" value="送信">
	</form>
	<hr>

	<p>元の投稿</p>
	<% SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); %>
	<%=b.getId() + " "%>
	投稿者：<%=b.getName() %>
	投稿時間：<%=sdf.format(b.getPosttime()) %>
	<% if(b.getEditingtime() != null){ %>
		編集時間：<%=sdf.format(b.getEditingtime()) %>
	<% } %>
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

	<!-- JavaScript/jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script>
		$(function(){
			$('#btn').click(function(){		//トップに遷移
				window.location.href="http://localhost:8080/board/Top"
			});

			$('#post').submit(function(){	//未入力チェック
				var content = document.getElementById("content").value;
				if(content == ""){
					alert("入力されていません。");
					return false;
				}
			});
		});
	</script>



</body>
</html>