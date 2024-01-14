package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Собирает логику
 */
@Service
public class UserService {


    //region Поля
    private final UserRepository userRepository;
    //endregion

    //region Конструктор
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //endregion

    //region Методы
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteById(User user) {
        userRepository.deleteById(user.getId());
        userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.updateUser(user, user.getId());

        userRepository.findAll();
    }


    //endregion
}