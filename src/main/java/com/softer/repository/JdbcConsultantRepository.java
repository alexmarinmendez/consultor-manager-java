package com.softer.repository;

import com.softer.config.DatabaseConfig;
import com.softer.model.Availability;
import com.softer.model.Consultant;
import com.softer.model.ProfessionalLevel;
import com.softer.model.Specialty;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class JdbcConsultantRepository implements ConsultantRepository {
    private final DatabaseConfig databaseConfig;

    public JdbcConsultantRepository(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public List<Consultant> findAll() throws SQLException {
        String sql = """
                SELECT id, first_name, last_name, email, phone, hourly_rate,
                       specialty_code, professional_level_code, availability_code
                FROM consultants
                ORDER BY last_name, first_name
                """;
        List<Consultant> consultants = new ArrayList<>();
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                consultants.add(mapRow(resultSet));
            }
        }
        return consultants;
    }

    @Override
    public void save(Consultant consultant) throws SQLException {
        if (consultant.getId() == null) {
            String sql = """
                    INSERT INTO consultants
                    (first_name, last_name, email, phone, hourly_rate,
                     specialty_code, professional_level_code, availability_code)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            try (Connection connection = databaseConfig.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                bindConsultant(statement, consultant);
                statement.executeUpdate();
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (keys.next()) consultant.setId(keys.getLong(1));
                }
            }
        } else {
            String sql = """
                    UPDATE consultants
                    SET first_name=?, last_name=?, email=?, phone=?, hourly_rate=?,
                        specialty_code=?, professional_level_code=?, availability_code=?
                    WHERE id=?
                    """;
            try (Connection connection = databaseConfig.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                bindConsultant(statement, consultant);
                statement.setLong(9, consultant.getId());
                statement.executeUpdate();
            }
        }
    }

    private void bindConsultant(PreparedStatement statement, Consultant consultant) throws SQLException {
        statement.setString(1, consultant.getFirstName());
        statement.setString(2, consultant.getLastName());
        statement.setString(3, consultant.getEmail());
        statement.setString(4, consultant.getPhone());
        statement.setBigDecimal(5, consultant.getHourlyRate());
        statement.setString(6, consultant.getSpecialty().name());
        statement.setString(7, consultant.getProfessionalLevel().name());
        statement.setString(8, consultant.getAvailability().name());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM consultants WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public Map<String, BigDecimal> findAverageRateBySpecialty() throws SQLException {
        String sql = """
                SELECT s.name_es, AVG(c.hourly_rate) AS average_rate
                FROM specialties s
                LEFT JOIN consultants c ON c.specialty_code = s.code
                GROUP BY s.code, s.name_es
                ORDER BY s.name_es
                """;
        Map<String, BigDecimal> averages = new LinkedHashMap<>();
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                averages.put(resultSet.getString("name_es"), resultSet.getBigDecimal("average_rate"));
            }
        }
        return averages;
    }

    private Consultant mapRow(ResultSet resultSet) throws SQLException {
        return new Consultant(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getBigDecimal("hourly_rate"),
                Specialty.valueOf(resultSet.getString("specialty_code")),
                ProfessionalLevel.valueOf(resultSet.getString("professional_level_code")),
                Availability.valueOf(resultSet.getString("availability_code"))
        );
    }
}
