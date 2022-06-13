package com.charminseok.company.error;

import lombok.Getter;

@Getter
public enum CompanyErrorCode {
    NO_SUCH_COMPANY("E2000000001", "없는 회사입니다."),
    ALREADY_EXISTS_CONTRACT("E2000000002", "해당 날짜에 이미 계약이 있습니다."),
    NOT_EXISTS_CONTRACT("E2000000003", "해당 회사는 계약이 없습니다."),
    EXPIRED_CONTRACT("E2000000004", "계약이 만료되었습니다."),
    NOT_EXISTS_COMPANY_OR_CONTRACT("E2000000005", "회사 또는 계약이 없습니다.");

    private final String code;
    private final String message;

    CompanyErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
