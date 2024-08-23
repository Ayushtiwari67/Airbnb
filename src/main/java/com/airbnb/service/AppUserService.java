package com.airbnb.service;

import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.LoginDto;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {
    public AppUserDto createUser(AppUserDto appUserDto);
    public void delete(long id);
    public AppUserDto update(long id, AppUserDto appUserDto);
    public String verifyLogin(LoginDto loginDto);
}
