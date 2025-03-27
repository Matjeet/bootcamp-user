package com.example.bootcamp.user.ports.driven.dynamodb.repository;

import com.example.bootcamp.user.ports.driven.dynamodb.entity.ProfileEntity;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRepository extends DynamoDBCrudRepository<ProfileEntity, String> {
}
