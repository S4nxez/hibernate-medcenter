package org.example.hibernatemedcentercrud.ui;

import org.example.hibernatemedcentercrud.domain.errors.WrongCredentialsError;
import org.example.hibernatemedcentercrud.domain.model.CredentialUI;
import org.example.hibernatemedcentercrud.domain.services.CredentialService;
import org.example.hibernatemedcentercrud.utils.Constantes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCredentials {

    private CredentialService credentialService;

    public RestCredentials(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/login")
    public boolean getCredential(@RequestBody CredentialUI credentialui) {
        String result = credentialService.get(credentialui);
        if (Constantes.SUCCESS.equals(result)) {
            return true;
        } else {
            throw new WrongCredentialsError(result);
        }
    }
}
