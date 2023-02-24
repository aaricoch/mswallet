package com.nttd.service;

import com.nttd.dto.BilleteraDto;
import com.nttd.dto.ResponseDto;

import io.smallrye.mutiny.Uni;

public interface WalletService {

    Uni<ResponseDto> add(BilleteraDto billeteraDto);

    Uni<ResponseDto> listAll();

    Uni<ResponseDto> getById(String id);

    Uni<ResponseDto> edit(String id, BilleteraDto billeteraDto);

    Uni<ResponseDto> remove(String id);

}
