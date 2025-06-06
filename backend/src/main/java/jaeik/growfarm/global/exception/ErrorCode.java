package jaeik.growfarm.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 유저 관련
    NULL_SECURITY_CONTEXT(HttpStatus.UNAUTHORIZED, "유저 인증 정보가 없습니다. 다시 로그인 해주세요"),
    NOT_MATCH_USER(HttpStatus.FORBIDDEN, "시큐리티 콘텍스트의 유저 정보가 DB에 없습니다"),
    NOT_FIND_TOKEN(HttpStatus.FORBIDDEN, "토큰을 찾을 수 없습니다. 다시 로그인 해주세요 "),
    BLACKLIST_USER(HttpStatus.FORBIDDEN, "차단된 회원은 회원가입이 불가능합니다"),
    KAKAO_GET_USERINFO_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "카카오 유저정보 가져오기 실패"),
    LOGOUT_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "로그아웃 실패"),
    WITHDRAW_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "회원탈퇴 실패"),
    NOT_FIND_KAKAO_FRIEND_FARM(HttpStatus.FORBIDDEN, "해당 카카오 친구의 농장을 찾을 수 없습니다."),
    KAKAO_FRIEND_CONSENT_FAIL(HttpStatus.UNAUTHORIZED, "카카오 친구 추가 동의을 해야 합니다."),

    // 게시판 관련
    NOT_FIND_POST(HttpStatus.FORBIDDEN, "해당 게시글이 존재하지 않습니다.");


    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
