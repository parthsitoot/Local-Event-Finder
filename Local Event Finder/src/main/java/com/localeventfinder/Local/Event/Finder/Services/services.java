package com.localeventfinder.Local.Event.Finder.Services;

import com.localeventfinder.Local.Event.Finder.Entity.User;
import com.localeventfinder.Local.Event.Finder.Entity.Variables;
import com.localeventfinder.Local.Event.Finder.Repository.localEventFinder_Repo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class services {
    @Autowired
    private localEventFinder_Repo eventRepo;
    @Autowired
    private User_services userServices;

    public void saveEntry(Variables var, String username) {
        User user = userServices.findByuserName(username);
        var.setDate(LocalDateTime.now());
        Variables saved = eventRepo.save(var);
        user.getVariables().add(saved);
        userServices.saveUser(user);
    }

    public List<Variables> getAll() {

        return eventRepo.findAll();
    }

    public Optional<Variables> findById(ObjectId id) {

        return eventRepo.findById(id);
    }

    public Optional<Variables> deleteById(ObjectId id) {
        Optional<Variables> var = eventRepo.findById(id);
        eventRepo.deleteById(id);
        return var;
    }
}
