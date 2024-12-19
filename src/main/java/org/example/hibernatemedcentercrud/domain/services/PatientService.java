package org.example.hibernatemedcentercrud.domain.services;


import org.example.hibernatemedcentercrud.dao.model.Credential;
import org.example.hibernatemedcentercrud.dao.model.Patient;
import org.example.hibernatemedcentercrud.dao.model.Payment;
import org.example.hibernatemedcentercrud.dao.repositories.PatientRepository;
import org.example.hibernatemedcentercrud.dao.repositories.PaymentRepository;
import org.example.hibernatemedcentercrud.domain.model.PatientUI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PaymentRepository paymentRepository;

    public PatientService(PatientRepository patientRepository, PaymentRepository paymentRepository) {
        this.patientRepository = patientRepository;
        this.paymentRepository = paymentRepository;
    }

    public List<PatientUI> get(){
        List<Patient> patient = patientRepository.getAll();
        List<PatientUI> patientsUI = new ArrayList<>();
        List<Payment> payments = paymentRepository.getAll();

        patient.forEach(patient1 ->
                patientsUI.add(new PatientUI(patient1.getId(),patient1.getName(), patient1.getBirthDate(),patient1.getPhone(),payments.stream()
                        .filter(payment -> payment.getPatient_id() == patient1.getId())
                        .map(Payment::getAmount)
                        .reduce(0f, Float::sum)
                        .intValue(),null,null)
                ));
        return patientsUI;
    }

   public int add(PatientUI patientui) { //TODO hay 2 ids ahí fijos que no deberían
//        Patient patient=new Patient(patientui.getBirthDate(),
  //              new Credential(2,patientui.getUserName(), patientui.getPassword(),2, patientui.getId()),
    //            patientui.getId(),patientui.getName(),patientui.getPhone());

    //    return patientRepository.add(patient);
    return 1;
    }

    public void update(PatientUI patientui) {
        Patient patient = new Patient(patientui.getBirthDate(),
                new Credential(patientui.getUserName(), patientui.getPassword(), patientui.getId()),
                patientui.getId(), patientui.getName(), patientui.getPhone());

        patientRepository.update(patient);
    }

    public void delete(int idDelete, boolean confirm) {
        patientRepository.delete(idDelete, confirm);
    }
}