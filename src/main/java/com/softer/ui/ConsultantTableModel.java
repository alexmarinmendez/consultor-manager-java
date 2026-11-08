package com.softer.ui;

import com.softer.model.Consultant;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ConsultantTableModel extends AbstractTableModel {
    private final String[] columns = {
            "ID", "Nombres", "Apellidos", "Correo", "Teléfono",
            "Tarifa por hora", "Especialidad", "Nivel", "Disponibilidad"
    };
    private List<Consultant> consultants = new ArrayList<>();

    public void setConsultants(List<Consultant> consultants) {
        this.consultants = new ArrayList<>(consultants);
        fireTableDataChanged();
    }

    public Consultant getConsultantAt(int row) {
        return consultants.get(row);
    }

    @Override
    public int getRowCount() { return consultants.size(); }

    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public String getColumnName(int column) { return columns[column]; }

    @Override
    public Object getValueAt(int row, int column) {
        Consultant consultant = consultants.get(row);
        return switch (column) {
            case 0 -> consultant.getId();
            case 1 -> consultant.getFirstName();
            case 2 -> consultant.getLastName();
            case 3 -> consultant.getEmail();
            case 4 -> consultant.getPhone();
            case 5 -> consultant.getHourlyRate();
            case 6 -> consultant.getSpecialty();
            case 7 -> consultant.getProfessionalLevel();
            case 8 -> consultant.getAvailability();
            default -> "";
        };
    }
}
