package com.nttd.entity;

import java.util.Date;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MongoEntity(collection = "operaciones")
public class OperationEntity extends ReactivePanacheMongoEntity {
    private String codeWallet;
    private String numberPhone;
    private Date operationDate;
    private Double amount;
    private String description;
    private String codePromotion;
    private String state;
}
