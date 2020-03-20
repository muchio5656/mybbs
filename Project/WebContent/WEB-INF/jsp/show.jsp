<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link href="./assets/css/bbs.css" rel="stylesheet" />
<title>みんな集まれ</title>
</head>
<body>
	<div class="nav">
		<ul class="nav nav-pills">
			<li class="nav-item"><a class="nav-link active" href="Index">HOME</a></li>
			<li class="nav-item"><a class="nav-link" href="Logout">ログアウト</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="NewPost">新規スレッド</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="Category"
				aria-disabled="true">カテゴリーで探す</a></li>
			<li class="nav-item">
				<form action="Search">
					<input class="form-control" type="text" placeholder="キーワードで探す"
						name="search_word">
				</form>
			</li>
			<li class="nav-item"><a href="User?id=${userInfo.id}"><button
						class="btn btn-outline-success" type="button">${userInfo.name}
						さん</button></a></li>
		</ul>
	</div>
	<div class="main">

		<c:forEach begin="0" end="0" var="th" items="${thread}">

			<h3 style="margin: 40px">スレッド名:${th.title}</h3>
		</c:forEach>

		<p>カテゴリー</p>
		<c:forEach var="th" items="${thread}">
			<a href="CategoryList?id=${th.categoryId}"> <span
				class="list_line_info_container">${th.categoryName}</span></a>
		</c:forEach>
		<c:forEach var="post" items="${posts}">
			<div class="post">
				<div class="meta"><%!int num = 1;%>
					<span class="number"><%=num%></span> <span class="name">${post.userName}</span>
					<span class="date">${post.formatCreateDate}</span>
					<div class="message">
						<span class="escaped">${post.message}</span>
					</div>
				</div>
			</div>
			<%
				num++;
			%>
		</c:forEach>
		<%
			num = 1;
		%>
		<div class="form-group">
			<label for="exampleFormControlTextarea1">レスを投稿する</label>
			<c:forEach begin="0" end="0" var="th" items="${thread}">

				<form action="Show?id=${th.id}" method="post">
					<textarea class="form-control" placeholder="コメント内容" name="message"></textarea>
					<div class="message_write">
						<input type="hidden" value="${th.title}" name="title">
						<button type="submit" class="btn btn-secondary">書き込む</button>
					</div>
				</form>
			</c:forEach>
		</div>
	</div>
</body>
</html>
