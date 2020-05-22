package com.example.spring.dao;

import com.example.spring.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeRepo implements FakeRepoInterface {
    private static List<User> User = new ArrayList<>();

    @Override
    public String insertUser(Long id, User user) {
        User.add(new User(id, user.getName(), user.getSurname()));
        return user.getName();
    }

    @Override
    public String findUserById(Long id) {
        String name = User.get(id.intValue()).toString();
        return name;
    }

    @Override
    public String deleteUser(Long id) {
        String name = User.get(id.intValue()).toString();
        User.remove(id);
        return name;
    }
}
