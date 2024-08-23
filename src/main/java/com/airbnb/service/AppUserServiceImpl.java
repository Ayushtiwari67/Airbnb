package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.LoginDto;
import com.airbnb.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService{


    private AppUserRepository appUserRepository;
    private JWTService jwtService;

    public AppUserServiceImpl(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AppUserDto createUser(AppUserDto appUserDto) {
        String hashpw = BCrypt.hashpw(appUserDto.getPassword(), BCrypt.gensalt(10));
        appUserDto.setPassword(hashpw);
        AppUser appUser = mapToEntity(appUserDto);
        AppUser save = appUserRepository.save(appUser);
        AppUserDto dto = mapToDto(save);
        dto.setMessage("User Save SuccessFully.");
        return dto;
    }

    AppUser mapToEntity(AppUserDto dto){
        AppUser entity = new AppUser();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        return entity;
    }

    AppUserDto mapToDto(AppUser entity){
        AppUserDto dto = new AppUserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        return dto;
    }
    @Override
    public void delete(long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public AppUserDto update(long id, AppUserDto appUserDto) {
        Optional<AppUser> byId = appUserRepository.findById(id);
        AppUser appUser = byId.get();
        appUser.setName(appUserDto.getName());
        appUser.setEmail(appUserDto.getEmail());
        appUser.setUsername(appUserDto.getUsername());
        appUser.setPassword(appUserDto.getPassword());
        appUser.setRole(appUser.getRole());
        AppUser save = appUserRepository.save(appUser);
        AppUserDto dto = mapToDto(save);
        return dto;
    }


    @Override
    public String verifyLogin(LoginDto loginDto){
        Optional<AppUser> opUser = appUserRepository.findByUsernameOrEmail(loginDto.getUsername(), loginDto.getUsername());
        if (opUser.isPresent()){
            AppUser appUser = opUser.get();
           if( BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword())){
              return jwtService.generateToken(appUser);
            }
        }
        return null;
    }


}
