# Definition of Done (DoD)

Contrato técnico de calidad de **Asesor CvLAC**, basado en ISO/IEC 25010. Una historia de usuario solo pasa a *Done* cuando cumple **todos** los puntos que le apliquen. No es negociable.

## Checklist

| # | Condición | Característica ISO/IEC 25010 | Verificación |
|---|---|---|---|
| 1 | Los criterios de aceptación BDD (Given-When-Then) están implementados y verificados | Adecuación funcional | Pruebas que traducen cada escenario |
| 2 | El proyecto compila sin errores ni advertencias | Mantenibilidad | `mvn clean verify` |
| 3 | Checkstyle (Google Java Style) reporta **0 advertencias** | Mantenibilidad (analizabilidad) | `mvn checkstyle:check` |
| 4 | El **100% de las pruebas** pasa | Fiabilidad | `mvn test` |
| 5 | La cobertura global de JaCoCo es **≥ 60%** en instrucciones y ramas | Fiabilidad / Mantenibilidad | `mvn verify` (`jacoco:check`) |
| 6 | Las entradas están validadas y los errores se manejan con respuestas controladas | Seguridad / Tolerancia a fallos | Pruebas de casos inválidos |
| 7 | Los endpoints privados exigen autenticación y validan la propiedad de los registros | Seguridad (confidencialidad) | Pruebas de acceso no autorizado |
| 8 | El código respeta la arquitectura MVC por capas y las convenciones del proyecto | Mantenibilidad (modularidad) | Autorrevisión del PR |
| 9 | Los commits siguen Conventional Commits | Mantenibilidad (analizabilidad) | Hook `commit-msg` |
| 10 | Los cambios llegan a `main` por Pull Request con CI en verde | Fiabilidad | Branch Protection |
| 11 | La checklist de autorrevisión del PR está completa | Mantenibilidad | Plantilla de PR |
| 12 | La documentación técnica relacionada está actualizada (README, ADR, diagramas) | Mantenibilidad (capacidad de ser modificado) | Autorrevisión del PR |

## Revisión por pares en un proyecto individual

El proyecto lo desarrolla una sola persona, por lo que GitHub no permite aprobar el propio Pull Request. La política es:

- El Pull Request es **obligatorio** para integrar a `main` (sin push directo), con 0 aprobaciones requeridas.
- El autor realiza una **autorrevisión documentada**: recorre el diff completo en GitHub, completa la checklist de la plantilla de PR y deja comentarios en el PR sobre los puntos revisados o las decisiones tomadas.
- El pipeline de CI actúa como revisor automático e independiente.

## Excepciones

No hay excepciones al DoD. Si una condición no puede cumplirse, la historia no pasa a *Done*.
