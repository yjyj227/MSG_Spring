package com.board.util;

public class StringUtil {
	public static String parseBr(String msg){
		
		if(msg == null) return null;
		//문자열을 변경->replace(변경 전 단어, 변경 후 단어)
		return msg.replace("\r\n", "<br>")
                  .replace("\n", "<br>");
	}
}
