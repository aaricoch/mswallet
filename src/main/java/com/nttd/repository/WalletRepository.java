package com.nttd.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.nttd.entity.WalletEntity;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WalletRepository {

    @ConfigProperty(name = "valor.flag.activo")
    String flag_activo;

    @ConfigProperty(name = "valor.flag.inactivo")
    String flag_inactivo;

    public Uni<WalletEntity> add(WalletEntity wallet) {
        wallet.setState(flag_activo);
        return wallet.persist();
    }

    public Uni<List<WalletEntity>> listAll() {
        return WalletEntity.listAll();
    }

    public Uni<WalletEntity> getById(String id) {
        return WalletEntity.findById(new ObjectId(id));
    }

    public Uni<WalletEntity> edit(WalletEntity wallet) {
        Uni<WalletEntity> wal = WalletEntity.findById(wallet.id);
        return wal.map(uni -> {
            uni.setAmount(wallet.getAmount());
            uni.setNumberPhone(wallet.getNumberPhone());
            uni.setCodeClient(wallet.getCodeClient());
            return uni;
        }).call(res -> {
            return res.persistOrUpdate();
        });
    }

    public Uni<WalletEntity> remove(String id) {
        Uni<WalletEntity> wallet = WalletEntity.findById(new ObjectId(id));
        return wallet.map(item -> {
            item.setState(flag_inactivo);
            return item;
        }).call(item -> {
            return item.persistOrUpdate();
        });
    }

}
