package logica;

import modelo.Entidad;
import modelo.Jugador;
import modelo.Enemigo;
import estructuras.Cola;
import estructuras.ListaEnlazada;

public class GestorTurnos {
    private Cola<Entidad> colaTurnos;
    private ListaEnlazada<Enemigo> enemigos;
    private Jugador jugador;
    private int turnosRestantes;

    public GestorTurnos(int turnosIniciales, Jugador jugador) {
        this.colaTurnos = new Cola<>();
        this.enemigos = new ListaEnlazada<>();
        this.jugador = jugador;
        this.turnosRestantes = turnosIniciales;
        inicializarTurnos();
    }

    public void agregarEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
        // If we add an enemy mid-game, we might want to add it to the current queue too
        colaTurnos.enqueue(enemigo);
    }

    public void inicializarTurnos() {
        decrementarTurnos();
        colaTurnos = new Cola<>();
        // Orden: 1. Jugador, 2. Enemigos
        if (jugador != null) {
            colaTurnos.enqueue(jugador);
        }
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo e = enemigos.get(i);
            if (e.isVivo()) {
                colaTurnos.enqueue(e);
            }
        }
    }

    public Entidad siguienteTurno() {
        if (colaTurnos.isEmpty()) {
            // End of round, new round starts
            inicializarTurnos();
        }
        
        Entidad actual = colaTurnos.dequeue();
        
        // If the entity is dead, skip it and get the next one
        if (!actual.isVivo()) {
            return siguienteTurno();
        }
        
        return actual;
    }

    private void decrementarTurnos() {
        this.turnosRestantes--;
    }

    public boolean isDerrotaPorTurnos() {
        return turnosRestantes <= 0;
    }

    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    public void finalizarTurno() {
        siguienteTurno();
    }

    public boolean esTurnoDelJugador() {
        if (colaTurnos.isEmpty()) {
            return false; // Should not happen if managed correctly
        }
        return colaTurnos.peek() instanceof Jugador;
    }
}
