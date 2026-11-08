# Consultor Manager

Aplicación de escritorio para administrar consultores tecnológicos. Está construida con Java 25, Swing, JDBC, MySQL y Gradle.

## Funcionalidades

- Registrar, consultar, actualizar y eliminar consultores.
- Asignar especialidad, nivel profesional y disponibilidad.
- Consultar la tarifa promedio por especialidad.
- Interfaz y mensajes en español.
- Código, paquetes, clases, métodos, variables, tablas y columnas en inglés.

## Arquitectura

```text
src/main/java/pe/cibertec/consultormanager/
├── App.java
├── config/
│   └── DatabaseConfig.java
├── model/
│   ├── Availability.java
│   ├── Consultant.java
│   ├── ProfessionalLevel.java
│   └── Specialty.java
├── repository/
│   ├── ConsultantRepository.java
│   └── JdbcConsultantRepository.java
├── service/
│   └── ConsultantService.java
└── ui/
    ├── ConsultantTableModel.java
    └── MainFrame.java
```

- **model:** entidades y catálogos del dominio.
- **repository:** acceso a datos mediante JDBC.
- **service:** reglas y validaciones del negocio.
- **ui:** interfaz gráfica Swing.
- **config:** configuración de conexión a MySQL.

## Requisitos

- JDK 25 (no basta con tener solamente un JRE).
- IntelliJ IDEA con soporte para Java 25.
- MySQL Server 8.0 o superior.
- Gradle 9.2 o superior, o el soporte Gradle integrado en IntelliJ.

## 1. Crear la base de datos

Abre MySQL Workbench o una consola MySQL y ejecuta el archivo:

`database/schema.sql`

El script crea la base `consultant_manager`, las tablas de catálogo, la tabla `consultants` y los valores iniciales de especialidades, niveles y disponibilidad.

## 2. Configurar la conexión

La aplicación lee estas variables de entorno:

| Variable | Valor predeterminado |
|---|---|
| `DB_URL` | `jdbc:mysql://localhost:3306/consultant_manager?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true` |
| `DB_USERNAME` | `root` |
| `DB_PASSWORD` | *(vacío)* |

En Windows PowerShell, por ejemplo:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/consultant_manager?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="tu_clave"
```

Ajusta los valores a tu instalación. Para un entorno real, crea un usuario MySQL específico para la aplicación y evita usar `root`.

## 3. Abrir en IntelliJ IDEA

1. Descomprime el ZIP.
2. En IntelliJ IDEA selecciona **Open** y elige la carpeta `consultor-manager-java`.
3. Cuando IntelliJ pregunte, importa el proyecto como proyecto Gradle.
4. Configura el **Gradle JVM** con JDK 25.
5. Espera a que Gradle descargue el conector JDBC.

## 4. Ejecutar

Desde la ventana Gradle de IntelliJ ejecuta:

`Tasks > application > run`

O desde una terminal ubicada en la raíz del proyecto:

```powershell
gradle run
```

## Catálogos

Los valores iniciales están en las tablas `specialties`, `professional_levels` y `availabilities`. La interfaz permite asignar estos valores a cada consultor mediante listas desplegables. Para agregar o retirar opciones del catálogo, modifica los registros de dichas tablas y agrega el código correspondiente al enum Java.

Códigos almacenados en la base de datos (en inglés):

- Especialidades: `SOFTWARE_DEVELOPMENT`, `DATA_ANALYTICS`, `CLOUD_DEVOPS`, `CYBERSECURITY`, `SOFTWARE_QUALITY`, `DIGITAL_TRANSFORMATION`.
- Niveles: `JUNIOR`, `MID_LEVEL`, `SENIOR`, `TECH_LEAD`.
- Disponibilidad: `AVAILABLE`, `ASSIGNED`, `ON_LEAVE`, `INACTIVE`.

## Notas

- La tarifa se registra como importe por hora y se almacena en `DECIMAL(10,2)`.
- El resumen estadístico calcula el promedio de `hourly_rate` agrupado por especialidad.
- Este proyecto es una base funcional para aprendizaje y ampliación. No incluye autenticación, control de permisos ni empaquetado como instalador de Windows.
