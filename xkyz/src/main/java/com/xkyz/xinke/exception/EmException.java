package com.xkyz.xinke.exception;


import com.xkyz.xinke.enums.ExceptionEnums;

public class EmException extends RuntimeException {

    public EmException(ExceptionEnums exceptionEnums) {
        super(exceptionEnums.getMsg());
    }


}
