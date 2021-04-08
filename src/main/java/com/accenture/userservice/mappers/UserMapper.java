package com.accenture.userservice.mappers;

import com.accenture.userservice.domains.User;
import com.accenture.userservice.requests.UserRegisterRequest;
import com.accenture.userservice.responses.UserDataResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

public class UserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public UserDataResponse convertUserDomainToUserResponse( User user){
        UserDataResponse userDataResponse = modelMapper.map(user, UserDataResponse.class);
        return userDataResponse;
    }

    public User convertUserRegisterRequestToEntity(UserRegisterRequest userRequest) throws ParseException{
        User userModel = modelMapper.map(userRequest, User.class);
        return userModel;
    }

    public UserDataResponse convertUserModelToUserResponse(User user) {
        UserDataResponse userDataResponse = modelMapper.map(user, UserDataResponse.class);
        return userDataResponse;
    }
//
//    public User convertUserRegisterRequestToEntity(UserRegisterRequest userRequest) throws ParseException {
//        User userModel = modelMapper.map(userRequest, User.class);
//        return userModel;
//    }
//
//    public User convertUserLoginRequestToEntity(UserLoginRequest userRequest) throws ParseException {
//        User userModel = modelMapper.map(userRequest, User.class);
//        return userModel;
//    }
}
