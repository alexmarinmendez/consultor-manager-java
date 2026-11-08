package com.softer.repository;

import com.softer.model.Consultant;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ConsultantRepository {
    List<Consultant> findAll() throws SQLException;
    void save(Consultant consultant) throws SQLException;
    void deleteById(long id) throws SQLException;
    Map<String, BigDecimal> findAverageRateBySpecialty() throws SQLException;
}
