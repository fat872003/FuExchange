package com.adkp.fuexchange.service;

import com.adkp.fuexchange.request.UpdateInformationStaffRequest;
import com.adkp.fuexchange.request.UpdateStaffPasswordRequest;
import com.adkp.fuexchange.response.ResponseObject;

public interface StaffService {
    ResponseObject<Object>viewMoreStaffs(int current,String identityCard);

    ResponseObject<Object>getStaffInforByStaffID(Integer staffID);
    ResponseObject<Object>updateStaffInforByStaffID(UpdateInformationStaffRequest updateInformationStaffRequest);
    ResponseObject<Object>updateStatusByStaffID(int staffId,int active);
    ResponseObject<Object>updatePassword(UpdateStaffPasswordRequest updateStaffPasswordRequest);
    void deleteStaffByStaffID(int staffID);
}
