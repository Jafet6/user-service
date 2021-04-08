package com.accenture.userservice.utils.feign;

import com.accenture.userservice.domains.CEP;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

public class UseFeign {
    @Bean
    public CommandLineRunner run(Feign client) {
        return args -> {
            String cep = "30350660";
            CEP endereco = client.buscarEnderecoPor(cep);
            System.out.println("\nEndereco\n" + endereco.toString());
        };
    }
}
