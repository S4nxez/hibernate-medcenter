package org.example.hibernatemedcentercrud.domain.services;

import jakarta.persistence.EntityManager;
import org.example.hibernatemedcentercrud.dao.JPAUtil;
import org.example.hibernatemedcentercrud.dao.model.Doctor;
import org.example.hibernatemedcentercrud.dao.model.MedRecord;
import org.example.hibernatemedcentercrud.dao.model.Medication;
import org.example.hibernatemedcentercrud.dao.model.Patient;
import org.example.hibernatemedcentercrud.dao.repositories.MedRecordRepository;
import org.example.hibernatemedcentercrud.domain.model.MedRecordUI;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class MedRecordService {


    private final JPAUtil jpautil;
    private EntityManager em;
    final private MedRecordRepository medRecordRepository;
    private final DoctorService doctorService;
    private PatientService patientService;

    public MedRecordService(MedRecordRepository medRecordRepository,
                            PatientService patientService, DoctorService doctorService, JPAUtil jpautil) {
        this.medRecordRepository = medRecordRepository;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.jpautil = jpautil;
    }


    public List<MedRecordUI> getAll(int idPatient) {

        List<MedRecordUI> medRecordUIList = new ArrayList<>();

        medRecordRepository.getAll(idPatient).forEach(medRecord -> {
            List<String> listMedications = new ArrayList<>();
            if (medRecord.getMedications() != null)
                medRecord.getMedications()
                        .forEach(medication -> listMedications.add(medication.getMedicationName()));
            medRecordUIList.add(new MedRecordUI(medRecord.getId(),
                    medRecord.getDiagnosis(), medRecord.getDate().toString(), medRecord.getPatient().getId()
                    , medRecord.getDoctor().getId(), listMedications));

        });
        return medRecordUIList;
    }

    public int add(MedRecordUI medRecordui) {
        ArrayList<Medication> medications = new ArrayList<>();
        LocalDate date = LocalDate.parse(medRecordui.getDate().toString());

        MedRecord medRecord = new MedRecord(
                medRecordui.getId(),
                patientService.getPatientById(medRecordui.getIdPatient()),
                doctorService.getDoctorById(medRecordui.getIdDoctor()),
                medRecordui.getDescription(),
                date,
                medications
        );

        medRecordui.getMedications().forEach(medication ->
                medications.add(new Medication(medication, medRecord)));

        return medRecordRepository.add(medRecord);
    }


    public void delete(int id) {
        medRecordRepository.delete(id);
    }

    public void update(MedRecordUI medRecordui) {
        ArrayList<Medication> medications = new ArrayList<>();
        LocalDate date = LocalDate.parse(medRecordui.getDate().toString());

        Patient patient = patientService.getPatientById(medRecordui.getIdPatient());
        Doctor doctor = doctorService.getDoctorById(medRecordui.getIdDoctor());

        em = jpautil.getEntityManager();
        patient = em.merge(patient);
        doctor = em.merge(doctor);

        MedRecord medRecord = new MedRecord(medRecordui.getId(),
                patient,
                doctor,
                medRecordui.getDescription(),
                date,
                medications);

        medRecordui.getMedications().forEach(medication ->
                medications.add(new Medication(medication, medRecord)));

        medRecordRepository.update(medRecord);
    }
}
