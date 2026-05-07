# REGISTRO_IA.md - Documentación de Uso de Inteligencia Artificial

Este documento registra la interacción con la IA durante el desarrollo del Proyecto Juego de Mazmorras, detallando los prompts utilizados y un análisis crítico de los resultados.

## 1. Registro de Prompts

| Categoría | Prompt Principal | Resultado Obtenido |
|-----------|------------------|-------------------|
| **Arquitectura** | "Diseñar un juego de mazmorras por turnos en Java con JavaFX, donde el jugador navega por un grafo de habitaciones, interactúa con elementos y debe alcanzar una salida. Restricción: Prohibido usar estructuras de `java.util`, uso obligatorio de estructuras propias." | Generación del PDR.md inicial y definición de la arquitectura Modelo-Vista-Controlador. |
| **Estructuras de Datos** | "Implementar en Java una ListaEnlazada, Cola y Grafo desde cero, sin utilizar la librería `java.util`. Incluir algoritmos de búsqueda como BFS para encontrar caminos mínimos." | Implementación de las clases base de datos para el inventario, turnos y el mapa del juego. |
| **Lógica de Combate** | "Implementar un sistema de combate por turnos con la fórmula: `vida = vida - max(0, ataque * (random * 2) - defensa)`. El daño debe ser calculado y aplicado al enemigo o jugador según el turno." | Implementación de la clase de combate y gestión de daño. |
| **Interfaz JavaFX** | "Crear una interfaz de usuario en JavaFX que utilice un GridPane para representar la matriz de la habitación, paneles laterales para el estado del jugador y un panel inferior para el log de eventos." | Estructura visual del juego y vinculación de eventos del controlador con el modelo. |
| **Persistencia** | "Implementar la lectura y escritura de archivos JSON para cargar la configuración inicial del mapa (habitaciones, enemigos, objetos) y guardar el estado actual de la partida." | Clases de persistencia para la carga y guardado de datos. |

## 2. Análisis Crítico

### Decisiones Correctas y Correcciones
- **Aciertos**: La IA fue muy eficiente proponiendo la estructura del grafo para el mapa y la implementación del algoritmo BFS para el movimiento y el camino óptimo. La separación de capas (MVC) fue sugerida correctamente desde el inicio.
- **Correcciones Necesarias**:
  - **`java.util.Random`**: La IA sugirió inicialmente el uso de `java.util.Random` para el cálculo del daño. Dado que la restricción era no usar `java.util`, se debió ajustar la implementación para asegurar el cumplimiento estricto de las reglas del proyecto.
  - **Lógica de Muerte**: En las primeras versiones del sistema de combate, la IA omitió la verificación de la condición de derrota (`vida <= 0`) inmediatamente después de un ataque, permitiendo que el combate continuara a pesar de que una entidad hubiera muerto. Esto requirió una intervención manual para integrar la lógica de fin de partida.

### Aceleración del Desarrollo
- **Prototipado**: El uso de IA permitió generar el PDR y el PROCESO.md en una fracción del tiempo habitual.
- **Código Base**: La generación de estructuras de datos repetitivas (getters, setters, constructores) y el boilerplate de JavaFX aceleró significativamente el inicio del proyecto.
- **Algoritmos**: La implementación del BFS fue inmediata, evitando errores comunes de punteros en la estructura del grafo.

### Limitaciones Encontradas
- **Contexto de Restricciones**: A veces la IA "olvidaba" la restricción de no usar `java.util` en sugerencias posteriores, proponiendo `ArrayList` o `HashMap` para solucionar problemas rápidos.
- **API de JavaFX**: Se detectaron algunas alucinaciones menores sobre métodos de JavaFX que no existen en la versión utilizada, requiriendo revisión de la documentación oficial.
