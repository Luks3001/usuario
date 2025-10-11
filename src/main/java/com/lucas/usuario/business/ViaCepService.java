package com.lucas.usuario.business;

import com.lucas.usuario.infrastructure.clients.ViaCepClient;
import com.lucas.usuario.infrastructure.clients.ViaCepDTO;
import com.lucas.usuario.infrastructure.exceptions.CepInvalidoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepDTO buscarDadosEndereco(String cep) {

        return client.buscaDadosEndereco(processarCep(cep));
    }

    private String processarCep(String cep) {
        String cepFormatado = cep.replace(" ", "")
                .replace("-", "");

        if (!cepFormatado.matches("\\d+")
                || !Objects.equals(cepFormatado.length(), 8)) {
            throw new CepInvalidoException("O cep contém caracteres inválidos, favor verificar");
        }
        return cepFormatado;
    }
}
