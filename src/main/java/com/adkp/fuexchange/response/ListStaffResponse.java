package com.adkp.fuexchange.response;

import lombok.Data;

import java.util.List;
@Data
public class ListStaffResponse {
    List<StaffInforResponse> StaffInforResponseList;

    public ListStaffResponse(List<StaffInforResponse> staffInforResponseList) {
        StaffInforResponseList = staffInforResponseList;
    }
}
