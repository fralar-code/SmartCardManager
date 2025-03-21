package com.wsda.CreditCard.service.impl;

import com.wsda.CreditCard.dto.UserDto;
import com.wsda.CreditCard.entity.Role;
import com.wsda.CreditCard.entity.User;
import com.wsda.CreditCard.repository.RoleRepository;
import com.wsda.CreditCard.repository.UserRepository;
import com.wsda.CreditCard.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Long saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setState(userDto.getState());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("SHOP");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        return userRepository.save(user).getId();
    }

    @Override
    public User findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public void updateStateById(Long id, String state) {
        userRepository.updateStateById(id, state);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> convertToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllShopkeepers() {
        List<User> users = userRepository.findAllShoopkeepers();
        return users.stream()
                .map((user) -> convertToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertToUserDto(User user) {
        UserDto userDto = null;
        if (user != null) {
            userDto = modelMapper.map(user, UserDto.class);
        }
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("SHOP");
        return roleRepository.save(role);
    }
}
