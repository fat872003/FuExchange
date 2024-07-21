package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.PostProductDTO;
import com.adkp.fuexchange.dto.StaffDTO;

import com.adkp.fuexchange.pojo.Roles;
import com.adkp.fuexchange.pojo.Staff;
import com.adkp.fuexchange.repository.RoleRepository;
import com.adkp.fuexchange.repository.StaffRepository;
import com.adkp.fuexchange.request.UpdateInformationStaffRequest;
import com.adkp.fuexchange.request.UpdateStaffPasswordRequest;
import com.adkp.fuexchange.response.ListStaffResponse;
import com.adkp.fuexchange.response.MetaResponse;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.response.StaffInforResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository, PasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;

        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public ResponseObject<Object> viewMoreStaffs(int current,String identityCard) {

        Pageable currentStaff = PageRequest.of(0, current);
        String identityCardNum = Optional.ofNullable(identityCard).map(String::valueOf).orElse("");
        List<Staff> staffList = staffRepository.topStaffs(currentStaff,identityCardNum);
        List<StaffInforResponse> staffInforResponse = new ArrayList<>();
        for (Staff staff :staffList){
            staffInforResponse.add(new StaffInforResponse(staff.getStaffId(),staff.getRoleId(),staff.getFirstName(),staff.getLastName()
                    ,staff.getGender(),staff.getIdentityCard(),staff.getAddress(),staff.getPhoneNumber(),staff.getDob(),staff.isActive()));
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thêm thành công!")
                .data(new ListStaffResponse(staffInforResponse))
                .meta(
                        MetaResponse.builder()
                                .total(countStaff(identityCardNum, staffInforResponse))
                                .current(current)
                                .build()
                )
                .build();
    }



    @Override
    public ResponseObject<Object> getStaffInforByStaffID(Integer staffID) {
        Staff staff =  staffRepository.getReferenceById(staffID);
        return ResponseObject.builder().status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Đã tìm thấy thông tin nhân viên!")
                .data(new StaffInforResponse(staff.getStaffId(),staff.getRoleId(),staff.getFirstName(),staff.getLastName()
                        ,staff.getGender(),staff.getIdentityCard(),staff.getAddress(),staff.getPhoneNumber(),staff.getDob(),staff.isActive())).build();

    }

    @Override
    @Transactional
    public ResponseObject<Object> updateStaffInforByStaffID(UpdateInformationStaffRequest updateInformationStaffRequest) {

        if(staffRepository.checkAvailableStaffByIdentify(updateInformationStaffRequest.getIdentityCard(), updateInformationStaffRequest.getStaffId())!=null){
            return ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.name())
                    .content("Căn cước công dân đã bị trùng!!")
                    .build();
        }
        if(staffRepository.checkAvailableStaffByNumberPhone(updateInformationStaffRequest.getPhoneNumber(),updateInformationStaffRequest.getStaffId())!=null){
            return ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.name())
                    .content("Số điện thoại đã bị trùng!!")
                    .build();
        }
        Staff staff = staffRepository.getReferenceById(updateInformationStaffRequest.getStaffId());
        staff.setFirstName(updateInformationStaffRequest.getFirstName());
        staff.setLastName(updateInformationStaffRequest.getLastName());
        staff.setGender(updateInformationStaffRequest.getGender());
        staff.setIdentityCard(updateInformationStaffRequest.getIdentityCard());
        staff.setAddress(updateInformationStaffRequest.getAddress());
        staff.setPhoneNumber(updateInformationStaffRequest.getPhoneNumber());
        staff.setDob(updateInformationStaffRequest.getDob());
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Đăng ký thành công").data(new StaffInforResponse(staff.getStaffId(),staff.getRoleId(),staff.getFirstName(),staff.getLastName()
                        ,staff.getGender(),staff.getIdentityCard(),staff.getAddress(),staff.getPhoneNumber(),staff.getDob(),staff.isActive()))

                .build();
    }

    @Override
    @Transactional
    public ResponseObject<Object> updateStatusByStaffID(int staffId,int active) {
        Staff staff = staffRepository.getReferenceById(staffId);
        staff.setActive((active==1)?true:false);
        staffRepository.save(staff);
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Thay đổi Trạng thái thành công!")
                .build();
    }

    @Override
    @Transactional
    public ResponseObject<Object> updatePassword(UpdateStaffPasswordRequest updateStaffPasswordRequest) {
        Staff staff = staffRepository.getReferenceById(updateStaffPasswordRequest.getStaffID());
        if(passwordEncoder.matches(updateStaffPasswordRequest.getOldPassword(),staff.getPassword())){
            if(updateStaffPasswordRequest.getNewPassword().equals(updateStaffPasswordRequest.getConfirmNewPassword())){
                staff.setPassword(passwordEncoder.encode(updateStaffPasswordRequest.getNewPassword()));
                staffRepository.save(staff);
                return ResponseObject.builder()
                        .status(HttpStatus.OK.value())
                        .message(HttpStatus.OK.name())
                        .content("Thay đổi mật khẩu thành công!")
                        .build();
            }
            return ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.name())
                    .content("Mật khẩu mới và mật khẩu xác nhận không trùng khớp!")
                    .build();
        }
        return ResponseObject.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.name())
                .content("Mật khẩu cũ không chính xác!")
                .build();
    }

    @Override
    @Transactional
    public void deleteStaffByStaffID(int staffID) {
        staffRepository.deleteById(staffID);
    }

    public long countStaff(String identityCard, List<StaffInforResponse>staffList) {
        if (identityCard == null) {
            return staffRepository.count();
        }
        return staffList.size();
    }

}
