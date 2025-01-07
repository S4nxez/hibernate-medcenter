package org.example.hibernatemedcentercrud.domain.services;

import org.example.hibernatemedcentercrud.dao.repositories.CredentialRepository;
import org.example.hibernatemedcentercrud.domain.model.CredentialUI;
import org.example.hibernatemedcentercrud.utils.Constantes;
import org.springframework.stereotype.Service;


@Service
public class CredentialService {


    private final CredentialRepository credentialRepository;

    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public String get(CredentialUI credentialui) {
    return credentialRepository.getAll().stream()
            .filter(credential -> credential.getUsername().equals(credentialui.getUsername()))
            .findFirst()
            .map(credential -> {
                if (credential.getPassword().equals(credentialui.getPassword())) {
                    return Constantes.SUCCESS;
                } else {
                    return Constantes.E_WRONG_PWD;
                }
            })
            .orElse(Constantes.E_WRONG_USR);
}
}
