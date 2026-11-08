package com.softer.model;

import java.math.BigDecimal;

public class Consultant {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private BigDecimal hourlyRate;
    private Specialty specialty;
    private ProfessionalLevel professionalLevel;
    private Availability availability;

    public Consultant() {
    }

    public Consultant(Long id, String firstName, String lastName, String email,
                      String phone, BigDecimal hourlyRate, Specialty specialty,
                      ProfessionalLevel professionalLevel, Availability availability) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hourlyRate = hourlyRate;
        this.specialty = specialty;
        this.professionalLevel = professionalLevel;
        this.availability = availability;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public BigDecimal getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(BigDecimal hourlyRate) { this.hourlyRate = hourlyRate; }
    public Specialty getSpecialty() { return specialty; }
    public void setSpecialty(Specialty specialty) { this.specialty = specialty; }
    public ProfessionalLevel getProfessionalLevel() { return professionalLevel; }
    public void setProfessionalLevel(ProfessionalLevel professionalLevel) { this.professionalLevel = professionalLevel; }
    public Availability getAvailability() { return availability; }
    public void setAvailability(Availability availability) { this.availability = availability; }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
