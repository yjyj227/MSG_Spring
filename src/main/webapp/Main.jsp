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
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<style>
@import url(css/styles2.css);
</style>
</head>
<body id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="Main.do"><img src="assets/img/msg-logo.png" alt="..." /></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
        		aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        	Menu<i class="fas fa-bars ms-1"></i>
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
					<c:out value="${mem_nickname}" />님 환영합니다.</a>
				</li>
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

<!-- Masthead-->
<header class="Masthead bg-dark">
	<div class="container">
        <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" aria-label="Slide 1"
																				   class="active" aria-current="true"></button>
			<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
			<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3" aria-label="Slide 4"></button>
			<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="4" aria-label="Slide 5"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="./assets/img/harrymain.png">
				<div class="carousel-caption"><h5>해리포터</h5></div>
			</div>
			<div class="carousel-item">
				<img src="./assets/img/zzangmain.png">
				<div class="carousel-caption"><h5>짱구는 못말려</h5></div>
			</div>
			<div class="carousel-item">
				<img src="./assets/img/marvelmain.png">
				<div class="carousel-caption"><h5>마블</h5></div>
			</div>
			<div class="carousel-item">
				<img src="./assets/img/ghiblimain.png">
				<div class="carousel-caption"><h5>지브리</h5></div>
			</div>
			<div class="carousel-item">
				<img src="./assets/img/ringmain.png">
				<div class="carousel-caption"><h5>반지의제왕</h5></div>
			</div>
		</div>
		<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span>
			<span class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span>
			<span class="visually-hidden">Next</span>
		</button>
		</div>
	</div>
</header>
      
<!-- Portfolio Grid-->
<section class="page-section bg-dark" id="portfolio">
<div class="container">
<a class="topbutton" href="#"><img src="assets/img/topbutton.png"></a>
<div class="text-center">
	<h2 class="section-heading text-uppercase"></h2>
	<h3 class="section-subheading text-muted">대한민국 1위 영화 커뮤니티!</h3>
</div>

<div class="row">
	<div class="col-lg-4 col-sm-6 mb-4">
		<!-- Portfolio item 1-->
		<div class="portfolio-item">
			<a class="portfolio-link"  href="H_List.do">
				<div class="portfolio-hover">
					<div class="portfolio-hover-content"><i class="fas fa-bars fa-3x"></i></div>
				</div>
				<img class="img-fluid" src="assets/img/portfolio/harry2.png" alt="..." />
			</a>
			<div class="portfolio-caption bg-dark">
				<div class="portfolio-caption-heading">해리포터</div>
				<div class="portfolio-caption-subheading text-muted"></div>
			</div>
		</div>
	</div>

	<div class="col-lg-4 col-sm-6 mb-4">
		<!-- Portfolio item 2-->
		<div class="portfolio-item">
			<a class="portfolio-link"  href="Z_List.do">
				<div class="portfolio-hover">
					<div class="portfolio-hover-content"><i class="fas fa-bars fa-3x"></i></div>
				</div>
				<img class="img-fluid" src="assets/img/portfolio/zzang.png" alt="..." />
			</a>
			<div class="portfolio-caption bg-dark">
				<div class="portfolio-caption-heading">짱구</div>
				<div class="portfolio-caption-subheading text-muted"></div>
			</div>
		</div>
	</div>

    <div class="col-lg-4 col-sm-6 mb-4">
    	<!-- Portfolio item 3-->
        <div class="portfolio-item">
            <a class="portfolio-link"  href="M_List.do">
                <div class="portfolio-hover">
                    <div class="portfolio-hover-content"><i class="fas fa-bars fa-3x"></i></div>
                </div>
                <img class="img-fluid" src="assets/img/portfolio/marvel.png" alt="..." />
            </a>
            <div class="portfolio-caption bg-dark">
            	<div class="portfolio-caption-heading">마블</div>
                <div class="portfolio-caption-subheading text-muted"></div>
            </div>
        </div>
	</div>
	
    <div class="col-lg-4 col-sm-6 mb-4 mb-lg-0">
    	<!-- Portfolio item 4-->
        <div class="portfolio-item">
        	<a class="portfolio-link"  href="G_List.do">
            	<div class="portfolio-hover">
                	<div class="portfolio-hover-content"><i class="fas fa-bars fa-3x"></i></div>
                </div>
                <img class="img-fluid" src="assets/img/portfolio/ghibli.png" alt="..." />
            </a>
            <div class="portfolio-caption bg-dark">
            	<div class="portfolio-caption-heading">지브리</div>
            	<div class="portfolio-caption-subheading text-muted"></div>
            </div>
		</div>
	</div>
	
	<div class="col-lg-4 col-sm-6 mb-4 mb-sm-0">
		<!-- Portfolio item 5-->
		<div class="portfolio-item">
			<a class="portfolio-link"  href="R_List.do">
				<div class="portfolio-hover">
					<div class="portfolio-hover-content"><i class="fas fa-bars fa-3x"></i></div>
				</div>
				<img class="img-fluid" src="assets/img/portfolio/ring.png" alt="..." />
			</a>
			<div class="portfolio-caption bg-dark">
				<div class="portfolio-caption-heading">반지의제왕</div>
				<div class="portfolio-caption-subheading text-muted"></div>
			</div>
		</div>
	</div>
	
	<div class="col-lg-4 col-sm-6">
		<!-- Portfolio item 6-->
		<div class="portfolio-item">
			<a class="portfolio-link"  href="N_List.do">
				<div class="portfolio-hover">
					<div class="portfolio-hover-content"><i class="fas fa-bars fa-3x"></i></div>
				</div>
				<img class="img-fluid" src="assets/img/portfolio/notice.png" alt="..." />
			</a>
			<div class="portfolio-caption bg-dark">
				<div class="portfolio-caption-heading">공지사항</div>
				<div class="portfolio-caption-subheading text-muted"></div>
			</div>
		</div>
	</div>
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