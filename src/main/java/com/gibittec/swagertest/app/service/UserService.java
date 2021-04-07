package com.gibittec.swagertest.app.service;


import com.gibittec.swagertest.app.model.User;
import com.gibittec.swagertest.app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // indicamos que es transacional osea si en una transacion algo sale mal hacer rollback
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(User user){
        return this.userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user){
        return this.userRepository.save(user);
    }

    @Transactional
    public void deleteUser(User user){
        this.userRepository.delete(user);
    }

    public User findById(Integer id){
        return this.userRepository.findById(id).get();
    }

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

}
