package com.svcbackend.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class GenericResponse<T> {
    private boolean success = true;
    private T data;
    private String msg;

    public GenericResponse(boolean success, String message) {
        this.success = success;
        this.msg = message;
    }

    public GenericResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.msg = message;
    }

    public GenericResponse(T data) {
        this.data = data;
    }

}
