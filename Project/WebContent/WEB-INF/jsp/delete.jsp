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
<title>削除確認</title>
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
		<h3 style="margin: 40px">削除確認</h3>
		<c:forEach var="thread" items="${threads}">
			<input type="hidden" value="${thread.id}" name="id">
			<div class="list-line">
				<a class="list_line_link" href="Show?id=${thread.id}">
					<div class="list_line_link_title">${thread.title}</div>
				</a>
				<div class="list_line_info">
					<a href="User?id=${thread.userId}"> <span
						class="list_line_info_container name">${thread.userName}</span></a> <span
						class="list_line_info_container">${thread.formatCreateDate}</span>
				</div>
			</div>
			<div align="center">
				<div class="delete_text">上記のスレッドを本当に削除しますか？</div>
				<div class="delete_button">
					<form action="Delete?id=${thread.id}" method="post">
						<button type="submit" class="btn btn-danger ">削除</button>
					</form>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
