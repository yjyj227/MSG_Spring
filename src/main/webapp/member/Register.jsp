<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8" />
<c:set var="mem_id" value="${sessionScope.idKey}" />
<c:set var="mem_passwd" value="${sessionScope.pwKey}" />
<c:set var="mem_nickname" value="${sessionScope.mem_nickname}" />
<c:set var="mem_point" value="${sessionScope.mem_point}" />
<c:set var="mem_grade" value="${sessionScope.mem_grade}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Movie so good</title>
<!-- 부트스트랩 -->

<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon2.ico" />
<!-- Font Awesome icons (free version)-->
<script LANGUAGE="JavaScript" src="./js/script.js?ver=1">
</script>
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="./css/styles.css" rel="stylesheet" />
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
@import url(css/styles2.css);
@import url(css/styles3.css);
</style>
</head>
<body id="page-top" class="bg-dark" onload="regForm.mem_id.focus()">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="Main.do"><img src="assets/img/msg-logo.png" alt="..." /></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <c:if test="${!empty mem_id}">
						<li class="nav-item"><a class="nav-link disabled" href="MyPage.do">
						<c:if test="${mem_id!='admin'}">
						<c:choose>
						<c:when test="${mem_grade==1}"><img src="assets/img/lv/1.png" width="15px" height="15px"></c:when>
						<c:when test="${mem_grade==2}"><img src="assets/img/lv/2.png" width="15px" height="15px"></c:when>
						<c:when test="${mem_grade==3}"><img src="assets/img/lv/3.png" width="15px" height="15px"></c:when>
						<c:when test="${mem_grade==4}"><img src="assets/img/lv/4.png" width="15px" height="15px"></c:when>
						<c:when test="${mem_grade==5}"><img src="assets/img/lv/5.png" width="15px" height="15px"></c:when>
						</c:choose>
						<c:out value="${mem_nickname}" />님 환영합니다.</a></li>
						</c:if>
						<li class="nav-item"><a class="nav-link" href="Logout.do">Logout</a></li>
						<c:choose>
						<c:when test="${mem_id=='admin'}">
						<li class="nav-item"><a class="nav-link" href="AdminPage.do">관리</a></li>
						</c:when>
						<c:when test="${mem_id!='admin'}">
						<li class="nav-item"><a class="nav-link" href="MyPage.do">My Page</a></li>
						</c:when>
						</c:choose>
						</c:if>
						<c:if test="${empty mem_id}">
						<li class="nav-item"><a class="nav-link" href="Login.do">Login</a></li>
						</c:if>
                        <li class="nav-item"><a class="nav-link" href="Main.do#portfolio">게시판</a></li>
                        <c:if test="${empty mem_id}">
						<li class="nav-item"><a class="nav-link" href="Agreement.do">Sign up</a></li>
						</c:if>
                    </ul>
            </div>
        </nav>
		
		<header class="Masthead bg-dark"></header>
	
		<section class="page-section bg-dark" id="portfolio">
			<div class="container">
            <a class="topbutton" href="#"><img src="assets/img/topbutton.png"></a>
            
            <div class="row">
            <div class="col-md-1"></div>
			<div class="col-md-10">
			<div class="col-md-12 pagetitle">회원가입</div>
			<div class="col-md-12" align="center">
				<form role="form" class="form-horizontal" name="regForm" method="post" action="Register.do" align="center">
						<input type="hidden" name="mem_point" value="0">
						<input type="hidden" name="mem_grade" value="1">
						
						<div class="form-group col-md-12" align="center">
							<div class="col-md-12"></div>
							<label for="id" class="col-md-3 control-label" align="right">아이디</label>
							<div class="col-md-6">
								<input type="text" class="form-control" placeholder="아이디" name="mem_id">
							</div>
							<div class="col-md-3" align="left">
								<button type="button" class="btn btn-default" onclick="idCheck(this.form.mem_id.value)">ID 중복확인</button>
							</div>
						</div>

						<div class="form-group col-md-12">
							<label for="password" class="col-md-3 control-label" align="right">비밀번호</label>
							<div class="col-md-6">
								<input type="password" class="form-control" placeholder="비밀번호" name="mem_passwd">
							</div>
						</div>

						<div class="form-group col-md-12">
							<label for="password" class="col-md-3 control-label" align="right">비밀번호 확인</label>
							<div class="col-md-6">
								<input type="password" class="form-control" placeholder="비밀번호 확인" name="mem_repasswd">
							</div>
						</div>
						
						<div class="form-group col-md-12">
							<label for="name" class="col-md-3 control-label" align="right">이름</label>
							<div class="col-md-6">
								<input type="text" class="form-control" placeholder="이름" name="mem_name">
							</div>
						</div>
						
						<div class="form-group col-md-12">
							<label for="name" class="col-md-3 control-label" align="right">닉네임</label>
							<div class="col-md-6">
								<input type="text" class="form-control" placeholder="닉네임" name="mem_nickname">
							</div>
							<div class="col-md-3" align="left">
								<button type="button" class="btn btn-default" onclick="nicknameCheck(this.form.mem_nickname.value)">닉네임 중복확인</button>
							</div>
						</div>
						
						<div class="form-group col-md-12">
							<label for="name" class="col-md-3 control-label" align="right">생일</label>
							<div class="col-md-6">
								<input type="date" class="form-control" name="mem_birth" value="yyyy-mm-dd">
							</div>
						</div>
						
						<div class="form-group col-md-12">
							<label for="name" class="col-md-3 control-label" align="right">전화번호</label>
							<div class="col-md-6">
								<input type="tel" class="form-control" placeholder="전화번호" name="mem_tel">
							</div>
						</div>
						
						<div class="form-group col-md-12">
							<label for="email" class="col-md-3 control-label" align="right">이메일</label>
							<div class="col-md-6">
								<input type="email" class="form-control" placeholder="이메일" name="mem_email">
							</div>
						</div>
						
						<div class="form-group col-md-12">
							<label for="address" class="col-md-3 control-label" align="right">주소</label>
							<div class="col-md-6">
								<input type="text" class="form-control" placeholder="주소" name="mem_addr" id="addr">
							</div>
							<div class="col-md-3" align="left">
								<button type="button" class="btn btn-default" onclick="execDaumPostcode()">주소 찾기</button>
							</div>
						</div>
						
						<div class="form-group genreform col-md-12" align="center">
							<label for="name" class="col-md-3 control-label" align="right">관심장르</label>
							<div class="col-md-6">
								<select class="form-control" name="mem_genre">
									<option value="0">선택</option>
									<option value="해리포터">해리포터</option>
									<option value="짱구">짱구</option>
									<option value="마블">마블</option>
									<option value="지브리">지브리</option>
									<option value="반지의 제왕">반지의 제왕</option>
								</select>
							</div>
						</div>

						<div class="form-group col-md-12" align="center">
							<div class="col-md-12 buttons">
									<button type="submit" class="btn btn-default">회원가입</button>
									<button type="reset" class="btn btn-default">다시쓰기</button>
							</div>
						</div>
					</form>
			
			
			</div>
			</div>
			<div class="col-md-1"></div>
			</div><!-- row -->
     		</div><!-- container -->
		</section>

		<!-- Footer-->
        <footer class="footer py-4 bg-dark">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-4 text-lg-start">Copyright &copy; MSG : Movie So Good 2022</div>
                    <div class="col-lg-4 my-3 my-lg-0">
                        <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-twitter"></i></a>
                        <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-facebook-f"></i></a>
                        <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <div class="col-lg-4 text-lg-end">
                        <a class="link-dark text-decoration-none me-3" href="#!">Privacy Policy</a>
                        <a class="link-dark text-decoration-none" href="#!">Terms of Use</a>
                    </div>
                </div>
            </div>
        </footer>

<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<!-- * *                               SB Forms JS                               * *-->
<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="../js/bootstrap.min.js"></script>
</body>
</html>