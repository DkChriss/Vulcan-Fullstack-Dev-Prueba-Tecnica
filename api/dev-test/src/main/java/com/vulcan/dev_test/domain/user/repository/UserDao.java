package com.vulcan.dev_test.domain.user.repository;

import com.vulcan.dev_test.domain.user.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserDao {
    Integer size();
    Page<User> list(String name, int page, int size);
    User store(User user);
    Optional<User> show(Long id);
    User update(User user);
    void destroy(User user);

    Boolean existsById(Long id);
    Optional<User> selectUserByUsername(String username);
}
