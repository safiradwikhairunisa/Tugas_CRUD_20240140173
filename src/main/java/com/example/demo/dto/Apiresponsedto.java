package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apiresponsedto<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> Apiresponsedto<T> success(String message, T data) {
        return new Apiresponsedto<>(true, message, data);
    }

    public static <T> Apiresponsedto<T> error(String message) {
        return new Apiresponsedto<>(false, message, null);
    }
}
