# ADR-0001: Estrategia de ramificación Trunk-Based Development

- **Estado:** Aceptada
- **Fecha:** 2026-11-11
- **Autor:** Juan David Vidal Muñoz

## Contexto

Asesor CvLAC es desarrollado por una sola persona. Se requiere una estrategia de ramificación explícita que sea compatible con integración continua, Conventional Commits y Pull Requests obligatorios. Las opciones consideradas fueron Trunk-Based Development y GitFlow.

## Decisión

Se adopta **Trunk-Based Development**:

- `main` es la única rama de larga vida y siempre debe estar en estado desplegable.
- Los cambios se desarrollan en ramas cortas `feature/*` (por ejemplo, `feature/HU01-registro-investigador`) creadas desde `main`.
- Todo cambio se integra a `main` mediante Pull Request, sin push directo.
- Branch Protection exige Pull Request y, una vez disponible el pipeline, que los status checks de CI pasen.

## Consecuencias

**Positivas**
- Flujo simple, sin ramas `develop` ni `release` que mantener sincronizadas.
- Integración frecuente, menos conflictos e historia lineal.
- `main` queda protegido por CI y la checklist de autorrevisión.

**Negativas**
- No hay una rama de estabilización para versiones. Si se requiriera mantener varias versiones en paralelo, habría que revisar esta decisión.
- Las funcionalidades incompletas no pueden quedar en `main`: deben dividirse en cambios pequeños o protegerse con configuración.

## Alternativas descartadas

- **GitFlow:** aporta estructura para equipos y ciclos de release formales, pero con un solo desarrollador agrega ramas y fusiones sin beneficio.
