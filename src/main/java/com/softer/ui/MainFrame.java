package com.softer.ui;

import com.softer.model.Availability;
import com.softer.model.Consultant;
import com.softer.model.ProfessionalLevel;
import com.softer.model.Specialty;
import com.softer.service.ConsultantService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

public class MainFrame extends JFrame {
    private final ConsultantService service;
    private final ConsultantTableModel tableModel = new ConsultantTableModel();
    private final JTable consultantTable = new JTable(tableModel);

    private final JTextField firstNameField = new JTextField(16);
    private final JTextField lastNameField = new JTextField(16);
    private final JTextField emailField = new JTextField(20);
    private final JTextField phoneField = new JTextField(14);
    private final JTextField hourlyRateField = new JTextField(10);
    private final JComboBox<Specialty> specialtyCombo = new JComboBox<>(Specialty.values());
    private final JComboBox<ProfessionalLevel> levelCombo = new JComboBox<>(ProfessionalLevel.values());
    private final JComboBox<Availability> availabilityCombo = new JComboBox<>(Availability.values());
    private Long selectedConsultantId;

    public MainFrame(ConsultantService service) {
        this.service = service;
        setTitle("Gestión de Consultores Tecnológicos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1050, 650));
        setSize(1250, 720);
        setLocationRelativeTo(null);
        buildInterface();
        loadConsultants();
    }

    private void buildInterface() {
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(new EmptyBorder(14, 14, 14, 14));
        setContentPane(root);

        JLabel title = new JLabel("Gestión de Consultores Tecnológicos");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        root.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(10, 10));
        center.add(buildForm(), BorderLayout.NORTH);

        consultantTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        consultantTable.setAutoCreateRowSorter(true);
        consultantTable.setFillsViewportHeight(true);
        consultantTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) fillFormFromSelection();
        });
        center.add(new JScrollPane(consultantTable), BorderLayout.CENTER);
        root.add(center, BorderLayout.CENTER);
        root.add(buildActions(), BorderLayout.SOUTH);
    }

    private JPanel buildForm() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos del consultor"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 7, 5, 7);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        addField(panel, c, 0, 0, "Nombres:", firstNameField);
        addField(panel, c, 2, 0, "Apellidos:", lastNameField);
        addField(panel, c, 0, 1, "Correo:", emailField);
        addField(panel, c, 2, 1, "Teléfono:", phoneField);
        addField(panel, c, 0, 2, "Tarifa por hora:", hourlyRateField);
        addField(panel, c, 2, 2, "Especialidad:", specialtyCombo);
        addField(panel, c, 0, 3, "Nivel profesional:", levelCombo);
        addField(panel, c, 2, 3, "Disponibilidad:", availabilityCombo);
        return panel;
    }

    private void addField(JPanel panel, GridBagConstraints c, int x, int y,
                          String label, JComponent field) {
        c.gridx = x;
        c.gridy = y;
        c.weightx = 0;
        panel.add(new JLabel(label), c);
        c.gridx = x + 1;
        c.weightx = 1;
        panel.add(field, c);
    }

    private JPanel buildActions() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton saveButton = new JButton("Guardar");
        JButton newButton = new JButton("Nuevo / Limpiar");
        JButton deleteButton = new JButton("Eliminar");
        JButton refreshButton = new JButton("Actualizar lista");
        JButton statisticsButton = new JButton("Estadísticas");

        saveButton.addActionListener(event -> saveConsultant());
        newButton.addActionListener(event -> clearForm());
        deleteButton.addActionListener(event -> deleteConsultant());
        refreshButton.addActionListener(event -> loadConsultants());
        statisticsButton.addActionListener(event -> showStatistics());

        panel.add(saveButton);
        panel.add(newButton);
        panel.add(deleteButton);
        panel.add(refreshButton);
        panel.add(statisticsButton);
        return panel;
    }

    private void loadConsultants() {
        try {
            tableModel.setConsultants(service.getAllConsultants());
        } catch (SQLException exception) {
            showError("No se pudo consultar la lista de consultores.", exception);
        }
    }

    private void saveConsultant() {
        try {
            BigDecimal hourlyRate = new BigDecimal(hourlyRateField.getText().trim().replace(",", "."));
            Consultant consultant = new Consultant(
                    selectedConsultantId,
                    firstNameField.getText().trim(),
                    lastNameField.getText().trim(),
                    emailField.getText().trim(),
                    phoneField.getText().trim(),
                    hourlyRate,
                    (Specialty) specialtyCombo.getSelectedItem(),
                    (ProfessionalLevel) levelCombo.getSelectedItem(),
                    (Availability) availabilityCombo.getSelectedItem()
            );
            service.saveConsultant(consultant);
            JOptionPane.showMessageDialog(this, "Los datos del consultor se guardaron correctamente.",
                    "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            loadConsultants();
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Ingresa una tarifa válida. Ejemplo: 85.50",
                    "Dato inválido", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(),
                    "Revisa los datos", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException exception) {
            showError("No se pudieron guardar los datos. Verifica que el correo no esté registrado.", exception);
        }
    }

    private void fillFormFromSelection() {
        int viewRow = consultantTable.getSelectedRow();
        if (viewRow < 0) return;
        int modelRow = consultantTable.convertRowIndexToModel(viewRow);
        Consultant consultant = tableModel.getConsultantAt(modelRow);
        selectedConsultantId = consultant.getId();
        firstNameField.setText(consultant.getFirstName());
        lastNameField.setText(consultant.getLastName());
        emailField.setText(consultant.getEmail());
        phoneField.setText(consultant.getPhone());
        hourlyRateField.setText(consultant.getHourlyRate().toPlainString());
        specialtyCombo.setSelectedItem(consultant.getSpecialty());
        levelCombo.setSelectedItem(consultant.getProfessionalLevel());
        availabilityCombo.setSelectedItem(consultant.getAvailability());
    }

    private void deleteConsultant() {
        if (selectedConsultantId == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un consultor de la lista.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int answer = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar al consultor seleccionado?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (answer != JOptionPane.YES_OPTION) return;
        try {
            service.deleteConsultant(selectedConsultantId);
            clearForm();
            loadConsultants();
        } catch (SQLException exception) {
            showError("No se pudo eliminar el consultor.", exception);
        }
    }

    private void showStatistics() {
        try {
            Map<String, BigDecimal> statistics = service.getAverageRateBySpecialty();
            StringBuilder message = new StringBuilder("Tarifa promedio por especialidad\n\n");
            statistics.forEach((specialty, average) -> message.append(specialty)
                    .append(": ")
                    .append(average == null ? "Sin registros" : average.setScale(2, java.math.RoundingMode.HALF_UP))
                    .append("\n"));
            JOptionPane.showMessageDialog(this, message.toString(),
                    "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException exception) {
            showError("No se pudieron calcular las estadísticas.", exception);
        }
    }

    private void clearForm() {
        selectedConsultantId = null;
        consultantTable.clearSelection();
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        hourlyRateField.setText("");
        specialtyCombo.setSelectedIndex(0);
        levelCombo.setSelectedIndex(0);
        availabilityCombo.setSelectedIndex(0);
        firstNameField.requestFocus();
    }

    private void showError(String message, Exception exception) {
        JOptionPane.showMessageDialog(this, message + "\n\nDetalle: " + exception.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
