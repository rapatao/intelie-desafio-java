package com.rapatao.intelie.job;

import org.springframework.data.mongodb.repository.MongoRepository;

interface JobRepository extends MongoRepository<Job, String> {

}
