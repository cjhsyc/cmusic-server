package com.example.common;

import lombok.Data;

@Data
public class Message {
    private Integer code = 200;
    private Object data;
    private String message;
    private String type;
    private Boolean success;

    public Message() {

    }

    public Message(String type) {
        setType(type);
        setByType(type);
    }

    public Message(String type, String message) {
        setType(type);
        setMessage(message);
        setByType(type);
    }

    public Message(String type, String message, Object data) {
        setType(type);
        setMessage(message);
        setData(data);
        setByType(type);
    }

    private void setByType(String type) {
        switch (type) {
            case "success":
                setSuccess(true);
                break;
            case "warning":
            case "error":
                setSuccess(false);
                break;
            default:
                System.out.println("type设置错误");
                break;
        }
    }
}
