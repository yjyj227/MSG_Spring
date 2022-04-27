<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="./css/styles.css" rel="stylesheet" />

<style>
@import url(css/styles2.css);
@import url(css/styles_R.css);
</style>
</head>
<c:if test="${mem_id == null}">
	<script>
		history.back();
	</script>
</c:if>

<c:if test="${mem_id != null}">
<body id="page-top" class="bg-dark">
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
            </div>
        </nav>
		
		<header class="Masthead bg-dark"></header>
	
		<section class="page-section bg-dark" id="portfolio">
			<div class="container">
            <a class="topbutton" href="#"><img src="assets/img/topbutton.png"></a>
            
            <div class="row">
			<div class="col-md-offset-1 col-md-10 col-md-offset-1">
			<div class="col-md-12 pagetitle" align="center">글 작성</div>
			<div class="form-group">
				<form role="form" class="form-horizontal" enctype="multipart/form-data" name="rwriteform" method="post" action="R_Write.do" onsubmit="return writeSave()">
					<!-- 입력하지 않고 매개변수로 전달해서 테이블에 저장 -->
					<input type="hidden" name="r_number" value="${r_number}">
					<input type="hidden" name="mem_id" value="${mem_id}">
					<input type="hidden" name="r_nickname" value="${mem_nickname}">
				<table class="table">
				<thead>
				
					<tr>
						<td>
							<div class="form-group refform col-md-1">
								<select class="form-control" name="r_ref">
									<option value="1">자유</option>
									<option value="2">정보</option>
								</select>
							</div>
							
							<div class="form-group cateform col-md-2">
								<select class="form-control" name="r_category">
									<option value="50">시리즈 선택</option>
									<option value="51">반지 원정대</option>
									<option value="52">두 개의 탑</option>
									<option value="53">왕의 귀환</option>
								</select>
							</div>
							
							<div class="col-md-9">
								<input type="text" name="r_title" placeholder="제목을 입력하세요." class="form-control">
							</div>
						</td>
					</tr>
				</thead>
				
					<tr>
						<td>
						<textarea name="r_body" rows="20" placeholder="내용을 입력하세요." class="form-control"></textarea>
						</td>
					</tr>	
					
					<tr>
						<td>
						&nbsp;&nbsp;<input type="file" name="upload" class="btn-xs">
						</td>
					</tr>
					
					<tr>
						<td>
						<div class="col-md-2" align="left">
						<button type="button" class="btn btn-default btn-sm" onclick="location.href='R_List.do'">목록으로</button>
						</div>
						<div class="col-md-8" align="center">
						<button type="submit" class="btn btn-default btn-sm">등록</button>&nbsp;&nbsp;&nbsp;
						<button type="reset" class="btn btn-default btn-sm">다시 작성</button>
						</div>
						<div class="col-md-2"></div>
						</td>
					</tr>
					
				</table>
				</form>
			</div>
			</div><!-- class="col-md-offset-1 col-md-10 col-md-offset-1" -->
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
</c:if>
</html>