package br.techyfood.productapp.queries;

import br.techyfood.productapp.services.ProductService;
import br.techyfood.productexternal.dtos.ProductDto;
import br.techyfood.productexternal.stubs.findproductsbyempresa.FindProductsByEmpresaInput;
import br.techyfood.productexternal.stubs.findproductsbyempresa.FindProductsByEmpresaOutput;
import br.techyfood.rabbitmq.model.ReplicationMessage;
import br.techyfood.restaurantexternal.dtos.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static br.techyfood.core.Utils.convert;
import static br.techyfood.productexternal.ProductExternalConstants.FIND_PRODUCTS_BY_EMPRESA;
import static br.techyfood.rabbitmq.RabbitConstants.EXCHANGE;

@Component
@RequiredArgsConstructor
public class FindProductsByEmpresa {

    private final ProductService productService;

    @Transactional(readOnly = true)
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    value = FIND_PRODUCTS_BY_EMPRESA,
                    durable = "true"
            ),
            exchange = @Exchange(value = EXCHANGE, type = ExchangeTypes.TOPIC),
            key = FIND_PRODUCTS_BY_EMPRESA
    ))
    public FindProductsByEmpresaOutput handleReplicationEvent(@Payload FindProductsByEmpresaInput payload) {
        var products = productService.findAllByRestaurantId(payload.getEmpresaId());
        var dtos = products.stream().map(it -> convert(it, ProductDto.class)).toList();
        return FindProductsByEmpresaOutput.builder().products(dtos).build();
    }

}
