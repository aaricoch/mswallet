package com.nttd.resource;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.nttd.dto.BilleteraDto;
import com.nttd.dto.ResponseDto;
import com.nttd.service.WalletService;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/wallet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletResource {

    @Inject
    WalletService walletService;

    @POST
    @Operation(summary = "Registrar la billetera", description = "Registrar la billetera")
    public Uni<ResponseDto> add(BilleteraDto billeteraDto) {
        return walletService.add(billeteraDto);
    }

    @GET
    @Operation(summary = "Obtener la lista de billeteras", description = "Obtener la lista de billeteras")
    public Uni<ResponseDto> listAll() {
        return walletService.listAll();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Obtener billetera", description = "Obtener billetera")
    public Uni<ResponseDto> getById(@PathParam("id") String id) {
        return walletService.getById(id);
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Modificar billetera", description = "Modificar billetera")
    public Uni<ResponseDto> update(@PathParam("id") String id, BilleteraDto billeteraDto) {
        return walletService.edit(id, billeteraDto);
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Eliminar una billetera", description = "Permite eliminar una billetera")
    public Uni<ResponseDto> delete(@PathParam("id") String id) {
        return walletService.remove(id);
    }

}
