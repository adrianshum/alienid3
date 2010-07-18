package com.alien.id3lib.exception;

public class CharsetNotSupportedException extends RuntimeException {
    private static final long serialVersionUID = 10121331297846414L;
    public CharsetNotSupportedException(String charsetName, Throwable cause) {
        super("Charset "+ charsetName + " not supported", cause);
    }

    public CharsetNotSupportedException(String charsetName) {
        this(charsetName, null);
    }
    
}
