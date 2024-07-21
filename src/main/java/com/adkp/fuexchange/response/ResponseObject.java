package com.adkp.fuexchange.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonInclude(JsonInclude.Include.NON_NULL) // Jackson không tuần tự hóa field null
public class ResponseObject<T> {

    private int status;

    private String message;

    private String content;

    private T data;

    private MetaResponse meta;
}
