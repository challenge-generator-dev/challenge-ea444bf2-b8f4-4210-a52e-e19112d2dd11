# Integración de Framework en Sistema de Pagos

El sistema de pagos de una fintech necesita integrar un framework para mejorar la gestión de solicitudes, control de variables de entorno, generación de logs y conexión con bases de datos. El framework debe permitir el enrutamiento de solicitudes, manejo de errores y uso de un ORM para la persistencia de datos. El sistema interactúa con un motor de fraude, un buró de riesgos y un gateway de pagos. Debe asegurar la consistencia de los datos entre estos componentes y manejar adecuadamente los errores provenientes de cada uno.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Experiencia en Frameworks |
| **Nivel** | advanced-l1 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración Inicial del Framework

**Objetivo:** Configurar el framework para enrutar solicitudes y controlar variables de entorno.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar y configurar las rutas necesarias para las solicitudes de pago.
- Establecer variables de entorno para diferentes entornos (desarrollo, pruebas, producción).

**Entregable:** Configuración del framework con rutas y variables de entorno.

<details>
<summary>Pistas de conocimiento</summary>

- Importancia de las rutas en la gestión de solicitudes.
- Uso de variables de entorno para gestionar configuraciones.

</details>

### Fase 2: Implementación de Manejo de Errores

**Objetivo:** Implementar un sistema de manejo de errores que capture y registre los errores provenientes del motor de fraude, buró de riesgos y gateway de pagos.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Crear un sistema de logs para registrar los errores.
- Implementar un mecanismo para capturar y manejar errores específicos de cada componente.

**Entregable:** Sistema de manejo de errores con logs.

<details>
<summary>Pistas de conocimiento</summary>

- Importancia de registrar los errores para la depuración y mejora del sistema.
- Manejo de errores específicos en sistemas distribuidos.

</details>

### Fase 3: Conexión con Base de Datos

**Objetivo:** Conectar el sistema con una base de datos utilizando un ORM para la persistencia de datos.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Configurar la conexión con la base de datos.
- Utilizar un ORM para mapear los objetos del dominio a tablas de la base de datos.

**Entregable:** Conexión con base de datos utilizando ORM.

<details>
<summary>Pistas de conocimiento</summary>

- Ventajas de utilizar un ORM para la persistencia de datos.
- Configuración de la conexión con la base de datos.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un framework y por qué se utiliza en el desarrollo de aplicaciones?
- **paraQueSirve**: ¿Para qué sirve el enrutamiento de solicitudes en un sistema de pagos?
- **comoSeUsa**: ¿Cómo se utiliza un ORM para la persistencia de datos en un sistema de pagos?
- **erroresComunes**: ¿Cuáles son los errores comunes al integrar un framework en un sistema de pagos y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la elección de un framework para un sistema de pagos?

## Criterios de Evaluacion

- Configuración correcta del framework con rutas y variables de entorno.
- Implementación efectiva de un sistema de manejo de errores con logs.
- Conexión exitosa con la base de datos utilizando un ORM.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
