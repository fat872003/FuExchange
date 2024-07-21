package com.adkp.fuexchange.security;

import com.adkp.fuexchange.pojo.RegisteredStudent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class RegisteredStudentDetails implements UserDetails {

    private final RegisteredStudent registeredStudent;

    public RegisteredStudentDetails(RegisteredStudent registeredStudent) {
        this.registeredStudent = registeredStudent;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(registeredStudent.getRoleId().getRoleName()));
    }

    @Override
    public String getPassword() {
        return registeredStudent.getPassword();
    }

    @Override
    public String getUsername() {
        return registeredStudent.getStudentId().getStudentId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return registeredStudent.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
