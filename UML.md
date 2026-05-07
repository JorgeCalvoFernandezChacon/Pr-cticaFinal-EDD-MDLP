# UML del Sistema - Juego de Mazmorras

Este documento contiene las representaciones visuales de la arquitectura del sistema utilizando la sintaxis de Mermaid.js.

## 1. Diagrama de Clases

```mermaid
classDiagram
    class Entidad {
        +int vida
        +int ataque
        +int defensa
        +boolean muerto
        +setVida(int vida)
        +isMuerto() boolean
    }
    class Jugador {
        +int velocidad
        +int habitacionId
        +int fila
        +int columna
        +Inventario inventario
    }
    class Enemigo {
        +Posicion posicion
        +actuar()
    }
    class Objeto {
        +String nombre
        +String descripcion
    }
    class Consumible {
        +int recuperacionVida
    }
    class Equipable {
        +int bonoAtaque
        +int bonoDefensa
    }
    class Llave {
        +String puertaId
    }
    class Celda {
        +Entidad entidad
        +Objeto objeto
    }
    class Habitacion {
        +int filas
        +int columnas
        +Celda[][] matriz
        +getCelda(int f, int c) Celda
    }
    class Grafo {
        +int numVertices
        +ListaEnlazada[] adj
        +addEdge(int s, int d, boolean b)
    }
    class MovimientoService {
        +obtenerCeldasAlcanzables(Jugador j, Habitacion h)
        +moverJugador(Jugador j, Habitacion h, Posicion p)
    }
    class CombateService {
        +calcularDanio(Entidad a, Entidad d)
        +ejecutarAtaque(Entidad a, Entidad d)
    }
    class InteraccionService {
        +recogerObjeto(Jugador j, Celda c)
        +usarObjeto(Jugador j, Objeto o)
        +atacarEnemigo(Jugador j, Enemigo e)
        +abrirPuerta(Jugador j, Puerta p)
    }
    class GestorTurnos {
        +int turnosRestantes
        +Cola colaTurnos
        +finalizarTurno()
    }

    Entidad <|-- Jugador
    Entidad <|-- Enemigo
    Objeto <|-- Consumible
    Objeto <|-- Equipable
    Objeto <|-- Llave
    Habitacion "1" *-- "many" Celda
    Celda "1" o-- "0..1" Entidad
    Celda "1" o-- "0..1" Objeto
    Grafo "1" *-- "many" Habitacion
    
    MovimientoService ..> Jugador
    MovimientoService ..> Habitacion
    CombateService ..> Entidad
    InteraccionService ..> Jugador
    InteraccionService ..> Objeto
    GestorTurnos ..> Jugador
```

## 2. Diagrama de Secuencia (Flujo de Turno)

```mermaid
sequenceDiagram
    participant J as Jugador
    participant MS as MovimientoService
    participant H as Habitacion
    participant IS as InteraccionService
    participant CS as CombateService
    participant GT as GestorTurnos
    participant UI as MainApp

    UI->>MS: obtenerCeldasAlcanzables(J, H)
    MS-->>UI: Lista de celdas
    UI->>UI: Iluminar celdas
    
    J->>UI: Seleccionar destino y mover
    UI->>MS: moverJugador(J, H, destino)
    MS->>H: verificar trampa en celda
    alt Hay Trampa
        H-->>J: Aplicar daño
    end
    MS-->>UI: Posición actualizada
    
    J->>UI: Seleccionar Acción (Atacar/Recoger/Usar)
    alt Atacar
        UI->>IS: atacarEnemigo(J, E)
        IS->>CS: ejecutarAtaque(J, E)
        CS-->>J: Resultado daño/muerte
    else Recoger
        UI->>IS: recogerObjeto(J, Celda)
        IS->>J: Añadir al inventario
    end
    
    UI->>GT: finalizarTurno()
    GT->>GT: Decrementar turnosRestantes
    GT->>UI: Cambiar turno a Enemigos
    
    loop Enemigos actúan
        UI->>CS: ejecutarAtaque(E, J)
    end
    
    UI->>UI: updateUI()
```
