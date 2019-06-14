package com.restart.Exception;

import com.restart.constant.CauseEnum;
import org.springframework.util.StringUtils;

public class BaseException extends RuntimeException {

    private String errorCode;

    public BaseException(CauseEnum causeEnum) {
        super(causeEnum.getErrorCode());
        this.errorCode = causeEnum.getErrorCode();
    }

    public BaseException(CauseEnum causeEnum, String description) {
        super(StringUtils.isEmpty(description)
                ? causeEnum.getErrorCode()
                : StringUtils.isEmpty(causeEnum.getErrorCode()) ? description : description + "[" + causeEnum.getErrorCode() + "】");
        this.errorCode = causeEnum.getErrorCode();
    }

    public BaseException(CauseEnum causeEnum, Throwable cause) {
        super(causeEnum.getErrorCode(), cause);
        this.errorCode = causeEnum.getErrorCode();
    }

    public String getErrorCode() {
        return errorCode;
    }
}
