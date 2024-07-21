package com.adkp.fuexchange.service;

import com.adkp.fuexchange.request.LoginRequest;
import com.adkp.fuexchange.request.RegisterRequest;
import com.adkp.fuexchange.request.RegisterStaffRequest;
import com.adkp.fuexchange.request.StaffLoginRequest;
import com.adkp.fuexchange.response.ResponseObject;

public interface AuthenticationService {

    ResponseObject<Object> login(LoginRequest loginRequest);

    ResponseObject<Object> register(RegisterRequest registerRequest);

    ResponseObject<Object> checkInformationRegister(String studentId, String identity);

    ResponseObject<Object> isRegistered(String studentId);

    ResponseObject<Object> staffLogin(StaffLoginRequest staffLoginRequest);

    ResponseObject<Object> staffRegister(RegisterStaffRequest registerStaffRequest);
}
