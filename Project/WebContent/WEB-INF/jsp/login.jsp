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
<title>ログイン画面</title>
</head>
<body>
	<div align="center">
		<div class="maine_title">
			<h2>ログイン</h2>
		</div>
		<form action="Index" method="post">
			<div class="form-group row col-4 ">
				<label class="mr-5">ユーザーID</label> <input class="form-control"
					name="userId" type="text">
			</div>
			<div class="form-group row col-4">
				<label class="mr-5">パスワード</label> <input class="form-control"
					name="password" type="password" name="password1">
			</div>
			<button type="submit" class="btn btn-secondary">ログイン</button>
		</form>
	</div>
</body>
</html>