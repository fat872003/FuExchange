package com.adkp.fuexchange.security;

import com.adkp.fuexchange.pojo.RegisteredStudent;
import com.adkp.fuexchange.pojo.Staff;
import com.adkp.fuexchange.repository.RegisteredStudentRepository;
import com.adkp.fuexchange.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserDetailService implements UserDetailsService {

    @Autowired
    private RegisteredStudentRepository registeredStudentRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        RegisteredStudent registeredStudent = registeredStudentRepository.findRegisteredStudentByStudentId(username);

        Staff staff = staffRepository.findStaffByNumberPhone(username);
        if (registeredStudent != null){
            return new RegisteredStudentDetails(registeredStudent);
        } else if (staff != null){
            return new StaffDetails(staff);
        }
        throw new UsernameNotFoundException("Không tìm thấy username!");
    }
}
