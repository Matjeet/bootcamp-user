package com.example.bootcamp.user.ports.driven.dynamodb.repository.impl;

import com.example.bootcamp.user.ports.driven.dynamodb.entity.ProfileEntity;
import com.example.bootcamp.user.ports.driven.dynamodb.repository.IProfileRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class ProfileRepositoryImpl implements IProfileRepository {

    private final DynamoDbTable<ProfileEntity> profileTable;

    public ProfileRepositoryImpl(DynamoDbEnhancedClient dynamoDbEnhancedClient){
        this.profileTable = dynamoDbEnhancedClient.table(
                "profile",
                TableSchema.fromBean(ProfileEntity.class)
        );
    }

    @Override
    public void save(ProfileEntity profileEntity) {
        profileTable.putItem(profileEntity);
    }
}
