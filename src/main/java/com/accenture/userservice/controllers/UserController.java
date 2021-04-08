package com.accenture.userservice.controllers;

import com.accenture.userservice.domains.User;
import com.accenture.userservice.mappers.UserMapper;
import com.accenture.userservice.repository.UserRepository;
import com.accenture.userservice.requests.UserRegisterRequest;
import com.accenture.userservice.responses.MessageResponse;
import com.accenture.userservice.responses.UserDataResponse;
import com.accenture.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private boolean checkRegister(String cpf, String login) throws Exception {
        if (userRepository.existsByCpfOrLogin(cpf,login)) {
            throw new Exception("CPF ou Login já cadastrado");
        }
        return  true;
    }


    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody UserRegisterRequest userRequest) throws Exception {
        User userDomain = userMapper.convertUserRegisterRequestToEntity(userRequest);
//        System.out.println(userRequest);
        UserDataResponse serviceResponse = userService.create(userDomain);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        MessageResponse message = userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        UserDataResponse userResponse = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/{id}")
    private User updateById(@PathVariable("id") Long id, @Valid @RequestBody UserRegisterRequest userRequest) throws Exception {
        User user = userMapper.convertUserRegisterRequestToEntity(userRequest);
        UserDataResponse userResponse = userService.updateById(id, user);
        return userRepository.save(user);
    }

    @GetMapping
    private ResponseEntity<List<UserDataResponse>> findAll() {
        List<UserDataResponse> serviceResponse = userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
    }
}
