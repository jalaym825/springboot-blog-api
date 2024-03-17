package com.springboot.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse extends HashMap<String, Object> {

    public ApiResponse(String message, boolean success) {
        this.put("message", message);
        this.put("success", success);
    }

    public ApiResponse(String message, boolean success, String fieldName, Object data) {
        this.put("message", message);
        this.put("success", success);
        this.put(fieldName, data);
    }

    public ApiResponse(boolean success, String fieldName, Object data) {
        this.put("success", success);
        this.put(fieldName, data);
    }

    public ApiResponse(boolean success, PageResponse pageResponse) throws IllegalAccessException, NoSuchFieldException {
        this.put("success", success);
        System.out.println(pageResponse);
        Map<String, Object> map = new HashMap<>();

        Class<?> clazz = ((PostsResponse)pageResponse).getClass();
        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(pageResponse);
                map.put(field.getName(), value);
            }
            clazz = clazz.getSuperclass();
        }

        this.putAll(map);
    }

}
