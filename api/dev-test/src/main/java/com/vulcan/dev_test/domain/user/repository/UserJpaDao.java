package com.vulcan.dev_test.domain.user.repository;

import com.vulcan.dev_test.domain.user.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("user-jpa")
@RequiredArgsConstructor
public class UserJpaDao implements  UserDao{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Integer size() {
        return this.userRepository.findAll().size();
    }

    @Override
    @Transactional
    public Page<User> list(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        if(name == null || name.isEmpty()) {
            return this.userRepository.findAll(pageable);
        }
        return this.userRepository.findByName(name,pageable);
    }

    @Override
    @Transactional
    public User store(User user) {
        return this.userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> show(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    @Transactional
    public User update(User user) {
        return this.userRepository.save(user);
    }

    @Override
    @Transactional
    public void destroy(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.userRepository.existsById(id);
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
