CREATE DATABASE IF NOT EXISTS consultant_manager
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE consultant_manager;

CREATE TABLE IF NOT EXISTS specialties (
    code VARCHAR(40) PRIMARY KEY,
    name_es VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS professional_levels (
    code VARCHAR(30) PRIMARY KEY,
    name_es VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS availabilities (
    code VARCHAR(30) PRIMARY KEY,
    name_es VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS consultants (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(30),
    hourly_rate DECIMAL(10,2) NOT NULL,
    specialty_code VARCHAR(40) NOT NULL,
    professional_level_code VARCHAR(30) NOT NULL,
    availability_code VARCHAR(30) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_consultants_specialty
        FOREIGN KEY (specialty_code) REFERENCES specialties(code),
    CONSTRAINT fk_consultants_level
        FOREIGN KEY (professional_level_code) REFERENCES professional_levels(code),
    CONSTRAINT fk_consultants_availability
        FOREIGN KEY (availability_code) REFERENCES availabilities(code),
    CONSTRAINT chk_consultants_hourly_rate CHECK (hourly_rate >= 0)
);

INSERT IGNORE INTO specialties (code, name_es) VALUES
('SOFTWARE_DEVELOPMENT', 'Desarrollo de software'),
('DATA_ANALYTICS', 'Datos y analítica'),
('CLOUD_DEVOPS', 'Cloud y DevOps'),
('CYBERSECURITY', 'Ciberseguridad'),
('SOFTWARE_QUALITY', 'Calidad de software'),
('DIGITAL_TRANSFORMATION', 'Transformación digital');

INSERT IGNORE INTO professional_levels (code, name_es) VALUES
('JUNIOR', 'Junior'),
('MID_LEVEL', 'Semi senior'),
('SENIOR', 'Senior'),
('TECH_LEAD', 'Líder técnico');

INSERT IGNORE INTO availabilities (code, name_es) VALUES
('AVAILABLE', 'Disponible'),
('ASSIGNED', 'Asignado'),
('ON_LEAVE', 'De licencia'),
('INACTIVE', 'Inactivo');



INSERT IGNORE INTO consultants (
    first_name,
    last_name,
    email,
    phone,
    hourly_rate,
    specialty_code,
    professional_level_code,
    availability_code
)
VALUES (
           'Alex',
           'Marín Méndez',
           'alexmarinmendez@gmail.com',
           '942270712',
           180.00,
           'SOFTWARE_DEVELOPMENT',
           'TECH_LEAD',
           'AVAILABLE'
       );