
package com.api.hateoas.service;
import com.api.hateoas.dto.LoginDto;
import com.api.hateoas.dto.LoginResponse;
import com.api.hateoas.dto.RegisterDto;
import com.api.hateoas.exceptions.AuthInvalidValueException;
import com.api.hateoas.exceptions.AuthenticationNotFound;
import com.api.hateoas.model.UserModel;
import com.api.hateoas.repository.UserJpa;
import com.api.hateoas.util.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;


@Service
public class AuthService {

    private final UserJpa userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticateUser;

    public AuthService(UserJpa userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticateUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticateUser = authenticateUser;
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

    public LoginResponse login(LoginDto loginDto) {
        String pass = loginDto.getPassword();
        String email = loginDto.getEmail();

        if (email == null || pass ==  null){
            throw  new AuthInvalidValueException("email o pass no pueden ser vacios");
        }

        Authentication authentication = this.authenticationManager(email, pass);

        if (!authentication.isAuthenticated() || Objects.equals(authentication.getPrincipal(), "anonymousUser")) {
            throw new AuthenticationNotFound("Usuario no autorizado");
        }
        UserModel userModel = (UserModel) authentication.getPrincipal();
        // crear token
        return new LoginResponse(
                userModel.getId(),
                userModel.getEmail(),
                userModel.getUsername(),
                userModel.getRole()
        );
    }

    private Authentication authenticationManager(String email, String password) {
        return authenticateUser.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
    }

}
