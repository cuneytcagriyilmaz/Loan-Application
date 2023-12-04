package com.cagri.loanapplication.response;

public class Meta {
    public int errorCode;
    public String errorDescription;

    public Meta() {
    }

    public Meta(int errorCode) {
        this.errorCode = errorCode;
    }

    public Meta(int errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDescription = errorDesc;
    }
}
