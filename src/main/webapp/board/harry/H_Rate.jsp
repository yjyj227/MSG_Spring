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
@import url(css/styles_H.css);

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
							<li class="col-md-3"><a href="H_List.do?h_ref=1">자유</a></li>
							<li class="col-md-3"><a href="H_List.do?h_ref=2">정보</a></li>
							<!-- 제이쿼리 $('li').on('click', function(){$(li).eq(1).attr('class', 'active')})와 같음 -->
							<li class="col-md-3"><a href="H_Rate.do">별점</a></li>
							<li class="col-md-3"><a href="H_List.do?h_ref=4">HOT</a></li>
						</ul>
					</div>


					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/11.jpg" width="240" height="320"></td>
							<td>해리포터와 마법사의 돌</td>
						</tr>
						<tr>
							<td>★ : ${avg11} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum11}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="H_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="11">
									<c:if test="${article11.v_like!=null}">
									<input type="hidden" name="v_like" value="${article11.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article11.v_star!=null}">
										<input type="number" name="v_star" value="${article11.v_star}" 
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
							
								<c:if test="${article11.v_star!=null}">
								<c:choose>
								<c:when test="${article11.v_like==1}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="11">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article11.v_star!=null}">
										<input type="hidden" name="v_star" value="${article11.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article11.v_like==0 || article11.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="11">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article11.v_star!=null}">
										<input type="hidden" name="v_star" value="${article11.v_star}">
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
							<td>해리 포터(다니엘 래드클리프 분)는 위압적인 버논 숙부(리챠드 그리피스 분)와 냉담한 이모 페투니아 (피오나 쇼 분), 욕심 많고 버릇없는 사촌 더즐리(해리 멜링 분) 밑에서 갖은 구박을 견디며 계단 밑 벽장에서 생활한다. 이모네 식구들 역시 해리와의 동거가 불편하기는 마찬가지. 이모 페투니아에겐 해리가 이상한(?) 언니 부부에 관한 기억을 떠올리게 만드는 달갑지 않은 존재다. 11살 생일이 며칠 앞으로 다가왔지만 한번도 생일파티를 치르거나 제대로 된 생일선물을 받아 본 적이 없는 해리로서는 특별히 신날 것도 기대 할 것도 없다. 11살 생일을 며칠 앞둔 어느 날 해리에게 초록색 잉크로 쓰여진 한 통의 편지가 배달된다. 그 편지의 내용은 다름 아닌 해리의 11살 생일을 맞이하여 전설적인“호그와트 마법학교”에서 보낸 입학초대장이었다. 그리고 해리의 생일을 축하하러 온 거인 해그리드는 해리가 모르고 있었던 해리의 진정한 정체를 알려주는데. 그것은 바로 해리가 굉장한 능력을 지닌 마법사라는 것!</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/12.jpg" width="240" height="320"></td>
							<td>해리포터와 비밀의 방</td>
						</tr>
						<tr>
							<td>★ : ${avg12} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum12}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="H_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="12">
									<c:if test="${article12.v_like!=null}">
									<input type="hidden" name="v_like" value="${article12.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article12.v_star!=null}">
										<input type="number" name="v_star" value="${article12.v_star}" 
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
								
								<c:if test="${article12.v_star!=null}">
								<c:choose>
								<c:when test="${article12.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="12">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article12.v_star!=null}">
										<input type="hidden" name="v_star" value="${article12.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article12.v_like==0 || article12.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="12">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article12.v_star!=null}">
										<input type="hidden" name="v_star" value="${article12.v_star}">
										</c:if>
										<button type="submit" class="btn btn-default btn-xs">좋아요</button>
									</form>
								</c:when>
								</c:choose>
								</c:if>
						</tr>
						</c:if>
						<tr>
							<td>해리 포터에겐 이번 여름방학이 별로 즐겁질 못했다. 마법이라면 질색을 하는 페투니아 이모(피오나 쇼 분)와 버논 이모부(리차드 그리피스 분)의 구박도 그렇지만, 무엇보다 속상한 건 단짝이었던 론 위즐리(루퍼트 그린트 분)와 헤르미온느 그레인저(엠마 왓슨 분)가 그 사이 자신을 까맣게 잊었는지 자신의 편지에 답장 한 통 없다는 것.
  그러던 어느날 꼬마 집요정 도비가 해리의 침실에 나타나 뜻밖의 얘기를 한다. 호그와트 마법학교로 돌아가면 무서운 일을 당할 거라는 것. 도비는 해리를 학교에 못 가게 하려고 자신이 여태까지 론과 헤르미온느의 답장을 가로채 왔음을 고백한다. 그러나 도비와 더즐리 가족의 방해에도 불구, 해리는 론과 그의 형제들이 타고 온 하늘을 나는 자동차를 타고 이모집을 탈출, 따뜻한 가족애가 넘치는 론 위즐리의 집으로 간다.</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/13.jpg" width="240" height="320"></td>
							<td>해리포터와 아즈카반의 죄수</td>
						</tr>
						<tr>
							<td>★ : ${avg13} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum13}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="H_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="13">
									<c:if test="${article13.v_like!=null}">
									<input type="hidden" name="v_like" value="${article13.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article13.v_star!=null}">
										<input type="number" name="v_star" value="${article13.v_star}" 
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
								
								<c:if test="${article13.v_star!=null}">
								<c:choose>
								<c:when test="${article13.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="13">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article13.v_star!=null}">
										<input type="hidden" name="v_star" value="${article13.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article13.v_like==0 || article13.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="13">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article13.v_star!=null}">
										<input type="hidden" name="v_star" value="${article13.v_star}">
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
							<td>13세가 된 ‘해리 포터(다니엘 래드클래프)’는 아버지의 험담을 하는 이모부의 누이 마지 아줌마를 거대한 괴물 풍선으로 만들어 버리고 만다. 일반 세상에서 마법 사용이 금지되어 있는 법을 어긴 해리는 마법부의 징계가 두려워 도망을 치다가 만나게 된 마법부 장관은 ‘시리우스 블랙’(게리 올드만)이 아즈카반의 감옥을 탈출해 해리를 찾고 있다는 소식을 전한다. 시리우스 블랙은 어둠의 마왕인 볼드모트 경을 해리의 부모님에게 이끌어 죽음으로 몰고 간 당사자. 설상가상으로 영혼을 빨아들이는 아즈카반의 무시무시한 간수 ‘디멘터’가 호그와트에 머물며 해리를 위협한다.</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/14.jpg" width="240" height="320"></td>
							<td>해리포터와 불의 잔</td>
						</tr>
						<tr>
							<td>★ : ${avg14} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum14}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="H_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="14">
									<c:if test="${article14.v_like!=null}">
									<input type="hidden" name="v_like" value="${article14.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article14.v_star!=null}">
										<input type="number" name="v_star" value="${article14.v_star}" 
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
								
								<c:if test="${article14.v_star!=null}">
								<c:choose>
								<c:when test="${article14.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="14">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article14.v_star!=null}">
										<input type="hidden" name="v_star" value="${article14.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article14.v_like==0 || article14.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="14">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article14.v_star!=null}">
										<input type="hidden" name="v_star" value="${article14.v_star}">
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
							<td>해리 포터 일생일대 최대 난관! 요즘 들어 매일 꾸는 악몽 때문에 이마의 상처에 더욱 통증을 느끼는 해리(다니엘 래드클래프)는 친구 론(루퍼트 그린트)과 헤르미온느(엠마 왓슨)와 함께 퀴디치 월드컵에 참가해 악몽에서 벗어날 수 있게 돼 마냥 기쁘다. 그러나 퀴디치 캠프장 근방 하늘에 불길한 기운, 바로 마왕 볼드모트의 상징인 '어둠의 표식'이 나타난다. 볼드모트가 13년 전에 자취를 감춘 뒤 감히 모습을 드러내지 못했던 그의 추종자 데스 이터들이 그 표식을 불러낸 것이다. 두려움으로 가득 찬 해리는 안전한 호그와트 마법학교로 돌아가고 싶어한다. 덤블도어 교장(마이클 갬본 경)이라면 자신을 지켜줄 수 있을 것이기에…. 최강의 챔피언을 찾아라! 트리위저드 마법경연대회! </td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/15.jpg" width="240" height="320"></td>
							<td>해리포터와 불사조 기사단</td>
						</tr>
						<tr>
							<td>★ : ${avg15} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum15}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="H_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="15">
									<c:if test="${article15.v_like!=null}">
									<input type="hidden" name="v_like" value="${article15.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article15.v_star!=null}">
										<input type="number" name="v_star" value="${article15.v_star}" 
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
								
								<c:if test="${article15.v_star!=null}">
								<c:choose>
								<c:when test="${article15.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="15">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article15.v_star!=null}">
										<input type="hidden" name="v_star" value="${article15.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article15.v_like==0 || article15.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="15">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article15.v_star!=null}">
										<input type="hidden" name="v_star" value="${article15.v_star}">
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
							<td>길고도 지루한 여름 날 호그와트 마법학교 다섯 번째 해를 기다리고 있는 해리포터(다니엘 래드클리프). 이모부 더즐리 식구들과 참고 사는 것도 지겨운데다 친구 론(루퍼트 그린트)과 헤르미온느(엠마 왓슨)에게서는 편지 한 통 오지 않는다. 그러던 중 예상치 못했던 편지 한 장이 도착한다. 그것은 해리가 학교 밖인 리틀 위닝에서 얄미운 사촌 두들리, 즉 머글 앞에서 디멘터들의 공격을 막는 마법을 사용했기 때문에 호그와트 마법학교에서 퇴학 당하게 되었다는 소식이었다. 앞이 캄캄한 해리. 갑자기 어둠의 마법사 오러들이 나타나 해리를 불사조 기사단의 비밀 장소로 데리고 간다.</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/16.jpg" width="240" height="320"></td>
							<td>해리포터와 혼혈 왕자</td>
						</tr>
						<tr>
							<td>★ : ${avg16} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum16}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="H_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="16">
									<c:if test="${article16.v_like!=null}">
									<input type="hidden" name="v_like" value="${article16.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article16.v_star!=null}">
										<input type="number" name="v_star" value="${article16.v_star}" 
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
								
								<c:if test="${article16.v_star!=null}">
								<c:choose>
								<c:when test="${article16.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="16">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article16.v_star!=null}">
										<input type="hidden" name="v_star" value="${article16.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article16.v_like==0 || article16.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="16">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article16.v_star!=null}">
										<input type="hidden" name="v_star" value="${article16.v_star}">
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
							<td>어둠의 세력이 더욱 강력해져 머글 세계와 호그와트까지 위협해온다. 위험한 기운을 감지한 덤블도어 교수는 다가올 전투에 대비하기 위해 해리 포터와 함께 대장정의 길을 나선다. 볼드모트를 물리칠 수 있는 유일한 단서이자 그의 영혼을 나누어 놓은 7개의 호크룩스를 파괴하는 미션을 수행해야만 하는 것! 또한 덤블도어 교수는 호크룩스를 찾는 기억여행에 결정적 도움을 줄 슬러그혼 교수를 호그와트로 초청한다. 한편 학교에서는 계속된 수업과 함께 로맨스의 기운도 무르익는다. 해리는 자신도 모르게 지니에게 점점 끌리게 되고, 새로운 여자 친구가 생긴 론에게 헤르미온느는 묘한 질투심을 느끼는데...</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/17.jpg" width="240" height="320"></td>
							<td>해리포터와 죽음의 성물 - 1부</td>
						</tr>
						<tr>
							<td>★ : ${avg17} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum17}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="H_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="17">
									<c:if test="${article17.v_like!=null}">
									<input type="hidden" name="v_like" value="${article17.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article17.v_star!=null}">
										<input type="number" name="v_star" value="${article17.v_star}" 
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
								
								<c:if test="${article17.v_star!=null}">
								<c:choose>
								<c:when test="${article17.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="17">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article17.v_star!=null}">
										<input type="hidden" name="v_star" value="${article17.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article17.v_like==0 || article17.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="17">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article17.v_star!=null}">
										<input type="hidden" name="v_star" value="${article17.v_star}">
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
							<td>덤블도어 교장의 죽음 이후, 마법부는 죽음을 먹는 자들에게 점령당하고 호그와트는 위기에 빠진다. 이에 해리와 론, 헤르미온느는 볼드모트를 물리칠 수 있는 유일한 단서이자 그의 영혼이 담긴 ‘성물’ 호크룩스를 찾기 위한 위험한 여정에 나선다. 그러나 영혼이 연결되어 있는 볼드모트와 해리. 볼드모트를 파괴하면 해리의 목숨 또한 위태로워질지 모른다! 죽느냐 죽이느냐, 이제 그 마지막 대결은 극한을 향해 치닫는데…</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/18.jpg" width="240" height="320"></td>
							<td>해리포터와 죽음의 성물 - 2부</td>
						</tr>
						<tr>
							<td>★ : ${avg18} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum18}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="H_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="18">
									<c:if test="${article18.v_like!=null}">
									<input type="hidden" name="v_like" value="${article18.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article18.v_star!=null}">
										<input type="number" name="v_star" value="${article18.v_star}" 
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
								
								<c:if test="${article18.v_star!=null}">
								<c:choose>
								<c:when test="${article18.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="18">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article18.v_star!=null}">
										<input type="hidden" name="v_star" value="${article18.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article18.v_like==0 || article18.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="H_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="18">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article18.v_star!=null}">
										<input type="hidden" name="v_star" value="${article18.v_star}">
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
							<td>모든 것을 끝낼 최후의 전투! 판타지의 아름다운 역사가 드디어 마침표를 찍는다! 덤블도어 교장이 남긴 ‘죽음의 성물’의 단서를 쫓던 해리 포터는 볼드모트가 그토록 찾아 다닌 절대적인 힘을 가진 지팡이의 비밀을 통해 드디어 마지막 퍼즐을 완성한다. 볼드모트의 영혼이 담긴 다섯 번째 ‘호크룩스’를 찾기 위해 마법학교 호그와트로 돌아온 해리와 친구들은 그들을 잡으려는 보안마법에 걸려 위기를 맞지만 덤블도어의 동생인 에버포스의 도움으로 벗어난다. 그리고 그에게서 덤블도어와 어둠의 마법사 그린델왈드에 관한 놀라운 과거에 대해 알게 된다.</td>
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