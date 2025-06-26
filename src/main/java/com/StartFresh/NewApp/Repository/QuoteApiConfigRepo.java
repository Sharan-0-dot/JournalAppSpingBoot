package com.StartFresh.NewApp.Repository;

import com.StartFresh.NewApp.Model.QuoteAPIConfig;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuoteApiConfigRepo extends MongoRepository<QuoteAPIConfig, ObjectId> {

}
