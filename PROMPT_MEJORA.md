# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/payments/domain/port/FraudEnginePort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/port/RiskBureauPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/port/PaymentGatewayPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/port/PaymentRepositoryPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/FraudEngineAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/FraudEngineAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/ErrorHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/RequestLoggingInterceptor.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `Payment.getReferenceNumber`: Se invoca `getReferenceNumber` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/FraudEngineAdapter.java` — `Payment.getPaymentId`: Se invoca `getPaymentId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/FraudEngineAdapter.java` — `Payment.getReferenceNumber`: Se invoca `getReferenceNumber` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/FraudEngineAdapter.java` — `Payment.getCustomerId`: Se invoca `getCustomerId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/FraudEngineAdapter.java` — `Payment.getMerchantId`: Se invoca `getMerchantId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/FraudEngineAdapter.java` — `Payment.getAmount`: Se invoca `getAmount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/FraudEngineAdapter.java` — `Payment.getCurrency`: Se invoca `getCurrency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/FraudEngineAdapter.java` — `Payment.getPaymentMethod`: Se invoca `getPaymentMethod` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauAdapter.java` — `Payment.getPaymentId`: Se invoca `getPaymentId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauAdapter.java` — `Payment.getCustomerId`: Se invoca `getCustomerId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauAdapter.java` — `Payment.getMerchantId`: Se invoca `getMerchantId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauAdapter.java` — `Payment.getAmount`: Se invoca `getAmount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauAdapter.java` — `Payment.getCurrency`: Se invoca `getCurrency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauAdapter.java` — `Payment.getPaymentMethod`: Se invoca `getPaymentMethod` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauAdapter.java` — `Payment.getFraudEngineResponse`: Se invoca `getFraudEngineResponse` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `Payment.getReferenceNumber`: Se invoca `getReferenceNumber` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `Payment.getPaymentId`: Se invoca `getPaymentId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `Payment.getCustomerId`: Se invoca `getCustomerId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `Payment.getMerchantId`: Se invoca `getMerchantId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `Payment.getAmount`: Se invoca `getAmount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `Payment.getCurrency`: Se invoca `getCurrency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/PaymentGatewayAdapter.java` — `Payment.getPaymentMethod`: Se invoca `getPaymentMethod` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getPaymentId`: Se invoca `getPaymentId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getReferenceNumber`: Se invoca `getReferenceNumber` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getCustomerId`: Se invoca `getCustomerId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getMerchantId`: Se invoca `getMerchantId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getAmount`: Se invoca `getAmount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getCurrency`: Se invoca `getCurrency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getPaymentMethod`: Se invoca `getPaymentMethod` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getStatus`: Se invoca `getStatus` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getCreatedAt`: Se invoca `getCreatedAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getUpdatedAt`: Se invoca `getUpdatedAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getFraudEngineResponse`: Se invoca `getFraudEngineResponse` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getRiskBureauResponse`: Se invoca `getRiskBureauResponse` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java` — `Payment.getPaymentGatewayResponse`: Se invoca `getPaymentGatewayResponse` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `CreatePaymentRequest.getCustomerId`: Se invoca `getCustomerId` sobre `CreatePaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `CreatePaymentRequest.getMerchantId`: Se invoca `getMerchantId` sobre `CreatePaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `CreatePaymentRequest.getReferenceNumber`: Se invoca `getReferenceNumber` sobre `CreatePaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `CreatePaymentRequest.getAmount`: Se invoca `getAmount` sobre `CreatePaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `CreatePaymentRequest.getCurrency`: Se invoca `getCurrency` sobre `CreatePaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `CreatePaymentRequest.getPaymentMethod`: Se invoca `getPaymentMethod` sobre `CreatePaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `Payment.getPaymentId`: Se invoca `getPaymentId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `PaymentService.getAllPayments`: Se invoca `getAllPayments` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `PaymentService.getPaymentsByCustomerId`: Se invoca `getPaymentsByCustomerId` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java` — `PaymentService.getPaymentsByMerchantId`: Se invoca `getPaymentsByMerchantId` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getPaymentId`: Se invoca `getPaymentId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getReferenceNumber`: Se invoca `getReferenceNumber` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getCustomerId`: Se invoca `getCustomerId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getMerchantId`: Se invoca `getMerchantId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getAmount`: Se invoca `getAmount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getCurrency`: Se invoca `getCurrency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getPaymentMethod`: Se invoca `getPaymentMethod` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getStatus`: Se invoca `getStatus` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getCreatedAt`: Se invoca `getCreatedAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getUpdatedAt`: Se invoca `getUpdatedAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getFraudEngineResponse`: Se invoca `getFraudEngineResponse` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getRiskBureauResponse`: Se invoca `getRiskBureauResponse` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java` — `Payment.getPaymentGatewayResponse`: Se invoca `getPaymentGatewayResponse` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `PaymentGatewayPort.process`: Se invoca `process` sobre `PaymentGatewayPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `PaymentService.processPayment`: Se invoca `processPayment` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `Payment.getPaymentId`: Se invoca `getPaymentId` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Chapter Backend, Especialidad Desarrollador, Tecnolog�a Java, Advanced � Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programaci�n principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM). � El candidato es un desarrollador Backend Advanced con experiencia en estructuras de datos y patrones GRASP. Est� listo para profundizar en frameworks como Spring Boot.

### Reto
- Tema: Experiencia en Frameworks
- Seniority: advanced-l1
- Tipo: practical
- Título: Integración de Framework en Sistema de Pagos
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración Inicial del Framework — objetivo: Configurar el framework para enrutar solicitudes y controlar variables de entorno. — entregable (NO resolver): Configuración del framework con rutas y variables de entorno.
- Fase 2: Implementación de Manejo de Errores — objetivo: Implementar un sistema de manejo de errores que capture y registre los errores provenientes del motor de fraude, buró de riesgos y gateway de pagos. — entregable (NO resolver): Sistema de manejo de errores con logs.
- Fase 3: Conexión con Base de Datos — objetivo: Conectar el sistema con una base de datos utilizando un ORM para la persistencia de datos. — entregable (NO resolver): Conexión con base de datos utilizando ORM.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>payments</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>payments</name>
    <description>Sistema de pagos con integracion de framework para gestion de solicitudes y persistencia</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
        <lombok.version>1.18.30</lombok.version>
        <postgresql.version>42.7.3</postgresql.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- OpenAPI Documentation -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>${postgresql.version}</version>
            <scope>runtime</scope>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/payments/Application.java ===
package com.pragma.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import java.time.Duration;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
public class Application {

    @Value("${fraud.engine.url}")
    private String fraudEngineUrl;

    @Value("${risk.bureau.url}")
    private String riskBureauUrl;

    @Value("${payment.gateway.url}")
    private String paymentGatewayUrl;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreaker fraudEngineCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .build();
        return circuitBreakerRegistry.circuitBreaker("fraudEngineCircuitBreaker", config);
    }

    @Bean
    public CircuitBreaker riskBureauCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .build();
        return circuitBreakerRegistry.circuitBreaker("riskBureauCircuitBreaker", config);
    }

    @Bean
    public CircuitBreaker paymentGatewayCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .build();
        return circuitBreakerRegistry.circuitBreaker("paymentGatewayCircuitBreaker", config);
    }

    @Bean
    public HealthIndicator fraudEngineHealthIndicator() {
        return () -> {
            try {
                // Simulación de verificación de salud del motor de fraude
                return Health.up().withDetail("url", fraudEngineUrl).build();
            } catch (Exception e) {
                return Health.down().withDetail("error", e.getMessage()).build();
            }
        };
    }

    @Bean
    public HealthIndicator riskBureauHealthIndicator() {
        return () -> {
            try {
                // Simulación de verificación de salud del buró de riesgos
                return Health.up().withDetail("url", riskBureauUrl).build();
            } catch (Exception e) {
                return Health.down().withDetail("error", e.getMessage()).build();
            }
        };
    }

    @Bean
    public HealthIndicator paymentGatewayHealthIndicator() {
        return () -> {
            try {
                // Simulación de verificación de salud del gateway de pagos
                return Health.up().withDetail("url", paymentGatewayUrl).build();
            } catch (Exception e) {
                return Health.down().withDetail("error", e.getMessage()).build();
            }
        };
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080
  servlet:
    context-path: /api/payments

spring:
  application:
    name: payments-service
  profiles:
    active: dev
  datasource:
    url: jdbc:postgresql://localhost:5432/payments_db
    username: payments_user
    password: payments_password
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 30000
      maximum-pool-size: 10
      pool-name: payments-pool
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
  mvc:
    throw-exception-if-no-handler-found: true
  web:
    resources:
      add-mappings: false

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,circuitbreakers
  endpoint:
    health:
      show-details: always
  health:
    circuitbreakers:
      enabled: true

logging:
  level:
    root: INFO
    com.pragma.payments: DEBUG
    org.springframework.web: INFO
    org.hibernate: ERROR
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/payments-service.log

resilience4j:
  circuitbreaker:
    instances:
      fraudEngineCircuitBreaker:
        registerHealthIndicator: true
        slidingWindowSize: 5
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 10s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
      riskBureauCircuitBreaker:
        registerHealthIndicator: true
        slidingWindowSize: 5
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 10s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
      paymentGatewayCircuitBreaker:
        registerHealthIndicator: true
        slidingWindowSize: 5
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 10s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10

fraud:
  engine:
    url: http://fraud-engine:8080/api/check
    timeout: 5000

risk:
  bureau:
    url: http://risk-bureau:8080/api/evaluate
    timeout: 5000

payment:
  gateway:
    url: http://payment-gateway:8080/api/process
    timeout: 5000
    merchantId: merchant_123
    secretKey: secret_key_123

// === ARCHIVO: src/main/java/com/pragma/payments/domain/model/Payment.java ===
package com.pragma.payments.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidad de dominio que representa un pago en el sistema.
 * Contiene los datos esenciales de una transacción financiera y su estado.
 */
@Getter
@Builder(toBuilder = true)
@ToString
public class Payment {
    private final UUID paymentId;
    private final String referenceNumber;
    private final String customerId;
    private final String merchantId;
    private final BigDecimal amount;
    private final String currency;
    private final PaymentMethod paymentMethod;
    private final PaymentStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String fraudEngineResponse;
    private final String riskBureauResponse;
    private final String paymentGatewayResponse;

    /**
     * Crea una nueva instancia de Payment con los valores iniciales.
     * @param referenceNumber Número de referencia único de la transacción.
     * @param customerId Identificador del cliente.
     * @param merchantId Identificador del comercio.
     * @param amount Monto de la transacción.
     * @param currency Moneda de la transacción.
     * @param paymentMethod Método de pago utilizado.
     * @return Nueva instancia de Payment.
     */
    public static Payment create(String referenceNumber, String customerId, String merchantId,
                              BigDecimal amount, String currency, PaymentMethod paymentMethod) {
        LocalDateTime now = LocalDateTime.now();
        return Payment.builder()
                .paymentId(UUID.randomUUID())
                .referenceNumber(referenceNumber)
                .customerId(customerId)
                .merchantId(merchantId)
                .amount(amount)
                .currency(currency)
                .paymentMethod(paymentMethod)
                .status(PaymentStatus.PENDING)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    /**
     * Actualiza el estado del pago y registra la respuesta del motor de fraude.
     * @param status Nuevo estado del pago.
     * @param fraudEngineResponse Respuesta del motor de fraude.
     * @return Nueva instancia de Payment con los valores actualizados.
     */
    public Payment withFraudEngineResponse(PaymentStatus status, String fraudEngineResponse) {
        return this.toBuilder()
                .status(status)
                .fraudEngineResponse(fraudEngineResponse)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    /**
     * Actualiza el estado del pago y registra la respuesta del buró de riesgos.
     * @param status Nuevo estado del pago.
     * @param riskBureauResponse Respuesta del buró de riesgos.
     * @return Nueva instancia de Payment con los valores actualizados.
     */
    public Payment withRiskBureauResponse(PaymentStatus status, String riskBureauResponse) {
        return this.toBuilder()
                .status(status)
                .riskBureauResponse(riskBureauResponse)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    /**
     * Actualiza el estado del pago y registra la respuesta del gateway de pagos.
     * @param status Nuevo estado del pago.
     * @param paymentGatewayResponse Respuesta del gateway de pagos.
     * @return Nueva instancia de Payment con los valores actualizados.
     */
    public Payment withPaymentGatewayResponse(PaymentStatus status, String paymentGatewayResponse) {
        return this.toBuilder()
                .status(status)
                .paymentGatewayResponse(paymentGatewayResponse)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, BANK_TRANSFER, MOBILE_PAYMENT
    }

    public enum PaymentStatus {
        PENDING, APPROVED, REJECTED, FRAUD_DETECTED, RISK_REJECTED, PROCESSED
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/FraudEnginePort.java ===
package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

/**
 * Puerto que define la interfaz para interactuar con el motor de fraude.
 * La implementación concreta se proporciona en la capa de infraestructura.
 */
public interface FraudEnginePort {
    /**
     * Evalúa una transacción con el motor de fraude.
     * @param payment Pago a evaluar.
     * @return Mono que emite el pago actualizado con el resultado de la evaluación de fraude.
     * @throws com.pragma.payments.domain.exception.FraudEngineException si ocurre un error en la comunicación.
     */
    Mono<Payment> evaluateForFraud(Payment payment);
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/RiskBureauPort.java ===
package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

/**
 * Puerto que define la interfaz para interactuar con el buró de riesgos.
 * La implementación concreta se proporciona en la capa de infraestructura.
 */
public interface RiskBureauPort {
    /**
     * Evalúa el riesgo de una transacción con el buró de riesgos.
     * @param payment Pago a evaluar.
     * @return Mono que emite el pago actualizado con el resultado de la evaluación de riesgo.
     * @throws com.pragma.payments.domain.exception.RiskBureauException si ocurre un error en la comunicación.
     */
    Mono<Payment> evaluateRisk(Payment payment);
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/PaymentGatewayPort.java ===
package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

/**
 * Puerto del dominio para la integración con el gateway de pagos.
 * Define el contrato que la infraestructura debe implementar para procesar pagos.
 */
public interface PaymentGatewayPort {

    /**
     * Procesa un pago a través del gateway externo.
     * @param payment la entidad de pago con los datos necesarios para la transacción
     * @return Mono que emite el pago actualizado con la respuesta del gateway
     */
    Mono<Payment> processPayment(Payment payment);

    /**
     * Consulta el estado de una transacción previamente procesada.
     * @param paymentId identificador único del pago
     * @return Mono que emite el pago con el estado actualizado
     */
    Mono<Payment> getPaymentStatus(String paymentId);

    /**
     * Cancela una transacción existente en el gateway.
     * @param paymentId identificador único del pago a cancelar
     * @return Mono que emite el pago con estado CANCELLED
     */
    Mono<Payment> cancelPayment(String paymentId);

    /**
     * Reembolsa un pago previamente procesado.
     * @param paymentId identificador único del pago a reembolsar
     * @param amount monto específico a reembolsar (puede ser parcial)
     * @return Mono que emite el pago con estado REFUNDED
     */
    Mono<Payment> refundPayment(String paymentId, java.math.BigDecimal amount);
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/port/PaymentRepositoryPort.java ===
package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Puerto del dominio para operaciones de persistencia de pagos.
 * Define el contrato para guardar, actualizar y consultar pagos en la capa de datos.
 */
public interface PaymentRepositoryPort {

    /**
     * Persiste un nuevo pago en el sistema.
     * @param payment la entidad de pago a guardar
     * @return Mono que emite el pago persistido con su ID asignado
     */
    Mono<Payment> save(Payment payment);

    /**
     * Actualiza el estado de un pago existente.
     * @param paymentId identificador único del pago
     * @param status nuevo estado del pago
     * @param response respuesta del servicio externo que actualizó el estado
     * @return Mono que emite el pago actualizado
     */
    Mono<Payment> updateStatus(UUID paymentId, PaymentStatus status, String response);

    /**
     * Busca un pago por su identificador único.
     * @param paymentId identificador del pago
     * @return Mono que emite el pago encontrado o vacío si no existe
     */
    Mono<Payment> findById(UUID paymentId);

    /**
     * Busca un pago por su número de referencia único.
     * @param referenceNumber número de referencia del pago
     * @return Mono que emite el pago encontrado o vacío
     */
    Mono<Payment> findByReferenceNumber(String referenceNumber);

    /**
     * Recupera todos los pagos de un cliente específico.
     * @param customerId identificador del cliente
     * @return Lista de pagos del cliente
     */
    Mono<List<Payment>> findByCustomerId(String customerId);

    /**
     * Recupera todos los pagos de un comerciante específico.
     * @param merchantId identificador del comerciante
     * @return Lista de pagos del comerciante
     */
    Mono<List<Payment>> findByMerchantId(String merchantId);

    /**
     * Busca pagos por estado específico.
     * @param status estado de los pagos a buscar
     * @return Lista de pagos con el estado especificado
     */
    Mono<List<Payment>> findByStatus(PaymentStatus status);

    /**
     * Recupera pagos dentro de un rango de fechas.
     * @param startDate fecha de inicio del rango
     * @param endDate fecha de fin del rango
     * @return Lista de pagos creados en el período especificado
     */
    Mono<List<Payment>> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Obtiene el total de pagos realizados por un cliente.
     * @param customerId identificador del cliente
     * @return suma total de los montos de los pagos
     */
    Mono<BigDecimal> getTotalAmountByCustomerId(String customerId);

    /**
     * Elimina un pago del sistema.
     * @param paymentId identificador del pago a eliminar
     * @return Mono que completa cuando la eliminación es exitosa
     */
    Mono<Void> deleteById(UUID paymentId);
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/exception/FraudEngineException.java ===
package com.pragma.payments.domain.exception;

/**
 * Excepción personalizada para errores provenientes del motor de fraude.
 * Se lanza cuando el servicio externo de evaluación de fraude falla o retorna
 * resultados que impiden continuar con el procesamiento del pago.
 */
public class FraudEngineException extends RuntimeException {

    private final String errorCode;
    private final String paymentId;
    private final boolean retryable;

    /**
     * Constructor con mensaje de error básico.
     * @param message descripción del error
     */
    public FraudEngineException(String message) {
        super(message);
        this.errorCode = "FRAUD_ENGINE_ERROR";
        this.paymentId = null;
        this.retryable = false;
    }

    /**
     * Constructor con mensaje y causa de la excepción.
     * @param message descripción del error
     * @param cause excepción original que causó el error
     */
    public FraudEngineException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "FRAUD_ENGINE_ERROR";
        this.paymentId = null;
        this.retryable = false;
    }

    /**
     * Constructor completo con todos los detalles del error.
     * @param message descripción del error
     * @param errorCode código específico del error del motor de fraude
     * @param paymentId identificador del pago que provocó el error
     * @param retryable indica si la operación puede reintentarse
     */
    public FraudEngineException(String message, String errorCode, String paymentId, boolean retryable) {
        super(message);
        this.errorCode = errorCode;
        this.paymentId = paymentId;
        this.retryable = retryable;
    }

    /**
     * Constructor completo con causa de la excepción.
     * @param message descripción del error
     * @param errorCode código específico del error
     * @param paymentId identificador del pago
     * @param retryable indica si la operación puede reintentarse
     * @param cause excepción original
     */
    public FraudEngineException(String message, String errorCode, String paymentId, boolean retryable, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.paymentId = paymentId;
        this.retryable = retryable;
    }

    /**
     * Crea una excepción para el caso de motor de fraude no disponible.
     * @param paymentId identificador del pago
     * @return excepción configurada como reintentable
     */
    public static FraudEngineException serviceUnavailable(String paymentId) {
        return new FraudEngineException(
            "El motor de fraude no está disponible en este momento",
            "FRAUD_SERVICE_UNAVAILABLE",
            paymentId,
            true
        );
    }

    /**
     * Crea una excepción para respuestas inválidas del motor de fraude.
     * @param paymentId identificador del pago
     * @param reason razón de la invalidez
     * @return excepción configurada como no reintentable
     */
    public static FraudEngineException invalidResponse(String paymentId, String reason) {
        return new FraudEngineException(
            "Respuesta inválida del motor de fraude: " + reason,
            "FRAUD_INVALID_RESPONSE",
            paymentId,
            false
        );
    }

    /**
     * Crea una excepción para pagos marcados como fraudulentos.
     * @param paymentId identificador del pago
     * @param details detalles adicionales del fraude detectado
     * @return excepción no reintentable ya que el pago fue rechazado
     */
    public static FraudEngineException fraudDetected(String paymentId, String details) {
        return new FraudEngineException(
            "Pago rechazado por sospecha de fraude: " + details,
            "FRAUD_DETECTED",
            paymentId,
            false
        );
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public boolean isRetryable() {
        return retryable;
    }

    @Override
    public String toString() {
        return "FraudEngineException{" +
                "errorCode='" + errorCode + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", retryable=" + retryable +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/exception/RiskBureauException.java ===
package com.pragma.payments.domain.exception;

import java.util.UUID;

public class RiskBureauException extends RuntimeException {

    private final String bureauIdentifier;
    private final String errorCode;
    private final UUID paymentId;
    private final String customerId;

    public RiskBureauException(String message) {
        super(message);
        this.bureauIdentifier = null;
        this.errorCode = null;
        this.paymentId = null;
        this.customerId = null;
    }

    public RiskBureauException(String message, String bureauIdentifier, String errorCode) {
        super(message);
        this.bureauIdentifier = bureauIdentifier;
        this.errorCode = errorCode;
        this.paymentId = null;
        this.customerId = null;
    }

    public RiskBureauException(String message, String bureauIdentifier, String errorCode, 
                               UUID paymentId, String customerId) {
        super(message);
        this.bureauIdentifier = bureauIdentifier;
        this.errorCode = errorCode;
        this.paymentId = paymentId;
        this.customerId = customerId;
    }

    public RiskBureauException(String message, Throwable cause, String bureauIdentifier, 
                               String errorCode, UUID paymentId, String customerId) {
        super(message, cause);
        this.bureauIdentifier = bureauIdentifier;
        this.errorCode = errorCode;
        this.paymentId = paymentId;
        this.customerId = customerId;
    }

    public String getBureauIdentifier() {
        return bureauIdentifier;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "RiskBureauException{" +
                "message='" + getMessage() + '\'' +
                ", bureauIdentifier='" + bureauIdentifier + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", paymentId=" + paymentId +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/exception/PaymentGatewayException.java ===
package com.pragma.payments.domain.exception;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentGatewayException extends RuntimeException {

    private final String gatewayName;
    private final String responseCode;
    private final UUID paymentId;
    private final BigDecimal amount;
    private final String merchantId;
    private final boolean retryable;

    public PaymentGatewayException(String message) {
        super(message);
        this.gatewayName = null;
        this.responseCode = null;
        this.paymentId = null;
        this.amount = null;
        this.merchantId = null;
        this.retryable = false;
    }

    public PaymentGatewayException(String message, String gatewayName, String responseCode) {
        super(message);
        this.gatewayName = gatewayName;
        this.responseCode = responseCode;
        this.paymentId = null;
        this.amount = null;
        this.merchantId = null;
        this.retryable = determineRetryability(responseCode);
    }

    public PaymentGatewayException(String message, String gatewayName, String responseCode, 
                                   UUID paymentId, BigDecimal amount, String merchantId) {
        super(message);
        this.gatewayName = gatewayName;
        this.responseCode = responseCode;
        this.paymentId = paymentId;
        this.amount = amount;
        this.merchantId = merchantId;
        this.retryable = determineRetryability(responseCode);
    }

    public PaymentGatewayException(String message, Throwable cause, String gatewayName, 
                                   String responseCode, UUID paymentId, BigDecimal amount, 
                                   String merchantId) {
        super(message, cause);
        this.gatewayName = gatewayName;
        this.responseCode = responseCode;
        this.paymentId = paymentId;
        this.amount = amount;
        this.merchantId = merchantId;
        this.retryable = determineRetryability(responseCode);
    }

    private boolean determineRetryability(String responseCode) {
        if (responseCode == null) {
            return false;
        }
        return responseCode.startsWith("TIMEOUT") || 
               responseCode.startsWith("NETWORK_ERROR") ||
               responseCode.equals("503") ||
               responseCode.equals("504");
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public boolean isRetryable() {
        return retryable;
    }

    @Override
    public String toString() {
        return "PaymentGatewayException{" +
                "message='" + getMessage() + '\'' +
                ", gatewayName='" + gatewayName + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", paymentId=" + paymentId +
                ", amount=" + amount +
                ", merchantId='" + merchantId + '\'' +
                ", retryable=" + retryable +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/PaymentJpaRepository.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.port.PaymentRepositoryPort;
import com.pragma.payments.infrastructure.adapter.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID>, PaymentRepositoryPort {

    @Override
    default PaymentEntity savePayment(PaymentEntity payment) {
        return save(payment);
    }

    @Override
    default Optional<PaymentEntity> findByPaymentId(UUID paymentId) {
        return findById(paymentId);
    }

    @Override
    default List<PaymentEntity> findByCustomerId(String customerId) {
        return findAllByCustomerId(customerId);
    }

    @Override
    default List<PaymentEntity> findByMerchantId(String merchantId) {
        return findAllByMerchantId(merchantId);
    }

    @Override
    default List<PaymentEntity> findByStatus(String status) {
        return findAllByStatus(status);
    }

    @Override
    default List<PaymentEntity> findByReferenceNumber(String referenceNumber) {
        return findAllByReferenceNumber(referenceNumber);
    }

    @Override
    default List<PaymentEntity> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return findAllByCreatedAtBetween(startDate, endDate);
    }

    @Query("SELECT p FROM PaymentEntity p WHERE p.customerId = :customerId AND p.status = :status ORDER BY p.createdAt DESC")
    List<PaymentEntity> findByCustomerIdAndStatus(@Param("customerId") String customerId, 
                                                   @Param("status") String status);

    @Query("SELECT p FROM PaymentEntity p WHERE p.merchantId = :merchantId AND p.createdAt >= :since ORDER BY p.createdAt DESC")
    List<PaymentEntity> findRecentByMerchantId(@Param("merchantId") String merchantId, 
                                                @Param("since") LocalDateTime since);

    @Query("SELECT COUNT(p) FROM PaymentEntity p WHERE p.merchantId = :merchantId AND p.status = :status AND p.createdAt >= :since")
    Long countByMerchantIdAndStatusSince(@Param("merchantId") String merchantId, 
                                         @Param("status") String status, 
                                         @Param("since") LocalDateTime since);

    @Query("SELECT p FROM PaymentEntity p WHERE p.referenceNumber = :referenceNumber AND p.customerId = :customerId")
    Optional<PaymentEntity> findByReferenceNumberAndCustomerId(@Param("referenceNumber") String referenceNumber, 
                                                                @Param("customerId") String customerId);

    List<PaymentEntity> findAllByCustomerId(String customerId);

    List<PaymentEntity> findAllByMerchantId(String merchantId);

    List<PaymentEntity> findAllByStatus(String status);

    List<PaymentEntity> findAllByReferenceNumber(String referenceNumber);

    List<PaymentEntity> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    boolean existsByReferenceNumberAndCustomerId(String referenceNumber, String customerId);

    boolean existsByPaymentIdAndStatus(UUID paymentId, String status);

    void deleteByPaymentId(UUID paymentId);

    @Query("SELECT p FROM PaymentEntity p WHERE p.paymentId = :paymentId AND p.merchantId = :merchantId")
    Optional<PaymentEntity> findByPaymentIdAndMerchantId(@Param("paymentId") UUID paymentId, 
                                                          @Param("merchantId") String merchantId);

    @Query("SELECT SUM(p.amount) FROM PaymentEntity p WHERE p.merchantId = :merchantId AND p.status = :status AND p.createdAt >= :since")
    java.math.BigDecimal sumAmountByMerchantIdAndStatusSince(@Param("merchantId") String merchantId, 
                                                              @Param("status") String status, 
                                                              @Param("since") LocalDateTime since);

    @Query("SELECT p FROM PaymentEntity p WHERE p.customerId = :customerId ORDER BY p.createdAt DESC LIMIT :limit")
    List<PaymentEntity> findLatestByCustomerId(@Param("customerId") String customerId, 
                                               @Param("limit") int limit);

    @Query("SELECT p FROM PaymentEntity p WHERE p.status = :status AND p.updatedAt < :olderThan AND p.retryCount < :maxRetries")
    List<PaymentEntity> findPaymentsToRetry(@Param("status") String status, 
                                            @Param("olderThan") LocalDateTime olderThan, 
                                            @Param("maxRetries") int maxRetries);
}

// === ARCHIVO: src/main/java/com/pragma/payments/application/PaymentService.java ===
package com.pragma.payments.application;

import com.pragma.payments.domain.exception.FraudEngineException;
import com.pragma.payments.domain.exception.PaymentGatewayException;
import com.pragma.payments.domain.exception.RiskBureauException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentMethod;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.FraudEnginePort;
import com.pragma.payments.domain.port.PaymentGatewayPort;
import com.pragma.payments.domain.port.PaymentRepositoryPort;
import com.pragma.payments.domain.port.RiskBureauPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final FraudEnginePort fraudEnginePort;
    private final RiskBureauPort riskBureauPort;
    private final PaymentGatewayPort paymentGatewayPort;
    private final PaymentRepositoryPort paymentRepositoryPort;

    public PaymentService(
            FraudEnginePort fraudEnginePort,
            RiskBureauPort riskBureauPort,
            PaymentGatewayPort paymentGatewayPort,
            PaymentRepositoryPort paymentRepositoryPort) {
        this.fraudEnginePort = fraudEnginePort;
        this.riskBureauPort = riskBureauPort;
        this.paymentGatewayPort = paymentGatewayPort;
        this.paymentRepositoryPort = paymentRepositoryPort;
    }

    public Mono<Payment> createPayment(
            String referenceNumber,
            String customerId,
            String merchantId,
            java.math.BigDecimal amount,
            String currency,
            PaymentMethod paymentMethod) {

        log.info("Iniciando proceso de pago para referencia: {}, cliente: {}, monto: {}",
                referenceNumber, customerId, amount);

        Payment payment = Payment.create(
                referenceNumber,
                customerId,
                merchantId,
                amount,
                currency,
                paymentMethod);

        return fraudEnginePort.evaluateForFraud(payment)
                .onErrorResume(FraudEngineException.class, ex -> {
                    log.error("Error en motor de fraude: {}", ex.getMessage());
                    Payment failedPayment = payment.withFraudEngineResponse(
                            PaymentStatus.FAILED,
                            "ERROR: " + ex.getMessage());
                    return paymentRepositoryPort.save(failedPayment)
                            .then(Mono.error(ex));
                })
                .flatMap(paymentWithFraud -> {
                    log.info("Evaluacion de fraude completada, estado: {}", 
                            paymentWithFraud.getStatus());
                    if (paymentWithFraud.getStatus() == PaymentStatus.REJECTED) {
                        log.warn("Pago rechazado por motor de fraude: {}", referenceNumber);
                        return paymentRepositoryPort.save(paymentWithFraud)
                                .then(Mono.just(paymentWithFraud));
                    }
                    return Mono.just(paymentWithFraud);
                })
                .flatMap(paymentAfterFraud -> 
                    riskBureauPort.evaluateRisk(paymentAfterFraud)
                        .onErrorResume(RiskBureauException.class, ex -> {
                            log.error("Error en bureau de riesgos: {}", ex.getMessage());
                            Payment failedPayment = paymentAfterFraud.withRiskBureauResponse(
                                    PaymentStatus.FAILED,
                                    "ERROR: " + ex.getMessage());
                            return paymentRepositoryPort.save(failedPayment)
                                    .then(Mono.error(ex));
                        })
                )
                .flatMap(paymentWithRisk -> {
                    log.info("Evaluacion de riesgo completada, estado: {}", 
                            paymentWithRisk.getStatus());
                    if (paymentWithRisk.getStatus() == PaymentStatus.REJECTED) {
                        log.warn("Pago rechazado por bureau de riesgos: {}", referenceNumber);
                        return paymentRepositoryPort.save(paymentWithRisk)
                                .then(Mono.just(paymentWithRisk));
                    }
                    return Mono.just(paymentWithRisk);
                })
                .flatMap(paymentAfterRisk ->
                    paymentGatewayPort.processPayment(paymentAfterRisk)
                        .onErrorResume(PaymentGatewayException.class, ex -> {
                            log.error("Error en gateway de pagos: {}", ex.getMessage());
                            Payment failedPayment = paymentAfterRisk.withPaymentGatewayResponse(
                                    PaymentStatus.FAILED,
                                    "ERROR: " + ex.getMessage());
                            return paymentRepositoryPort.save(failedPayment)
                                    .then(Mono.error(ex));
                        })
                )
                .flatMap(paymentFinal -> {
                    log.info("Procesamiento de pago completado, estado final: {}", 
                            paymentFinal.getStatus());
                    return paymentRepositoryPort.save(paymentFinal)
                            .then(Mono.just(paymentFinal));
                });
    }

    public Mono<Payment> getPaymentById(java.util.UUID paymentId) {
        log.info("Consultando pago con ID: {}", paymentId);
        return paymentRepositoryPort.findById(paymentId)
                .doOnSuccess(payment -> {
                    if (payment != null) {
                        log.debug("Pago encontrado: {}", payment.getReferenceNumber());
                    } else {
                        log.warn("Pago no encontrado para ID: {}", paymentId);
                    }
                });
    }

    public Mono<java.util.List<Payment>> getPaymentsByCustomer(String customerId) {
        log.info("Consultando pagos para cliente: {}", customerId);
        return paymentRepositoryPort.findByCustomerId(customerId)
                .doOnSuccess(payments -> 
                    log.info("Se encontraron {} pagos para cliente: {}", 
                            payments.size(), customerId));
    }

    public Mono<java.util.List<Payment>> getPaymentsByMerchant(String merchantId) {
        log.info("Consultando pagos para comercio: {}", merchantId);
        return paymentRepositoryPort.findByMerchantId(merchantId)
                .doOnSuccess(payments -> 
                    log.info("Se encontraron {} pagos para comercio: {}", 
                            payments.size(), merchantId));
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/FraudEngineAdapter.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.exception.FraudEngineException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.FraudEnginePort;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class FraudEngineAdapter implements FraudEnginePort {

    private static final Logger log = LoggerFactory.getLogger(FraudEngineAdapter.class);

    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;

    public FraudEngineAdapter(
            RestTemplate restTemplate,
            @Qualifier("fraudEngineCircuitBreaker") CircuitBreaker circuitBreaker) {
        this.restTemplate = restTemplate;
        this.circuitBreaker = circuitBreaker;
    }

    @Override
    public Mono<Payment> evaluateForFraud(Payment payment) {
        log.info("Evaluando pago {} en motor de fraude", payment.getPaymentId());

        return Mono.fromCallable(() -> {
            String url = buildFraudEngineUrl(payment);
            log.debug("Llamando a motor de fraude: {}", url);

            Map<String, Object> requestBody = buildFraudRequest(payment);
            
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    url,
                    requestBody,
                    Map.class);

            return processFraudResponse(payment, response);
        })
        .transformDeferred(mono -> CircuitBreakerOperator.of(circuitBreaker, mono))
        .doOnSuccess(result -> log.info("Evaluacion de fraude exitosa para pago {}", 
                payment.getPaymentId()))
        .doOnError(error -> log.error("Error en evaluacion de fraude para pago {}: {}", 
                payment.getPaymentId(), error.getMessage()));
    }

    private String buildFraudEngineUrl(Payment payment) {
        return String.format("http://fraud-engine/api/v1/evaluate?paymentId=%s", 
                payment.getPaymentId());
    }

    private Map<String, Object> buildFraudRequest(Payment payment) {
        return Map.of(
                "paymentId", payment.getPaymentId().toString(),
                "referenceNumber", payment.getReferenceNumber(),
                "customerId", payment.getCustomerId(),
                "merchantId", payment.getMerchantId(),
                "amount", payment.getAmount().toString(),
                "currency", payment.getCurrency(),
                "paymentMethod", payment.getPaymentMethod().name()
        );
    }

    private Payment processFraudResponse(Payment payment, ResponseEntity<Map> response) {
        if (response.getBody() == null) {
            throw new FraudEngineException("Respuesta vacia del motor de fraude");
        }

        Map<String, Object> body = response.getBody();
        Boolean approved = (Boolean) body.get("approved");
        String riskScore = (String) body.get("riskScore");
        String recommendation = (String) body.getOrDefault("recommendation", "NONE");

        if (approved == null || !approved) {
            log.warn("Pago {} rechazado por motor de fraude. Score: {}, Recomendacion: {}",
                    payment.getPaymentId(), riskScore, recommendation);
            return payment.withFraudEngineResponse(PaymentStatus.REJECTED,
                    String.format("RiskScore: %s, Recommendation: %s", riskScore, recommendation));
        }

        log.debug("Pago {} aprobado por motor de fraude. Score: {}", 
                payment.getPaymentId(), riskScore);
        return payment.withFraudEngineResponse(PaymentStatus.PENDING,
                String.format("RiskScore: %s, Recommendation: %s", riskScore, recommendation));
    }

    private static class CircuitBreakerOperator<T> implements reactor.core.publisher.CoreOperator<T, T> {
        private final CircuitBreaker circuitBreaker;
        private final reactor.core.publisher.Mono<T> source;

        private CircuitBreakerOperator(CircuitBreaker circuitBreaker, 
                reactor.core.publisher.Mono<T> source) {
            this.circuitBreaker = circuitBreaker;
            this.source = source;
        }

        public static <T> CircuitBreakerOperator<T> of(CircuitBreaker circuitBreaker,
                reactor.core.publisher.Mono<T> source) {
            return new CircuitBreakerOperator<>(circuitBreaker, source);
        }

        @Override
        public reactor.core.publisher.CoreSubscriber<? super T> subscribe(
                reactor.core.publisher.CoreSubscriber<? super T> actual) {
            return source.subscribeWith(actual);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/RiskBureauAdapter.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.exception.RiskBureauException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.RiskBureauPort;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class RiskBureauAdapter implements RiskBureauPort {

    private static final Logger log = LoggerFactory.getLogger(RiskBureauAdapter.class);

    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;

    public RiskBureauAdapter(
            RestTemplate restTemplate,
            @Qualifier("riskBureauCircuitBreaker") CircuitBreaker circuitBreaker) {
        this.restTemplate = restTemplate;
        this.circuitBreaker = circuitBreaker;
    }

    @Override
    public Mono<Payment> evaluateRisk(Payment payment) {
        log.info("Evaluando riesgo para pago {} en bureau", payment.getPaymentId());

        return Mono.fromCallable(() -> {
            String url = buildRiskBureauUrl(payment);
            log.debug("Llamando a bureau de riesgos: {}", url);

            Map<String, Object> requestBody = buildRiskRequest(payment);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    url,
                    requestBody,
                    Map.class);

            return processRiskResponse(payment, response);
        })
        .transformDeferred(mono -> RiskBureauCircuitBreakerOperator.of(circuitBreaker, mono))
        .doOnSuccess(result -> log.info("Evaluacion de riesgo exitosa para pago {}", 
                payment.getPaymentId()))
        .doOnError(error -> log.error("Error en evaluacion de riesgo para pago {}: {}", 
                payment.getPaymentId(), error.getMessage()));
    }

    private String buildRiskBureauUrl(Payment payment) {
        return String.format("http://risk-bureau/api/v2/assess?paymentId=%s", 
                payment.getPaymentId());
    }

    private Map<String, Object> buildRiskRequest(Payment payment) {
        Map<String, Object> request = new HashMap<>();
        request.put("paymentId", payment.getPaymentId().toString());
        request.put("customerId", payment.getCustomerId());
        request.put("merchantId", payment.getMerchantId());
        request.put("amount", payment.getAmount().toString());
        request.put("currency", payment.getCurrency());
        request.put("paymentMethod", payment.getPaymentMethod().name());
        request.put("fraudEngineResponse", payment.getFraudEngineResponse());
        return request;
    }

    private Payment processRiskResponse(Payment payment, ResponseEntity<Map> response) {
        if (response.getBody() == null) {
            throw new RiskBureauException("Respuesta vacia del bureau de riesgos");
        }

        Map<String, Object> body = response.getBody();
        String riskCategory = (String) body.get("riskCategory");
        Integer riskLevel = (Integer) body.get("riskLevel");
        String creditScore = (String) body.get("creditScore");

        if (riskLevel == null || riskLevel > 70) {
            log.warn("Pago {} rechazado por bureau de riesgos. Categoria: {}, Nivel: {}",
                    payment.getPaymentId(), riskCategory, riskLevel);
            return payment.withRiskBureauResponse(PaymentStatus.REJECTED,
                    String.format("Category: %s, Level: %d, CreditScore: %s", 
                            riskCategory, riskLevel, creditScore));
        }

        log.debug("Pago {} aprobado por bureau de riesgos. Categoria: {}, Nivel: {}", 
                payment.getPaymentId(), riskCategory, riskLevel);
        return payment.withRiskBureauResponse(PaymentStatus.PENDING,
                String.format("Category: %s, Level: %d, CreditScore: %s", 
                        riskCategory, riskLevel, creditScore));
    }

    private static class RiskBureauCircuitBreakerOperator<T> 
            implements reactor.core.publisher.CoreOperator<T, T> {
        private final CircuitBreaker circuitBreaker;
        private final reactor.core.publisher.Mono<T> source;

        private RiskBureauCircuitBreakerOperator(CircuitBreaker circuitBreaker, 
                reactor.core.publisher.Mono<T> source) {
            this.circuitBreaker = circuitBreaker;
            this.source = source;
        }

        public static <T> RiskBureauCircuitBreakerOperator<T> of(CircuitBreaker circuitBreaker,
                reactor.core.publisher.Mono<T> source) {
            return new RiskBureauCircuitBreakerOperator<>(circuitBreaker, source);
        }

        @Override
        public reactor.core.publisher.CoreSubscriber<? super T> subscribe(
                reactor.core.publisher.CoreSubscriber<? super T> actual) {
            return source.subscribeWith(actual);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/PaymentGatewayAdapter.java ===
package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.exception.PaymentGatewayException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.port.PaymentGatewayPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PaymentGatewayAdapter implements PaymentGatewayPort {

    private static final Logger log = LoggerFactory.getLogger(PaymentGatewayAdapter.class);
    private final RestTemplate restTemplate;
    private final String paymentGatewayUrl;
    private static final String CIRCUIT_BREAKER_NAME = "paymentGateway";

    public PaymentGatewayAdapter(RestTemplate restTemplate,
                                  @Value("${external.services.payment-gateway.url}") String paymentGatewayUrl) {
        this.restTemplate = restTemplate;
        this.paymentGatewayUrl = paymentGatewayUrl;
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "processPaymentFallback")
    public Mono<Payment> processPayment(Payment payment) {
        return Mono.fromCallable(() -> {
            log.info("Procesando pago en gateway para referencia: {}", payment.getReferenceNumber());
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("paymentId", payment.getPaymentId().toString());
            requestBody.put("referenceNumber", payment.getReferenceNumber());
            requestBody.put("customerId", payment.getCustomerId());
            requestBody.put("merchantId", payment.getMerchantId());
            requestBody.put("amount", payment.getAmount().toPlainString());
            requestBody.put("currency", payment.getCurrency());
            requestBody.put("paymentMethod", payment.getPaymentMethod().name());
            
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
            
            Map<String, Object> response = restTemplate.postForObject(
                    paymentGatewayUrl + "/api/v1/payments/process",
                    request,
                    Map.class
            );
            
            if (response == null) {
                throw new PaymentGatewayException("Respuesta nula del gateway de pagos");
            }
            
            String statusStr = (String) response.get("status");
            PaymentStatus gatewayStatus = mapGatewayStatus(statusStr);
            String gatewayResponse = buildGatewayResponse(response);
            
            log.info("Pago procesado exitosamente. Estado: {}", gatewayStatus);
            
            return payment.withPaymentGatewayResponse(gatewayStatus, gatewayResponse);
        });
    }

    private PaymentStatus mapGatewayStatus(String gatewayStatus) {
        if (gatewayStatus == null) {
            return PaymentStatus.FAILED;
        }
        
        return switch (gatewayStatus.toUpperCase()) {
            case "APPROVED", "AUTHORIZED", "SUCCESS" -> PaymentStatus.APPROVED;
            case "PENDING" -> PaymentStatus.PENDING;
            case "DECLINED", "REJECTED" -> PaymentStatus.DECLINED;
            case "CANCELLED" -> PaymentStatus.CANCELLED;
            default -> PaymentStatus.FAILED;
        };
    }

    private String buildGatewayResponse(Map<String, Object> response) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"transactionId\":\"").append(response.get("transactionId")).append("\",");
        sb.append("\"status\":\"").append(response.get("status")).append("\",");
        sb.append("\"timestamp\":\"").append(LocalDateTime.now()).append("\"");
        
        if (response.containsKey("authorizationCode")) {
            sb.append(",\"authorizationCode\":\"").append(response.get("authorizationCode")).append("\"");
        }
        if (response.containsKey("message")) {
            sb.append(",\"message\":\"").append(response.get("message")).append("\"");
        }
        
        sb.append("}");
        return sb.toString();
    }

    private Mono<Payment> processPaymentFallback(Payment payment, Throwable t) {
        log.error("Fallback activado para PaymentGateway. Referencia: {}, Error: {}", 
                payment.getReferenceNumber(), t.getMessage());
        
        String fallbackResponse = "{\"status\":\"FALLBACK\",\"error\":\"" + t.getMessage() + "\"}";
        return Mono.just(payment.withPaymentGatewayResponse(PaymentStatus.FAILED, fallbackResponse));
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapter/entity/PaymentEntity.java ===
package com.pragma.payments.infrastructure.adapter.entity;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentMethod;
import com.pragma.payments.domain.model.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments", indexes = {
        @Index(name = "idx_reference_number", columnList = "referenceNumber"),
        @Index(name = "idx_customer_id", columnList = "customerId"),
        @Index(name = "idx_merchant_id", columnList = "merchantId"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_created_at", columnList = "createdAt")
})
public class PaymentEntity {

    @Id
    @Column(name = "payment_id", nullable = false)
    private UUID paymentId;

    @Column(name = "reference_number", nullable = false, unique = true, length = 50)
    private String referenceNumber;

    @Column(name = "customer_id", nullable = false, length = 100)
    private String customerId;

    @Column(name = "merchant_id", nullable = false, length = 100)
    private String merchantId;

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 20)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PaymentStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "fraud_engine_response", columnDefinition = "TEXT")
    private String fraudEngineResponse;

    @Column(name = "risk_bureau_response", columnDefinition = "TEXT")
    private String riskBureauResponse;

    @Column(name = "payment_gateway_response", columnDefinition = "TEXT")
    private String paymentGatewayResponse;

    public PaymentEntity() {
    }

    public static PaymentEntity fromDomain(Payment payment) {
        PaymentEntity entity = new PaymentEntity();
        entity.paymentId = payment.getPaymentId();
        entity.referenceNumber = payment.getReferenceNumber();
        entity.customerId = payment.getCustomerId();
        entity.merchantId = payment.getMerchantId();
        entity.amount = payment.getAmount();
        entity.currency = payment.getCurrency();
        entity.paymentMethod = payment.getPaymentMethod();
        entity.status = payment.getStatus();
        entity.createdAt = payment.getCreatedAt();
        entity.updatedAt = payment.getUpdatedAt();
        entity.fraudEngineResponse = payment.getFraudEngineResponse();
        entity.riskBureauResponse = payment.getRiskBureauResponse();
        entity.paymentGatewayResponse = payment.getPaymentGatewayResponse();
        return entity;
    }

    public Payment toDomain() {
        return Payment.builder()
                .paymentId(this.paymentId)
                .referenceNumber(this.referenceNumber)
                .customerId(this.customerId)
                .merchantId(this.merchantId)
                .amount(this.amount)
                .currency(this.currency)
                .paymentMethod(this.paymentMethod)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .fraudEngineResponse(this.fraudEngineResponse)
                .riskBureauResponse(this.riskBureauResponse)
                .paymentGatewayResponse(this.paymentGatewayResponse)
                .build();
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFraudEngineResponse() {
        return fraudEngineResponse;
    }

    public void setFraudEngineResponse(String fraudEngineResponse) {
        this.fraudEngineResponse = fraudEngineResponse;
    }

    public String getRiskBureauResponse() {
        return riskBureauResponse;
    }

    public void setRiskBureauResponse(String riskBureauResponse) {
        this.riskBureauResponse = riskBureauResponse;
    }

    public String getPaymentGatewayResponse() {
        return paymentGatewayResponse;
    }

    public void setPaymentGatewayResponse(String paymentGatewayResponse) {
        this.paymentGatewayResponse = paymentGatewayResponse;
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/ErrorHandler.java ===
package com.pragma.payments.infrastructure.config;


import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.exception.FraudEngineException;
import com.pragma.payments.domain.exception.PaymentGatewayException;
import com.pragma.payments.domain.exception.RiskBureauException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);
    private static final String TIMESTAMP_FIELD = "timestamp";
    private static final String STATUS_FIELD = "status";
    private static final String ERROR_FIELD = "error";
    private static final String MESSAGE_FIELD = "message";
    private static final String DETAILS_FIELD = "details";
    private static final String PATH_FIELD = "path";

    @ExceptionHandler(FraudEngineException.class)
    public ResponseEntity<Map<String, Object>> handleFraudEngineException(
            FraudEngineException ex, WebRequest request) {
        log.error("Error del motor de fraude: {}", ex.getMessage(), ex);
        
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Fraud Engine Error",
                ex.getMessage(),
                request
        );
        
        body.put("service", "fraud-engine");
        body.put("originalStatus", ex.getStatus());
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @ExceptionHandler(RiskBureauException.class)
    public ResponseEntity<Map<String, Object>> handleRiskBureauException(
            RiskBureauException ex, WebRequest request) {
        log.error("Error del bureau de riesgos: {}", ex.getMessage(), ex);
        
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Risk Bureau Error",
                ex.getMessage(),
                request
        );
        
        body.put("service", "risk-bureau");
        body.put("originalStatus", ex.getStatus());
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @ExceptionHandler(PaymentGatewayException.class)
    public ResponseEntity<Map<String, Object>> handlePaymentGatewayException(
            PaymentGatewayException ex, WebRequest request) {
        log.error("Error del gateway de pagos: {}", ex.getMessage(), ex);
        
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Payment Gateway Error",
                ex.getMessage(),
                request
        );
        
        body.put("service", "payment-gateway");
        body.put("originalStatus", ex.getStatus());
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {
        log.warn("Error de validación: {}", ex.getMessage());
        
        Map<String, String> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage() != null 
                                ? error.getDefaultMessage() 
                                : "Valor inválido",
                        (existing, replacement) -> existing
                ));
        
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Los datos proporcionados no son válidos",
                request
        );
        
        body.put(DETAILS_FIELD, validationErrors);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        log.warn("Argumento ilegal: {}", ex.getMessage());
        
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(
            Exception ex, WebRequest request) {
        log.error("Error inesperado del sistema: {}", ex.getMessage(), ex);
        
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Ha ocurrido un error inesperado. Por favor, contacte al administrador.",
                request
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private Map<String, Object> buildErrorResponse(int status, String error, 
                                                    String message, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP_FIELD, LocalDateTime.now().toString());
        body.put(STATUS_FIELD, status);
        body.put(ERROR_FIELD, error);
        body.put(MESSAGE_FIELD, message);
        body.put(PATH_FIELD, extractPath(request));
        return body;
    }

    private String extractPath(WebRequest request) {
        String description = request.getDescription(false);
        if (description.startsWith("uri=")) {
            return description.substring(4);
        }
        return description;
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/LoggingConfig.java ===
package com.pragma.payments.infrastructure.config;

import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingConfiguration;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class LoggingConfig implements WebMvcConfigurer {

    private static final String LOG_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n";
    private static final String PAYMENT_LOGGER = "com.pragma.payments";
    private static final String CONTROLLER_LOGGER = "com.pragma.payments.infrastructure.controller";
    private static final String SERVICE_LOGGER = "com.pragma.payments.application";
    private static final String ADAPTER_LOGGER = "com.pragma.payments.infrastructure.adapter";

    @Bean
    public LoggingConfiguration loggingConfiguration(Environment environment) {
        LoggingConfiguration config = new LoggingConfiguration();
        
        Map<String, LogLevel> logLevels = new HashMap<>();
        logLevels.put(PAYMENT_LOGGER, LogLevel.INFO);
        logLevels.put(CONTROLLER_LOGGER, LogLevel.DEBUG);
        logLevels.put(SERVICE_LOGGER, LogLevel.DEBUG);
        logLevels.put(ADAPTER_LOGGER, LogLevel.DEBUG);
        
        logLevels.put("org.springframework.web", LogLevel.WARN);
        logLevels.put("org.springframework.boot", LogLevel.INFO);
        logLevels.put("org.hibernate", LogLevel.WARN);
        logLevels.put("org.postgresql", LogLevel.WARN);
        logLevels.put("io.github.resilience4j", LogLevel.INFO);
        
        return config;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestLoggingInterceptor());
    }

    @Bean
    public RequestLoggingInterceptor requestLoggingInterceptor() {
        return new RequestLoggingInterceptor();
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/RequestLoggingInterceptor.java ===
package com.pragma.payments.infrastructure.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);
    private static final String START_TIME_ATTRIBUTE = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME_ATTRIBUTE, startTime);
        
        logger.info("Incoming request: {} {} from {}", 
                request.getMethod(), 
                request.getRequestURI(),
                request.getRemoteAddr());
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
                          Object handler, ModelAndView modelAndView) {
        Long startTime = (Long) request.getAttribute(START_TIME_ATTRIBUTE);
        if (startTime != null) {
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Request processed: {} {} - Status: {} - Duration: {}ms",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    duration);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                               Object handler, Exception ex) {
        if (ex != null) {
            logger.error("Request failed: {} {} - Exception: {}",
                    request.getMethod(),
                    request.getRequestURI(),
                    ex.getMessage(), ex);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/controller/PaymentController.java ===
package com.pragma.payments.infrastructure.controller;

import com.pragma.payments.application.PaymentService;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.infrastructure.controller.dto.CreatePaymentRequest;
import com.pragma.payments.infrastructure.controller.dto.PaymentResponse;
import com.pragma.payments.infrastructure.controller.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "Payments", description = "API para gestión de pagos")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo pago", description = "Crea un nuevo pago y lo procesa a través del motor de fraude, buró de riesgos y gateway de pagos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pago creado exitosamente",
                     content = @Content(schema = @Schema(implementation = PaymentResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<PaymentResponse> createPayment(
            @Parameter(description = "Datos del pago a crear") @RequestBody CreatePaymentRequest request) {
        logger.info("Received payment creation request for customer: {} and merchant: {}",
                request.getCustomerId(), request.getMerchantId());
        
        try {
            Payment payment = paymentService.createPayment(
                    request.getReferenceNumber(),
                    request.getCustomerId(),
                    request.getMerchantId(),
                    request.getAmount(),
                    request.getCurrency(),
                    request.getPaymentMethod()
            );
            
            logger.info("Payment created successfully with ID: {}", payment.getPaymentId());
            return ResponseEntity.status(HttpStatus.CREATED).body(PaymentResponse.fromDomain(payment));
            
        } catch (Exception e) {
            logger.error("Error creating payment: {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/{paymentId}")
    @Operation(summary = "Obtener un pago por ID", description = "Recupera los detalles de un pago específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado",
                     content = @Content(schema = @Schema(implementation = PaymentResponse.class))),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<PaymentResponse> getPayment(
            @Parameter(description = "ID del pago") @PathVariable UUID paymentId) {
        logger.debug("Fetching payment with ID: {}", paymentId);
        
        Payment payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(PaymentResponse.fromDomain(payment));
    }

    @GetMapping
    @Operation(summary = "Listar todos los pagos", description = "Obtiene una lista de todos los pagos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos",
                     content = @Content(schema = @Schema(implementation = PaymentResponse.class)))
    })
    public ResponseEntity<List<PaymentResponse>> getAllPayments(
            @Parameter(description = "Número de página") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página") @RequestParam(defaultValue = "20") int size) {
        logger.debug("Fetching payments - page: {}, size: {}", page, size);
        
        List<Payment> payments = paymentService.getAllPayments(page, size);
        List<PaymentResponse> responses = payments.stream()
                .map(PaymentResponse::fromDomain)
                .toList();
        
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Listar pagos por cliente", description = "Obtiene todos los pagos de un cliente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos del cliente",
                     content = @Content(schema = @Schema(implementation = PaymentResponse.class)))
    })
    public ResponseEntity<List<PaymentResponse>> getPaymentsByCustomer(
            @Parameter(description = "ID del cliente") @PathVariable String customerId) {
        logger.debug("Fetching payments for customer: {}", customerId);
        
        List<Payment> payments = paymentService.getPaymentsByCustomerId(customerId);
        List<PaymentResponse> responses = payments.stream()
                .map(PaymentResponse::fromDomain)
                .toList();
        
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/merchant/{merchantId}")
    @Operation(summary = "Listar pagos por comercio", description = "Obtiene todos los pagos de un comercio específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos del comercio",
                     content = @Content(schema = @Schema(implementation = PaymentResponse.class)))
    })
    public ResponseEntity<List<PaymentResponse>> getPaymentsByMerchant(
            @Parameter(description = "ID del comercio") @PathVariable String merchantId) {
        logger.debug("Fetching payments for merchant: {}", merchantId);
        
        List<Payment> payments = paymentService.getPaymentsByMerchantId(merchantId);
        List<PaymentResponse> responses = payments.stream()
                .map(PaymentResponse::fromDomain)
                .toList();
        
        return ResponseEntity.ok(responses);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/controller/dto/CreatePaymentRequest.java ===
package com.pragma.payments.infrastructure.controller.dto;


import com.pragma.payments.domain.model.PaymentMethod;
import com.pragma.payments.domain.model.Payment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreatePaymentRequest(
        @NotBlank(message = "Reference number is required")
        @Size(min = 1, max = 100, message = "Reference number must be between 1 and 100 characters")
        String referenceNumber,
        
        @NotBlank(message = "Customer ID is required")
        @Size(min = 1, max = 50, message = "Customer ID must be between 1 and 50 characters")
        String customerId,
        
        @NotBlank(message = "Merchant ID is required")
        @Size(min = 1, max = 50, message = "Merchant ID must be between 1 and 50 characters")
        String merchantId,
        
        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,
        
        @NotBlank(message = "Currency is required")
        @Size(min = 3, max = 3, message = "Currency must be a 3-letter code")
        String currency,
        
        @NotBlank(message = "Payment method is required")
        String paymentMethod
) {
    public Payment.PaymentMethod getPaymentMethodAsEnum() {
        try {
            return Payment.PaymentMethod.valueOf(paymentMethod.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Payment.PaymentMethod.UNKNOWN;
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/controller/dto/PaymentResponse.java ===
package com.pragma.payments.infrastructure.controller.dto;

import com.pragma.payments.domain.model.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponse(
        UUID paymentId,
        String referenceNumber,
        String customerId,
        String merchantId,
        BigDecimal amount,
        String currency,
        String paymentMethod,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String fraudEngineResponse,
        String riskBureauResponse,
        String paymentGatewayResponse
) {
    public static PaymentResponse fromDomain(Payment payment) {
        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getReferenceNumber(),
                payment.getCustomerId(),
                payment.getMerchantId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getPaymentMethod().name(),
                payment.getStatus().name(),
                payment.getCreatedAt(),
                payment.getUpdatedAt(),
                payment.getFraudEngineResponse(),
                payment.getRiskBureauResponse(),
                payment.getPaymentGatewayResponse()
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/controller/dto/ErrorResponse.java ===
package com.pragma.payments.infrastructure.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp,
        Map<String, List<String>> validationErrors
) {
    public static ErrorResponse of(int status, String error, String message, String path) {
        return new ErrorResponse(status, error, message, path, LocalDateTime.now(), null);
    }

    public static ErrorResponse withValidation(int status, String error, String message, 
                                               String path, Map<String, List<String>> validationErrors) {
        return new ErrorResponse(status, error, message, path, LocalDateTime.now(), validationErrors);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/OpenApiConfig.java ===
package com.pragma.payments.infrastructure.config;


import com.pragma.payments.domain.model.Payment;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name:payments}")
    private String applicationName;

    @Value("${app.version:1.0.0}")
    private String apiVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Payments API")
                        .version(apiVersion)
                        .description("""
                                Sistema de gestión de pagos con integración de servicios externos.
                                
                                ## Funcionalidades
                                - Creación y gestión de pagos
                                - Evaluación de fraude mediante motor externo
                                - Evaluación de riesgo mediante buró de riesgos
                                - Procesamiento de pagos a través de gateway
                                
                                ## Servicios Externos
                                - **Fraud Engine**: Evaluación de fraude en tiempo real
                                - **Risk Bureau**: Análisis de riesgo crediticio
                                - **Payment Gateway**: Procesamiento de transacciones
                                """)
                        .contact(new Contact()
                                .name("Pragma Team")
                                .email("team@pragma.co")
                                .url("https://www.pragma.co"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT token de autenticación")));
    }

    @Bean
    public io.swagger.v3.oas.models.servers.Server devServer() {
        io.swagger.v3.oas.models.servers.Server server = new io.swagger.v3.oas.models.servers.Server();
        server.setUrl("{scheme}://{host}:{port}/{basePath}");
        server.setDescription("Servidor de desarrollo");
        server.addVariablesItem(new io.swagger.v3.oas.models.servers.ServerVariable()
                .addEnumItem("scheme")
                .addEnumItem("http")
                .addEnumItem("https"));
        return server;
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/application/PaymentServiceTest.java ===
package com.pragma.payments.application;

import com.pragma.payments.domain.exception.FraudEngineException;
import com.pragma.payments.domain.exception.PaymentGatewayException;
import com.pragma.payments.domain.exception.RiskBureauException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentMethod;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.port.FraudEnginePort;
import com.pragma.payments.domain.port.PaymentGatewayPort;
import com.pragma.payments.domain.port.PaymentRepositoryPort;
import com.pragma.payments.domain.port.RiskBureauPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentService - Pruebas Unitarias")
class PaymentServiceTest {

    @Mock
    private FraudEnginePort fraudEnginePort;

    @Mock
    private RiskBureauPort riskBureauPort;

    @Mock
    private PaymentGatewayPort paymentGatewayPort;

    @Mock
    private PaymentRepositoryPort paymentRepositoryPort;

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService(
            fraudEnginePort,
            riskBureauPort,
            paymentGatewayPort,
            paymentRepositoryPort
        );
    }

    private Payment createTestPayment() {
        return Payment.create(
            "REF-001",
            "customer-123",
            "merchant-456",
            new BigDecimal("1000.00"),
            "USD",
            PaymentMethod.CREDIT_CARD
        );
    }

    @Nested
    @DisplayName("Escenario: Procesamiento exitoso de pago")
    class ProcessPaymentSuccess {

        @Test
        @DisplayName("Debe procesar pago exitosamente cuando todos los servicios externos responden correctamente")
        void shouldProcessPaymentSuccessfully() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "fraud-check-passed"
            );
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(
                PaymentStatus.APPROVED, "risk-low"
            );
            Payment finalPayment = paymentWithRisk.withPaymentGatewayResponse(
                PaymentStatus.COMPLETED, "payment-authorized"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> {
                    assertThat(result.getStatus()).isEqualTo(PaymentStatus.COMPLETED);
                    assertThat(result.getFraudEngineResponse()).isEqualTo("fraud-check-passed");
                    assertThat(result.getRiskBureauResponse()).isEqualTo("risk-low");
                    assertThat(result.getPaymentGatewayResponse()).isEqualTo("payment-authorized");
                })
                .verifyComplete();

            verify(fraudEnginePort).evaluateForFraud(payment);
            verify(riskBureauPort).evaluateRisk(paymentWithFraud);
            verify(paymentGatewayPort).process(paymentWithRisk);
            verify(paymentRepositoryPort).save(finalPayment);
        }

        @Test
        @DisplayName("Debe rechazar pago cuando el motor de fraude lo marca como fraudulento")
        void shouldRejectPaymentWhenFraudDetected() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.REJECTED, "fraud-detected-high-risk"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> {
                    assertThat(result.getStatus()).isEqualTo(PaymentStatus.REJECTED);
                    assertThat(result.getFraudEngineResponse()).contains("fraud-detected");
                })
                .verifyComplete();

            verify(fraudEnginePort).evaluateForFraud(payment);
            verify(riskBureauPort, never()).evaluateRisk(any(Payment.class));
            verify(paymentGatewayPort, never()).process(any(Payment.class));
        }

        @Test
        @DisplayName("Debe rechazar pago cuando el buró de riesgos indica alto riesgo")
        void shouldRejectPaymentWhenHighRisk() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "fraud-check-passed"
            );
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(
                PaymentStatus.REJECTED, "risk-score-85-high-risk"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> {
                    assertThat(result.getStatus()).isEqualTo(PaymentStatus.REJECTED);
                    assertThat(result.getRiskBureauResponse()).contains("high-risk");
                })
                .verifyComplete();

            verify(fraudEnginePort).evaluateForFraud(payment);
            verify(riskBureauPort).evaluateRisk(paymentWithFraud);
            verify(paymentGatewayPort, never()).process(any(Payment.class));
        }

        @Test
        @DisplayName("Debe fallar cuando el gateway de pagos rechaza la transacción")
        void shouldFailWhenGatewayRejects() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "fraud-check-passed"
            );
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(
                PaymentStatus.APPROVED, "risk-low"
            );
            Payment paymentWithGateway = paymentWithRisk.withPaymentGatewayResponse(
                PaymentStatus.FAILED, "insufficient-funds"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithGateway));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithGateway));

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> {
                    assertThat(result.getStatus()).isEqualTo(PaymentStatus.FAILED);
                    assertThat(result.getPaymentGatewayResponse()).contains("insufficient-funds");
                })
                .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenario: Manejo de errores de servicios externos")
    class ExternalServiceErrorHandling {

        @Test
        @DisplayName("Debe manejar excepción del motor de fraude y propagar error")
        void shouldHandleFraudEngineException() {
            Payment payment = createTestPayment();

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.error(new FraudEngineException("Servicio de fraude no disponible")));

            StepVerifier.create(paymentService.processPayment(payment))
                .expectError(FraudEngineException.class)
                .verify();

            verify(fraudEnginePort).evaluateForFraud(payment);
            verify(riskBureauPort, never()).evaluateRisk(any(Payment.class));
            verify(paymentGatewayPort, never()).process(any(Payment.class));
            verify(paymentRepositoryPort, never()).save(any(Payment.class));
        }

        @Test
        @DisplayName("Debe manejar excepción del buró de riesgos y propagar error")
        void shouldHandleRiskBureauException() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "fraud-check-passed"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.error(new RiskBureauException("Conexión con buró de riesgos fallida")));

            StepVerifier.create(paymentService.processPayment(payment))
                .expectError(RiskBureauException.class)
                .verify();

            verify(fraudEnginePort).evaluateForFraud(payment);
            verify(riskBureauPort).evaluateRisk(paymentWithFraud);
            verify(paymentGatewayPort, never()).process(any(Payment.class));
            verify(paymentRepositoryPort, never()).save(any(Payment.class));
        }

        @Test
        @DisplayName("Debe manejar excepción del gateway de pagos y propagar error")
        void shouldHandlePaymentGatewayException() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "fraud-check-passed"
            );
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(
                PaymentStatus.APPROVED, "risk-low"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.error(new PaymentGatewayException("Gateway de pagos fuera de servicio")));

            StepVerifier.create(paymentService.processPayment(payment))
                .expectError(PaymentGatewayException.class)
                .verify();

            verify(fraudEnginePort).evaluateForFraud(payment);
            verify(riskBureauPort).evaluateRisk(paymentWithFraud);
            verify(paymentGatewayPort).process(paymentWithRisk);
            verify(paymentRepositoryPort, never()).save(any(Payment.class));
        }

        @Test
        @DisplayName("Debe manejar error al persistir pago en repositorio")
        void shouldHandleRepositorySaveError() {
            Payment payment = createTestPayment();
            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "fraud-check-passed"
            );
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(
                PaymentStatus.APPROVED, "risk-low"
            );
            Payment finalPayment = paymentWithRisk.withPaymentGatewayResponse(
                PaymentStatus.COMPLETED, "payment-authorized"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Error de conexión a base de datos")));

            StepVerifier.create(paymentService.processPayment(payment))
                .expectError(RuntimeException.class)
                .verify();
        }
    }

    @Nested
    @DisplayName("Escenario: Validación de parámetros de entrada")
    class InputValidation {

        @Test
        @DisplayName("Debe rechazar pago nulo con excepción")
        void shouldRejectNullPayment() {
            StepVerifier.create(paymentService.processPayment(null))
                .expectError(NullPointerException.class)
                .verify();
        }
    }

    @Nested
    @DisplayName("Escenario: Verificación de flujo de datos")
    class DataFlowVerification {

        @Test
        @DisplayName("Debe pasar el paymentId generado a través de todo el pipeline")
        void shouldPreservePaymentIdThroughPipeline() {
            Payment payment = createTestPayment();
            UUID paymentId = payment.getPaymentId();

            Payment paymentWithFraud = payment.withFraudEngineResponse(
                PaymentStatus.APPROVED, "ok"
            );
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(
                PaymentStatus.APPROVED, "ok"
            );
            Payment finalPayment = paymentWithRisk.withPaymentGatewayResponse(
                PaymentStatus.COMPLETED, "ok"
            );

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));

            ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> assertThat(result.getPaymentId()).isEqualTo(paymentId))
                .verifyComplete();

            verify(paymentRepositoryPort).save(paymentCaptor.capture());
            assertThat(paymentCaptor.getValue().getPaymentId()).isEqualTo(paymentId);
        }

        @Test
        @DisplayName("Debe mantener el monto y moneda originales durante todo el procesamiento")
        void shouldPreserveAmountAndCurrency() {
            BigDecimal originalAmount = new BigDecimal("5000.00");
            String originalCurrency = "EUR";

            Payment payment = Payment.create(
                "REF-TEST",
                "customer-789",
                "merchant-101",
                originalAmount,
                originalCurrency,
                PaymentMethod.DEBIT_CARD
            );

            Payment paymentWithFraud = payment.withFraudEngineResponse(PaymentStatus.APPROVED, "ok");
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(PaymentStatus.APPROVED, "ok");
            Payment finalPayment = paymentWithRisk.withPaymentGatewayResponse(PaymentStatus.COMPLETED, "ok");

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> {
                    assertThat(result.getAmount()).isEqualByComparingTo(originalAmount);
                    assertThat(result.getCurrency()).isEqualTo(originalCurrency);
                })
                .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenario: Trazabilidad del pago")
    class PaymentTraceability {

        @Test
        @DisplayName("Debe registrar timestamps de creación y actualización")
        void shouldRecordTimestamps() {
            Payment payment = createTestPayment();
            LocalDateTime beforeCreation = LocalDateTime.now();

            Payment paymentWithFraud = payment.withFraudEngineResponse(PaymentStatus.APPROVED, "ok");
            Payment paymentWithRisk = paymentWithFraud.withRiskBureauResponse(PaymentStatus.APPROVED, "ok");
            Payment finalPayment = paymentWithRisk.withPaymentGatewayResponse(PaymentStatus.COMPLETED, "ok");

            when(fraudEnginePort.evaluateForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithFraud));
            when(riskBureauPort.evaluateRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(paymentGatewayPort.process(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));
            when(paymentRepositoryPort.save(any(Payment.class)))
                .thenReturn(Mono.just(finalPayment));

            StepVerifier.create(paymentService.processPayment(payment))
                .assertNext(result -> {
                    assertThat(result.getCreatedAt()).isAfterOrEqualTo(beforeCreation);
                    assertThat(result.getUpdatedAt()).isAfterOrEqualTo(result.getCreatedAt());
                })
                .verifyComplete();
        }
    }
}
```
