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
@import url(css/styles_M.css);

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
							<li class="col-md-3"><a href="M_List.do?m_ref=1">자유</a></li>
							<li class="col-md-3"><a href="M_List.do?m_ref=2">정보</a></li>
							<!-- 제이쿼리 $('li').on('click', function(){$(li).eq(1).attr('class', 'active')})와 같음 -->
							<li class="col-md-3"><a href="M_Rate.do">별점</a></li>
							<li class="col-md-3"><a href="M_List.do?m_ref=4">HOT</a></li>
						</ul>
					</div>


					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/31.jpg" width="240" height="320"></td>
							<td>어벤져스</td>
						</tr>
						<tr>
							<td>★ : ${avg31} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum31}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="M_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="31">
									<c:if test="${article31.v_like!=null}">
									<input type="hidden" name="v_like" value="${article31.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article31.v_star!=null}">
										<input type="number" name="v_star" value="${article31.v_star}" 
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
							
								<c:if test="${article31.v_star!=null}">
								<c:choose>
								<c:when test="${article31.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="M_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="31">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article31.v_star!=null}">
										<input type="hidden" name="v_star" value="${article31.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article31.v_like==0 || article31.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="M_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="31">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article31.v_star!=null}">
										<input type="hidden" name="v_star" value="${article31.v_star}">
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
							<td>ASSEMBLE! 최강의 슈퍼히어로들이 모였다!
지구의 운명을 건 거대한 전쟁이 시작된다!
지구의 안보가 위협당하는 위기의 상황에서 슈퍼히어로들을 불러모아 세상을 구하는, 일명 [어벤져스] 작전.
 에너지원 ‘큐브’를 이용한 적의 등장으로 인류가 위험에 처하자 국제평화유지기구인 쉴드 (S.H.I.E.L.D)의 국장 닉 퓨리(사무엘 L.잭슨)는 [어벤져스] 작전을 위해 전 세계에 흩어져 있던 슈퍼히어로들을 찾아나선다. 아이언맨(로버트 다우니 주니어)부터 토르(크리스 헴스워스), 헐크(마크 러팔로), 캡틴 아메리카(크리스 에반스)는 물론, 쉴드의 요원인 블랙 위도우(스칼렛 요한슨), 호크 아이(제레미 레너)까지, 최고의 슈퍼히어로들이 [어벤져스]의 멤버로 모이게 되지만, 각기 개성이 강한 이들의 만남은 예상치 못한 방향으로 흘러가는데…
 지구의 운명을 건 거대한 전쟁 앞에 [어벤져스] 작전은 성공할 수 있을까?</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/32.jpg" width="240" height="320"></td>
							<td>아이언맨</td>
						</tr>
						<tr>
							<td>★ : ${avg32} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum32}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="M_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="32">
									<c:if test="${article32.v_like!=null}">
									<input type="hidden" name="v_like" value="${article32.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article32.v_star!=null}">
										<input type="number" name="v_star" value="${article32.v_star}" 
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
								
								<c:if test="${article32.v_star!=null}">
								<c:choose>
								<c:when test="${article32.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="M_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="32">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article32.v_star!=null}">
										<input type="hidden" name="v_star" value="${article32.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article32.v_like==0 || article32.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="M_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="32">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article32.v_star!=null}">
										<input type="hidden" name="v_star" value="${article32.v_star}">
										</c:if>
										<button type="submit" class="btn btn-default btn-xs">좋아요</button>
									</form>
								</c:when>
								</c:choose>
								</c:if>
						</tr>
						</c:if>
						<tr>
							<td>하이테크 슈퍼 히어로의 탄생 | 이제 업그레이드는 끝났다
천재적인 두뇌와 재능으로 세계 최강의 무기업체를 이끄는 CEO이자, 타고난 매력으로 셀러브리티 못지않은 화려한 삶을 살아가던 억만장자 토니 스타크. 아프가니스탄에서 자신이 개발한 신무기 발표를 성공리에 마치고 돌아가던 그는 게릴라군의 갑작스런 공격에 의해 가슴에 치명적인 부상을 입고 게릴라군에게 납치된다. 가까스로 목숨을 건진 그에게 게릴라군은 자신들을 위한 강력한 무기를 개발하라며 그를 위협한다. 그러나 그는 게릴라군을 위한 무기 대신, 탈출을 위한 무기가 장착된 철갑수트를 몰래 만드는 데 성공하고, 그의 첫 수트인 ‘Mark1’를 입고 탈출에 성공한다.</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/33.jpg" width="240" height="320"></td>
							<td>스파이더맨</td>
						</tr>
						<tr>
							<td>★ : ${avg33} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum33}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="M_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="33">
									<c:if test="${article33.v_like!=null}">
									<input type="hidden" name="v_like" value="${article33.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article33.v_star!=null}">
										<input type="number" name="v_star" value="${article33.v_star}" 
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
								
								<c:if test="${article33.v_star!=null}">
								<c:choose>
								<c:when test="${article33.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="M_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="33">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article33.v_star!=null}">
										<input type="hidden" name="v_star" value="${article33.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${articl13.v_like==0 || article33.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="M_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="33">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article33.v_star!=null}">
										<input type="hidden" name="v_star" value="${article33.v_star}">
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
							<td>세상의 파워를 지배하는 남자, 기다렸던 그 영웅을 이제야 만난다.
평범하고 내성적인 학생 피터 파커, 그는 우연히 유전자가 조작된 슈퍼거미에 물린다. 그 후, 피터는 손에서 거미줄이 튀어 나오고 벽을 기어 오를 수 있는 거미와 같은 능력을 갖게 된다. 다가오는 위험을 본능적으로 감지하는 초감각과 엄청난 파워까지. 피터는 짝사랑하던 '메리 제인'의 관심을 끌기 위해 멋진 스포츠카를 구입하는데 초능력을 처음 사용한다. 그러다 사랑하는 벤 아저씨의 죽음을 계기로 엄청난 파워에는 그만큼의 책임이 동반된다는 사실을 깨닫는다.</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/34.jpg" width="240" height="320"></td>
							<td>블랙 팬서</td>
						</tr>
						<tr>
							<td>★ : ${avg34} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum34}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="M_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="34">
									<c:if test="${article34.v_like!=null}">
									<input type="hidden" name="v_like" value="${article34.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article34.v_star!=null}">
										<input type="number" name="v_star" value="${article34.v_star}" 
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
								
								<c:if test="${article34.v_star!=null}">
								<c:choose>
								<c:when test="${article34.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="M_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="34">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article34.v_star!=null}">
										<input type="hidden" name="v_star" value="${article34.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article34.v_like==0 || article34.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="M_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="34">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article34.v_star!=null}">
										<input type="hidden" name="v_star" value="${article34.v_star}">
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
							<td>‘시빌 워’ 이후 와칸다의 왕위를 계승한 티찰라(채드윅 보스만)는
 와칸다에만 존재하는 최강 희귀 금속 ‘비브라늄’과
 왕좌를 노리는 숙적들의 음모가 전세계적인 위협으로 번지자
 세상을 구할 히어로 ‘블랙 팬서’로서 피할 수 없는 전쟁에 나서는데…</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/35.jpg" width="240" height="320"></td>
							<td>가디언즈 오브 갤럭시</td>
						</tr>
						<tr>
							<td>★ : ${avg35} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum35}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="M_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="35">
									<c:if test="${article35.v_like!=null}">
									<input type="hidden" name="v_like" value="${article35.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article35.v_star!=null}">
										<input type="number" name="v_star" value="${article35.v_star}" 
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
								
								<c:if test="${article35.v_star!=null}">
								<c:choose>
								<c:when test="${article35.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="M_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="35">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article35.v_star!=null}">
										<input type="hidden" name="v_star" value="${article35.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article35.v_like==0 || article35.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="M_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="35">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article35.v_star!=null}">
										<input type="hidden" name="v_star" value="${article35.v_star}">
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
							<td>자칭 전설의 무법자 스타로드,
 그러나 현실은 우주를 떠도는 그저그런 좀도둑에 불과한 피터 퀼(크리스 프랫).
 뜻하지 않게 갤럭시의 절대악 타노스와 로난의 타겟이 된 그는
 감옥에서 만난 암살자 가모라(조 샐다나), 거구의 파이터 드랙스(데이브 바티스타),
 현상금 사냥꾼 로켓(브래들리 쿠퍼)과 그루트(빈 디젤) 콤비와 불편한 동맹을 맺고 일명 ‘가디언즈 오브 갤럭시’를 결성한다.
 범상치 않은 화려한 과거를 지닌 이들이 과연 120억 명의 운명을 구할 유일한 희망이 될 수 있을까?
 
 흩어지면 무법자, 뭉치면 히어로
 차원이 다른 마블의 새로운 세계를 목격하라!</td>
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
					<a class="link-dark text-decoration-none me-3" href="#!">Privacy
						Policy</a> <a class="link-dark text-decoration-none" href="#!">Terms
						of Use</a>
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