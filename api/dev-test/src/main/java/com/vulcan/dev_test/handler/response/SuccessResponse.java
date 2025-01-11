package com.vulcan.dev_test.handler.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class SuccessResponse <T>{
    private String message;
    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}
