package br.techyfood.productexternal.stubs.findproductsbyempresa;

import br.techyfood.rabbitmq.RabbitMessaging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static br.techyfood.productexternal.ProductExternalConstants.FIND_PRODUCTS_BY_EMPRESA;
import static br.techyfood.rabbitmq.RabbitConstants.EXCHANGE;

@Component
@RequiredArgsConstructor
public class FindProductsByEmpresaStubImpl implements FindProductsByEmpresaStub {

    private final RabbitMessaging messaging;

    @Override
    public FindProductsByEmpresaOutput findProductsByEmpresa(FindProductsByEmpresaInput input) {
        return messaging.requestMessage(EXCHANGE, FIND_PRODUCTS_BY_EMPRESA, input, FindProductsByEmpresaOutput.class, true);
    }
}
