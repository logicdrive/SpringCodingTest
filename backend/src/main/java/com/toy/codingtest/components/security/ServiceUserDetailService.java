package com.toy.codingtest.components.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toy.codingtest.user.manageUser.services.ManageUserService;

@Service
public class ServiceUserDetailService implements UserDetailsService {
    final private ManageUserService manageUserService;

    public ServiceUserDetailService(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new ServiceUserDetail(this.manageUserService.findByEmail(username));
    }
}
