package com.nttd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.nttd.dto.BilleteraDto;
import com.nttd.dto.ResponseDto;
import com.nttd.entity.WalletEntity;
import com.nttd.repository.WalletRepository;
import com.nttd.service.WalletService;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class WalletServiceImpl implements WalletService {

    @Inject
    WalletRepository walletRepository;

    @ConfigProperty(name = "mensaje.general")
    String messagegeneric;

    @ConfigProperty(name = "message.001")
    String message001;

    @ConfigProperty(name = "error.generic")
    String errorgeneric;

    public Uni<ResponseDto> add(BilleteraDto billeteraDto) {
        try {
            return walletRepository.add(towallet(billeteraDto))
                    .map(wallet -> new ResponseDto(201, message001, towalletDto(wallet)))
                    .onFailure().recoverWithItem(error -> new ResponseDto(400, errorgeneric, error.getMessage()));
        } catch (Exception ex) {
            return Uni.createFrom().item(new ResponseDto(400, errorgeneric, ex.getMessage()));
        }
    }

    public Uni<ResponseDto> listAll() {
        try {
            return walletRepository.listAll()
                    .map(wallets -> {
                        List<BilleteraDto> lista = new ArrayList<>();

                        for (WalletEntity wallet : wallets) {
                            BilleteraDto walletDto = new BilleteraDto();

                            walletDto.setIdBilletera(wallet.id.toString());
                            walletDto.setCodigoCliente(wallet.getCodeClient());
                            walletDto.setMonto(wallet.getAmount());
                            walletDto.setCelular(wallet.getNumberPhone());
                            walletDto.setEstado(wallet.getState());

                            lista.add(walletDto);
                        }

                        return lista;
                    })
                    .map(walletDto -> new ResponseDto(200, messagegeneric, walletDto))
                    .onFailure().recoverWithItem(error -> new ResponseDto(400, errorgeneric,
                            error.getMessage()));
        } catch (Exception ex) {
            return Uni.createFrom().item(new ResponseDto(400, errorgeneric, ex.getMessage()));
        }
    }

    @Override
    public Uni<ResponseDto> getById(String id) {
        try {
            return walletRepository.getById(id)
                    .map(wallet -> new ResponseDto(200, messagegeneric, towalletDto(wallet)))
                    .onFailure().recoverWithItem(error -> new ResponseDto(400, errorgeneric, error.getMessage()));
        } catch (Exception ex) {
            return Uni.createFrom().item(new ResponseDto(400, errorgeneric, ex.getMessage()));
        }
    }

    @Override
    public Uni<ResponseDto> edit(String id, BilleteraDto walletDto) {
        walletDto.setIdBilletera(id);
        return walletRepository.edit(towallet(walletDto))
                .map(wallet -> new ResponseDto(200, messagegeneric, towalletDto(wallet)))
                .onFailure().recoverWithItem(error -> new ResponseDto(400, errorgeneric, error.getMessage()));
    }

    @Override
    public Uni<ResponseDto> remove(String id) {
        return walletRepository.remove(id)
                .map(wallet -> new ResponseDto(200, messagegeneric, null))
                .onFailure().recoverWithItem(error -> new ResponseDto(400, errorgeneric, error.getMessage()));
    }

    WalletEntity towallet(BilleteraDto walletDto) {
        WalletEntity wallet = new WalletEntity();
        if (walletDto.getIdBilletera() != null)
            wallet.id = new ObjectId(walletDto.getIdBilletera());
        wallet.setCodeClient(walletDto.getCodigoCliente());
        wallet.setAmount(walletDto.getMonto());
        wallet.setNumberPhone(walletDto.getCelular());
        wallet.setState(walletDto.getEstado());

        return wallet;
    }

    BilleteraDto towalletDto(WalletEntity wallet) {
        BilleteraDto walletDto = new BilleteraDto();

        walletDto.setIdBilletera(wallet.id.toString());
        walletDto.setCodigoCliente(wallet.getCodeClient());
        walletDto.setMonto(wallet.getAmount());
        walletDto.setCelular(wallet.getNumberPhone());
        walletDto.setEstado(wallet.getState());

        return walletDto;
    }

}
