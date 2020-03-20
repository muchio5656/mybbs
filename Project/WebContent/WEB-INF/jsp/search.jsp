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
<title>ワード検索：【${searchWord}】</title>
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
<c:if test="${errMsg != null}">
				<div align="center" class="alert alert-danger" role="alert">
					${errMsg}</div></c:if>

<c:if test="${errMsg == null}">
		<h3 style="margin: 40px">検索結果：【${searchWord}】</h3>
		<div class="th-serch">スレッド検索結果</div>
		<c:if test="${errMsg1 != null}">
			<div align="center" class="alert alert-danger" role="alert">【${searchWord}】が含まれる
				${errMsg1}</div>
		</c:if>
		<c:forEach var="thread" items="${searchThreads}">
			<div class="list-line serch-list">
				<a class="list_line_link" href="Show?id=${thread.id}">
					<div class="list_line_link_title">${thread.title}</div>
				</a>
				<div class="list_line_info">
					<a href="User?id=${thread.userId}"><span
						class="list_line_info_container name">${thread.userName}</span></a> <span
						class="list_line_info_container">${thread.formatCreateDate}</span>
				</div>
			</div>
		</c:forEach>
		<div class="res-serch">レス検索結果</div>
		<c:if test="${errMsg2 != null}">
			<div align="center" class="alert alert-danger" role="alert">【${searchWord}】が含まれる
				${errMsg2}</div>
		</c:if>
		<c:forEach var="post" items="${searchPosts}">
			<div class="list-line serch-list">
				<a class="list_line_link" href="Show?id=${post.id}">
					<div class="list_line_link_title">${post.title}</div>
				</a>
				<div class="message">
					<span class="escaped">${post.message}</span>
				</div>
				<div class="list_line_info">
					<a href="User?id=${post.userId}"> <span
						class="list_line_info_container name">${post.userName}</span></a> <span
						class="list_line_info_container">${post.formatCreateDate}</span>
				</div>
			</div>
		</c:forEach>
</c:if>
	</div>
</body>
</html>
