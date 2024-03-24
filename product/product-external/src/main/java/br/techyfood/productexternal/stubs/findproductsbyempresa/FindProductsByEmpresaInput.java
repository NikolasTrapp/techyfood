package br.techyfood.productexternal.stubs.findproductsbyempresa;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindProductsByEmpresaInput {

    @NotNull
    private UUID empresaId;

}
