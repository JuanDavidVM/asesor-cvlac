# Guía de configuración del Sprint 0

Pasos manuales para dejar el repositorio listo (*Readiness*). Los archivos de configuración ya están en el proyecto; aquí se configura GitHub y el entorno local.

## 1. Identidad de Git

```bash
git config --global user.name "Tu Nombre Completo"
git config --global user.email "tu.correo@uniajc.edu.co"
git config --global init.defaultBranch main
```

Verifica con `git config --global --list`. El correo debe estar agregado y verificado en GitHub (*Settings → Emails*) para que los commits se asocien a tu cuenta.

## 2. Llave SSH

```bash
ssh-keygen -t ed25519 -C "tu.correo@uniajc.edu.co"
# Linux/macOS
eval "$(ssh-agent -s)" && ssh-add ~/.ssh/id_ed25519
# Mostrar la llave pública para copiarla
cat ~/.ssh/id_ed25519.pub
```

En GitHub: *Settings → SSH and GPG keys → New SSH key* y pega la llave pública. Prueba con:

```bash
ssh -T git@github.com
```

## 3. Crear el repositorio

1. En GitHub: *New repository* → nombre `asesor-cvlac`, público, **sin** README, `.gitignore` ni licencia (ya los trae el proyecto).
2. Desde la carpeta del proyecto:

```bash
git init
git remote add origin git@github.com:<tu-usuario>/asesor-cvlac.git
```

## 4. Activar Husky

Requiere Node.js 20+ y Maven instalados.

```bash
npm install
```

Esto instala Husky y activa `.husky/pre-commit` (`mvn test` + `mvn checkstyle:check`) y `.husky/commit-msg` (valida Conventional Commits). En Windows, si el hook no se ejecuta, marca los archivos como ejecutables en Git:

```bash
git update-index --chmod=+x .husky/pre-commit .husky/commit-msg
```

Verifica el proyecto antes del primer commit:

```bash
mvn verify
```

## 5. Primer commit directo a `main`

La protección de `main` se activa después, así que el commit inicial (único push directo permitido) se hace ahora:

```bash
git add .
git commit -m "chore: configurar Sprint 0 (Maven, Checkstyle, JaCoCo, Husky, documentacion)"
git push -u origin main
```

Si el hook rechaza el commit, revisa el mensaje de error (fallo de pruebas, Checkstyle o formato del mensaje).

## 6. Branch Protection para `main`

*Settings → Branches → Add branch ruleset* (o *Add classic branch protection rule*), objetivo `main`:

- **Require a pull request before merging**
  - Required approvals: **0** (revisión por pares reemplazada por la autorrevisión documentada, ver `docs/dod.md`)
- **Block force pushes**
- **Restrict deletions**
- **Do not allow bypassing the above settings** (incluye a administradores, para que tampoco puedas hacer push directo)
- **Require status checks to pass:** agrégala en el Sprint 1, cuando exista el workflow `ci.yml` y haya corrido al menos una vez (GitHub solo ofrece los checks que ya se han ejecutado).

## 7. Tablero Kanban (GitHub Projects)

1. En el repositorio: *Projects → New project → Board*, nombre `Asesor CvLAC`. Vincúlalo al repositorio.
2. Renombra las opciones del campo **Status** para tener las columnas obligatorias:
   `Product Backlog` · `Sprint Backlog (To Do)` · `In Progress` · `Code Review / Testing` · `Done`
3. Campos personalizados sugeridos:
   - `Story Points` (Number)
   - `MoSCoW` (Single select: Must, Should, Could, Won't)
   - `Sprint` (Iteration, 5 iteraciones)
4. Crea un issue por historia (HU-01 a HU-10) y agrégalo al tablero en `Product Backlog`.

**WIP Limits:** GitHub Projects no impone límites de trabajo en progreso de forma nativa. Documenta la política en la descripción de cada columna y respétala manualmente:

| Columna | Límite |
|---|---|
| In Progress | máximo 3 tarjetas |
| Code Review / Testing | máximo 2 Pull Requests |

**Política de salida a Done:** una tarjeta pasa a `Done` solo cuando el PR está fusionado, las pruebas pasan, Checkstyle reporta 0 advertencias y la checklist de autorrevisión está completa.

## 8. Flujo de trabajo por historia

```bash
git switch main && git pull
git switch -c feature/HU01-registro-investigador
# ... TDD: test (rojo) -> feat (verde) -> refactor
git commit -m "test(auth): escenario de registro exitoso HU-01"
git commit -m "feat(auth): registrar investigador"
git push -u origin feature/HU01-registro-investigador
# Abrir Pull Request en GitHub, completar la plantilla, fusionar y borrar la rama
```

## Lista de verificación del Sprint 0

- [ ] `git config` con correo institucional y llave SSH funcionando
- [ ] Repositorio público creado y primer commit en `main`
- [ ] `.gitignore`, `checkstyle.xml`, `pom.xml` (Checkstyle + JaCoCo) en el repositorio
- [ ] Husky activo (`pre-commit` y `commit-msg`)
- [ ] `README.md` con visión, branching y diagrama Mermaid
- [ ] `docs/dod.md` y `docs/adr/0001-trunk-based-development.md`
- [ ] Plantilla de Pull Request
- [ ] Branch Protection activa en `main`
- [ ] Tablero GitHub Projects con columnas, políticas y las 10 HUs
