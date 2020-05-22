package com.example.spring.service;

import com.example.spring.dao.FakeRepoInterface;
import com.example.spring.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

public class UserServiceImpl {
    private final FakeRepoInterface fakeRepoInterface;

    public UserServiceImpl(FakeRepoInterface fakeRepoInterface) {
        this.fakeRepoInterface = fakeRepoInterface;
    }

    public String addUser (User user){
        return fakeRepoInterface.insertUser(user)+" entered";
    }

    public String removeUser (Long id){
        return fakeRepoInterface.deleteUser(id)+" removed";
    }

    @Cacheable(value = "getUserCache")
    public String getUser (Long id){
        try
        {
            System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
            Thread.sleep(1000*5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return "hello "+fakeRepoInterface.findUserById(id);
    }
}
