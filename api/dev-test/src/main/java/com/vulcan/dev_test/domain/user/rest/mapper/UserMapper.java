package com.vulcan.dev_test.domain.user.rest.mapper;

import com.vulcan.dev_test.domain.user.User;
import com.vulcan.dev_test.domain.user.rest.request.UserDto;
import com.vulcan.dev_test.domain.user.rest.request.UserStoreDto;
import com.vulcan.dev_test.domain.user.rest.request.UserUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    //STORE
    public User toEntity(UserStoreDto userStoreDto) {
        if (userStoreDto == null) {
            return null;
        }
        return User.builder()
                .name(userStoreDto.name())
                .username(userStoreDto.username())
                .password(userStoreDto.password())
                .build();
    }
    //RESPONSE
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getUsername()
        );
    }
    //UPDATE
    public User toEntity(UserUpdateDto userUpdateDto) {
        if (userUpdateDto == null) {
            return null;
        }
        return User.builder()
                .id(userUpdateDto.getId())
                .name(userUpdateDto.getName())
                .username(userUpdateDto.getUsername())
                .password(userUpdateDto.getPassword())
                .build();
    }
}
