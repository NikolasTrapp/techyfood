package br.techyfood.productexternal.stubs.findproductsbyempresa;

import br.techyfood.productexternal.dtos.ProductDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindProductsByEmpresaOutput {

    @NotNull
    List<ProductDto> products;

}
