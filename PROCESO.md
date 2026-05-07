# PROCESO.md - Seguimiento de Tareas

## Reglas de Proceso
- **Estados de Tarea**:
  - `pendiente`: Tarea no iniciada.
  - `en_proceso`: Tarea siendo ejecutada por el subagente.
  - `completado`: Tarea aprobada por el subagente validador.
  - `error`: Tarea rechazada por el validador o con fallo crítico.
- **Flujo de Trabajo**:
  1. El agente principal marca la tarea como `en_proceso`.
  2. Se asigna un **Subagente Ejecutor** para implementar la funcionalidad.
  3. Se asigna un **Subagente Validador** para revisar que el resultado cumpla con el `PDR.md` y no introduzca regresiones.
  4. **Regla de Oro**: No se puede pasar a la siguiente tarea hasta que el validador marque la actual como `completado`.
  5. En caso de `error`, el ejecutor debe corregir basándose en el feedback del validador.
- **Actualización**: Este archivo se actualiza inmediatamente tras cada cambio de estado o nota de validación.

## Lista de Tareas

| ID | Descripción | Estado | Ejecutor ID | Validador ID | Notas | Última Actualización |
|----|-------------|--------|-------------|--------------|-------|----------------------|
| T1 | Diseñar bocetos de interfaz y distribución de elementos (Sec 18) | completado | Exec_T1 | Val_T1 | Aprobado por Val_T1 | 2026-05-06 |
| T2 | Implementar estructuras de datos propias: ListaEnlazada, Cola, Grafo, BFS (Sec 20) | completado | Exec_T2 | Val_T2 | Aprobado por Val_T2 | 2026-05-06 |
| T3 | Implementar modelo de dominio: Jugador, Enemigo, Objeto, Celda, Habitación (Sec 3) | completado | Exec_T3 | Val_T3 | Aprobado por Val_T3 | 2026-05-06 |
| T4 | Implementar sistema de turnos y cola de turnos (Sec 4) | completado | Exec_T4 | Val_T4 | Aprobado por Val_T4 | 2026-05-06 |
| T5 | Implementar sistema de movimiento y visualización de celdas alcanzables con BFS (Sec 5) | completado | Exec_T5 | Val_T5 | Aprobado por Val_T5 | 2026-05-06 |
| T6 | Implementar sistema de combate y fórmula de daño (Sec 6) | completado | Exec_T6 | Val_T6 | Aprobado por Val_T6 | 2026-05-06 |
| T7 | Implementar sistema de inventario con ListaEnlazada (Sec 7) | completado | Exec_T7 | Val_T7 | Aprobado por Val_T7 | 2026-05-06 |
| T8 | Implementar interacciones: recoger, usar, atacar, abrir puertas (Sec 8) | completado | Exec_T8 | Val_T8 | Aprobado por Val_T8 | 2026-05-06 |
| T9 | Implementar sistema de trampas (Sec 9) | completado | Exec_T9 | Val_T9 | Aprobado por Val_T9 | 2026-05-06 |
| T10| Implementar sistema de puertas y llaves (Sec 10) | completado | Exec_T10 | Val_T10 | Aprobado por Val_T10 | 2026-05-06 |
| T11| Implementar interfaz JavaFX: GridPane, paneles y botón "camino óptimo" (Sec 11) | completado | Exec_T11 | Val_T11 | Aprobado por Val_T11 | 2026-05-06 |
| T12| Implementar persistencia JSON: configuración inicial y estado de partida (Sec 12) | completado | Exec_T12 | Val_T12 | Aprobado por Val_T12 | 2026-05-06 |
| T13| Implementar log del sistema con ListaEnlazada (Sec 13) | completado | Exec_T13 | Val_T13 | Aprobado por Val_T13 | 2026-05-06 |
| T14| Verificar e implementar invariantes del sistema (Sec 14) | completado | Exec_T14 | Val_T14 | Aprobado por Val_T14 | 2026-05-06 |
| T15| Implementar excepciones personalizadas (Sec 15) | completado | Exec_T15 | Val_T15 | Aprobado por Val_T15 | 2026-05-06 |
| T16| Implementar y validar casos de uso (Sec 16) | completado | Exec_T16 | Val_T16 | Aprobado por Val_T16 | 2026-05-06 |
| T17| Ejecutar pruebas unitarias y de integración (Sec 17) | completado | Exec_T17 | Val_T17 | Aprobado por Val_T17 | 2026-05-06 |
| T18| Registrar prompts y análisis crítico de IA (Sec 19) | completado | Exec_T18 | Val_T18 | Aprobado por Val_T18 | 2026-05-06 |
| T19| Crear diagramas UML del sistema (Sec 23) | completado | Exec_T19 | Val_T19 | Aprobado por Val_T19 | 2026-05-06 |
| T20| Generar código base final integrado (Sec 23) | completado | Exec_T20 | Val_T20 | Aprobado por Val_T20 | 2026-05-06 |

## Histórico de Cambios
- **2026-05-06**: Creación de `PROCESO.md` y definición de tareas iniciales.
