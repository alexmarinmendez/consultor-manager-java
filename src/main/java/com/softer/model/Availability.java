package com.softer.model;

public enum Availability {
    AVAILABLE("Disponible"),
    ASSIGNED("Asignado"),
    ON_LEAVE("De licencia"),
    INACTIVE("Inactivo");

    private final String label;

    Availability(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
