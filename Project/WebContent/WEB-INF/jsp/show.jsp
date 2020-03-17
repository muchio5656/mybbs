<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
      <link href="./assets/css/bbs.css" rel="stylesheet" />
	<title>みんな集まれ</title>
</head>
	<body>
       <div class="nav">
       <ul class="nav nav-pills">
			<li class="nav-item"><a class="nav-link active"
				href="Index">HOME</a></li>

			<li class="nav-item"><a class="nav-link" href="Logout">ログアウト</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="New">新規スレッド</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="Categoy"
				aria-disabled="true">カテゴリーで探す</a></li>
			<li class="nav-item">
				<form action="Search">
					<input class="form-control" type="text" placeholder="キーワードで探す"
						name="search_word">
				</form>
			</li>
		</ul>
        </div>
         <div class="main">

    <h3 style="margin: 40px" >スレッド名：みんな集まれ</h3>
              <a href="category.html">
           <span class="list_line_info_container">野球</span></a>
              <a href="category.html">
           <span class="list_line_info_container">実況</span></a>
              <a href="category.html">
           <span class="list_line_info_container">なんでも</span></a>



             <div class="post">
            <div class="meta">
            <span class="number">1</span>
             <span class="name">俺ちゃん</span>
             <span class="date">2020/03/14(土) 18:42.52.06</span>
             <span class="uid">ID:12345</span>
                 <div class="message">
                     <span class="escaped">まじか</span>
                </div>
                 </div>


             </div>


             <div class="post" >
            <div class="meta">
            <span class="number">1</span>
             <span class="name">俺ちゃん</span>
             <span class="date">2020/03/14(土) 18:42.52.06</span>
             <span class="uid">ID:12345</span>
                 <div class="message">
                     <span class="escaped">まじか</span>
                </div>
                 </div>


             </div>


             <div class="post" >
            <div class="meta">
            <span class="number">1</span>
             <span class="name">俺ちゃん</span>
             <span class="date">2020/03/14(土) 18:42.52.06</span>
             <span class="uid">ID:12345</span>
                 <div class="message">
                     <span class="escaped">まじか</span>
                </div>
                 </div>
             </div>

             <div class="form-group">
    <label for="exampleFormControlTextarea1">レスを投稿する</label>
                 <form action="" method="post">
    <textarea class="form-control"　placeholder="コメント内容"　name="message"></textarea>
                     <div class="message_write">
                         <button type="submit" class="btn btn-secondary">書き込む</button></div>

                     </form>
  </div>




              </div>



	</body>
</html>
