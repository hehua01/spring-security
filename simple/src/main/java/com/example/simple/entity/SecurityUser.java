package com.example.simple.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Date 2022/3/2 15:49
 * @Description
 */
public class SecurityUser extends User implements UserDetails {
    public SecurityUser(){}
    public SecurityUser(User sysUser){
        super.setId(sysUser.getId());
        super.setUsername(sysUser.getUsername());
        super.setPassword(sysUser.getPassword());
        super.setRoles(sysUser.getRoles());
    }

    //spring security用于存放权限的属性
    private List<GrantedAuthority> authorityList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setAuthorityList(List<GrantedAuthority> authorityList) {
        this.authorityList = authorityList;
    }

}
