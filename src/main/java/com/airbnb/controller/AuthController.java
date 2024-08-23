package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.exception.UserExists;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.JWTToken;
import com.airbnb.payload.LoginDto;
import com.airbnb.repository.AppUserRepository;
import com.airbnb.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AppUserService appUserService;
    private AppUserRepository appUserRepository;

    public AuthController(AppUserService appUserService, AppUserRepository appUserRepository) {
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/createuser")
    public ResponseEntity<AppUserDto> createUser(
            @RequestBody AppUserDto appUserDto){


        appUserDto.setRole("ROLE_USER");
        AppUserDto add = appUserService.createUser(appUserDto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @PostMapping("/createpropertyowner")
    public ResponseEntity<AppUserDto> createPropertyOwner(
            @RequestBody AppUserDto appUserDto){

        Optional<AppUser> opEmail = appUserRepository.findByEmail(appUserDto.getEmail());
        if(opEmail.isPresent()){
            throw new UserExists("Email is already exists");
        }
        Optional<AppUser> opUsername = appUserRepository.findByUsername(appUserDto.getUsername());
        if (opUsername.isPresent()){
            throw new UserExists("Username is Already exists");
        }
        appUserDto.setRole("ROLE_OWNER");
        AppUserDto add = appUserService.createUser(appUserDto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @PostMapping("/createpropertymanager")
    public ResponseEntity<AppUserDto> createPropertyManager(
            @RequestBody AppUserDto appUserDto){

        Optional<AppUser> opEmail = appUserRepository.findByEmail(appUserDto.getEmail());
        if(opEmail.isPresent()){
            throw new UserExists("Email is already exists");
        }
        Optional<AppUser> opUsername = appUserRepository.findByUsername(appUserDto.getUsername());
        if (opUsername.isPresent()){
            throw new UserExists("Username is Already exists");
        }
        appUserDto.setRole("ROLE_MANAGER");
        AppUserDto add = appUserService.createUser(appUserDto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }
    @PostMapping("/createadmin")
    public ResponseEntity<AppUserDto> createAdmin(
            @RequestBody AppUserDto appUserDto){

        Optional<AppUser> opEmail = appUserRepository.findByEmail(appUserDto.getEmail());
        if(opEmail.isPresent()){
            throw new UserExists("Email is already exists");
        }
        Optional<AppUser> opUsername = appUserRepository.findByUsername(appUserDto.getUsername());
        if (opUsername.isPresent()){
            throw new UserExists("Username is Already exists");
        }
        appUserDto.setRole("ROLE_ADMIN");
        AppUserDto add = appUserService.createUser(appUserDto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAppUser(@RequestParam long id){
        Optional<AppUser> poId = appUserRepository.findById(id);
        if(poId.isEmpty()){
            throw new UserExists("User not exists");
        }
        appUserRepository.deleteById(id);
        return new ResponseEntity<>("Delete user Successfully",HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<AppUserDto> updateAppUser(
            @RequestParam long id,
            @RequestBody AppUserDto appUserDto){
        AppUserDto update = appUserService.update(id, appUserDto);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(
            @RequestBody LoginDto loginDto
    ){
        String token = appUserService.verifyLogin(loginDto);
        JWTToken jwtToken = new JWTToken();
        if (token!=null){
            jwtToken.setTokenType("JWT");

            jwtToken.setToken(token);
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid username/password", HttpStatus.UNAUTHORIZED);
        }
    }


     


}
