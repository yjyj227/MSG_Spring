package com.board.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.board.harry.domain.HBoardDTO;

//유효성 검사를 하는 방법=>Validator인터페이스를 상속
public class BoardValidator implements Validator {

	//1. 유효성 검사를 할 클래스명을 지정해주는 메서드
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		//형식) return DTO클래스명.class.isAssignableFrom(clazz);
		return HBoardDTO.class.isAssignableFrom(clazz);
	}

	//2. 유효성검사를 해주는 메서드(1. 입력대상자(DTO객체(=커맨드객체))  2. 에러정보를 저장할 에러객체명)
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		//입력하지 않았거나 공백을 체크해주는 메서드->에러정보를 저장(에러객체)
		//1. 에러객체명  2. 적용시킬 필드명  3. 적용시킬 에러코드명
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required"); //required.pwd
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "required");
	}

}
