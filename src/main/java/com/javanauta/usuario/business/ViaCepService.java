package com.javanauta.usuario.business;

import com.javanauta.usuario.infrastructure.cliets.ViaCepClient;
import com.javanauta.usuario.infrastructure.cliets.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepDTO buscarDadosEndereco(String cep) {
        return client.buscaEnderecoPorCep(processarCep(cep));
    }

    private String processarCep(String cep) {
        String cepFormatado = cep.replace(" ", "")
                .replace("-", "");

        if (!cepFormatado.matches("\\d+") || !Objects.equals(cepFormatado.length(), 8)) {
            try {
                throw new IllegalAccessException("O cep contém caracteres inválidos");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return cepFormatado;
    }

}
