package com.ecommercebackend.core.exception;

public class ValidatorException extends Throwable {
    private String key;
    private String value;
    private String language;

    public ValidatorException(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public ValidatorException(String key, String value, String language) {
        this.key = key;
        this.value = value;
        this.language = language;
    }


    public ValidatorException(String key, Throwable throwable) {
        super(throwable);
        this.key = key;
    }

    public ValidatorException(String key, String value, String language,Throwable throwable) {
        super(throwable);
        this.key = key;
        this.value = value;
        this.language = language;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
