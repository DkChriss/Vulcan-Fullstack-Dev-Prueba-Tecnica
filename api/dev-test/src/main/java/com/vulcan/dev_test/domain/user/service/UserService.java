package com.vulcan.dev_test.domain.user.service;

import com.vulcan.dev_test.domain.user.User;
import com.vulcan.dev_test.domain.user.repository.UserDao;
import com.vulcan.dev_test.handler.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;


    public Integer size() {
        return this.userDao.size();
    }

    public Page<User> list(String name, int page, int size) {
        return this.userDao.list(name, page, size);
    }

    public User store(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userDao.store(user);
    }

    public User show(Long id) {
        return  this.userDao.show(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se existe el usuario que desea obtener"));
    }

    public User update(User user) {
        return this.userDao.update(user);
    }

    public void destroy(Long id) {
        User user = this.userDao.show(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se existe el usuario que desea eliminar"));
        this.userDao.destroy(user);
    }


}
