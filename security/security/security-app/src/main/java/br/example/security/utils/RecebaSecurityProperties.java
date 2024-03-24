package br.example.security.utils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Component
@Validated
public class RecebaSecurityProperties {

    @NotBlank(message = "providerUrl cannot be empty.")
    @Value("${techyfood.security.provider-url}")
    private String providerUrl;

    @NotNull(message = "jksLocation cannot be null.")
    @Value("${techyfood.security.jksLocation}")
    private Resource jksLocation;

    @NotBlank(message = "password cannot be empty.")
    @Value("${techyfood.security.password}")
    private String password;

    @NotBlank(message = "keypairAlias cannot be empty.")
    @Value("${techyfood.security.keypair-alias}")
    private String keypairAlias;
}
