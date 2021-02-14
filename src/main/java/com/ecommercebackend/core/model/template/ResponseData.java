package com.ecommercebackend.core.model.template;


import com.ecommercebackend.core.model.model.ErrorMessage;

public class ResponseData<B> {

    private B body;
    private ErrorMessage error;

    public ResponseData() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResponseData(B body, ErrorMessage error) {
        super();
        this.body = body;
        this.error = error;
    }

    public B getBody() {
        return body;
    }

    public void setBody(B body) {
        this.body = body;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }


}
