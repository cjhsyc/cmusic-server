package com.example.common;

import lombok.Data;

@Data
public class Message {
    private Integer code;
    private Object data;

    public Message() {

    }

    public Message(Integer code) {
        this.code = code;
    }

    public Message(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }
}
