package com.softer.service;

import com.softer.model.Consultant;
import com.softer.repository.ConsultantRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ConsultantService {
    private final ConsultantRepository repository;

    public ConsultantService(ConsultantRepository repository) {
        this.repository = repository;
    }

    public List<Consultant> getAllConsultants() throws SQLException {
        return repository.findAll();
    }

    public void saveConsultant(Consultant consultant) throws SQLException {
        validate(consultant);
        repository.save(consultant);
    }

    public void deleteConsultant(long id) throws SQLException {
        if (id <= 0) throw new IllegalArgumentException("El consultor seleccionado no es válido.");
        repository.deleteById(id);
    }

    public Map<String, BigDecimal> getAverageRateBySpecialty() throws SQLException {
        return repository.findAverageRateBySpecialty();
    }

    private void validate(Consultant consultant) {
        if (consultant.getFirstName() == null || consultant.getFirstName().isBlank())
            throw new IllegalArgumentException("Ingresa los nombres del consultor.");
        if (consultant.getLastName() == null || consultant.getLastName().isBlank())
            throw new IllegalArgumentException("Ingresa los apellidos del consultor.");
        if (consultant.getEmail() == null || !consultant.getEmail().matches("^[\\w.!#$%&'*+/=?^`{|}~-]+@[\\w-]+(\\.[\\w-]+)+$"))
            throw new IllegalArgumentException("Ingresa un correo electrónico válido.");
        if (consultant.getHourlyRate() == null || consultant.getHourlyRate().compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("La tarifa por hora debe ser un número mayor o igual a cero.");
        if (consultant.getSpecialty() == null || consultant.getProfessionalLevel() == null
                || consultant.getAvailability() == null)
            throw new IllegalArgumentException("Selecciona la especialidad, el nivel y la disponibilidad.");
    }
}
