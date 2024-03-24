package br.techyfood.rabbitmq.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplicationMessage <T> {

    @NotNull(message = "Entity can't be null.")
    private T entity;

    @NotNull(message = "Operation can't be null.")
    private Operation operation;

}
