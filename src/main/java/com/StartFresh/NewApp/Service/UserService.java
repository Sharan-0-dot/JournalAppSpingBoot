package com.StartFresh.NewApp.Service;

import com.StartFresh.NewApp.Model.User;
import com.StartFresh.NewApp.Repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo repo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(ObjectId id) {
        return repo.findById(id).orElse(null);
    }

    public User postUser(User u) {
        return repo.save(u);
    }

    public User postNewUser(User u) {
        try {
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            u.setRoles(Arrays.asList("USER"));
            return repo.save(u);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    public User postNewAdmin(User u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        u.setRoles(Arrays.asList("USER", "ADMIN"));
        return repo.save(u);
    }

    public void deleteById(ObjectId id) {
        repo.deleteById(id);
    }

    public User findByUserName(String s) {
        return repo.findByusername(s);
    }

}
