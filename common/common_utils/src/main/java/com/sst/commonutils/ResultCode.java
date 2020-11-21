package com.sst.commonutils;


public enum ResultCode {
    SUCCESS(200),FAIL(204);
    private int value;
    ResultCode(int value){
        this.value = value;
    }
    public int getValue(){return value;}

}
