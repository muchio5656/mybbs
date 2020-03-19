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
<title>HOME</title>
</head>
<body>
	<div class="nav">
		<ul class="nav nav-pills">
			<li class="nav-item"><a class="nav-link active" href="Index">HOME</a></li>

			<li class="nav-item"><a class="nav-link" href="Logout">ログアウト</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="New">新規スレッド</a></li>
			<li class="nav-item"><a class="nav-link" href="Category"
				aria-disabled="true">カテゴリーで探す</a></li>
			<li class="nav-item">
				<form action="Search">
					<input class="form-control" type="text" placeholder="キーワードで探す"
						name="search_word">
				</form>
			</li>
			<li class="nav-item"><a class="nav-link" href="User"
				aria-disabled="true">${userInfo.name}さん</a></li>
			<li class="nav-item">
		</ul>
	</div>
	<div class="main">

		<h3 style="margin: 40px">新規スレッド作成</h3>

		<form action="NewPost" method="post">
			<div class="form-group row col-4 ">
				<label class="mr-5">スレッドタイトル</label> <input
					name="title" class="form-control thread_title" type="text">
			</div>
			<div class="form-group">
				<label for="exampleFormControlTextarea1">レス</label>
				<textarea class="form-control" placeholder="１つ目のレスを書き込もう" name="message"></textarea>
				<div class="message_write">
					<button type="submit" class="btn btn-secondary">書き込む</button>
				</div>
			</div>
			<div class="check_box_totle">カテゴリーを選択してください</div>
						<c:forEach var="category" items="${categories}">

			<div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" name="category_list" value="${category.id}" >
  <label class="form-check-label" for="inlineRadio1">${category.categoryName}</label>
</div></c:forEach>

		</form>
	</div>
</body>
</html>
