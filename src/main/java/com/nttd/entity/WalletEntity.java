package com.nttd.entity;

import java.util.ArrayList;
import java.util.List;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MongoEntity(collection = "billeteras")
public class WalletEntity extends ReactivePanacheMongoEntity {
    private String numberPhone;
    private String codeClient;
    private Double amount;
    private List<OperationEntity> operations;
    private String state;

    public WalletEntity() {
        operations = new ArrayList<>();
    }
}
