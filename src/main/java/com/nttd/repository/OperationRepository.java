package com.nttd.repository;

import java.util.Date;
import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.nttd.entity.OperationEntity;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OperationRepository {

    @ConfigProperty(name = "valor.flag.activo")
    String flag_activo;

    public Uni<OperationEntity> add(OperationEntity operation) {
        operation.setOperationDate(new Date());
        operation.setState(flag_activo);
        return operation.persist();
    }

    public Uni<List<OperationEntity>> listAll() {
        return OperationEntity.listAll();
    }

    public Uni<List<OperationEntity>> getByWallet(String id) {
        return OperationEntity.find("codeWallet", id).list();
    }

}
