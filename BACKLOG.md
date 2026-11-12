# Product Backlog — Asesor CvLAC

## 1. Información general del producto

**Proyecto:** Asesor CvLAC  
**Versión del backlog:** 1.0  
**Stack tecnológico previsto:** Java 17+, Spring Boot, PostgreSQL y frontend web.  
**Arquitectura:** aplicación web organizada por capas bajo el patrón MVC, con servicios REST y persistencia relacional.

### 1.1. Visión del producto

Asesor CvLAC es una plataforma web de apoyo a investigadores que permite registrar, organizar y consultar su trayectoria académica e investigativa. Su propósito es facilitar la preparación de la información que posteriormente se registra en CvLAC, orientar al investigador sobre los datos que necesita completar y ayudarle a realizar seguimiento a sus actividades mediante un checklist y recordatorios.

La plataforma contempla el registro de información personal, investigaciones, publicaciones, formación académica, proyectos, aportes, contribuciones, evidencias documentales y experiencia laboral.

### 1.2. Objetivo del Product Backlog

Definir las funcionalidades iniciales de Asesor CvLAC mediante diez historias de usuario, organizadas en cinco sprints. Cada sprint tiene una estimación de ocho Story Points y respeta el límite máximo establecido de diez Story Points.

## 2. Alcance funcional

Los módulos funcionales considerados en esta versión son:

| Módulo | Alcance |
|---|---|
| Usuarios y seguridad | Registro de investigadores, inicio de sesión y protección de endpoints mediante JWT. |
| Información investigativa | Registro y consulta de investigaciones y publicaciones. |
| Formación académica | Registro de estudios y formación complementaria. |
| Proyectos y contribuciones | Organización de proyectos investigativos, roles, aportes y contribuciones. |
| Documentos y evidencias | Asociación de documentos de respaldo a investigaciones, proyectos y contribuciones. |
| Trayectoria profesional | Registro de experiencias laborales. |
| Agenda y recordatorios | Programación y consulta de recordatorios vinculados a actividades investigativas. |
| Asesoría y checklist CvLAC | Guía informativa, organización por categorías, identificación de información pendiente y seguimiento de la información que el investigador ya registró en CvLAC. |

## 3. Criterios de priorización y estimación

### 3.1. Prioridad MoSCoW

- **Must Have:** funcionalidad necesaria para cumplir el propósito principal de la primera versión.
- **Should Have:** funcionalidad importante que aporta valor, pero que puede desarrollarse después de las capacidades fundamentales.
- **Could Have:** funcionalidad conveniente que puede incorporarse si existe capacidad disponible.
- **Won't Have (por ahora):** funcionalidad que queda explícitamente fuera del alcance de esta versión.

### 3.2. Story Points

Los Story Points representan una estimación relativa del esfuerzo, complejidad y trabajo requerido. Las estimaciones de este documento son una propuesta inicial y deben validarse con el equipo mediante Planning Poker.

**Restricción de planificación:** ningún sprint puede superar los 10 Story Points. La planificación propuesta asigna 8 Story Points a cada sprint, dejando 2 puntos de margen frente al límite.

## 4. Resumen del Product Backlog

| ID | Historia de usuario | Prioridad | Story Points | Sprint |
|---|---|---:|---:|---:|
| HU-01 | Registro de investigadores | Must Have | 3 | Sprint 1 |
| HU-03 | Autenticación mediante JWT | Must Have | 5 | Sprint 1 |
| HU-02 | Registro de investigaciones y publicaciones | Must Have | 5 | Sprint 2 |
| HU-04 | Registro de formación académica | Must Have | 3 | Sprint 2 |
| HU-05 | Registro de proyectos de investigación | Must Have | 5 | Sprint 3 |
| HU-06 | Registro de aportes y contribuciones | Must Have | 3 | Sprint 3 |
| HU-07 | Gestión de documentos y evidencias | Should Have | 5 | Sprint 4 |
| HU-08 | Registro de experiencia laboral | Should Have | 3 | Sprint 4 |
| HU-09 | Agenda y recordatorios investigativos | Should Have | 3 | Sprint 5 |
| HU-10 | Guía y checklist de preparación para CvLAC | Must Have | 5 | Sprint 5 |
| **Total** | **10 historias de usuario** | | **40** | **5 sprints** |

> **Nota sobre los identificadores:** se conserva HU-02 para el registro de investigaciones y HU-03 para autenticación. Por dependencia funcional, la autenticación se implementa en el Sprint 1 y el registro de investigaciones en el Sprint 2.

## 5. Historias de usuario y criterios de aceptación BDD

### Sprint 1 — Usuarios y seguridad

**Capacidad planificada:** 8 Story Points de un máximo de 10.  
**Sprint Goal:** habilitar el registro de investigadores y la autenticación segura para identificar a cada usuario y proteger el acceso a la información de Asesor CvLAC.

#### HU-01: Registro de investigadores

**Prioridad:** Must Have  
**Estimación:** 3 Story Points  
**Como** persona interesada en la investigación, **quiero** registrarme en la plataforma como investigador, **para** almacenar mis aportes académicos, gestionar mis proyectos y organizar mi actividad investigativa.

**Escenario BDD — Registro exitoso**

- **Given** que una persona no registrada accede al endpoint `POST /api/v1/auth/register`.
- **When** envía un JSON con nombre completo, número de cédula, correo electrónico, contraseña y nivel de investigador cuando corresponda.
- **Then** el sistema valida los datos, registra al investigador en PostgreSQL y responde con `201 Created`, junto con el identificador único del usuario.

**Criterios de aceptación adicionales**

1. El sistema valida los campos obligatorios y el formato del correo electrónico.
2. El sistema impide registros duplicados por número de identificación o correo electrónico.
3. La contraseña se almacena mediante un mecanismo seguro de hash y nunca como texto plano.
4. Si los datos no son válidos, el sistema devuelve una respuesta de error controlada y comprensible.

#### HU-03: Autenticación mediante JWT

**Prioridad:** Must Have  
**Estimación:** 5 Story Points  
**Como** investigador registrado, **quiero** iniciar sesión con mis credenciales, **para** obtener un token JWT que me permita acceder de forma segura a las funcionalidades protegidas de la plataforma.

**Escenario BDD — Inicio de sesión exitoso**

- **Given** que un investigador se encuentra registrado en la plataforma.
- **When** envía sus credenciales al endpoint `POST /api/v1/auth/login`.
- **Then** el sistema valida las credenciales y responde con `200 OK`, generando un token JWT válido para consumir los endpoints protegidos.

**Criterios de aceptación adicionales**

1. Las credenciales incorrectas generan `401 Unauthorized` sin revelar información sensible.
2. El sistema valida la firma y vigencia del token JWT en los endpoints protegidos.
3. Las solicitudes sin token válido no pueden acceder a recursos protegidos.
4. La configuración de expiración del token debe ser controlable mediante la configuración de la aplicación.

### Sprint 2 — Información investigativa y formación académica

**Capacidad planificada:** 8 Story Points de un máximo de 10.  
**Sprint Goal:** permitir que el investigador almacene información de sus investigaciones, publicaciones y formación académica para preparar su trayectoria en la plataforma.

#### HU-02: Registro de investigaciones y publicaciones

**Prioridad:** Must Have  
**Estimación:** 5 Story Points  
**Como** investigador autenticado, **quiero** registrar mis investigaciones y publicaciones en la plataforma, **para** conservar su información y utilizarla posteriormente en el proceso de actualización de mi CvLAC.

**Escenario BDD — Registro de investigación**

- **Given** que un investigador tiene un token JWT válido.
- **When** envía una solicitud a `POST /api/v1/investigaciones` con título, tipo de producto investigativo, descripción y fechas correspondientes.
- **Then** el sistema valida la información, almacena el registro asociado al investigador autenticado y responde con `201 Created` junto con un identificador único.

**Criterios de aceptación adicionales**

1. El registro permite clasificar la investigación o publicación según su tipo.
2. El sistema permite registrar trabajos realizados en fechas anteriores.
3. La fecha de publicación puede ser opcional cuando no corresponda al tipo de producto registrado.
4. El sistema valida la coherencia de las fechas y evita combinaciones inválidas.
5. Cada registro queda asociado al investigador autenticado; el usuario no puede atribuirse registros de otro investigador.

#### HU-04: Registro de formación académica

**Prioridad:** Must Have  
**Estimación:** 3 Story Points  
**Como** investigador, **quiero** registrar mi formación académica y complementaria, **para** mantener organizada mi trayectoria educativa y disponer de esta información al preparar mi CvLAC.

**Escenario BDD — Registro de formación**

- **Given** que un investigador autenticado accede al módulo de formación académica.
- **When** registra el título o programa, institución educativa, nivel o tipo de formación, fecha de inicio y fecha de finalización cuando corresponda.
- **Then** el sistema valida los datos, guarda la información asociada al investigador y responde con `201 Created`.

**Criterios de aceptación adicionales**

1. Se pueden registrar distintos niveles y tipos de formación, incluidos estudios de pregrado, posgrado y formación complementaria.
2. Se permite indicar que una formación se encuentra en curso.
3. Cuando se informa una fecha de finalización, esta no puede ser anterior a la fecha de inicio.
4. La información solo puede ser consultada o modificada por el investigador propietario, de acuerdo con las reglas de autorización.

### Sprint 3 — Proyectos y contribuciones

**Capacidad planificada:** 8 Story Points de un máximo de 10.  
**Sprint Goal:** organizar los proyectos investigativos y las contribuciones académicas para mantener la trazabilidad de las actividades desarrolladas por cada investigador.

#### HU-05: Registro de proyectos de investigación

**Prioridad:** Must Have  
**Estimación:** 5 Story Points  
**Como** investigador, **quiero** registrar mis proyectos de investigación, **para** organizar su información, identificar mi participación y hacer seguimiento a su desarrollo.

**Escenario BDD — Registro de proyecto**

- **Given** que un investigador se encuentra autenticado en la plataforma.
- **When** registra un proyecto mediante `POST /api/v1/proyectos`, proporcionando nombre, descripción, objetivo, línea de investigación, institución, rol y fechas disponibles.
- **Then** el sistema valida los datos, almacena el proyecto relacionado con el investigador y responde con `201 Created` y un identificador único.

**Criterios de aceptación adicionales**

1. El proyecto puede clasificarse por estado: planificado, en desarrollo o finalizado.
2. Se registra el rol o tipo de participación del investigador.
3. Se validan las fechas de inicio y finalización cuando ambas estén informadas.
4. El investigador puede consultar sus propios proyectos.

#### HU-06: Registro de aportes y contribuciones

**Prioridad:** Must Have  
**Estimación:** 3 Story Points  
**Como** investigador, **quiero** registrar mis aportes y contribuciones académicas, **para** mantener trazabilidad de mis actividades y facilitar su clasificación al preparar la información para CvLAC.

**Escenario BDD — Registro de contribución**

- **Given** que un investigador autenticado accede al módulo de contribuciones.
- **When** registra una contribución con tipo, descripción, fecha de realización y proyecto relacionado cuando corresponda.
- **Then** el sistema valida los datos, almacena la contribución vinculada al investigador y responde con `201 Created`.

**Criterios de aceptación adicionales**

1. Las contribuciones pueden clasificarse por tipo o naturaleza.
2. Una contribución puede relacionarse con un proyecto existente, cuando corresponda.
3. El sistema conserva la relación entre la contribución y el investigador responsable.
4. El investigador puede consultar sus contribuciones registradas.

### Sprint 4 — Evidencias documentales y trayectoria profesional

**Capacidad planificada:** 8 Story Points de un máximo de 10.  
**Sprint Goal:** fortalecer la organización y trazabilidad de la información mediante documentos de respaldo y el registro de la experiencia laboral del investigador.

#### HU-07: Gestión de documentos y evidencias

**Prioridad:** Should Have  
**Estimación:** 5 Story Points  
**Como** investigador, **quiero** adjuntar documentos y evidencias a mis investigaciones, proyectos y contribuciones, **para** mantener respaldada la información que utilizaré al preparar mi CvLAC.

**Escenario BDD — Adjuntar evidencia**

- **Given** que un investigador autenticado tiene un registro investigativo previamente creado.
- **When** adjunta un documento mediante `POST /api/v1/evidencias`, indicando el registro al que pertenece.
- **Then** el sistema valida el archivo, almacena el documento en el servicio de almacenamiento configurado, guarda sus metadatos y referencia en PostgreSQL y responde con `201 Created`.

**Criterios de aceptación adicionales**

1. Las evidencias pueden asociarse a investigaciones, proyectos o contribuciones.
2. PostgreSQL conserva los metadatos del documento, su propietario, la relación con el registro y la referencia o clave de almacenamiento.
3. El archivo original se conserva en un almacenamiento de archivos u objetos; el proveedor queda pendiente de selección técnica.
4. Se validan los formatos y tamaños permitidos por la configuración de la aplicación.
5. El acceso a los documentos respeta la autenticación y autorización del investigador propietario.

#### HU-08: Registro de experiencia laboral

**Prioridad:** Should Have  
**Estimación:** 3 Story Points  
**Como** investigador, **quiero** registrar mi experiencia laboral y profesional, **para** mantener organizada mi trayectoria y disponer de esta información al diligenciar mi CvLAC.

**Escenario BDD — Registro de experiencia**

- **Given** que un investigador autenticado ingresa al módulo de experiencia laboral.
- **When** registra cargo, institución u organización, funciones desempeñadas, fecha de inicio y fecha de finalización cuando corresponda.
- **Then** el sistema valida los datos, almacena la experiencia asociada al investigador y responde con `201 Created`.

**Criterios de aceptación adicionales**

1. Se pueden registrar experiencias laborales anteriores y vigentes.
2. Se permite dejar sin fecha de finalización una experiencia actual.
3. Cuando se informan ambas fechas, la fecha de finalización no puede ser anterior a la de inicio.
4. El investigador puede consultar y gestionar sus propios registros.

### Sprint 5 — Agenda, recordatorios y asesoría CvLAC

**Capacidad planificada:** 8 Story Points de un máximo de 10.  
**Sprint Goal:** completar las herramientas de seguimiento y orientación para que el investigador identifique información pendiente, prepare sus registros y controle las actividades relacionadas con la actualización de CvLAC.

#### HU-09: Agenda y recordatorios investigativos

**Prioridad:** Should Have  
**Estimación:** 3 Story Points  
**Como** investigador, **quiero** programar recordatorios relacionados con mis proyectos y actividades académicas, **para** mantener seguimiento de mis compromisos investigativos y de las tareas pendientes para actualizar CvLAC.

**Escenario BDD — Crear recordatorio**

- **Given** que un investigador autenticado tiene acceso a la agenda.
- **When** programa un recordatorio con título, descripción y fecha de notificación mediante `POST /api/v1/recordatorios`.
- **Then** el sistema almacena el recordatorio asociado al investigador y lo presenta como pendiente en la agenda.

**Criterios de aceptación adicionales**

1. Un recordatorio puede relacionarse con un proyecto, investigación u otra actividad, cuando corresponda.
2. El investigador puede consultar sus recordatorios pendientes y atendidos.
3. El investigador puede modificar o cancelar sus recordatorios.
4. La aplicación identifica los recordatorios cuya fecha de notificación ya llegó y los presenta al usuario.
5. En esta versión, el alcance contempla recordatorios dentro de la plataforma; el envío de correos o notificaciones externas queda sujeto a una decisión posterior.

#### HU-10: Guía y checklist de preparación para CvLAC

**Prioridad:** Must Have  
**Estimación:** 5 Story Points  
**Como** investigador, **quiero** consultar una guía y un checklist que organicen mis investigaciones, publicaciones, formación, proyectos y contribuciones según las categorías de CvLAC, **para** saber qué información debo completar, preparar su registro y hacer seguimiento a lo que ya incorporé en la plataforma oficial.

**Escenario BDD — Consultar checklist de CvLAC**

- **Given** que un investigador autenticado tiene información registrada en Asesor CvLAC.
- **When** accede al módulo de asesoría y selecciona una categoría de información.
- **Then** el sistema presenta una orientación sobre la categoría, los registros disponibles y los campos o datos pendientes que el investigador debe completar.

**Escenario BDD — Actualizar estado de un registro en CvLAC**

- **Given** que un investigador consulta un registro preparado para su ingreso en CvLAC.
- **When** marca manualmente el registro como incorporado en la plataforma oficial y confirma la actualización.
- **Then** el sistema guarda el estado de seguimiento y permite identificar que el investigador reportó ese registro como cargado en CvLAC.

**Criterios de aceptación adicionales**

1. La guía organiza la información por categorías relacionadas con la trayectoria del investigador.
2. El checklist identifica información incompleta o pendiente de preparar.
3. Los registros pueden distinguirse, como mínimo, entre pendiente de completar, preparado para registrar y reportado como registrado en CvLAC.
4. El investigador puede actualizar manualmente el estado de seguimiento de cada registro.
5. La plataforma muestra los recordatorios pendientes relacionados con la preparación o actualización de CvLAC.
6. La guía explica los pasos generales para que el investigador realice el registro en la plataforma oficial.
7. La información almacenada en Asesor CvLAC sirve para preparar y orientar el registro; esta historia no presupone una conexión automática ni una carga directa a CvLAC.

## 6. Planificación de los sprints

| Sprint | Historias incluidas | Objetivo principal | Total | Límite |
|---|---|---|---:|---:|
| Sprint 1 | HU-01, HU-03 | Registro de investigadores y autenticación segura. | 8 SP | 10 SP |
| Sprint 2 | HU-02, HU-04 | Registro de investigaciones, publicaciones y formación académica. | 8 SP | 10 SP |
| Sprint 3 | HU-05, HU-06 | Gestión de proyectos y contribuciones investigativas. | 8 SP | 10 SP |
| Sprint 4 | HU-07, HU-08 | Evidencias documentales y experiencia laboral. | 8 SP | 10 SP |
| Sprint 5 | HU-09, HU-10 | Agenda, recordatorios, guía y checklist CvLAC. | 8 SP | 10 SP |
| **Total** | **10 historias** | | **40 SP** | |

### 6.1. Dependencias funcionales

- HU-03 depende de que exista un usuario registrado mediante HU-01.
- HU-02, HU-04, HU-05, HU-06, HU-07, HU-08, HU-09 y HU-10 requieren un investigador autenticado.
- HU-06 puede relacionarse con proyectos registrados en HU-05.
- HU-07 requiere que exista el registro investigativo al que se asociará la evidencia.
- HU-09 puede relacionarse con proyectos y actividades registradas previamente.
- HU-10 consolida información de los módulos anteriores y permite hacer seguimiento a su preparación y registro en CvLAC.

## 7. Lineamientos técnicos transversales

Las siguientes condiciones aplican al desarrollo de las historias cuando corresponda:

1. **Tecnologías:** Java 17 o superior, Spring Boot, PostgreSQL y frontend web.
2. **Arquitectura:** separación por capas bajo MVC, con responsabilidades diferenciadas para controladores, servicios y persistencia.
3. **Persistencia:** utilizar Spring Data JPA y el patrón Repository para el acceso a PostgreSQL.
4. **Seguridad:** proteger los endpoints privados mediante Spring Security y JWT; validar la propiedad de los registros para evitar accesos entre investigadores.
5. **Validaciones:** utilizar validaciones de entrada y respuestas controladas para errores de negocio, datos inválidos o recursos inexistentes.
6. **Documentos:** PostgreSQL almacena los metadatos y referencias de los archivos; el contenido binario se aloja en un servicio de almacenamiento de archivos u objetos que debe seleccionarse y configurarse.
7. **Patrones de diseño:** aplicar y justificar los patrones Repository, Factory, Observer o Singleton donde aporten valor al dominio; no incorporarlos de forma artificial.
8. **Pruebas:** utilizar JUnit 5 y Mockito para pruebas unitarias y de integración, según corresponda.
9. **Calidad:** configurar Checkstyle y JaCoCo, con una cobertura mínima global del 60% según el requisito del proyecto.
10. **Integración continua:** ejecutar compilación, análisis estático y pruebas automatizadas mediante GitHub Actions en los eventos definidos para el repositorio.
11. **Control de versiones:** utilizar la estrategia de ramas definida por el equipo, Conventional Commits y revisión por pares mediante Pull Requests.

## 8. Definition of Done (DoD) general

Una historia se considera terminada cuando cumple las condiciones que le correspondan:

- Los criterios de aceptación BDD están implementados y verificados.
- El código respeta la arquitectura y las convenciones del proyecto.
- Las entradas están validadas y los errores se manejan de manera controlada.
- La persistencia funciona correctamente cuando la historia requiere almacenar información.
- Se cuenta con pruebas unitarias y/o de integración para los comportamientos implementados.
- Checkstyle no reporta advertencias o incumplimientos pendientes.
- La ejecución de pruebas y los controles configurados en CI finalizan correctamente.
- La cobertura global de JaCoCo cumple el mínimo del 60% exigido por el proyecto.
- Los cambios fueron enviados mediante Pull Request y revisados por otro integrante del equipo antes de integrarse.
- La documentación técnica relacionada se encuentra actualizada.

## 9. Fuera del alcance confirmado de esta versión

Para evitar comprometer funcionalidades que todavía no tienen una solución técnica definida, se dejan fuera del alcance confirmado:

- Carga automática de información hacia CvLAC o integración directa con sistemas de MinCiencias. Esta posibilidad requiere verificar previamente la existencia, disponibilidad, autorización y condiciones técnicas de una interfaz oficial.
- Envío de recordatorios por correo electrónico, SMS o servicios externos. La primera versión contempla la gestión y visualización de recordatorios dentro de la aplicación.
- Selección definitiva del proveedor externo de almacenamiento de archivos. Se definirá durante el diseño técnico de HU-07.

## 10. Trazabilidad con el propósito del proyecto

La distribución propuesta sigue la evolución funcional de Asesor CvLAC:

1. **Sprint 1:** identifica y protege al investigador que utiliza la plataforma.
2. **Sprint 2:** permite comenzar a registrar su producción investigativa y formación.
3. **Sprint 3:** amplía su trayectoria con proyectos y contribuciones.
4. **Sprint 4:** incorpora soportes documentales y experiencia profesional.
5. **Sprint 5:** integra la organización de la información con una guía, checklist y recordatorios para preparar y hacer seguimiento al registro en CvLAC.

La estimación total inicial es de **40 Story Points**, distribuidos en cinco sprints de **8 Story Points cada uno**, sin superar el máximo de 10 Story Points por sprint.
