package com.example.validation.dto;

import com.example.validation.constraints.annotation.Blacklist;
import com.example.validation.constraints.annotation.EmailWhitelist;
import com.example.validation.constraints.annotation.Phone010;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;

    @Blacklist(blacklist = {"malware.good","trojan.jjang"}) // 전달해줌
    @NotBlank
    @Size(min = 8, message = "최소 글자 수는 8글자 입니다") // 최소 길이 이상
    private String username; // 이름 비어있지 않도록

    @Email
    // 이메일이 지정된 도메인이 되도록
    // 검증하는 어노테이션 만들기
    @EmailWhitelist
    private String email; // 형식이 이메일이어야 한다

    @NotNull
    @Phone010
    private String phone; // 비어있지 않아야 한다

    @NotNull
    @Min(value = 14, message = "14세 미만은 부모님의 동의가 필요합니다")
    private Integer age; // 빈칸 아니면서, 14세 이상 이어야한다

    @NotNull
    @Future(message = "현재 이후의 시간부터 가능합니다") // 내일 날짜부터 가능
    private LocalDate validUntil;

    @NotNull // null이 아닌지만 검증
    private String notNullString;
    @NotEmpty // 길이가 0이 아닌지만 검증 (String, list 등 사용가능)
    private String notEmptyString;
    @NotBlank // 공백 문자로만 이루어지지 않았는지 공백 체크, 문자열 에서만 검증 가능
    private String notBlankString;

}

/*
{
    "username" : "sunga"
    "email" : "hee123@naver.com"
    "phone" : "010-3214-3214"
}
*/