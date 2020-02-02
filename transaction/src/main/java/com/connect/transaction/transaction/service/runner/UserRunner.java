package com.connect.transaction.transaction.service.runner;

import com.connect.transaction.transaction.service.UserService;
import com.connect.transaction.transaction.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class UserRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Inside Command line runner..");

        try {
            User user1 = new User("Raj", "Ops", 10000L);
            User user2 = new User("Raghu", "IT", 20000L);
            User user3 = new User("Vishnu", "IT", 30000L);
            User user4 = new User("Vaith", "Ops", 40000L);

            List<User> users = Arrays.asList(
                    user1, user2, user3, user4
            );

            userService.insertUser(users);
        }catch(RuntimeException ex){
            System.err.println("Exception " + ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("List of Users" + userService.getUser());
    }
}
