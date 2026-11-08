package com.softer.model;

public enum ProfessionalLevel {
    JUNIOR("Junior"),
    MID_LEVEL("Semi senior"),
    SENIOR("Senior"),
    TECH_LEAD("Líder técnico");

    private final String label;

    ProfessionalLevel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
