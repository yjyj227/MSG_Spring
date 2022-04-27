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
@import url(css/styles_G.css);
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
							<li class="col-md-3"><a href="G_List.do?g_ref=1">자유</a></li>
							<li class="col-md-3"><a href="G_List.do?g_ref=2">정보</a></li>
							<!-- 제이쿼리 $('li').on('click', function(){$(li).eq(1).attr('class', 'active')})와 같음 -->
							<li class="col-md-3"><a href="G_Rate.do">별점</a></li>
							<li class="col-md-3"><a href="G_List.do?g_ref=4">HOT</a></li>
						</ul>
					</div>


					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/41.jpg" width="240" height="320"></td>
							<td>천공의 성 라퓨타</td>
						</tr>
						<tr>
							<td>★ : ${avg41} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum41}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="G_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="41">
									<c:if test="${article41.v_like!=null}">
									<input type="hidden" name="v_like" value="${article41.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article41.v_star!=null}">
										<input type="number" name="v_star" value="${article41.v_star}" 
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
							
								<c:if test="${article41.v_star!=null}">
								<c:choose>
								<c:when test="${article41.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="41">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article41.v_star!=null}">
										<input type="hidden" name="v_star" value="${article41.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article41.v_like==0 || article41.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="41">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article41.v_star!=null}">
										<input type="hidden" name="v_star" value="${article41.v_star}">
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
							<td>신비한 목걸이가 밝게 빛나면, 구름 너머 전설의 성이 드러난다!
조용한 밤하늘에 한척의 비행선이 떠있다. 꼬리날개에 그려진 해골 마크가 이 배가 해적선이라는 사실을 말해주고 있다. 갑자기 사람들이 뛰어다니면서 기내가 부산스러워진다. 그들이 바라보는 곳에는 그 발밑으로 또다른 거대한 비행선 한척이 떠가고 있었다. 곧 해적선에 작은 비행정 몇 대가 발진한다. 거대한 비행선. 방 안에 한 소녀와 검은 양복에 선글라스의 두 남자가 있다. 한 남자가 소녀에게 식사를 가져오지만, 소녀는 아무말도 없이 고개를 돌려 버린다. 창밖을 바라보던 소녀는 이쪽으로 날아오고 있는 해적들의 비행정을 발견하게 된다. 곧 해적들의 습격이 시작된다. 해적들은 저항하는 선원들을 가볍게 제압하며 배안으로 침입한다.</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/42.jpg" width="240" height="320"></td>
							<td>이웃집 토토로</td>
						</tr>
						<tr>
							<td>★ : ${avg42} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum42}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="G_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="42">
									<c:if test="${article42.v_like!=null}">
									<input type="hidden" name="v_like" value="${article42.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article42.v_star!=null}">
										<input type="number" name="v_star" value="${article42.v_star}" 
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
								
								<c:if test="${article42.v_star!=null}">
								<c:choose>
								<c:when test="${article42.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="42">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article42.v_star!=null}">
										<input type="hidden" name="v_star" value="${article42.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article42.v_like==0 || article42.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="42">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article42.v_star!=null}">
										<input type="hidden" name="v_star" value="${article42.v_star}">
										</c:if>
										<button type="submit" class="btn btn-default btn-xs">좋아요</button>
									</form>
								</c:when>
								</c:choose>
								</c:if>
						</tr>
						</c:if>
						<tr>
							<td>숲속에 살고 있는 특별한 친구를 만났다!
도시를 떠나 시골로 이사 온 ‘사츠키’와 ‘메이’는 우연히 숲속에 살고 있는 신비로운 생명체 ‘토토로’를 만나 신비한 모험을 함께 한다.
 그러던 어느 날, 어머니의 병원에서 위태로운 소식이 도착하고 언니 ‘사츠키’가 정신없이 아빠에게 연락을 취하는 와중에 ‘메이’가 행방불명 되는데…</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/43.jpg" width="240" height="320"></td>
							<td>마녀배달부 키키</td>
						</tr>
						<tr>
							<td>★ : ${avg43} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum43}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="G_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="43">
									<c:if test="${article43.v_like!=null}">
									<input type="hidden" name="v_like" value="${article443.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article43.v_star!=null}">
										<input type="number" name="v_star" value="${article43.v_star}" 
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
								
								<c:if test="${article443.v_star!=null}">
								<c:choose>
								<c:when test="${article43.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="43">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article43.v_star!=null}">
										<input type="hidden" name="v_star" value="${article43.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article43.v_like==0 || article43.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="43">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article43.v_star!=null}">
										<input type="hidden" name="v_star" value="${article43.v_star}">
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
							<td>13살 초보마녀 키키의 아주 특별한 마법 같은 모험!
사랑스러운 초보마녀 ‘키키’는 검은 고양이 ‘지지’와 함께 빗자루를 타고 마녀 수련을 떠난다.
 항구 마을에 불시착한 키키는 첫날부터 우여곡절을 겪지만, ‘배달’에 재능이 있다는 걸 발견하고
 본격적인 마법 수련을 시작하는데…</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/44.jpg" width="240" height="320"></td>
							<td>붉은 돼지</td>
						</tr>
						<tr>
							<td>★ : ${avg44} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum44}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="G_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="44">
									<c:if test="${article44.v_like!=null}">
									<input type="hidden" name="v_like" value="${article44.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article44.v_star!=null}">
										<input type="number" name="v_star" value="${article44.v_star}" 
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
								
								<c:if test="${article44.v_star!=null}">
								<c:choose>
								<c:when test="${article44.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="44">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article44.v_star!=null}">
										<input type="hidden" name="v_star" value="${article44.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article44.v_like==0 || article44.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="44">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article44.v_star!=null}">
										<input type="hidden" name="v_star" value="${article44.v_star}">
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
							<td>낭만을 꿈꾸는 로맨티스트
마르코 파곳(Captain Marco Pagot)는 1차세계 대전 중의 이태리 공군의 에이스 파이롯이었다. 하지만 그가 파시즘의 발호를 목도하고는 공군을 그만두고, 그의 의지대로 자유롭게 날고자 했다. 그는 국경의 헌터가 되었고, 포르코 롯소가 되었다. 그의 젊은 인간의 얼굴은 지나의 레스토랑의 벽에 걸려있는 사진 속에 유일하게 남아 있다. 미야자끼 말로는 포르코는 한때 지나와 결혼하러 했지만, 세계대전이 발발하였고, 지나는 오스트리아령의 섬에 살고 있었다. 공군장교로서 적국의 땅에서 결혼할 수 없었던 거란다. 애국심과 애정 사이에 고민하던 그는 결국 국가를 택했단다. 하지만 그가 그의 동료 파이럿의 죽음 - 지나의 남편을 포함하여 - 을 보자 그의 행동에 회의를 품게 된다. 국가를 위해 죽는 것에 대해. 그의 마음 속에서 불타 오르는 이러한 문제를 해결못하고 그는 돼지가 된 것이다.</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/45.jpg" width="240" height="320"></td>
							<td>센과 치히로의 행방불명</td>
						</tr>
						<tr>
							<td>★ : ${avg45} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum45}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="G_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="45">
									<c:if test="${article45.v_like!=null}">
									<input type="hidden" name="v_like" value="${article45.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article45.v_star!=null}">
										<input type="number" name="v_star" value="${article45.v_star}" 
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
								
								<c:if test="${article45.v_star!=null}">
								<c:choose>
								<c:when test="${article45.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="45">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article45.v_star!=null}">
										<input type="hidden" name="v_star" value="${article45.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article45.v_like==0 || article45.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="45">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article45.v_star!=null}">
										<input type="hidden" name="v_star" value="${article45.v_star}">
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
							<td>금지된 세계의 문이 열렸다!
 
 이사 가던 날, 수상한 터널을 지나자 인간에게는 금지된 신들의 세계로 오게 된 치히로..
 신들의 음식을 먹은 치히로의 부모님은 돼지로 변해버린다.
 
 “걱정마, 내가 꼭 구해줄게…”
 
 겁에 질린 치히로에게 다가온 정체불명의 소년 하쿠.
 그의 따뜻한 말에 힘을 얻은 치히로는 인간 세계로 돌아가기 위해 사상 초유의 미션을 시작하는데…</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/46.jpg" width="240" height="320"></td>
							<td>고양이의 보은</td>
						</tr>
						<tr>
							<td>★ : ${avg46} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum46}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="G_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="46">
									<c:if test="${article46.v_like!=null}">
									<input type="hidden" name="v_like" value="${article46.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article46.v_star!=null}">
										<input type="number" name="v_star" value="${article46.v_star}" 
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
								
								<c:if test="${article46.v_star!=null}">
								<c:choose>
								<c:when test="${article16.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="46">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article46.v_star!=null}">
										<input type="hidden" name="v_star" value="${article46.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article46.v_like==0 || article46.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="46">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article46.v_star!=null}">
										<input type="hidden" name="v_star" value="${article46.v_star}">
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
							<td>17살 평범한 여고생 '하루'. 매일 매일이 따분하기만 하고 즐거운 일이 하나도 없다. 그날은 늦잠 자서 학교도 지각하고, 친구들 앞에서 창피까지 당하고 정말 우울한 날이었다. 그런데 집에 가는 길에 우연히 트럭에 치일 뻔한 고양이를 구해주었는데 그 고양이가 몸을 툴툴 털고 일어나 고맙다고 인사를 하는게 아닌가. 그 순간부터 그녀의 일상은 이상한 일로 변화가 생겼다. '하루'가 구해준 고양이가 고양이 왕국의 '룬' 왕자라는 것이다. 그 날밤 '하루'의 집에 찾아온 고양이 떼는 자신들의 왕자를 구해준 보답으로 '하루'를 '룬'왕자와 결혼해달라는 요청과 함께 앞으로는 행복한 일들만 일어날 것이라는 이상한 말을 남긴 채 사라졌다. 다음 날부터 '하루'는 고양이들이 적극적인 감사인사에 휩싸여 정신없이 보내게 된다. 그러던 중 이상한 목소리에 이끌려 고양이 왕국으로 초대되었다. 늘 깜짝 놀랄 만한 일을 기대해왔던 '하루'에게 진짜 신기한 일이 생긴 것이다.</td>
						</tr>
					</table>
					
					<table class="table">
						<tr>
							<td rowspan="4" width="240" height="320"><img src="./assets/img/poster/47.jpg" width="240" height="320"></td>
							<td>하울의 움직이는 성</td>
						</tr>
						<tr>
							<td>★ : ${avg47} / 5&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;좋아요 ${sum47}</td>
						</tr>
						<c:if test="${!empty mem_id}">
						<tr>
							<td>
								<form role="form" class="rankForm" name="rankForm" method="post" action="G_Rate.do" onsubmit="return writeSave()">
									<input type="hidden" name="v_number" value="${v_number}">
									<input type="hidden" name="mem_id" value="${mem_id}">
									<input type="hidden" name="v_movie" value="47">
									<c:if test="${article47.v_like!=null}">
									<input type="hidden" name="v_like" value="${article47.v_like}">
									</c:if>
									<c:choose>
									<c:when test="${article47.v_star!=null}">
										<input type="number" name="v_star" value="${article47.v_star}" 
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
								
								<c:if test="${article47.v_star!=null}">
								<c:choose>
								<c:when test="${article47.v_like==1}"><!-- 좋아요가 1일 때 -->
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="47">
										<input type="hidden" name="v_like" value="0">
										<c:if test="${article47.v_star!=null}">
										<input type="hidden" name="v_star" value="${article47.v_star}">
										</c:if>
										<button type="submit" class="btn btn-danger btn-xs">좋아요</button>
									</form>
								</c:when>
								<c:when test="${article47.v_like==0 || article47.v_like==null}">
									<form role="form" class="rankForm" name="rankForm2" method="post" action="G_Rate.do" onsubmit="return writeSave()">
										<input type="hidden" name="v_number" value="${v_number}">
										<input type="hidden" name="mem_id" value="${mem_id}">
										<input type="hidden" name="v_movie" value="47">
										<input type="hidden" name="v_like" value="1">
										<c:if test="${article47.v_star!=null}">
										<input type="hidden" name="v_star" value="${article47.v_star}">
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
							<td>소녀가 마법에 걸린 순간, 꽃미남 마법사의 성문이 열렸다!
어느 날, 영문도 모른 채 마녀의 저주로 인해 할머니가 된 소녀 '소피'
 절망 속에서 길을 걷다가 거대한 마법의 성에 들어가게 된다.
 그곳에서 자신과 마법사 하울의 계약을 깨주면 저주를 풀어주겠다는
 불꽃악마 캘시퍼의 제안을 받고 청소부가 되어 ‘움직이는 성’에 머물게 되는데…</td>
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