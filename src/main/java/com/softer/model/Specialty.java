package com.softer.model;

public enum Specialty {
    SOFTWARE_DEVELOPMENT("Desarrollo de software"),
    DATA_ANALYTICS("Datos y analítica"),
    CLOUD_DEVOPS("Cloud y DevOps"),
    CYBERSECURITY("Ciberseguridad"),
    SOFTWARE_QUALITY("Calidad de software"),
    DIGITAL_TRANSFORMATION("Transformación digital");

    private final String label;

    Specialty(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
