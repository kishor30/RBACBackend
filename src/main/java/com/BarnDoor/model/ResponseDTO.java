package com.BarnDoor.model;

import org.springframework.http.HttpStatus;

public class ResponseDTO<T> {

    private HttpStatus responseCode;
    private String responseMessage;
    private T responseObject;

    public ResponseDTO() {}

    public ResponseDTO(HttpStatus code, String msg) {
        this.responseCode = code;
        this.responseMessage = msg;
    }
    public ResponseDTO(HttpStatus code, String msg, T responseObject) {
        this.responseCode = code;
        this.responseMessage = msg;
        this.responseObject = responseObject;
    }
    public HttpStatus getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(HttpStatus responseCode) {
        this.responseCode = responseCode;
    }
    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    public T getResponseObject() {
        return responseObject;
    }
    public void setResponseObject(T responseObject) {
        this.responseObject = responseObject;
    }

    public void setSuccessResponse() {
        this.setResponseCode(HttpStatus.OK);
        this.setResponseMessage("Success");
    }

    public void setInternalServerErrorResponse() {
        this.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
        this.setResponseMessage("Failure");
    }
}
