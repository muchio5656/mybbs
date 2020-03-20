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
<title>カテゴリー別：野球</title>
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

		<h3 style="margin: 40px">カテゴリー別：野球</h3>

		<div class="list-line">
			<a class="list_line_link" href="show.html">
				<div class="list_line_link_title">みんな集まれ！！！</div>
			</a>
			<div class="list_line_info">
				<span class="list_line_info_container">俺ちゃん</span> <span
					class="list_line_info_container">2020年03月16日 07:51</span> <a
					href="category.html"> <span class="list_line_info_container">野球</span></a>

			</div>



		</div>
		<div class="list-line">
			<a class="list_line_link" href="show.html">
				<div class="list_line_link_title">みんな集まれ！！！</div>
			</a>
			<div class="list_line_info">
				<span class="list_line_info_container">俺ちゃん</span> <span
					class="list_line_info_container">2020年03月16日 07:51</span> <a
					href="category.html"> <span class="list_line_info_container">野球</span></a>


			</div>



		</div>
		<div class="list-line">
			<a class="list_line_link" href="show.html">
				<div class="list_line_link_title">みんな集まれ！！！</div>
			</a>
			<div class="list_line_info">
				<span class="list_line_info_container">俺ちゃん</span> <span
					class="list_line_info_container">2020年03月16日 07:51</span> <a
					href="category.html"> <span class="list_line_info_container">野球</span></a>



			</div>



		</div>


	</div>



</body>
</html>
