package org.example.hibernatemedcentercrud.dao.repositories;


import org.example.hibernatemedcentercrud.dao.model.Credential;

import java.util.List;

public interface CredentialRepository{
     List<Credential> getAll();
}
