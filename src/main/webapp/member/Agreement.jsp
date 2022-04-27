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
@import url(css/styles3.css);
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
			
			<div class="col-md-offset-1 col-md-10 col-md-offset-1">
			<div class="col-md-12 pagetitle" align="center">MSG 이용약관</div>
			<div class="col-md-12">
				<textarea rows="20" class="form-control">
					제 1 조 (약관의 적용)
인터넷 서비스(이하"서비스"라 합니다)의 이용에는 기본 이용약관(이하"기본약관"이라 합니다)과 이 약관을 함께 적용합니다

 제 2 조 (용어의 정의)
 이 약관에서 사용하는 용어의 정의는 다음과 같습니다. 
 ① 운영자 : 서비스의 전반적인 관리와 원활한 운영을 위하여 회사에서 선정한 사람 
 ② 해지 : 회원이 서비스 사용 후 이용계약을 해약하는 것 
 ③ 회원 : 회사와 서비스 이용계약을 체결하고 이용자 ID를 부여받은 자
 ④ 회사: 서비스의 제공자

	
제 2 장 서비스 이용계약

 제 3 조 (이용계약의 성립)
 ① 서비스 가입 신청시 본 약관을 읽고 "동의함" 버튼을 누르면 이 약관에 동의하는 것으로 간주됩니다. 
 ② 이용계약은 서비스 이용희망자의 이용약관 동의 후 이용신청에 대하여 회사가 승낙함으로써 성립합니다. 
			
 제 4 조 (계약사항의 변경) 
 회원은 개인정보관리를 통해 언제든지 개인정보를 열람하고 수정할 수 있습니다. 
 회원은 이용신청시 기재한 사항이 변경되었을 경우에는 온라인으로 수정을 해야 하고 미변경으로 인하여 발생되는 문제의 책임은 회원에게 있습니다.


제 3 장  서비스의 이용

 제 5조 (정보의 제공)
 회사는 회원이 서비스 이용 중 필요가 있다고 인정되는 다양한 정보를 공지사항이나 전자우편, SMS 등의 방법으로 회원에게 제공할 수 있습니다.  

 제 6조 (요금 및 유료정보 등)
 회사가 제공하는 서비스는 기본적으로 무료입니다. 단, 별도의 유료정보에 대해서는 해당 정보에 명시된 요금에 대한 지불을 동의하여야 사용이 가능합니다.

 제 7조 (광고게재 및 광고주와의 거래)
 ① 회사가 회원에게 서비스를 제공할 수 있는 서비스 투자기반의 일부는 광고게재를 통한 수익으로부터 나옵니다. 서비스를 이용하고자 하는 자는 서비스 이용시 노출되는 광고게재에 대해 동의하는 것으로 간주됩니다.
 ② 회사는 본 서비스상에 게재되어 있거나 본 서비스를 통한 광고주의 판촉활동에 회원이 참여하거나 교신 또는 거래의 결과로서 발생하는 모든 손실 또는 손해에 대해 책임을 지지 않습니다. 

 제 8조 (서비스 이용시간) 
 ① 서비스의 이용은 회사의 업무상 또는 기술상 특별한 지장이 없는 한 연중무휴 1일 24시간을 원칙으로 합니다. 다만 정기 점검 등의 필요로 회사가 정한 날이나 시간은 그러하지 않습니다.
 ② 회사는 서비스를 일정범위로 분할하여 각 범위별로 이용가능 시간을 별도로 정할 수 있습니다. 이 경우 그 내용을 사전에 공지합니다. 

 제 9조 (서비스 이용 책임) 
 회원은 회사에서 권한 있는 사원이 서명한 명시적인 서면에 구체적으로 허용한 경우를 제외하고는 서비스를 이용하여 상품을 판매하는 영업활동을 할 수 없으며 특히 해킹, 돈벌이 광고, 음란사이트를 통한 상업행위, 상용S/W 및 저작권 자료의 불법배포 등을 할 수 없습니다. 이를 어기고 발생한 영업활동의 결과 및 손실, 관계기관에 의한 구속 등 법적 조치등에 관해서는 회사가 책임을 지지 않습니다. 


제 4 장 계약해지 및 이용제한

 제 10 조 (계약해지 및 이용제한)
 ① 회원이 이용계약을 해지하고자 하는 때에는 회원 본인이 온라인, 우편, 방문 및 전화를 통해 회사에 해지 신청을 하여야 합니다. 
 ② 회원이 다음 각 호의 1에 해당하는 행위를 하였을 경우 사전통지 없이 이용계약을 해지하거나 또는 기간을 정하여 서비스 이용을 중지할 수 있습니다. 
 다만, 이용계약을 해지하는 경우에는 회원에게 이를 통지하고, 이용계약 해지 전에 소명할 기회를 부여합니다.
 가. 타인의 서비스 ID 및 비밀번호를 도용한 경우 
 나. 서비스 운영을 고의로 방해한 경우 
 다. 가입한 이름이 실명이 아닌 경우 
 라. 같은 사용자가 다른 ID로 이중등록을 한 경우 
 마. 공공질서 및 미풍양속에 저해되는 내용을 고의로 유포시킨 경우 
 바. 회원이 국익 또는 사회적 공익을 저해할 목적으로 서비스이용을 계획 또는 실행하는 경우 
 사. 타인의 명예를 손상시키거나 불이익을 주는 행위를 한 경우 
 아. 서비스의 안정적 운영을 방해할 목적으로 다량의 정보를 전송하거나 광고성 정보를 전송하는 경우 
 자. 정보통신설비의 오작동이나 정보 등의 파괴를 유발시키는 컴퓨터 바이러스 프로그램 등을 유포하는 경우 
 차. 회사, 다른 회원 또는 제3자의 지적재산권을 침해하는 경우 
 카. 정보통신윤리위원회 등 외부기관의 시정요구가 있거나 불법선거운동과 관련하여 선거관리위원회의 유권해석을 받은 경우 
 타. 타인의 개인정보, 이용자ID 및 비빌번호를 부정하게 사용하는 경우 
 파. 회사의 서비스 정보를 이용하여 얻은 정보를 회사의 사전 승낙없이 복제 또는 유통시키거나 상업적으로 이용하는 경우 
 하. 회원이 자신의 홈페이지와 게시판에 음란물을 게재하거나 음란사이트 링크하는 경우 
 거. 본 약관 제9조 또는 제10조를 위반한 경우
 너. 기타 서비스 이용계약상의 의무를 위반한 경우
 
제 5 장  손해배상

 제11조 (손해배상의 범위)
 ① 회사가 제공하는 서비스중 무료 서비스의 경우에는 손해배상에 해당되지 않습니다.
 ③ 회사는 그 손해가 천재지변 등 불가항력이거나 이용자의 고의 또는 과실로 인하여 발생한 때에는 손해배상을 하지 아니합니다.

 제12조 (손해배상의 청구)
 ① 손해배상 청구는 회사에 청구사유, 청구금액 및 산출근거를 기재하여 전화 및 서면으로하여야 하며 그 사유가 발생한 날로부터 6개월이 경과한 경우에는 소멸합니다.
 ② 제10조의 서비스 이용정지 사유중 회사 및 타인에게 피해를 주어 피해자의 고발 또는 소송제기로 인하여 손해배상을 청구할 경우 그 사유를 제공한 자는 이에 응하여야 합니다.

 제13조 (면책)
 ① 회사는 천재지변 또는 이에 준하는 불가항력으로 서비스를 제공할 수 없는 경우와 서비스의 효율적 제공을 위한 시스템 개선 공사, 장비 증설 및 상향공사 등 계획공사의 사유로 고객에게 사전 통보한 경우에는 책임을 면합니다.
 ② 자유게시판 서비스의 경우 각 게시판에 게재된 데이터의 보존 책임이 없습니다.
 ③ 회사는 이용자의 귀책사유로 인한 서비스 이용의 장애에 대하여는 책임을 면합니다.
 ④ 회사는 이용자가 서비스를 통해 얻은 정보 또는 자료 등으로 인해 발생한 손익,서비스를 이용하거나 할 것으로부터 발생하거나 기대하는 손익 등에 대하여 책임을 면합니다.
 ⑤ 회사는 이용자가 게시 또는 전송한 자료의 내용에 관하여는 책임을 면합니다.
 ⑥ 약관의 적용은 이용자에 한하며, 제3자로 부터의 어떠한 배상, 클레임 등에 대하여 회사는 책임을 면합니다.
 ⑦ 회사는 자유게시판 서비스를 무료로 사용하는 이용자들에게 회사의 귀책 사유로 서비스를 제공하지 못하는 경우 책임을 면합니다.
 ⑧ 자유게시판 서비스와 같이 이용자 자신이 서비스를 개설하고 회원을 모집하여 관리하는 경우 개설한 이용자와 해당 서비스에 회원으로 가입한 이용자간의 각종 법규위반 및 금지 행위로 인한 문제에 대한 책임은 이용자간에 있으며 회사는 책임을 면합니다.

 제34조 (관할법원)
 요금 등 서비스 이용으로 발생한 분쟁에 대해 소송이 제기될 경우 회사의 본사 소재지 법원을 관할법원으로 합니다.
				
				</textarea>
			</div>
			<div class="col-md-12" align="center">
				<button type="button" class="btn btn-default" onclick="document.location='Register.do'">동의합니다.</button>
				<button type="button" class="btn btn-default" onclick="history.back()">동의하지 않습니다.</button>
			</div>
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