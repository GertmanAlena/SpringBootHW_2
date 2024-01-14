package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Соединение с БД (@Repository загружается в контейнер)
 */
@Repository
public class UserRepository {

    //region Поля
    private final JdbcTemplate jdbc; //для работы с БД
    //endregion

    //region Конструктор
    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    //endregion

    //region Методы

    /**
     * Метод получения всех пользователей из БД
     * @return
     */
    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        return jdbc.query(sql, userRowMapper);
    }

    /**
     * Метод добавления в БД
     * @param user
     * @return
     */
    public User save(User user) {
        String sql = "INSERT INTO userTable VALUES (DEFAULT, ?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return  user;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

    public void updateUser(User user, int id) {

        System.out.println("------------UserRepository updateUser");
        System.out.println(id);
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());

        String sql = "UPDATE userTable SET firstName=?, lastName=? WHERE id=?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), id);
        System.out.println("-----------");

    }


    //endregion
}