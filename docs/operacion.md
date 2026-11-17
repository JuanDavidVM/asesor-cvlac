# Entornos y operación

| Entorno | Datos | Uso |
|---|---|---|
| Local | Ficticios y credenciales de desarrollo | Implementación |
| Revisión | Rama o proyecto Neon con datos ficticios | Aceptación y revisión cruzada |
| Producción | Proyecto y credenciales dedicados | Datos reales |

El alojamiento web debe soportar Next.js con ejecución en servidor. Elegir el proveedor antes del piloto y colocar la app cerca de la región de Neon. Configurar credenciales solo en el entorno de servidor. Antes de habilitar auth, registrar las URLs válidas de cada entorno en Neon. Antes de habilitar archivos, crear un bucket privado con límites y probar el acceso entre dos cuentas.

Al incorporar Drizzle, versionar el SQL de cada migración. Aplicarla primero en revisión y después en producción como paso explícito de despliegue. Mantener un plan de exportación de PostgreSQL y otro para archivos. Probar cómo recuperar ambos antes del piloto.

En Neon Free, el cómputo de PostgreSQL se duerme por inactividad y despierta con la siguiente consulta. La primera carga puede tardar más. Vigilar las cuotas de base de datos, cómputo y archivos. Los límites del alojamiento web se evalúan por separado. [Descripción del plan Neon](https://neon.com/blog/neon-free-plan-1-gb-per-project).
