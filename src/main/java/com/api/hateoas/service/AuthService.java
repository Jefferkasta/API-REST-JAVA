package com.api.hateoas.service;

import com.api.hateoas.dto.CuentaDto;
import com.api.hateoas.dto.RegisterDto;
import com.api.hateoas.model.Cuenta;
import com.api.hateoas.model.UserModel;
import com.api.hateoas.repository.CuentaRepository;
import com.api.hateoas.repository.UserJpa;
import com.api.hateoas.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class AuthService {

    private final UserJpa userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserJpa userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterDto save(RegisterDto registerDto){
        String hash = passwordEncoder.encode(registerDto.getPassword());

        // mapeo toUserModel
        UserModel toUserModel = new UserModel(
                registerDto.getName(),
                registerDto.getEmail(),
                hash
        );
        //agregar role a toUserModel
        toUserModel.setRole(Role.valueOf("USER"));
        // guardar en la db
        UserModel userModel2 = userRepository.save(toUserModel);

        // mapeo toRegisterDto
        RegisterDto toRegisterDto = new RegisterDto();
        toRegisterDto.setName(userModel2.getName());
        toRegisterDto.setEmail(userModel2.getEmail());

        return toRegisterDto;
    }

}
