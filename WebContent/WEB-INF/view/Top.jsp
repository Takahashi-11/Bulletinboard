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
<title>掲示板</title>
</head>
<body>
	<!-- ページ上部 -->
	<button id="btn" class="btn">管理画面へ</button>

	<form id="post" action="/board/Post" enctype="multipart/form-data" data-ajax="false" method="POST">
		投稿者<br>
		<input type="text" name="name" id="name"><br>
		メールアドレス<br>
		<input type="email" name="email" id="email"><br>
		内容<br>
		<textarea name="content" id="content" rows="8" cols="80"></textarea><br>
		添付ファイル<br>
		<input type="file" name="file" id="file"/><br>
		<input type="submit" value="投稿">
	</form>
	<hr>

	<!-- 投稿履歴 -->
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
			$('#btn').click(function(){		//管理画面に遷移
				window.location.href="http://localhost:8080/board/Management"
			});

			$('#post').submit(function(){	//未入力チェック
				var name = document.getElementById("name").value;
				var email = document.getElementById("email").value;
				var content = document.getElementById("content").value;
				var filename = document.getElementById("file").value;

				if(name == "" || email == "" || content == ""){
					alert("入力されていない項目があります。");
					return false;
				}else{
					//エスケープ処理
					document.getElementById("name").value = e(name);
					document.getElementById("email").value = e(email);
					document.getElementById("content").value = e(content);
				}

				//拡張子判定
				if(filename.length > 0 && !(checkExt(filename))){
					alert("ファイルの拡張子が正しくありません。");
					return false;
				}
			});

			//エスケープ処理
			function e(str){
				  return String(str).replace(/&/g,"&amp;")
				    .replace(/"/g,"&quot;")
				    .replace(/</g,"&lt;")
				    .replace(/>/g,"&gt;")
				    return str;
				}

			//拡張子の取得
			function getExt(filename){
				var pos = filename.lastIndexOf('.');	//'.'の位置
				if(pos === -1)
					return '';
				return filename.slice(pos + 1);		//'.'以降の文字（拡張子）を取得
			}

			//拡張子チェック
			var allow_exts = new Array('jpg','jpeg','png','gif','docx','xlsx','ppt','pdf');
			function checkExt(filename){
				var ext = getExt(filename).toLowerCase();	//小文字に変換
				//許可する拡張子に対象の拡張子が含まれているか確認する
				if(allow_exts.indexOf(ext) === -1)
					return false;
				return true;
			}
		});
	</script>

	<!-- チャレンジ課題２ -->
	<!-- アラートの表示を無限ループさせるJavaScriptコードとしてHTMLに埋め込まれるため -->

</body>
</html>