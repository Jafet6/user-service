package com.accenture.userservice.services;

import com.accenture.userservice.domains.User;
import com.accenture.userservice.mappers.UserMapper;
import com.accenture.userservice.repository.UserRepository;
import com.accenture.userservice.responses.MessageResponse;
import com.accenture.userservice.responses.UserDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserDataResponse create(User user) throws Exception {
       userRepository.save(user);

       UserDataResponse userResponse = userMapper.convertUserDomainToUserResponse(user);
         return userResponse;
    }

    public MessageResponse deleteById(Long id) {
        userRepository.deleteById(id);
        MessageResponse response = new MessageResponse ("Usuário deletado com sucesso");
        return response;
    }

    public UserDataResponse findById(Long id) {
        User user = userRepository.findById(id).get();
        UserDataResponse response = userMapper.convertUserDomainToUserResponse(user);
        return response;
    }

    public UserDataResponse updateById(Long id, User user) throws Exception {
        user.setId(id);
        user.setName(user.getName());
        user.setLogin(user.getLogin());
        user.setPassword(user.getPassword());
        userRepository.save(user);
        UserDataResponse response = userMapper.convertUserDomainToUserResponse(user);
        return response;
    }

    public List<UserDataResponse> findAll() {
        List<User> users = userRepository.findAll();

        List<UserDataResponse> response = users.stream().map(
                user -> userMapper.convertUserDomainToUserResponse(user))
        .collect(Collectors.toList());
        return response;
    }
}
