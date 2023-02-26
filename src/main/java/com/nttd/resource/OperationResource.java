package com.nttd.resource;

import jakarta.ws.rs.Produces;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.nttd.dto.OperacionDto;
import com.nttd.dto.ResponseDto;
import com.nttd.service.OperationService;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;

@Path("/api/operation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OperationResource {

    @Inject
    OperationService operationService;

    @POST
    @Path("/pay")
    @Operation(summary = "Realizar pago", description = "Realizar pago")
    public Uni<ResponseDto> add(OperacionDto operacionDto) {
        return operationService.add(operacionDto);
    }

    @GET
    @Operation(summary = "Obtener la lista de operaciones", description = "Obtener la lista de operaciones")
    public Uni<ResponseDto> listAll() {
        return operationService.listAll();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Obtener operaciones de la  billetera", description = "Obtener operaciones de la billetera")
    public Uni<ResponseDto> getByWallet(@PathParam("id") String id) {
        return operationService.getByWallet(id);
    }

}
