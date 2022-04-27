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
@import url(css/styles3.css);
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
			<div class="col-md-12 pagetitle" align="center">내가 쓴 글</div>
			
			<div class="col-md-12">
			
			<ul class="nav nav-pills nav-justified">
			<li class="col-md-1"></li>
			<li class="col-md-2"><a href="MyArticles.do?genre=h">해리포터</a></li>
			<li class="col-md-2"><a href="MyArticles.do?genre=z">짱구</a></li>
			<li class="col-md-2"><a href="MyArticles.do?genre=m">마블</a></li>
			<li class="col-md-2"><a href="MyArticles.do?genre=g">지브리</a></li>
			<li class="col-md-2"><a href="MyArticles.do?genre=r">반지의 제왕</a></li>
			<li class="col-md-1"></li>
			</ul>
			</div>
			
			<div class="col-md-12">
			
			</div>
			
			
			
			<div class="col-md-12">
			
			<c:if test="${genre==null}">
				<table class="table table-hover">
					<tr align="center">
						<td>장르를 선택해주세요.</td>
					</tr>
				</table>
			</c:if>
			
			<c:if test="${pgList.count==0 && genre!=null}">
				<table class="table table-hover">
					<tr align="center">
						<td>작성하신 글이 없습니다.</td>
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
						<!-- 공지글 있던 자리 -->
					<c:set var="number" value="${pgList.number}" />
					<c:forEach var="article" items="${articleList}">
						<tr>
							<td width="5%" align="center">
							<c:out value="${number}" />
    						<c:set var="number" value="${number-1}" />
    						</td>
    						
    						<td width="14%" align="center" class="categories">
    							
    							<c:if test="${genre=='h'}">
    							<c:choose>
								<c:when test="${article.h_category==11}">
									<font style="color:gray;">[마법사의 돌]</font>
								</c:when>
								<c:when test="${article.h_category==12}">
									<font style="color:gray">[비밀의 방]</font>
								</c:when>
								<c:when test="${article.h_category==13}">
									<font style="color:gray">[아즈카반의 죄수]</font>
								</c:when>
								<c:when test="${article.h_category==14}">
									<font style="color:gray">[불의 잔]</font>
								</c:when>
								<c:when test="${article.h_category==15}">
									<font style="color:gray">[불사조 기사단]</font>
								</c:when>
								<c:when test="${article.h_category==16}">
									<font style="color:gray">[혼혈 왕자]</font>
								</c:when>
								<c:when test="${article.h_category==17}">
									<font style="color:gray">[죽음의 성물 - 1부]</font>
								</c:when>
								<c:when test="${article.h_category==18}">
									<font style="color:gray">[죽음의 성물 - 2부]</font>
								</c:when>
								</c:choose>
								</c:if>
								
								<c:if test="${genre=='z'}">
								<c:choose>
								<c:when test="${article.z_category==21}">
									<font style="color:gray;">[헨더랜드의 대모험]</font>
								</c:when>
								<c:when test="${article.z_category==22}">
									<font style="color:gray">[암흑마왕 대추적]</font>
								</c:when>
								<c:when test="${article.z_category==23}">
									<font style="color:gray">[전격! 돼지발굽 대작전]</font>
								</c:when>
								</c:choose>
								</c:if>
								
								<c:if test="${genre=='m'}">
								<c:choose>
								<c:when test="${article.m_category==31}">
									<font style="color:gray;">[어벤져스]</font>
								</c:when>
								<c:when test="${article.m_category==32}">
									<font style="color:gray">[아이언맨]</font>
								</c:when>
								<c:when test="${article.m_category==33}">
									<font style="color:gray">[스파이더맨]</font>
								</c:when>
								<c:when test="${article.m_category==34}">
									<font style="color:gray">[블랙펜서]</font>
								</c:when>
								<c:when test="${article.m_category==35}">
									<font style="color:gray">[가디언즈 오브 갤럭시]</font>
								</c:when>
								</c:choose>
								</c:if>
								
								<c:if test="${genre=='g'}">
								<c:choose>
								<c:when test="${article.g_category==41}">
									<font style="color:gray;">[천공의 성 라퓨타]</font>
								</c:when>
								<c:when test="${article.g_category==42}">
									<font style="color:gray">[이웃집 토토로]</font>
								</c:when>
								<c:when test="${article.g_category==43}">
									<font style="color:gray">[마녀배달부 키키]</font>
								</c:when>
								<c:when test="${article.g_category==44}">
									<font style="color:gray">[붉은 돼지]</font>
								</c:when>
								<c:when test="${article.g_category==45}">
									<font style="color:gray">[센과 치히로의 행방불명]</font>
								</c:when>
								<c:when test="${article.g_category==46}">
									<font style="color:gray">[고양이의 보은]</font>
								</c:when>
								<c:when test="${article.g_category==47}">
									<font style="color:gray">[하울의 움직이는 성]</font>
								</c:when>
								</c:choose>
								</c:if>
								
								<c:if test="${genre=='r'}">
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
								</c:if>
								
							
    						</td>
    						
							<td width="51%" align="left">
								<c:if test="${genre=='h'}">
									<c:if test="${article.h_ref==1}">
										<font style="color:gray;">[자유]&nbsp;</font>
									</c:if>
									<c:if test="${article.h_ref==2}">
										<font style="color:gray">[정보]&nbsp;</font>
									</c:if>
								</c:if>
								
								<c:if test="${genre=='z'}">
									<c:if test="${article.z_ref==1}">
										<font style="color:gray;">[자유]&nbsp;</font>
									</c:if>
									<c:if test="${article.z_ref==2}">
										<font style="color:gray">[정보]&nbsp;</font>
									</c:if>
								</c:if>
								
								<c:if test="${genre=='m'}">
									<c:if test="${article.m_ref==1}">
										<font style="color:gray;">[자유]&nbsp;</font>
									</c:if>
									<c:if test="${article.m_ref==2}">
										<font style="color:gray">[정보]&nbsp;</font>
									</c:if>
								</c:if>
								
								<c:if test="${genre=='g'}">
									<c:if test="${article.g_ref==1}">
										<font style="color:gray;">[자유]&nbsp;</font>
									</c:if>
									<c:if test="${article.g_ref==2}">
										<font style="color:gray">[정보]&nbsp;</font>
									</c:if>
								</c:if>
								
								<c:if test="${genre=='r'}">
									<c:if test="${article.r_ref==1}">
										<font style="color:gray;">[자유]&nbsp;</font>
									</c:if>
									<c:if test="${article.r_ref==2}">
										<font style="color:gray">[정보]&nbsp;</font>
									</c:if>
								</c:if>
							
							<c:choose>
								<c:when test="${genre=='h'}">
									<a href="H_Content.do?h_number=${article.h_number}">${article.h_title}&nbsp;(${article.h_comments})</a>
								</c:when>
								<c:when test="${genre=='z'}">
									<a href="Z_Content.do?z_number=${article.z_number}&pageNum=${pgList.currentPage}">${article.z_title}&nbsp;(${article.z_comments})</a>
								</c:when>
								<c:when test="${genre=='m'}">
									<a href="M_Content.do?m_number=${article.m_number}&pageNum=${pgList.currentPage}">${article.m_title}&nbsp;(${article.m_comments})</a>
								</c:when>
								<c:when test="${genre=='g'}">
									<a href="G_Content.do?g_number=${article.g_number}&pageNum=${pgList.currentPage}">${article.g_title}&nbsp;(${article.g_comments})</a>
								</c:when>
								<c:when test="${genre=='r'}">
									<a href="R_Content.do?r_number=${article.r_number}&pageNum=${pgList.currentPage}">${article.r_title}&nbsp;(${article.r_comments})</a>
								</c:when>
							</c:choose>
							
							</td>
							
							<c:if test="${genre=='h'}">
								<td width="10%" align="center">
									${article.h_nickname}
								</td>
								<td width="15%" align="center">
									<fmt:formatDate value="${article.h_date}" timeStyle="medium" pattern="yy.MM.dd  HH:mm" />
								</td>
								<td width="5%" align="center">
									${article.h_count}
								</td>
							</c:if>
							
							<c:if test="${genre=='z'}">
								<td width="10%" align="center">
									${article.z_nickname}
								</td>
								<td width="15%" align="center">
									<fmt:formatDate value="${article.z_date}" timeStyle="medium" pattern="yy.MM.dd  HH:mm" />
								</td>
								<td width="5%" align="center">
									${article.z_count}
								</td>
							</c:if>
							
							<c:if test="${genre=='m'}">
								<td width="10%" align="center">
									${article.m_nickname}
								</td>
								<td width="15%" align="center">
									<fmt:formatDate value="${article.m_date}" timeStyle="medium" pattern="yy.MM.dd  HH:mm" />
								</td>
								<td width="5%" align="center">
									${article.m_count}
								</td>
							</c:if>
							
							<c:if test="${genre=='g'}">
								<td width="10%" align="center">
									${article.g_nickname}
								</td>
								<td width="15%" align="center">
									<fmt:formatDate value="${article.g_date}" timeStyle="medium" pattern="yy.MM.dd  HH:mm" />
								</td>
								<td width="5%" align="center">
									${article.g_count}
								</td>
							</c:if>
							
							<c:if test="${genre=='r'}">
								<td width="10%" align="center">
									${article.r_nickname}
								</td>
								<td width="15%" align="center">
									<fmt:formatDate value="${article.r_date}" timeStyle="medium" pattern="yy.MM.dd  HH:mm" />
								</td>
								<td width="5%" align="center">
									${article.r_count}
								</td>
							</c:if>
						
						</tr>
				
				</c:forEach>
				</table>
			</c:if>
			
			</div>
			
			<div class="col-md-12">
				<%-- <c:choose>
				<c:when test="${genre!=null}">
					<div class="col-md-1"><button type="button" class="btn btn-default btn-sm" onclick="location.href='MyArticles.do'">모든 글</button></div>
				</c:when>
				<c:otherwise>
					<div class="col-md-1"></div>
				</c:otherwise>
				</c:choose> --%>
				<div class="col-md-1"></div>
			<div class="col-md-10">
			<div class="col-md-12" align="center">
			<ul class="pagination">
			
				<c:if test="${pgList.startPage > pgList.blockSize}">
				<li><a href="MyArticles.do?genre=${genre}&mem_id=${mem_id}&pageNum=${pgList.startPage-pgList.blockSize}"> < </a></li>
				</c:if>
			
				<c:forEach var="i" begin="${pgList.startPage}"	end="${pgList.endPage}">
				<li>
					<a href="MyArticles.do?genre=${genre}&mem_id=${mem_id}&pageNum=${i}">
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
				<li><a href="MyArticles.do?genre=${genre}&mem_id=${mem_id}&pageNum=${pgList.startPage+pgList.blockSize}"> > </a></li>
				</c:if>
			
			</ul>
			</div>
			
			
			
					
					<div class="col-md-12"><!-- 검색기능 -->
					<!-- <div class="col-md-offset-2 col-md-8 col-md-offset-2">
						<form action="H_List.do" name="bus">
							<div class="col-md-3">
							<select class="form-control" name="search">
								<option value="h_title">제목</option>
								<option value="h_title_h_body">제목+본문</option>
								<option value="h_nickname">닉네임</option>
							</select>
							</div>
							<div class="col-md-7">
							<input type="text" class="form-control" name="searchtext">
							</div>
							<div class="col-md-2">
							<button type="submit" class="btn btn-default">검색</button>
							</div>
						</form>
					</div> -->
					</div><!-- 검색기능 -->
					
				</div><!-- 10 -->
				<div class="col-md-1"></div>
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