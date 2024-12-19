package org.example.hibernatemedcentercrud.dao.repositories.JPA;

import org.example.hibernatemedcentercrud.dao.model.MedRecord;
import org.example.hibernatemedcentercrud.dao.repositories.MedRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedRecordRepositoryImpl implements MedRecordRepository {
    @Override
    public List<MedRecord> getAll(int idPatient) {
        return List.of();
    }

    @Override
    public int add(MedRecord medRecord) {
        return 0;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(MedRecord medRecord) {

    }
}
