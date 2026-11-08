package com.softer;

import com.softer.config.DatabaseConfig;
import com.softer.repository.JdbcConsultantRepository;
import com.softer.service.ConsultantService;
import com.softer.ui.MainFrame;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseConfig databaseConfig = new DatabaseConfig();
                ConsultantService service = new ConsultantService(
                        new JdbcConsultantRepository(databaseConfig)
                );
                MainFrame frame = new MainFrame(service);
                frame.setVisible(true);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(
                        null,
                        "No se pudo iniciar la aplicación. Verifica MySQL y la configuración de conexión.\n\n"
                                + exception.getMessage(),
                        "Error de conexión",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
