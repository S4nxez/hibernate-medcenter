package org.example.hibernatemedcentercrud.domain.services;

import org.example.hibernatemedcentercrud.dao.model.MedRecord;
import org.example.hibernatemedcentercrud.dao.model.Medication;
import org.example.hibernatemedcentercrud.dao.repositories.MedRecordRepository;
import org.example.hibernatemedcentercrud.domain.model.MedRecordUI;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class MedRecordService {


    final private MedRecordRepository medRecordRepository;

    public MedRecordService(MedRecordRepository medRecordRepository) {this.medRecordRepository = medRecordRepository;}


    public List<MedRecordUI> getAll(int idPatient) {

        List<MedRecordUI> medRecordUIList=new ArrayList<>();

        medRecordRepository.getAll(idPatient).forEach(medRecord -> {
            List<String> listMedications=new ArrayList<>();
            if (medRecord.getMedications() != null)
                medRecord.getMedications().forEach(medication -> listMedications.add(medication.getMedicationName()));
            medRecordUIList.add(new MedRecordUI(medRecord.getId(), medRecord.getDiagnosis(), medRecord.getDate().toString(), medRecord.getIdPatient()
            , medRecord.getIdDoctor(),listMedications));

        });

        return medRecordUIList;

    }

    public int add(MedRecordUI medRecordui) {

        ArrayList<Medication> medications = new ArrayList<>();
        LocalDate date=LocalDate.parse(medRecordui.getDate().toString());

        medRecordui.getMedications().forEach(medication ->
            medications.add(new Medication(medRecordui.getIdPatient(), medRecordui.getMedications().toString(), medRecordui.getId())));

        MedRecord medRecord= new MedRecord(medRecordui.getId(), medRecordui.getIdPatient(), medRecordui.getIdDoctor(),
                medRecordui.getDescription(), date,medications);

        return medRecordRepository.add(medRecord);
    }

    public void delete(int id) {
        medRecordRepository.delete(id);
    }

    public void update(MedRecordUI medRecordui) {

        ArrayList<Medication> medications = new ArrayList<>();
        LocalDate date=LocalDate.parse(medRecordui.getDate().toString());

        medRecordui.getMedications().forEach(medication ->
            medications.add(new Medication(medRecordui.getIdPatient(), medRecordui.getMedications().toString(), medRecordui.getId())));

        MedRecord medRecord= new MedRecord(medRecordui.getId(), medRecordui.getIdPatient(), medRecordui.getIdDoctor(),
                medRecordui.getDescription(), date,medications);

        medRecordRepository.update(medRecord);
    }
}
