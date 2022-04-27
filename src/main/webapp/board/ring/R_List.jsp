<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="mem_id" value="${sessionScope.idKey}" />
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
@import url(css/styles_R.css);
</style>
</head>
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
			
			
			
			<div class="col-md-12">
			
			<c:if test="${pgList.count==0}">
				<table class="table table-hover">
					<tr align="center">
						<td>작성된 글이 없습니다.</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${pgList.count > 0}">
				<table class="table table-hover">
				
					<thead>
						<tr align="center">
							<td width="5%">번호</td><td width="14%"></td><td width="51%">제목</td><td width="10%">작성자</td><td width="15%">작성일</td><td width="5%">조회</td>
						</tr>
					</thead>
						
					<c:set var="number2" value="${pgList2.number}" />
					<c:forEach var="notice" items="${noticeList}">
						<tr style="background-color: rgba(211, 174, 60, 0.2);color:rgb(255, 255, 255);">
							<td width="5%" align="center">
							<c:out value="${number2}" />
    						<c:set var="number2" value="${number2-1}" />
    						</td>
    						
    						<td width="14%" align="center" class="categories"></td>
    						
							<td width="51%" align="left">
							<a href="N_Content.do?notice_number=${notice.notice_number}&pageNum=${pgList2.currentPage}">${notice.not_title}</a>
							</td>
							
							<td width="10%" align="center">${notice.admin_id}</td>
							<td width="15%" align="center"><fmt:formatDate value="${notice.not_date}" timeStyle="medium" pattern="yy.MM.dd  HH:mm" /></td>
							<td width="5%" align="center">${notice.not_count}</td>
						</tr>
					</c:forEach>
					
					<c:set var="number" value="${pgList.number}" />
					<c:forEach var="article" items="${articleList}">
						<tr>
							<td width="5%" align="center">
							<c:out value="${number}" />
    						<c:set var="number" value="${number-1}" />
    						</td>
    						
    						<td width="14%" align="center" class="categories">
    							<c:choose>
								<c:when test="${article.r_category==51}">
									<font style="color:gray;">[반지 원정대]</font>
								</c:when>
								<c:when test="${article.r_category==52}">
									<font style="color:gray">[두 개의 탑]</font>
								</c:when>
								<c:when test="${article.r_category==53}">
									<font style="color:gray">[왕의 귀환]</font>
								</c:when>
							</c:choose>
    						</td>
    						
							<td width="51%" align="left">
							
							<c:choose>
								<c:when test="${article.r_ref==1}">
									<font style="color:gray;">[자유]&nbsp;</font>
								</c:when>
								<c:when test="${article.r_ref==2}">
									<font style="color:gray">[정보]&nbsp;</font>
								</c:when>
							</c:choose>
							
							
							<a href="R_Content.do?r_number=${article.r_number}&pageNum=${pgList.currentPage}">${article.r_title}&nbsp;(${article.r_comments})</a>
							
							<%-- <c:if test="${article.h_count>=20}">
							<!-- <img src="img/hot.gif" border="0" height="16"> -->
							
							</c:if> --%>
							
							</td>
							<td width="10%" align="center">${article.r_nickname}</td>
							<td width="15%" align="center"><fmt:formatDate value="${article.r_date}" timeStyle="medium" pattern="yy.MM.dd  HH:mm" /></td>
							<td width="5%" align="center">${article.r_count}</td>
						</tr>
				
				</c:forEach>
				</table>
			</c:if>
			
			</div>
			
			<div class="col-md-12">
				<c:choose>
				<c:when test="${param.r_ref!=null}">
					<div class="col-md-1"><button type="button" class="btn btn-default btn-sm" onclick="location.href='R_List.do'">모든 글</button></div>
				</c:when>
				<c:otherwise>
					<div class="col-md-1"></div>
				</c:otherwise>
				</c:choose>
			<div class="col-md-10">
			<div class="col-md-12" align="center">
			<ul class="pagination">
			
				<c:if test="${pgList.startPage > pgList.blockSize}">
				<li>
				<c:choose>
					<c:when test="${param.r_ref==null}">
					<a href="R_List.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}"> < </a>
					</c:when>
					<c:otherwise>
					<a href="R_List.do?r_ref=${param.r_ref}&pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}"> < </a>
					</c:otherwise>
				</c:choose>
				</li>
				</c:if>
			
				<c:forEach var="i" begin="${pgList.startPage}"	end="${pgList.endPage}">
				<li>
				<c:choose>
					<c:when test="${param.r_ref==null}">
					<a href="R_List.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
					</c:when>
					<c:otherwise>
					<a href="R_List.do?r_ref=${param.r_ref}&pageNum=${i}&search=${search}&searchtext=${searchtext}">
					</c:otherwise>
				</c:choose>
				<c:if test="${pgList.currentPage==i}">
					<font color="red"><b>${i}</b></font>
				</c:if>
				<c:if test="${pgList.currentPage!=i}">
					${i}
				</c:if>
				</a>
				</li>
				</c:forEach>
				<!-- 다음 페이지 -->
				<c:if test="${pgList.endPage < pgList.pageCount}">
				<li>
				<c:choose>
					<c:when test="${param.r_ref==null}">
					<a href="R_List.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}"> > </a>
					</c:when>
					<c:otherwise>
					<a href="R_List.do?r_ref=${param.r_ref}&pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}"> > </a>
					</c:otherwise>
				</c:choose>
				</li>
				</c:if>
			
			</ul>
			</div>
			
			
			
					
					<div class="col-md-12"><!-- 검색기능 -->
					<div class="col-md-offset-2 col-md-8 col-md-offset-2">
						<form action="R_List.do" name="bus">
							<c:if test="${param.r_ref!=null}">
								<input type="hidden" name="r_ref" value="${param.r_ref}">
							</c:if>
							<div class="col-md-3">
							<select class="form-control" name="search">
								<option value="r_all">전체</option>
								<option value="r_title">제목</option>
								<option value="r_body">본문</option>
								<option value="r_nickname">닉네임</option>
							</select>
							</div>
							<div class="col-md-7">
							<input type="text" class="form-control" name="searchtext">
							</div>
							<div class="col-md-2">
							<button type="submit" class="btn btn-default btn-sm">검색</button>
							</div>
						</form>
					</div>
					</div><!-- 검색기능 -->
					
				</div><!-- 10 -->
				<div class="col-md-1"><button type="button" class="btn btn-default btn-sm" onclick="location.href='R_Write.do'">글쓰기</button></div>
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
</html>