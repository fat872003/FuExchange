package com.adkp.fuexchange.controller;

import com.adkp.fuexchange.request.LoginRequest;
import com.adkp.fuexchange.request.RegisterRequest;
import com.adkp.fuexchange.request.RegisterStaffRequest;
import com.adkp.fuexchange.request.StaffLoginRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
@Validated
public class AuthenticateController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticateController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Login into website")
    @PostMapping("/login")
    public ResponseObject<Object> loginStudent(@RequestBody LoginRequest loginRequest) {
        if (
                loginRequest.getPassword() != null
                        && loginRequest.getUsername() != null
        ) {
            return authenticationService.login(loginRequest);
        }
        return ResponseObject.builder()

                .status(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.name())
                .content("Vui lòng nhập đầy đủ thông tin")
                .build();
    }

    @Operation(summary = "Register to become user in website")
    @PostMapping("/register")
    public ResponseObject<Object> registerStudent(
            @Valid @RequestBody RegisterRequest registerRequest
    ) {
        return authenticationService.register(registerRequest);
    }


    @Operation(summary = "Check information by registeredStudentId and identity")
    @GetMapping("/check-information")
    public ResponseObject<Object> checkInformationRegister(
            @RequestParam("studentId") String studentId,
            @RequestParam("identity") String identity
    ) {
        return authenticationService.checkInformationRegister(studentId, identity);
    }

    @Operation(summary = "Check registered by studentId")
    @GetMapping("/isRegistered/{studentId}")
    public ResponseObject<Object> IsRegistered(@PathVariable String studentId) {
        return authenticationService.isRegistered(studentId);
    }

    @PostMapping("/staff/login")
    public ResponseObject<Object> loginStaff(@RequestBody @Valid StaffLoginRequest staffLoginRequest) {
        return authenticationService.staffLogin(staffLoginRequest);
    }

    @Operation(summary = "Register staff  in website")
    @PostMapping("/staff/register")
    public ResponseObject<Object> registerStaff(
            @Valid @RequestBody RegisterStaffRequest registerStaffRequest
    ) {
        return authenticationService.staffRegister(registerStaffRequest);
    }

}
