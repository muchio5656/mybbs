<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link href="./assets/css/bbs.css" rel="stylesheet" />
<title>ユーザー新規登録</title>
</head>
<body>
	<div align="center">
		<div class="main">
			<div class="maine_title">
				<h2>ユーザー新規登録</h2>
			</div>
			<c:if test="${errMsg != null}">
				<div align="center" class="alert alert-danger" role="alert">
					${errMsg}</div>
			</c:if>
			<form action="New" method="post">
				<div class="form-group row col-4 ">
					<label class="mr-5">ユーザー名</label> <input class="form-control"
						name="name" value="${name}" type="text">
				</div>
				<div class="form-group row col-4 ">
					<label class="mr-5">メールアドレス</label> <input class="form-control"
						name="email" value="${email}" type="email">
				</div>
				<div class="form-group row col-4">
					<label class="mr-5">パスワード</label> <input class="form-control"
						type="password" name="password">
				</div>
				<div class="form-group row col-4">
					<label class="mr-2">パスワード(確認)</label> <input class="form-control"
						type="password" name="password2">
				</div>
				<button type="submit" class="btn btn-secondary">登録</button>

				<div class="back">
					<a href="Login"><u>戻る</u></a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
