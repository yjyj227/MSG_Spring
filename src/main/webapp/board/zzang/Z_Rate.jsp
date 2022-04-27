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
@import url(css/styles_Z.css);

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
							<li class="col-md-3"><a href="Z_List.do?z_ref=1">자유</a></li>
							<li class="col-md-3"><a href="Z_List.do?z_ref=2">정보</a></li>
							<!-- 제이쿼리 $('li').on('click', function(){$(li).eq(1).attr('class', 'active')})와 같음 -->
							<li class="col-md-3"><a href="Z_Rate.do">별점</a></li>
							<li class="col-md-3"><a href="Z_List.do?z_ref=4">HOT</a></li>
						</ul>
					</div>


					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/21.jpg" width="240" height="320"></td>
							<td>영화 크레용 신짱: 헨더랜드의 대모험</td>
						</tr>
						<tr>
							<td>★ : ${avg21} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum21}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="Z_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="21">
									<c:if test="${article21.v_like!=null}">
									<input type="hidden" name="v_like" value="${article21.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article21.v_star!=null}">
										<input type="number" name="v_star" value="${article21.v_star}" 
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
							
								<c:if test="${article21.v_star!=null}">
								<c:choose>
								<c:when test="${article21.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="Z_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="21">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article21.v_star!=null}">
										<input type="hidden" name="v_star" value="${article21.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article21.v_like==0 || article21.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="Z_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="21">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article21.v_star!=null}">
										<input type="hidden" name="v_star" value="${article21.v_star}">
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
							<td>어느날 일본에 유원지 '헨더랜드' 가 설립되고, 신 짱은 유치원에서 그곳으로 놀러간다.
하지만 신 짱은, 그곳에서 말하는 여자 인형 '토페마 마페트' 를 만나고, 헨더랜드는 중성마녀라는 자들이 세운 지구 정복을 위한 유원지를 가장한 기지이며, 신 짱이 마법의 '스게이나 스고이데스 트럼프' 를 사용해 그들을 물리쳐달라고 한다. 신 짱은 무섭다고 거절하지만, 트럼프는 신 짱의 손에 들어와서 이미 신 짱은 중성마녀들의 표적이 되어 엄마 미사에와 아빠 히로시는 헨더랜드로 잡혀가고, 신 짱은 토페마, 그리고 트럼프로 불러낸 액션가면, 칸탐로봇, 부리부리자에몽과 함께 헨더랜드로 쳐들어가 중성마녀들을 무찌르기 위해 떠난다.</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/22.jpg" width="240" height="320"></td>
							<td>영화 크레용 신짱: 암흑 마왕 대추적</td>
						</tr>
						<tr>
							<td>★ : ${avg22} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum22}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="Z_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="22">
									<c:if test="${article22.v_like!=null}">
									<input type="hidden" name="v_like" value="${article22.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article22.v_star!=null}">
										<input type="number" name="v_star" value="${article22.v_star}" 
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
								
								<c:if test="${article22.v_star!=null}">
								<c:choose>
								<c:when test="${article22.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="Z_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="22">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article22.v_star!=null}">
										<input type="hidden" name="v_star" value="${article22.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article22.v_like==0 || article22.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="Z_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="22">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article22.v_star!=null}">
										<input type="hidden" name="v_star" value="${article22.v_star}">
										</c:if>
										<button type="submit" class="btn btn-default btn-xs">좋아요</button>
									</form>
								</c:when>
								</c:choose>
								</c:if>
						</tr>
						</c:if>
						<tr>
							<td>옛날에, 타마유랑족과 타마요미즈족이라는 종족이 악마 쟈크를 토기와 두개의 구슬에 봉인했다. 1997년 현재, 타마요미즈족의 후손들은 쟈크를 부활시켜 세계를 정복하고자 한다. 그런데 신 짱이 강가에서 그 구슬을 줍고, 신 짱의 여동생 히마와리(코오로기 사토미 목소리 분)가 그 구슬을 삼켜버려, 타마유랑족의 후손 세 형제가 노하라 일가 전원을 데리고 도망친다. 그러나 타마요미즈족의 보스 타마오 나카무레, 그리고 사람의 마음을 읽을 수 있는 타마요미즈족의 외국의 초능력자 후손 헥슨은 히마와리를 납치해 버리고, 타마유랑족 , 히가시 마츠야마 요네 형사, 타마요미즈족에서 빠져나온 프로레슬러 사타케와 노하라 일가는, 히마와리를 구하고 쟈크의 부활을 저지하기 위해 떠난다.</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/23.jpg" width="240" height="320"></td>
							<td>영화 크레용 신짱: 전격! 돼지발굽 대작전</td>
						</tr>
						<tr>
							<td>★ : ${avg23} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum23}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="Z_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="23">
									<c:if test="${article23.v_like!=null}">
									<input type="hidden" name="v_like" value="${article23.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article23.v_star!=null}">
										<input type="number" name="v_star" value="${article23.v_star}" 
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
								
								<c:if test="${article23.v_star!=null}">
								<c:choose>
								<c:when test="${article23.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="Z_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="23">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article23.v_star!=null}">
										<input type="hidden" name="v_star" value="${article23.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article23.v_like==0 || article23.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="Z_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="23">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article23.v_star!=null}">
										<input type="hidden" name="v_star" value="${article23.v_star}">
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
							<td>전대미문의 바이러스 부리부리 꿀꿀로 전 세계 컴퓨터를 장악하여 세계를 정복하려는 악의 집단 돼지 발굽을 막기 위해 세계 평화를 수호하는 비밀 조직 SML은 암호명 슈퍼 모델을 보내 바이러스 디스크를 찾아오게 한다. 무사히 임무를 완수한 슈퍼 모델은 돼지 발굽의 비행선에서 탈출해 한강으로 떨어지고, 마침 유람선을 타고 소풍을 나온 짱구와 친구들을 만나게 된다. 그러나 짱구와 친구들, 그리고 슈퍼 모델은 디스크가 도난 당한 사실을 알고 추적해 오던 돼지 발굽에게 납치된다. 사고 소식을 들은 슬퍼하던 짱구 아빠와 엄마는 SML의 비밀요원 근육맨의 방문을 받는다. 근육맨은 자신이 아이들을 구출해 오겠다고 하지만 짱구를 직접 찾겠다고 결심한 아빠, 엄마는 근육맨에게 변비약까지 먹이며 소동을 벌인 끝에 근육맨의 비밀 서류를 손에 넣고, 짱구를 찾아 홍콩에 있는 돼지 발굽의 배에 잠입한다. 한편 슈퍼모델은 아이들을 캡슐에 태워 탈출시키는 데 성공하지만 황량한 사막에 떨어진 아이들은 마을을 찾아 헤매다 또다시 돼지 발굽에게 잡히게 된다. 결국 위기에 처한 아이들을 구하기 위해 슈퍼 모델은 돼지 발굽에게 디스켓을 넘겨주는데.</td>
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