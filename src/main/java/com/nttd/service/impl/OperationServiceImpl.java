package com.nttd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.nttd.dto.OperacionDto;
import com.nttd.dto.ResponseDto;
import com.nttd.entity.OperationEntity;
import com.nttd.repository.OperationRepository;
import com.nttd.service.OperationService;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OperationServiceImpl implements OperationService {

    @Inject
    OperationRepository operationRepository;

    @ConfigProperty(name = "mensaje.general")
    String messagegeneric;

    @ConfigProperty(name = "message.001")
    String message001;

    @ConfigProperty(name = "error.generic")
    String errorgeneric;

    public Uni<ResponseDto> add(OperacionDto operacionDto) {
        try {
            return operationRepository.add(toOperation(operacionDto))
                    .map(operation -> new ResponseDto(201, message001, toOperationDto(operation)))
                    .onFailure().recoverWithItem(error -> new ResponseDto(400, errorgeneric, error.getMessage()));
        } catch (Exception ex) {
            return Uni.createFrom().item(new ResponseDto(400, errorgeneric, ex.getMessage()));
        }
    }

    public Uni<ResponseDto> listAll() {
        try {
            return operationRepository.listAll()
                    .map(operations -> {
                        List<OperacionDto> lista = new ArrayList<>();

                        for (OperationEntity operation : operations) {
                            OperacionDto operacionDto = new OperacionDto();

                            operacionDto.setIdOperacion(operation.id.toString());
                            operacionDto.setCelular(operation.getNumberPhone());
                            operacionDto.setCodigoBilletera(operation.getCodeWallet());
                            operacionDto.setCodigoPromocion(operation.getCodePromotion());
                            operacionDto.setDescripcion(operation.getDescription());
                            operacionDto.setFechaOperacion(operation.getOperationDate().toString());
                            operacionDto.setMonto(operation.getAmount());

                            lista.add(operacionDto);
                        }

                        return new ResponseDto(200, messagegeneric, lista);
                    })
                    .onFailure().recoverWithItem(error -> new ResponseDto(400, errorgeneric,
                            error.getMessage()));
        } catch (Exception ex) {
            return Uni.createFrom().item(new ResponseDto(400, errorgeneric, ex.getMessage()));
        }
    }

    public Uni<ResponseDto> getByWallet(String id) {
        return operationRepository.getByWallet(id)
                .map(operations -> {
                    List<OperacionDto> lista = new ArrayList<>();

                    for (OperationEntity operation : operations) {
                        OperacionDto operacionDto = new OperacionDto();

                        operacionDto.setIdOperacion(operation.id.toString());
                        operacionDto.setCelular(operation.getNumberPhone());
                        operacionDto.setCodigoBilletera(operation.getCodeWallet());
                        operacionDto.setCodigoPromocion(operation.getCodePromotion());
                        operacionDto.setDescripcion(operation.getDescription());
                        operacionDto.setFechaOperacion(operation.getOperationDate().toString());
                        operacionDto.setMonto(operation.getAmount());

                        lista.add(operacionDto);
                    }

                    return new ResponseDto(200, messagegeneric, lista);
                })
                .onFailure().recoverWithItem(error -> new ResponseDto(400, errorgeneric,
                        error.getMessage()));
    }

    private OperationEntity toOperation(OperacionDto operacionDto) {
        OperationEntity operation = new OperationEntity();

        operation.setAmount(operacionDto.getMonto());
        operation.setCodeWallet(operacionDto.getCodigoBilletera());
        operation.setCodePromotion(operacionDto.getCodigoPromocion());
        operation.setDescription(operacionDto.getCodigoBilletera());
        operation.setNumberPhone(operacionDto.getCelular());
        // operation.setOperationDate(operacionDto.getFechaOperacion());

        return operation;
    }

    private OperacionDto toOperationDto(OperationEntity operation) {
        OperacionDto operacionDto = new OperacionDto();

        operacionDto.setIdOperacion(operation.id.toString());
        operacionDto.setCelular(operation.getNumberPhone());
        operacionDto.setCodigoBilletera(operation.getCodeWallet());
        operacionDto.setCodigoPromocion(operation.getCodePromotion());
        operacionDto.setDescripcion(operation.getDescription());
        operacionDto.setFechaOperacion(operation.getOperationDate().toString());
        operacionDto.setMonto(operation.getAmount());

        return operacionDto;
    }

}
