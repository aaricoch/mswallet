package com.nttd.service;

import com.nttd.dto.OperacionDto;
import com.nttd.dto.ResponseDto;

import io.smallrye.mutiny.Uni;

public interface OperationService {

    Uni<ResponseDto> add(OperacionDto operacionDto);

    Uni<ResponseDto> listAll();

    Uni<ResponseDto> getByWallet(String id);

}
