package org.example.hibernatemedcentercrud.dao.repositories;


import org.example.hibernatemedcentercrud.dao.model.MedRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedRecordRepository {

    List<MedRecord> getAll(int idPatient);

    int add(MedRecord medRecord);

    void delete(int id);

    void update(MedRecord medRecord);
}
