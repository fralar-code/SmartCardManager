package com.wsda.CreditCard.service;

import com.wsda.CreditCard.dto.UserDto;
import com.wsda.CreditCard.entity.User;

import java.util.List;

public interface UserService {
    Long saveUser(UserDto userDto);

    User findUserByEmail(String email);

    void updateStateById(Long id, String state);

    List<UserDto> findAllUsers();

    List<UserDto> findAllShopkeepers();

}
