package com.agba.wealth.wrapper.entity.response;

import lombok.Data;

@Data
public class ErrorRes {
    String timestamp;
    Integer status;
    String error;
    String message;
}