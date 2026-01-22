package com.api.hateoas.service;

import com.api.hateoas.dto.CuentaDto;
import com.api.hateoas.dto.LoginDto;
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
import java.util.Optional;

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

    //LOGIN DTO CON GEMINI
    public LoginDto login(LoginDto loginDto) {
        // 1. BUSCAR al usuario en la DB por su email
        // Usamos el repositorio (UserJpa/userRepository)
        //UserModel userModel = userRepository.findByEmail(loginDto.getEmail());
        Optional<UserModel> userModel = userRepository.findByEmail(loginDto.getEmail());

        // 2. VALIDAR si el usuario existe
        if (userModel == null) {
            return null; // O puedes lanzar una excepción personalizada
        }

        // 3. COMPARAR contraseñas
        // El passwordEncoder compara la clave plana del DTO con el hash de la DB
        boolean matches = passwordEncoder.matches(loginDto.getPassword(), userModel.getPassword());

        if (matches) {
            // 4. MAPEO a LoginDto (para devolver la respuesta)
            LoginDto toLoginDto = new LoginDto();
            toLoginDto.setEmail(userModel.getEmail());
            // IMPORTANTE: Nunca devuelvas la contraseña en el DTO de respuesta

            return toLoginDto;
        }

        return null; // Si la contraseña no coincide
    }

}
