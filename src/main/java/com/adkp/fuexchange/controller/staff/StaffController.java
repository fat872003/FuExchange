package com.adkp.fuexchange.controller.staff;


import com.adkp.fuexchange.request.UpdateInformationStaffRequest;
import com.adkp.fuexchange.request.UpdateStaffPasswordRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
@Tag(name = "Staff")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }
    @Operation(summary = "Get all Moderator staff ")
    @GetMapping("/{current}")
    public ResponseObject<Object>viewAllStaffs(
            @PathVariable("current") int current,
            @RequestParam(value = "identityCard", required = false) String identityCard
    ){
        return  staffService.viewMoreStaffs(current,identityCard);
    }

    @Operation(summary = "Get detail staff by staffID")
    @GetMapping("detail/{staffId}")
    public ResponseObject<Object> viewDetailProductByProductId(
            @PathVariable("staffId") Integer staffId
    ) {
           return staffService.getStaffInforByStaffID(staffId);
    }
    @Operation(summary = "Update staff information")
    @PutMapping("/{staffId}/update-staff")
    public ResponseObject<Object> updateStudentById(
            @Valid @RequestBody UpdateInformationStaffRequest updateInformationStaffRequest
    ) {
        return staffService.updateStaffInforByStaffID(updateInformationStaffRequest);
    }
    @Operation(summary = "Update staff status")
    @PutMapping("/{staffId}/update-status")
    public ResponseObject<Object> updateActiveById(
            @PathVariable("staffId") int staffID,
           @RequestParam("active") int active
    ) {
        return staffService.updateStatusByStaffID(staffID,active);
    }
    @Operation(summary = "Update password")
    @PutMapping("/update-password")
    public ResponseObject<Object> updatePassword(
            @Valid @RequestBody UpdateStaffPasswordRequest updateStaffPasswordRequest
    ) {
        return staffService.updatePassword(updateStaffPasswordRequest);
    }

    @DeleteMapping("/{staffID}")
    public ResponseObject<Object> deleteStudentById(
            @PathVariable("staffID") int staffID
    ) {
        staffService.deleteStaffByStaffID(staffID);
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xóa thành công")
                .build();
    }

}
