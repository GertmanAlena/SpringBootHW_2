package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Ответы на запросы от браузера
 */
@Controller
public class UserController {

    //region Поля
    private final UserService userService;
    //endregion

    //region Конструктор
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    //endregion

    //region GetMapping

    /**
     * Обработчик получения данных из БД
     * @param model
     * @return
     */
    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        return "user-list";
//        return "home.html";
    }

    /**
     * Обработчик создания пользователя (выводит на страницу поля для заполнения
     * @param user
     * @return
     */
    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    /***
     * Обработчик удаления пользователя
     * @param user
     * @return
     */
    @GetMapping("user-delete/{id}")
    public String deleteUser(User user){
        userService.deleteById(user);
        return "redirect:/users";
    }

    /**
     * Обработчик обновления пользователя (возвращает пользователя по указанному идентификатору)
     * @param user
     * @return
     */
    @GetMapping("/user-update/{id}")
    public String updateUserForm(User user){
        return "user-update";
    }

    /***
     * Принимает изменённые данные
     * @param user
     * @return
     */
    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/users";
    }

    /***
     * Сохраняет введённые данные
     * @param user
     * @return
     */
    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }


    //endregion
}