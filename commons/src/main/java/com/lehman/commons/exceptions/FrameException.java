package com.lehman.commons.exceptions;


import com.spring.jdbc.assistants.enums.IEnum;


public class FrameException extends RuntimeException {

    /**
     * Exception code
     */
    protected String resultCode = "UN_KNOWN_EXCEPTION";

    /**
     * Exception message
     */
    protected String resultMsg = "未知异常";

    /**
     * Constructor
     */
    public FrameException() {
        super();
    }

    /**
     * Instantiates a new DexcoderException.
     *
     * @param e the e
     */
    public FrameException( IEnum e ) {
        super( e.getDesc() );
        this.resultCode = e.getCode();
        this.resultMsg = e.getDesc();
    }

    /**
     * Instantiates a new DexcoderException.
     *
     * @param e the e
     */
    public FrameException( Throwable e ) {
        super( e );
        this.resultMsg = e.getMessage();
    }

    /**
     * Constructor
     *
     * @param message the message
     */
    public FrameException( String message ) {
        super( message );
        this.resultMsg = message;
    }

    /**
     * Constructor
     *
     * @param code    the code
     * @param message the message
     */
    public FrameException( String code, String message ) {
        super( message );
        this.resultCode = code;
        this.resultMsg = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode( String resultCode ) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg( String resultMsg ) {
        this.resultMsg = resultMsg;
    }
}
