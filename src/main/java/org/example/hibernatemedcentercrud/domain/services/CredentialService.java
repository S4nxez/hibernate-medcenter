package org.example.hibernatemedcentercrud.domain.services;

import org.example.hibernatemedcentercrud.dao.repositories.CredentialRepository;
import org.example.hibernatemedcentercrud.domain.model.CredentialUI;
import org.springframework.stereotype.Service;


@Service
public class CredentialService {

    private final CredentialRepository credentialRepository;

    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public boolean get(CredentialUI credentialui) {
        return credentialRepository.getAll().stream()
                .anyMatch(credential ->
                        credential.getPassword().equals(credentialui.getPassword()) &&
                                credential.getUsername().equals(credentialui.getUsername())
                );
    }
}
