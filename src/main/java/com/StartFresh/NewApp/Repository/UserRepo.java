package com.StartFresh.NewApp.Repository;

import com.StartFresh.NewApp.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByusername(String s);
}
