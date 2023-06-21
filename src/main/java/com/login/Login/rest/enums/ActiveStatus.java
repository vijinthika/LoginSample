package com.login.Login.rest.enums;

import lombok.Getter;

@Getter
public enum ActiveStatus {

    VERIFIED("Verified"),
    DEACTIVE("Deactive"),
    NEW("new");
    private String status;
    private ActiveStatus(String status) {
        this.status = status;
    }

}



