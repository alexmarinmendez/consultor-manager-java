# Especificación de Requerimientos Funcionales

## Sistema de Gestión de Consultores Tecnológicos

**Código del documento:** ERF-SGCT-001  
**Versión:** 1.0  
**Estado:** Propuesta para revisión  
**Organización:** Por definir  
**Tipo de documento:** Especificación de Requerimientos Funcionales  
**Fecha:** 08/11/2026

---

## 1. Control del documento

| Campo | Detalle |
|---|---|
| Nombre del sistema | Sistema de Gestión de Consultores Tecnológicos |
| Sigla | SGCT |
| Propietario del producto | Por definir |
| Versión | 1.0 |
| Estado | Propuesta para revisión |
| Audiencia | Patrocinadores, usuarios clave, analistas, desarrolladores, personal de calidad y responsables de soporte |

### 1.1 Historial de versiones

| Versión | Descripción del cambio | Responsable | Fecha |
|---|---|---|---|
| 1.0 | Formalización de los requerimientos iniciales del Sistema de Gestión de Consultores Tecnológicos | Por definir | 08/11/2026 |

### 1.2 Aprobaciones

| Rol | Nombre | Estado | Fecha |
|---|---|---|---|
| Patrocinador del proyecto | Por definir | Pendiente | Por definir |
| Propietario del producto | Por definir | Pendiente | Por definir |
| Responsable de tecnología | Por definir | Pendiente | Por definir |
| Responsable de calidad | Por definir | Pendiente | Por definir |

---

## 2. Propósito

El presente documento especifica los requerimientos funcionales del Sistema de Gestión de Consultores Tecnológicos. El sistema permitirá registrar, consultar, actualizar y eliminar consultores; asignarles una especialidad, un nivel profesional y un estado de disponibilidad; y consultar estadísticas de tarifas promedio por especialidad.

La especificación establece funcionalidades verificables, reglas de negocio, restricciones técnicas y criterios de aceptación que servirán como base para el análisis, diseño, desarrollo, pruebas y aceptación de una aplicación de escritorio para Windows.

---

## 3. Alcance

El sistema comprenderá las siguientes capacidades:

- Registrar consultores tecnológicos.
- Consultar la lista y el detalle de los consultores registrados.
- Actualizar la información de un consultor sin modificar su código.
- Eliminar consultores mediante confirmación previa.
- Validar campos obligatorios, códigos y correos electrónicos duplicados, y tarifas no válidas.
- Asignar y actualizar la especialidad del consultor.
- Asignar y actualizar el nivel profesional del consultor.
- Asignar y actualizar la disponibilidad del consultor.
- Calcular y mostrar la tarifa promedio por especialidad.
- Almacenar y recuperar la información mediante una base de datos relacional MySQL y JDBC.
- Proporcionar una interfaz de escritorio Java Swing completamente en español.
- Mantener en inglés los nombres de clases, métodos, variables, paquetes, tablas y columnas.

### 3.1 Fuera del alcance

No se consideran dentro de esta versión, salvo aprobación posterior mediante control de cambios:

- Gestión de usuarios, roles y permisos.
- Autenticación y autorización.
- Gestión de clientes, contratos, proyectos o asignaciones detalladas.
- Registro de horas trabajadas, nómina, facturación o pagos.
- Gestión documental o almacenamiento de archivos adjuntos.
- Conversión automática entre monedas.
- Auditoría detallada del historial de cambios.
- Integración con CRM, ERP, plataformas de recursos humanos u otros sistemas.
- Importación o exportación masiva de datos.
- Aplicación web, móvil o soporte para sistemas operativos distintos de Windows.
- Mantenimiento libre de los catálogos desde la interfaz; en esta versión se consideran catálogos cerrados.

---

## 4. Definiciones y convenciones

| Término | Definición |
|---|---|
| Consultor tecnológico | Profesional registrado en el sistema que presta servicios relacionados con tecnología. |
| Código de consultor | Identificador de negocio único e inmutable asignado a un consultor. |
| Identificador | Clave técnica interna y única generada por la base de datos. |
| Especialidad | Área principal de conocimiento o práctica profesional del consultor. |
| Nivel profesional | Clasificación de experiencia y responsabilidad del consultor. |
| Disponibilidad | Situación operativa actual del consultor. |
| Tarifa por hora | Importe monetario asociado a una hora de servicio del consultor. |
| Tarifa promedio | Media aritmética de las tarifas por hora de los consultores de una especialidad. |
| Catálogo cerrado | Conjunto de valores permitidos que no puede modificarse desde la interfaz en esta versión. |
| RF | Requerimiento funcional. |
| RT | Requerimiento técnico. |
| RN | Regla de negocio. |
| CA | Criterio de aceptación. |

### 4.1 Prioridades

- **Alta:** funcionalidad indispensable para la operación básica del sistema.
- **Media:** funcionalidad necesaria para mejorar la consulta, el análisis o la presentación.
- **Baja:** funcionalidad deseable que puede planificarse para una versión posterior.

### 4.2 Convención de redacción

Cada requerimiento utiliza un identificador único y la expresión **“El sistema deberá”** para establecer una obligación verificable.

### 4.3 Convención de idioma

- Los títulos, menús, etiquetas, botones, mensajes, confirmaciones, validaciones y contenidos visibles para el usuario deberán estar en español.
- Los nombres de clases, interfaces, métodos, variables, constantes, paquetes, módulos, tablas, vistas, índices y columnas deberán escribirse en inglés.
- Los valores visibles de los catálogos deberán mostrarse en español, aunque sus identificadores internos podrán estar en inglés.

---

## 5. Actores

| Actor | Descripción | Responsabilidades principales |
|---|---|---|
| Usuario gestor de consultores | Persona autorizada para administrar la información de consultores. | Registrar, consultar, actualizar y eliminar consultores. |
| Usuario de consulta | Persona que requiere revisar consultores y estadísticas. | Consultar la lista, visualizar detalles y revisar tarifas promedio. |
| Sistema | Aplicación de gestión de consultores tecnológicos. | Validar, procesar, almacenar, recuperar y presentar la información. |

> **Nota:** La distinción de permisos entre actores deberá definirse cuando se incorpore la gestión de usuarios y roles.

---

## 6. Datos del consultor

| Campo visible | Identificador en inglés | Tipo lógico | Obligatorio | Editable después del registro | Regla principal |
|---|---|---|---|---|---|
| Identificador | `id` | Entero | Sí, generado por la base de datos | No | Debe ser único y autogenerado. |
| Código del consultor | `consultantCode` / `consultant_code` | Texto | Sí | No | No puede estar vacío ni duplicado. |
| Nombres | `firstName` / `first_name` | Texto | Sí | Sí | No puede estar vacío. |
| Apellidos | `lastName` / `last_name` | Texto | Sí | Sí | No puede estar vacío. |
| Correo electrónico | `email` | Texto | Sí | Sí | Debe tener formato válido y ser único. |
| Teléfono | `phone` | Texto | No | Sí | Debe respetar el formato y longitud definidos. |
| Especialidad | `specialtyId` / `specialty_id` | Catálogo | Sí | Sí | Debe corresponder a una especialidad permitida. |
| Nivel profesional | `professionalLevelId` / `professional_level_id` | Catálogo | Sí | Sí | Debe corresponder a un nivel permitido. |
| Disponibilidad | `availabilityStatusId` / `availability_status_id` | Catálogo | No | Sí | Si se omite, se asigna “Disponible”. |
| Tarifa por hora | `hourlyRate` / `hourly_rate` | Decimal | Sí | Sí | Debe ser mayor que cero y conservar dos decimales. |
| Fecha de registro | `createdAt` / `created_at` | Fecha y hora | Sí, generada por el sistema | No | Se utilizará para ordenar los registros. |
| Fecha de actualización | `updatedAt` / `updated_at` | Fecha y hora | Sí, generada por el sistema | No directamente | Se actualiza después de cada modificación exitosa. |

### 6.1 Catálogo de especialidades

| Valor visible en español | Código interno sugerido |
|---|---|
| Desarrollo de software | `SOFTWARE_DEVELOPMENT` |
| Datos y analítica | `DATA_ANALYTICS` |
| Cloud y DevOps | `CLOUD_DEVOPS` |
| Ciberseguridad | `CYBERSECURITY` |
| Calidad de software | `SOFTWARE_QUALITY` |
| Transformación digital | `DIGITAL_TRANSFORMATION` |

### 6.2 Catálogo de niveles profesionales

| Valor visible en español | Código interno sugerido |
|---|---|
| Junior | `JUNIOR` |
| Semi senior | `SEMI_SENIOR` |
| Senior | `SENIOR` |
| Líder técnico | `TECH_LEAD` |

### 6.3 Catálogo de disponibilidad

| Valor visible en español | Código interno sugerido |
|---|---|
| Disponible | `AVAILABLE` |
| Asignado | `ASSIGNED` |
| De licencia | `ON_LEAVE` |
| Inactivo | `INACTIVE` |

---

## 7. Reglas de negocio

| Código | Regla |
|---|---|
| RN-01 | El código del consultor es obligatorio. |
| RN-02 | El código del consultor debe ser único. |
| RN-03 | El código del consultor no puede modificarse después del registro. |
| RN-04 | Los nombres y apellidos son obligatorios. |
| RN-05 | El correo electrónico es obligatorio, debe tener un formato válido y debe ser único. |
| RN-06 | La tarifa por hora debe ser numérica y mayor que cero. |
| RN-07 | Las tarifas deberán almacenarse con precisión decimal y mostrarse con dos decimales. |
| RN-08 | Las especialidades permitidas son Desarrollo de software, Datos y analítica, Cloud y DevOps, Ciberseguridad, Calidad de software y Transformación digital. |
| RN-09 | Los niveles profesionales permitidos son Junior, Semi senior, Senior y Líder técnico. |
| RN-10 | Los estados de disponibilidad permitidos son Disponible, Asignado, De licencia e Inactivo. |
| RN-11 | Si no se especifica la disponibilidad al registrar un consultor, el sistema deberá asignar “Disponible”. |
| RN-12 | La tarifa promedio por especialidad corresponde a la suma de las tarifas por hora de los consultores de esa especialidad dividida entre la cantidad de consultores incluidos. |
| RN-13 | Si una especialidad no tiene consultores registrados, el sistema deberá informar que no existen datos para calcular el promedio. |
| RN-14 | La consulta general deberá ordenar los consultores desde el registro más reciente hasta el más antiguo. |
| RN-15 | La eliminación de un consultor requerirá confirmación explícita del usuario. |
| RN-16 | Una operación fallida no deberá dejar registros incompletos ni datos parcialmente actualizados. |
| RN-17 | La interfaz de usuario deberá mostrarse completamente en español. |
| RN-18 | Los identificadores técnicos del código fuente y la base de datos deberán estar en inglés. |

---

## 8. Requerimientos funcionales

### RF-01. Registrar un nuevo consultor

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir registrar un nuevo consultor tecnológico. |
| Actor principal | Usuario gestor de consultores |
| Prioridad | Alta |
| Precondiciones | La aplicación deberá tener conexión con la base de datos y el usuario deberá encontrarse en la opción de registro. |
| Entradas | Código, nombres, apellidos, correo electrónico, teléfono, especialidad, nivel profesional, disponibilidad y tarifa por hora. |
| Proceso | El sistema validará la información, asignará la disponibilidad predeterminada cuando corresponda y almacenará el consultor. |
| Resultado | Consultor registrado y disponible para consulta. |
| Excepciones | Datos obligatorios vacíos, tarifa no válida, código o correo duplicado, correo con formato incorrecto o valor de catálogo no permitido. |

**Criterios de aceptación**

- **CA-01.1:** Dado que los datos son válidos, cuando el usuario confirme el registro, entonces el sistema deberá guardar el consultor y mostrar una confirmación en español.
- **CA-01.2:** Si existe un error de validación, el sistema no deberá guardar información parcial y deberá identificar el campo o la regla incumplida.
- **CA-01.3:** Después de un registro exitoso, el nuevo consultor deberá aparecer en la consulta general.
- **CA-01.4:** El sistema deberá limpiar o cerrar el formulario únicamente después de completar el registro correctamente.

### RF-02. Consultar consultores registrados

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá mostrar los consultores almacenados. |
| Actor principal | Usuario gestor de consultores o usuario de consulta |
| Prioridad | Alta |
| Precondiciones | La aplicación deberá tener acceso a la base de datos. |
| Salidas | Código, nombres, apellidos, correo, especialidad, nivel profesional, disponibilidad y tarifa por hora. |
| Orden | Desde el registro más reciente hasta el más antiguo. |

**Criterios de aceptación**

- **CA-02.1:** Al acceder a la consulta, el sistema deberá recuperar y mostrar todos los consultores disponibles.
- **CA-02.2:** Los consultores deberán presentarse en orden descendente de fecha de registro.
- **CA-02.3:** Si no existen consultores, el sistema deberá mostrar una indicación clara en español.
- **CA-02.4:** La tabla deberá mostrar encabezados y valores de catálogo en español.

### RF-03. Consultar el detalle de un consultor

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir seleccionar un consultor y visualizar todos sus datos. |
| Actor principal | Usuario gestor de consultores o usuario de consulta |
| Prioridad | Alta |
| Precondiciones | El consultor deberá existir y estar seleccionado. |
| Resultado | Información completa presentada en un formulario de detalle o edición. |

**Criterios de aceptación**

- **CA-03.1:** Al seleccionar un consultor existente, el sistema deberá mostrar toda su información almacenada.
- **CA-03.2:** Si el consultor ya no existe, el sistema deberá informar la situación y actualizar la lista.

### RF-04. Actualizar un consultor

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir modificar los nombres, apellidos, correo, teléfono, especialidad, nivel profesional, disponibilidad y tarifa por hora. |
| Actor principal | Usuario gestor de consultores |
| Prioridad | Alta |
| Precondiciones | El consultor deberá existir y haber sido seleccionado. |
| Restricción | El código del consultor no podrá modificarse. |
| Resultado | Información actualizada y persistida. |

**Criterios de aceptación**

- **CA-04.1:** Si los cambios son válidos, el sistema deberá guardar los nuevos valores y mostrar una confirmación.
- **CA-04.2:** El código del consultor deberá presentarse como un dato no editable.
- **CA-04.3:** Si la tarifa, el correo o un catálogo no son válidos, el sistema deberá rechazar la operación y conservar los datos anteriores.
- **CA-04.4:** Si el consultor no existe al guardar, el sistema deberá cancelar la actualización, informar al usuario y actualizar la lista.
- **CA-04.5:** Después de guardar, la fecha de actualización deberá renovarse.

### RF-05. Eliminar un consultor

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir eliminar un consultor seleccionado. |
| Actor principal | Usuario gestor de consultores |
| Prioridad | Alta |
| Precondiciones | El consultor deberá existir y haber sido seleccionado. |
| Confirmación | El sistema deberá solicitar confirmación antes de eliminar. |
| Resultado | Consultor eliminado de la base de datos. |

**Criterios de aceptación**

- **CA-05.1:** Al solicitar la eliminación, el sistema deberá mostrar una confirmación en español que permita aceptar o cancelar.
- **CA-05.2:** Si el usuario confirma, el sistema deberá eliminar el consultor, actualizar la lista y mostrar una confirmación.
- **CA-05.3:** Si el usuario cancela, el sistema no deberá modificar la información.
- **CA-05.4:** Si el consultor ya no existe, el sistema deberá informar la situación y actualizar la lista.

### RF-06. Validar el código del consultor

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá verificar que el código no esté vacío durante el registro. |
| Prioridad | Alta |
| Regla asociada | RN-01 |

**Criterios de aceptación**

- **CA-06.1:** Si el código está vacío o contiene únicamente espacios, el sistema deberá impedir el registro e informar que el campo es obligatorio.
- **CA-06.2:** Antes de validar, el sistema deberá eliminar espacios innecesarios al inicio y al final.

### RF-07. Validar nombres y apellidos

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá verificar que los nombres y apellidos no estén vacíos. |
| Prioridad | Alta |
| Regla asociada | RN-04 |

**Criterios de aceptación**

- **CA-07.1:** Si los nombres o apellidos están vacíos o contienen únicamente espacios, el sistema deberá impedir la operación e identificar el campo obligatorio.

### RF-08. Validar el correo electrónico

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá comprobar la presencia, el formato y la unicidad del correo electrónico. |
| Prioridad | Alta |
| Regla asociada | RN-05 |

**Criterios de aceptación**

- **CA-08.1:** El sistema deberá aceptar un correo con un formato válido.
- **CA-08.2:** El sistema deberá rechazar un correo vacío, con un formato incorrecto o utilizado por otro consultor.
- **CA-08.3:** Al actualizar, el consultor deberá poder conservar su propio correo sin provocar un falso duplicado.
- **CA-08.4:** La base de datos deberá reforzar la unicidad mediante una restricción única.

### RF-09. Validar la tarifa por hora

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá verificar que la tarifa por hora sea numérica y mayor que cero durante el registro y la actualización. |
| Prioridad | Alta |
| Regla asociada | RN-06, RN-07 |

**Criterios de aceptación**

- **CA-09.1:** El sistema deberá aceptar una tarifa numérica mayor que cero.
- **CA-09.2:** El sistema deberá rechazar valores iguales a cero, negativos, vacíos o no numéricos.
- **CA-09.3:** La tarifa deberá almacenarse con un tipo decimal y mostrarse con dos posiciones decimales.
- **CA-09.4:** Ante un valor no válido, el sistema deberá mostrar un mensaje y no completar la operación.

### RF-10. Validar códigos duplicados

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá impedir el registro de dos consultores con el mismo código. |
| Prioridad | Alta |
| Regla asociada | RN-02 |

**Criterios de aceptación**

- **CA-10.1:** Si el código ya está asociado a otro consultor, el sistema deberá rechazar el registro e informar que el código ya existe.
- **CA-10.2:** La base de datos deberá reforzar la unicidad del código mediante una restricción única.

### RF-11. Gestionar la especialidad

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir asignar y actualizar la especialidad de un consultor. |
| Prioridad | Alta |
| Valores permitidos | Desarrollo de software, Datos y analítica, Cloud y DevOps, Ciberseguridad, Calidad de software y Transformación digital. |
| Regla asociada | RN-08 |

**Criterios de aceptación**

- **CA-11.1:** El formulario deberá presentar únicamente las especialidades permitidas.
- **CA-11.2:** El sistema deberá impedir el almacenamiento de una especialidad ajena al catálogo.
- **CA-11.3:** La especialidad seleccionada deberá visualizarse en español en la lista y el detalle.

### RF-12. Gestionar el nivel profesional

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir asignar y actualizar el nivel profesional de un consultor. |
| Prioridad | Alta |
| Valores permitidos | Junior, Semi senior, Senior y Líder técnico. |
| Regla asociada | RN-09 |

**Criterios de aceptación**

- **CA-12.1:** El formulario deberá presentar únicamente los niveles profesionales permitidos.
- **CA-12.2:** El sistema deberá impedir el almacenamiento de un nivel ajeno al catálogo.
- **CA-12.3:** El nivel seleccionado deberá visualizarse en español en la lista y el detalle.

### RF-13. Gestionar la disponibilidad

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá permitir asignar y actualizar la disponibilidad de un consultor. |
| Prioridad | Alta |
| Valores permitidos | Disponible, Asignado, De licencia e Inactivo. |
| Valor predeterminado | Disponible. |
| Regla asociada | RN-10, RN-11 |

**Criterios de aceptación**

- **CA-13.1:** El formulario deberá presentar únicamente los estados de disponibilidad permitidos.
- **CA-13.2:** Si la disponibilidad se omite durante el registro, el consultor deberá almacenarse como “Disponible”.
- **CA-13.3:** Al actualizar la disponibilidad, el nuevo valor deberá mostrarse en la lista y el detalle.
- **CA-13.4:** El sistema deberá impedir el almacenamiento de un estado ajeno al catálogo.

### RF-14. Consultar la tarifa promedio por especialidad

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá calcular la tarifa promedio de los consultores pertenecientes a la especialidad seleccionada. |
| Actor principal | Usuario de consulta o usuario gestor de consultores |
| Prioridad | Alta |
| Entrada | Especialidad. |
| Cálculo | Suma de las tarifas por hora dividida entre la cantidad de consultores de la especialidad. |
| Regla asociada | RN-12, RN-13 |

**Criterios de aceptación**

- **CA-14.1:** Dada una especialidad con consultores, el sistema deberá calcular el promedio utilizando únicamente los consultores de esa especialidad.
- **CA-14.2:** Los consultores de otras especialidades no deberán incluirse en el cálculo.
- **CA-14.3:** El resultado deberá redondearse y mostrarse con dos posiciones decimales.
- **CA-14.4:** Si no existen consultores en la especialidad, el sistema deberá informar que no hay datos suficientes y no deberá mostrar un valor engañoso.

### RF-15. Mostrar los resultados estadísticos

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá presentar la tarifa promedio correspondiente a la especialidad seleccionada. |
| Prioridad | Media |
| Salidas | Especialidad, cantidad de consultores incluidos y tarifa promedio. |
| Regla asociada | RN-07, RN-12, RN-13 |

**Criterios de aceptación**

- **CA-15.1:** El resultado deberá identificar la especialidad utilizada en el cálculo.
- **CA-15.2:** El resultado deberá indicar la cantidad de consultores incluidos.
- **CA-15.3:** La tarifa promedio deberá mostrarse con dos posiciones decimales y con la moneda definida para el sistema.
- **CA-15.4:** Si ocurre un error de recuperación o cálculo, el sistema deberá informar que no fue posible obtener la estadística y no deberá mostrar un valor incorrecto como válido.

### RF-16. Almacenar la información de los consultores

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá almacenar permanentemente la información de los consultores en una base de datos relacional MySQL. |
| Prioridad | Alta |
| Resultado | Los registros permanecerán disponibles después de cerrar y volver a ejecutar la aplicación. |
| Regla asociada | RN-16 |

**Criterios de aceptación**

- **CA-16.1:** Después de registrar o actualizar un consultor y reiniciar la aplicación, la información deberá permanecer disponible.
- **CA-16.2:** Una operación fallida no deberá dejar registros incompletos ni datos parcialmente actualizados.
- **CA-16.3:** Una eliminación confirmada deberá reflejarse de forma permanente en la base de datos.
- **CA-16.4:** Las operaciones de persistencia deberán utilizar JDBC y sentencias parametrizadas.

### RF-17. Recuperar la información almacenada

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá recuperar los consultores almacenados y mostrarlos desde el registro más reciente hasta el más antiguo. |
| Prioridad | Alta |
| Regla asociada | RN-14 |

**Criterios de aceptación**

- **CA-17.1:** Al iniciar la consulta, el sistema deberá recuperar los registros persistidos en MySQL.
- **CA-17.2:** Los resultados deberán ordenarse por `created_at` en orden descendente.
- **CA-17.3:** Si no es posible acceder a la base de datos, el sistema deberá mostrar un mensaje comprensible en español y evitar presentar información incompleta como actualizada.

### RF-18. Presentar la interfaz en español

| Atributo | Especificación |
|---|---|
| Descripción | El sistema deberá presentar en español todos los elementos visibles de la interfaz. |
| Prioridad | Alta |
| Regla asociada | RN-17, RN-18 |

**Criterios de aceptación**

- **CA-18.1:** Los títulos de ventanas, menús, etiquetas, botones, encabezados, mensajes y confirmaciones deberán estar en español.
- **CA-18.2:** Los valores de especialidad, nivel profesional y disponibilidad deberán mostrarse en español.
- **CA-18.3:** Los nombres técnicos del código fuente y de la base de datos deberán permanecer en inglés.
- **CA-18.4:** No deberán mostrarse excepciones, trazas ni mensajes técnicos en inglés al usuario final.

---

## 9. Requerimientos técnicos

| Código | Requerimiento técnico | Verificación |
|---|---|---|
| RT-01 | La aplicación deberá desarrollarse con Java 25. | El proyecto compila y se ejecuta con un JDK 25. |
| RT-02 | El entorno de desarrollo recomendado será IntelliJ IDEA. | El proyecto puede importarse, compilarse y ejecutarse desde IntelliJ IDEA. |
| RT-03 | La persistencia deberá implementarse con MySQL y JDBC. | Las operaciones CRUD y estadísticas se ejecutan mediante JDBC sobre MySQL. |
| RT-04 | La interfaz de escritorio deberá implementarse con Java Swing y ejecutarse en Windows. | Las ventanas y controles funcionan correctamente en la versión de Windows definida para el proyecto. |
| RT-05 | El proyecto deberá utilizar una arquitectura por capas con los paquetes `model`, `repository`, `service`, `ui` y `config`. | El código mantiene las responsabilidades y dependencias definidas por cada capa. |
| RT-06 | Gradle deberá utilizarse como gestor de construcción y dependencias. | El proyecto puede compilarse y probarse con Gradle Wrapper. |
| RT-07 | Las credenciales y la URL de conexión no deberán codificarse directamente en las clases Java. | La configuración se obtiene desde propiedades o variables de entorno. |
| RT-08 | Las consultas SQL deberán utilizar `PreparedStatement` o mecanismos JDBC equivalentes con parámetros. | No se concatenan entradas del usuario en sentencias SQL. |
| RT-09 | Los cálculos monetarios deberán utilizar `BigDecimal`; no se utilizarán `float` o `double` para tarifas. | Las tarifas y promedios conservan la precisión decimal esperada. |
| RT-10 | Las operaciones JDBC deberán cerrar conexiones, sentencias y resultados de forma segura. | El código utiliza `try-with-resources` o un mecanismo equivalente. |
| RT-11 | La aplicación deberá guardar y recuperar texto en Unicode. | Los nombres, apellidos y textos con tildes o eñes se almacenan y muestran correctamente. |
| RT-12 | Los errores técnicos deberán registrarse sin exponer credenciales ni sentencias sensibles al usuario. | El usuario recibe un mensaje comprensible y el diagnóstico técnico queda disponible para soporte. |

### 9.1 Responsabilidades por capa

| Paquete | Responsabilidad | Elementos sugeridos |
|---|---|---|
| `model` | Representar las entidades y valores del dominio. | `Consultant`, `Specialty`, `ProfessionalLevel`, `AvailabilityStatus`, `SpecialtyRateStatistic` |
| `repository` | Ejecutar operaciones de persistencia y consultas SQL mediante JDBC. | `ConsultantRepository`, `JdbcConsultantRepository`, `SpecialtyRepository` |
| `service` | Aplicar reglas de negocio, validaciones y coordinación de casos de uso. | `ConsultantService`, `StatisticsService`, `ConsultantValidator` |
| `ui` | Implementar formularios, tablas, diálogos y controladores Swing. | `MainFrame`, `ConsultantFormDialog`, `ConsultantTableModel`, `StatisticsPanel` |
| `config` | Gestionar configuración y creación de conexiones. | `DatabaseConfig`, `ConnectionFactory` |

### 9.2 Dependencias permitidas entre capas

- `ui` podrá depender de `service` y de tipos de `model`.
- `service` podrá depender de abstracciones de `repository` y de tipos de `model`.
- `repository` podrá depender de `config` y de tipos de `model`.
- `config` no deberá depender de `ui`, `service` ni `repository`.
- `model` no deberá depender de las demás capas.
- La interfaz Swing no deberá ejecutar SQL directamente.
- La capa `repository` no deberá contener reglas de presentación.

### 9.3 Estructura Gradle sugerida

```text
src/
├── main/
│   ├── java/
│   │   └── com/example/consultantmanagement/
│   │       ├── config/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── ui/
│   └── resources/
│       ├── application.properties.example
│       └── db/
│           └── migration/
└── test/
    └── java/
        └── com/example/consultantmanagement/
```

> El nombre raíz del paquete deberá adaptarse a la organización responsable y mantenerse en inglés, en minúsculas y sin caracteres especiales.

### 9.4 Modelo relacional sugerido

| Tabla | Propósito | Columnas principales sugeridas |
|---|---|---|
| `consultants` | Almacenar la información de los consultores. | `id`, `consultant_code`, `first_name`, `last_name`, `email`, `phone`, `specialty_id`, `professional_level_id`, `availability_status_id`, `hourly_rate`, `created_at`, `updated_at` |
| `specialties` | Almacenar el catálogo de especialidades. | `id`, `code`, `display_name` |
| `professional_levels` | Almacenar el catálogo de niveles profesionales. | `id`, `code`, `display_name` |
| `availability_statuses` | Almacenar el catálogo de disponibilidad. | `id`, `code`, `display_name` |

**Restricciones mínimas sugeridas**

- Clave primaria en el campo `id` de cada tabla.
- Restricción única para `consultants.consultant_code`.
- Restricción única para `consultants.email`.
- Restricción única para el campo `code` de cada catálogo.
- Claves foráneas desde `consultants` hacia las tres tablas de catálogo.
- Restricción que impida almacenar una `hourly_rate` menor o igual que cero, cuando la versión de MySQL utilizada lo permita.
- Codificación `utf8mb4` para tablas y conexiones.

---

## 10. Matriz de trazabilidad

| Requerimiento | Reglas de negocio | Caso de uso asociado | Prioridad |
|---|---|---|---|
| RF-01 | RN-01, RN-02, RN-04, RN-05, RN-06, RN-08, RN-09, RN-10, RN-11, RN-16 | CU-01 Registrar consultor | Alta |
| RF-02 | RN-07, RN-14, RN-17 | CU-02 Consultar consultores | Alta |
| RF-03 | RN-17 | CU-03 Consultar detalle | Alta |
| RF-04 | RN-03, RN-04, RN-05, RN-06, RN-07, RN-08, RN-09, RN-10, RN-16 | CU-04 Actualizar consultor | Alta |
| RF-05 | RN-15, RN-16 | CU-05 Eliminar consultor | Alta |
| RF-06 | RN-01 | CU-01 Registrar consultor | Alta |
| RF-07 | RN-04 | CU-01 / CU-04 | Alta |
| RF-08 | RN-05 | CU-01 / CU-04 | Alta |
| RF-09 | RN-06, RN-07 | CU-01 / CU-04 | Alta |
| RF-10 | RN-02 | CU-01 Registrar consultor | Alta |
| RF-11 | RN-08 | CU-01 / CU-04 | Alta |
| RF-12 | RN-09 | CU-01 / CU-04 | Alta |
| RF-13 | RN-10, RN-11 | CU-01 / CU-04 | Alta |
| RF-14 | RN-12, RN-13 | CU-06 Consultar tarifa promedio | Alta |
| RF-15 | RN-07, RN-12, RN-13 | CU-06 Consultar tarifa promedio | Media |
| RF-16 | RN-16 | Todos los casos con persistencia | Alta |
| RF-17 | RN-14 | CU-02 Consultar consultores | Alta |
| RF-18 | RN-17, RN-18 | Todos los casos con interfaz | Alta |

---

## 11. Resumen de requerimientos

| Código | Nombre | Prioridad |
|---|---|---|
| RF-01 | Registrar un nuevo consultor | Alta |
| RF-02 | Consultar consultores registrados | Alta |
| RF-03 | Consultar el detalle de un consultor | Alta |
| RF-04 | Actualizar un consultor | Alta |
| RF-05 | Eliminar un consultor | Alta |
| RF-06 | Validar el código del consultor | Alta |
| RF-07 | Validar nombres y apellidos | Alta |
| RF-08 | Validar el correo electrónico | Alta |
| RF-09 | Validar la tarifa por hora | Alta |
| RF-10 | Validar códigos duplicados | Alta |
| RF-11 | Gestionar la especialidad | Alta |
| RF-12 | Gestionar el nivel profesional | Alta |
| RF-13 | Gestionar la disponibilidad | Alta |
| RF-14 | Consultar la tarifa promedio por especialidad | Alta |
| RF-15 | Mostrar los resultados estadísticos | Media |
| RF-16 | Almacenar la información de los consultores | Alta |
| RF-17 | Recuperar la información almacenada | Alta |
| RF-18 | Presentar la interfaz en español | Alta |

---

## 12. Supuestos y dependencias

- La organización dispondrá de una instancia operativa de MySQL y de las credenciales de conexión correspondientes.
- Los equipos cliente dispondrán de Windows y de un entorno compatible con Java 25 o de una distribución empaquetada que incluya el entorno de ejecución necesario.
- IntelliJ IDEA será el entorno recomendado, pero Gradle Wrapper será la referencia reproducible para compilar y probar el proyecto.
- Las especialidades, niveles profesionales y estados de disponibilidad constituyen catálogos cerrados en esta versión.
- Cada consultor tendrá una sola especialidad principal, un solo nivel profesional y un solo estado de disponibilidad activos.
- Cada consultor tendrá una única tarifa por hora vigente.
- El sistema contará con fechas de creación y actualización para ordenar y controlar los registros.
- La definición de perfiles de seguridad, permisos y auditoría queda pendiente para una versión posterior.
- La aplicación utilizará una única moneda configurada para todas las tarifas; su código deberá definirse antes del desarrollo.

---

## 13. Decisiones pendientes

Antes de aprobar la especificación, las partes interesadas deberán definir:

- Nombre de la organización y propietario del producto.
- Longitud, formato, generación automática y sensibilidad a mayúsculas del código del consultor.
- Longitud máxima de nombres, apellidos, correo y teléfono.
- Reglas exactas de validación y normalización del teléfono.
- Si el correo electrónico debe compararse sin distinguir mayúsculas y minúsculas.
- Moneda aplicable a las tarifas y símbolo que deberá mostrarse.
- Precisión y valor máximo permitido para la tarifa por hora.
- Si la estadística deberá incluir o excluir consultores inactivos o de licencia.
- Política de redondeo para las tarifas promedio.
- Si un consultor podrá tener varias especialidades en versiones posteriores.
- Si los catálogos deberán poder administrarse desde la interfaz en una versión futura.
- Política de eliminación física o lógica de consultores.
- Zona horaria aplicable a las fechas de creación y actualización.
- Perfiles autorizados para registrar, actualizar y eliminar.
- Versión mínima de Windows y versión exacta de MySQL compatibles.
- Estrategia de distribución para Windows: JAR ejecutable, instalador o imagen de aplicación.
- Estrategia de migraciones de base de datos.
- Nivel de detalle requerido para mensajes de error y registros técnicos.
- Necesidad de filtros, búsqueda, paginación y exportación en la consulta general.
- Necesidad de pruebas automatizadas y porcentaje mínimo de cobertura.

---

## 14. Criterios generales de calidad funcional

- Cada operación deberá proporcionar una confirmación de éxito o un mensaje de error comprensible en español.
- Las validaciones deberán ejecutarse antes de persistir cambios.
- Los mensajes deberán identificar la causa sin exponer credenciales, SQL, trazas o detalles técnicos sensibles.
- Las operaciones de escritura deberán mantener la consistencia de los datos.
- La interfaz deberá evitar la modificación accidental del código del consultor.
- Las tarifas y promedios deberán conservar precisión decimal y no deberán calcularse con tipos de punto flotante binario.
- Las operaciones que puedan tardar deberán evitar el bloqueo prolongado del hilo de eventos de Swing.
- Los controles deberán conservar una disposición legible con la configuración de pantalla admitida.
- La aplicación deberá admitir correctamente caracteres españoles y nombres con tildes, diéresis y eñes.
- Las conexiones y recursos JDBC deberán liberarse incluso cuando ocurra un error.

---

## 15. Condiciones de aceptación del producto

La versión funcional será candidata a aceptación cuando:

- Todos los requerimientos de prioridad alta hayan sido implementados.
- Todos los criterios de aceptación asociados hayan sido probados con resultado satisfactorio.
- No existan defectos críticos ni altos abiertos.
- La aplicación compile y se ejecute con Java 25 mediante Gradle Wrapper.
- La interfaz Java Swing haya sido verificada en la versión de Windows aprobada.
- La persistencia y recuperación desde MySQL mediante JDBC hayan sido verificadas después de reiniciar la aplicación.
- Los códigos y correos duplicados sean rechazados por la aplicación y por restricciones de la base de datos.
- Los cálculos de tarifas promedio por especialidad hayan sido contrastados con datos de prueba conocidos.
- La interfaz visible para el usuario esté completamente en español.
- Los identificadores del código fuente y del modelo de datos estén en inglés.
- La estructura del proyecto respete las capas `model`, `repository`, `service`, `ui` y `config`.
- Las decisiones pendientes que afecten el desarrollo hayan sido aprobadas y documentadas.

