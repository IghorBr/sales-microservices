package com.microservices.delivery.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class StandardError implements Serializable {

    @Getter @Setter
    private Integer status;

    @Getter @Setter
    private String msg;

    @Getter @Setter
    private String timestamp;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public StandardError(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.timestamp = sdf.format(System.currentTimeMillis());
    }
}
