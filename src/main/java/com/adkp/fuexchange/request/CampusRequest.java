package com.adkp.fuexchange.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampusRequest {

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String campusName;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String imgUrl;

}
