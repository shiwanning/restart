package com.restart.Exception;

import com.restart.constant.CauseEnum;

public class BaseException extends Exception {


    public BaseException(CauseEnum causeEnum) {
        super(causeEnum.getDescription());
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
