package com.StartFresh.NewApp.Service;

import com.StartFresh.NewApp.Model.Journal;
import com.StartFresh.NewApp.Model.User;
import com.StartFresh.NewApp.Repository.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalEntryService {
    @Autowired
    private JournalRepo repo;

    @Autowired
    private UserService userService;

    public List<Journal> getAll() {
        return repo.findAll();
    }

    public Journal getById(ObjectId id) {
        return repo.findById(id).orElse(null);
    }

    @Transactional
    public Journal postJournalOfUser(Journal j, String name) {
        try {
            j.setDate(LocalDateTime.now());
            Journal j1 = repo.save(j);
            User u = userService.findByUserName(name);
            u.getJournalEntries().add(j1);
            userService.postUser(u);
            return j1;
        } catch (Exception e) {
            throw new RuntimeException("error has been raised", e);
        }
    }

    public boolean isPresent(String name, ObjectId id) {
        User u = userService.findByUserName(name);
        for(Journal j : u.getJournalEntries()) {
            if(j.getId().equals(id)) return true;
        }
        return false;
    }

    @Transactional
    public Journal putJournal(String name, ObjectId id, Journal j) {
        try {
            User u = userService.findByUserName(name);
            for(Journal cur : u.getJournalEntries()) {
                if(cur.getId().equals(id)) {
                    cur.setTitle(j.getTitle() != null && !j.getTitle().isEmpty() ? j.getTitle() : cur.getTitle());
                    cur.setContent(j.getContent() != null && !j.getContent().isEmpty() ? j.getContent() : cur.getContent());
                    return repo.save(cur);
                }
            }
            return null;
        } catch (Exception e) {
            throw  new RuntimeException("error has been raised", e);
        }
    }

    @Transactional
    public Journal deleteById(ObjectId id, String name) {
        try {
            Journal j = repo.findById(id).orElse(null);
            if(j == null) return null;
            User u = userService.findByUserName(name);
            u.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userService.postUser(u);
            repo.deleteById(id);
            return j;
        } catch (Exception e) {
            throw new RuntimeException("error has been raised", e);
        }
    }
}
