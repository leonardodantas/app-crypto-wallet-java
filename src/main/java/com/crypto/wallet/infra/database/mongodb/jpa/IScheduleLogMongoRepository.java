package com.crypto.wallet.infra.database.mongodb.jpa;

import com.crypto.wallet.infra.database.mongodb.documents.ScheduleLogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IScheduleLogMongoRepository extends MongoRepository<ScheduleLogDocument, String> {

    Optional<ScheduleLogDocument> findByName(final String name);

}
