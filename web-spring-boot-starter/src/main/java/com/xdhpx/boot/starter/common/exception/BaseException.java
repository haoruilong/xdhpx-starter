package com.xdhpx.boot.starter.common.exception;

/**
 * @ClassName: BaseException
 * @Description: 自定义异常
 * @author 郝瑞龙
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = -7068557153706428362L;

    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause,
                         boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);

    }


}