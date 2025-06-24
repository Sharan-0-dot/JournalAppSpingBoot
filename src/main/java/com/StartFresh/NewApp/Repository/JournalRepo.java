package com.StartFresh.NewApp.Repository;

import com.StartFresh.NewApp.Model.Journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepo extends MongoRepository<Journal, ObjectId> {

}
