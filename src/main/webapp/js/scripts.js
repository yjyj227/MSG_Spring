/*!
* Start Bootstrap - Agency v7.0.10 (https://startbootstrap.com/theme/agency)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-agency/blob/master/LICENSE)
*/
//
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Navbar shrink function
    var navbarShrink = function () {
        const navbarCollapsible = document.body.querySelector('#mainNav');
        if (!navbarCollapsible) {
            return;
        }
        if (window.scrollY === 0) {
            navbarCollapsible.classList.remove('navbar-shrink')
        } else {
            navbarCollapsible.classList.add('navbar-shrink')
        }

    };

    // Shrink the navbar 
    navbarShrink();

    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);

    // Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            offset: 74,
        });
    };

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });

});

//------------------------------------

function loginCheck(){
	if(document.login.mem_id.value==""){
		alert("아이디를 입력하세요.");
		document.login.mem_id.focus();
		return; //return false;
	}
	if(document.login.mem_passwd.value==""){
		alert("비밀번호를 입력하세요.");
		document.login.mem_passwd.focus();
		return;
	}
	document.login.submit();
}

//회원가입창으로 전환
function memberReg(){
	document.location="Agreement.jsp"
}

function inputCheck(){
	
	if(document.regForm.mem_id.value==""){
		alert("아이디를 입력하세요.");
		document.regForm.mem_id.focus();
		return;
	}
	if(document.regForm.mem_passwd.value==""){
		alert("비밀번호를 입력하세요.");
		document.regForm.mem_passwd.focus();
		return;
	}
	if(document.regForm.mem_repasswd.value==""){
		alert("비밀번호를 다시 입력하세요");
		document.regForm.mem_repasswd.focus();
		return;
	}
	if(document.regForm.mem_passwd.value != document.regForm.mem_repasswd.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.mem_repasswd.focus();
		return;
	}
	if(document.regForm.mem_name.value==""){
		alert("이름을 입력하세요.");
		document.regForm.mem_name.focus();
		return;
	}
	if(document.regForm.mem_nickname.value==""){
		alert("닉네임을 입력하세요.");
		document.regForm.mem_nickname.focus();
		return;
	}
	if(document.regForm.mem_birth.value==""){
		alert("생일을 입력하세요.");
		document.regForm.mem_birth.focus();
		return;
	}
	if(document.regForm.mem_tel.value==""){
		alert("생일을 입력하세요.");
		document.regForm.mem_tel.focus();
		return;
	}
	if(document.regForm.mem_email.value==""){
		alert("이메일을 입력하세요.");
		document.regForm.mem_email.focus();
		return;
	}
	if(document.regForm.mem_addr.value==""){
		alert("주소를 입력하세요.");
		document.regForm.mem_addr.focus();
		return;
	}
	if(document.regForm.mem_genre.value=="0"){
		alert("관심장르를 선택하세요.");
		document.regForm.mem_genre.focus();
		return;
	}
	
	document.regForm.submit();
}

//중복ID체크 해주는 자바스크립트함수 선언
function idCheck(id){
   if (id=="") {
		alert("아이디를 먼저 입력하세요!");
		document.regForm.mem_id.focus(); //document.폼객체명.입력양식객체명.함수명(~)
	}else { //입력했다면 jsp파일에게 매개변수로 전달->checkId(id)
		url="IdCheck.do?mem_id="+id; //request.getParameter("mem_id");가 null이 나오면 =을 안 쓴 것(전달이 되지 않아서)
		window.open(url, "post", "left=450, top=150, width=300, height=150");
	}
}

//닉네임 중복체크
function nicknameCheck(nickname){
   if (nickname=="") {
		alert("닉네임을 먼저 입력하세요!");
		document.regForm.mem_nickname.focus(); //document.폼객체명.입력양식객체명.함수명(~)
	}else { //입력했다면 jsp파일에게 매개변수로 전달->checkId(id)
		url="NicknameCheck.do?mem_nickname="+nickname; //request.getParameter("mem_id");가 null이 나오면 =을 안 쓴 것(전달이 되지 않아서)
		window.open(url, "post", "left=450, top=150, width=350, height=150");
	}
}

//정말 탈퇴할 건지 묻기
function deleteCheck() {
	const result=confirm("정말 탈퇴하시겠습니까?");
	
	if (result) {
		location.href="DeleteCheckForm.do?mem_id=${mem_id}";
	} else {
		history.back();
	}
}

//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 참고 항목 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if (data.buildingName !== '' && data.apartment === 'Y') {
				extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			}
			// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if (extraRoadAddr !== '') {
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('addr').value = "(" + data.zonecode + ") " + roadAddr + ", " + data.jibunAddress;

			self.close();
		}
	}).open();
}


