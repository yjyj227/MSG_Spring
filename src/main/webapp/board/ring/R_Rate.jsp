<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="mem_id" value="${sessionScope.idKey}" />
<c:set var="mem_nickname" value="${sessionScope.mem_nickname}" />
<c:set var="mem_point" value="${sessionScope.mem_point}" />
<c:set var="mem_grade" value="${sessionScope.mem_grade}" />
<!DOCTYPE html>
<html>
<head>
<%
	pageContext.setAttribute("replaceChar", "\n");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Movie so good</title>
<!-- 부트스트랩 -->

<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon2.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />

<style>
@import url(css/styles2.css);
@import url(css/styles_R.css);

.inline {
	display: inline;
}

.inline-block {
	display: inline-block;
}

.inline-block1 {
	vertical-align: top;
	border: 1px solid red;
}

.block {
	display: block;
}

.btn-xs, .btn-group-xs>.btn {
	padding: 1px 5px;
	font-size: 12px;
	line-height: 1.5;
	border-radius: 3px;
}

.form-control2 {
	width: 8%; height:30px;
	display:inline-block;
}
</style>
<script type="text/javascript">

	var b="a";
	function re(id) {
		var content2=document.getElementById(b);
		content2.style.display="none";
		var a="a"+id;
		var content=document.getElementById(a);
		content.style.display="block";
		b=a;
	}
	
</script>
</head>
<body id="page-top" class="bg-dark">
	<div id="a"></div>
	<div id="b"></div>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="Main.do"><img
				src="assets/img/msg-logo.png" alt="..." /></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars ms-1"></i>
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
	<div id="a"></div>
	<div id="b"></div>
	<section class="page-section bg-dark" id="portfolio">
		<div class="container">
			<a class="topbutton" href="#"><img src="assets/img/topbutton.png"></a>

			<div class="row">
				<div class="col-md-offset-1 col-md-10 col-md-offset-1">
					<!-- 메뉴 바 넣기 -->
					<div class="col-md-12">

						<ul class="nav nav-pills nav-justified">
							<li class="col-md-3"><a href="R_List.do?r_ref=1">자유</a></li>
							<li class="col-md-3"><a href="R_List.do?r_ref=2">정보</a></li>
							<!-- 제이쿼리 $('li').on('click', function(){$(li).eq(1).attr('class', 'active')})와 같음 -->
							<li class="col-md-3"><a href="R_Rate.do">별점</a></li>
							<li class="col-md-3"><a href="R_List.do?r_ref=4">HOT</a></li>
						</ul>
					</div>


					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/51.jpg" width="240" height="320"></td>
							<td>반지의 제왕: 반지 원정대</td>
						</tr>
						<tr>
							<td>★ : ${avg51} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum51}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="R_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="51">
									<c:if test="${article51.v_like!=null}">
									<input type="hidden" name="v_like" value="${article51.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article51.v_star!=null}">
										<input type="number" name="v_star" value="${article51.v_star}" 
												class="form-control form-control2" min="0" max="5" step="0.5" required>&nbsp;&nbsp;
									</c:when>
									<c:otherwise>
										<input type="number" name="v_star" 
												class="form-control form-control2" min="0" max="5" step="0.5" required>&nbsp;&nbsp;
									</c:otherwise>
									</c:choose>
									<button type="submit" class="btn btn-default btn-xs">별점등록</button>
									&nbsp;&nbsp;&nbsp;
								</form>
							
								<c:if test="${article51.v_star!=null}">
								<c:choose>
								<c:when test="${article51.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="R_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="51">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article51.v_star!=null}">
										<input type="hidden" name="v_star" value="${article51.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article51.v_like==0 || article51.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="R_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="51">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article51.v_star!=null}">
										<input type="hidden" name="v_star" value="${article51.v_star}">
										</c:if>
										<button type="submit" class="btn btn-default btn-xs">좋아요</button>
									</form>
								</c:when>
								</c:choose>
								</c:if>
							</td>
						</tr>
						</c:if>
						<tr>
							<td>21세기 가장 위대한 판타지 걸작의 귀환!
모든 힘을 지배할 악의 군주 ‘사우론’의 절대반지가 깨어나고
 악의 세력이 세상을 지배해가며 중간계는 대혼란에 처한다.
 
 호빗 ‘프로도’와 그의 친구들, 엘프 ‘레골라스’, 인간 전사 ‘아라곤’과 ‘보로미르’
 드워프 ‘김리’ 그리고 마법사 ‘간달프’로 구성된 반지원정대는
 평화를 지키기 위해 절대반지를 파괴할 유일한 방법인
 반지가 만들어진 모르도르를 향해 목숨을 건 여정을 떠난다.
 
 한편, 점점 세력을 넓혀온 사우론과의 피할 수 없는 전쟁을 앞둔
 반지원정대는 드디어 거대한 최후의 전쟁을 시작하는데...</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/52.jpg" width="240" height="320"></td>
							<td>반지의 제왕: 두 개의 탑</td>
						</tr>
						<tr>
							<td>★ : ${avg52} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum52}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="R_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="52">
									<c:if test="${article52.v_like!=null}">
									<input type="hidden" name="v_like" value="${article52.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article52.v_star!=null}">
										<input type="number" name="v_star" value="${article52.v_star}" 
												class="form-control form-control2" min="0" max="5" step="0.5" required>&nbsp;&nbsp;
									</c:when>
									<c:otherwise>
										<input type="number" name="v_star" 
												class="form-control form-control2" min="0" max="5" step="0.5" required>&nbsp;&nbsp;
									</c:otherwise>
									</c:choose>
									<button type="submit" class="btn btn-default btn-xs">별점등록</button>
									&nbsp;&nbsp;&nbsp;
								</form>
								
								<c:if test="${article52.v_star!=null}">
								<c:choose>
								<c:when test="${article52.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="R_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="52">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article52.v_star!=null}">
										<input type="hidden" name="v_star" value="${article52.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article52.v_like==0 || article52.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="R_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="52">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article52.v_star!=null}">
										<input type="hidden" name="v_star" value="${article52.v_star}">
										</c:if>
										<button type="submit" class="btn btn-default btn-xs">좋아요</button>
									</form>
								</c:when>
								</c:choose>
								</c:if>
						</tr>
						</c:if>
						<tr>
							<td>21세기 가장 위대한 판타지 걸작의 귀환!
모든 힘을 지배할 악의 군주 ‘사우론’의 절대반지가 깨어나고
 악의 세력이 세상을 지배해가며 중간계는 대혼란에 처한다.
 
 호빗 ‘프로도’와 그의 친구들, 엘프 ‘레골라스’, 인간 전사 ‘아라곤’과 ‘보로미르’
 드워프 ‘김리’ 그리고 마법사 ‘간달프’로 구성된 반지원정대는
 평화를 지키기 위해 절대반지를 파괴할 유일한 방법인
 반지가 만들어진 모르도르를 향해 목숨을 건 여정을 떠난다.
 
 한편, 점점 세력을 넓혀온 사우론과의 피할 수 없는 전쟁을 앞둔
 반지원정대는 드디어 거대한 최후의 전쟁을 시작하는데...</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/53.jpg" width="240" height="320"></td>
							<td>반지의 제왕: 왕의 귀환</td>
						</tr>
						<tr>
							<td>★ : ${avg53} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum53}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="R_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="53">
									<c:if test="${article53.v_like!=null}">
									<input type="hidden" name="v_like" value="${article53.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article53.v_star!=null}">
										<input type="number" name="v_star" value="${article53.v_star}" 
												class="form-control form-control2" min="0" max="5" step="0.5" required>&nbsp;&nbsp;
									</c:when>
									<c:otherwise>
										<input type="number" name="v_star" 
												class="form-control form-control2" min="0" max="5" step="0.5" required>&nbsp;&nbsp;
									</c:otherwise>
									</c:choose>
									<button type="submit" class="btn btn-default btn-xs">별점등록</button>
									&nbsp;&nbsp;&nbsp;
								</form>
								
								<c:if test="${article53.v_star!=null}">
								<c:choose>
								<c:when test="${article53.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="R_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="53">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article53.v_star!=null}">
										<input type="hidden" name="v_star" value="${article53.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${articl13.v_like==0 || article53.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="R_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="53">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article53.v_star!=null}">
										<input type="hidden" name="v_star" value="${article53.v_star}">
										</c:if>
										<button type="submit" class="btn btn-default btn-xs">좋아요</button>
									</form>
								</c:when>
								</c:choose>
								</c:if>
							</td>
						</tr>
						</c:if>
						<tr>
							<td>21세기 가장 위대한 판타지 걸작의 귀환!
모든 힘을 지배할 악의 군주 ‘사우론’의 절대반지가 깨어나고
 악의 세력이 세상을 지배해가며 중간계는 대혼란에 처한다.
 
 호빗 ‘프로도’와 그의 친구들, 엘프 ‘레골라스’, 인간 전사 ‘아라곤’과 ‘보로미르’
 드워프 ‘김리’ 그리고 마법사 ‘간달프’로 구성된 반지원정대는
 평화를 지키기 위해 절대반지를 파괴할 유일한 방법인
 반지가 만들어진 모르도르를 향해 목숨을 건 여정을 떠난다.
 
 한편, 점점 세력을 넓혀온 사우론과의 피할 수 없는 전쟁을 앞둔
 반지원정대는 드디어 거대한 최후의 전쟁을 시작하는데...</td>
						</tr>
					</table>
					
				<!-- class="col-md-offset-1 col-md-10 col-md-offset-1" -->
			</div>
			<!-- row -->
		</div>
		<!-- container -->
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>