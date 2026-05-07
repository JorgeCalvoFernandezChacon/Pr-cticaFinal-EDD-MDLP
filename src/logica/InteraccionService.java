package logica;

import modelo.*;
import excepciones.AccionInvalidaException;

public class InteraccionService {
    private CombateService combateService;

    public InteraccionService() {
        this.combateService = new CombateService();
    }

    public void recogerObjeto(Jugador jugador, Celda celda) throws AccionInvalidaException {
        if (jugador == null || celda == null) return;
        
        Objeto objeto = celda.getObjeto();
        if (objeto == null) {
            throw new AccionInvalidaException("La celda no contiene ningún objeto.");
        }
        if (jugador.getInventario().añadirObjeto(objeto)) {
            celda.setObjeto(null);
            SistemaLog.getInstance().registrarEvento("Jugador recogió el objeto: " + objeto.getNombre() + ".");
        }
    }

    public void usarObjeto(Jugador jugador, Objeto objeto) throws AccionInvalidaException {
        if (jugador == null || objeto == null) return;
        
        Inventario inv = jugador.getInventario();
        boolean contains = false;
        for (int i = 0; i < inv.getTamaño(); i++) {
            if (inv.obtenerObjeto(i) == objeto) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            throw new AccionInvalidaException("El inventario no contiene el objeto.");
        }
        
        if (!(objeto instanceof Consumible)) {
            throw new AccionInvalidaException("El objeto no es consumible.");
        }
        
        objeto.usar(jugador);
        SistemaLog.getInstance().registrarEvento("Jugador usó el objeto: " + objeto.getNombre() + ".");
        
        jugador.getInventario().eliminarObjeto(objeto);
    }

    public void atacarEnemigo(Jugador jugador, Enemigo enemigo) throws AccionInvalidaException {
        if (jugador == null || enemigo == null) {
            throw new AccionInvalidaException("No hay un enemigo en la posición objetivo.");
        }
        if (enemigo.isMuerto()) {
            throw new AccionInvalidaException("El enemigo ya está muerto.");
        }
        combateService.ejecutarAtaque(jugador, enemigo);
    }

    public void abrirPuerta(Jugador jugador, Puerta puerta, GestorTurnos gestorTurnos) throws AccionInvalidaException {
        if (jugador == null || puerta == null) return;
        
        if (puerta.isAbierta()) {
            throw new AccionInvalidaException("La puerta ya está abierta.");
        }
        
        String keyId = puerta.getRequiredKeyId();
        boolean canOpen = false;
        
        if (keyId == null) {
            canOpen = true;
        } else {
            Inventario inv = jugador.getInventario();
            for (int i = 0; i < inv.getTamaño(); i++) {
                Objeto obj = inv.obtenerObjeto(i);
                if (obj instanceof Llave) {
                    Llave llave = (Llave) obj;
                    if (llave.getPuertaId().equals(keyId)) {
                        canOpen = true;
                        break;
                    }
                }
            }
        }
        
        if (!canOpen) {
            if (keyId == null) {
                throw new AccionInvalidaException("La puerta no se puede abrir.");
            } else {
                throw new AccionInvalidaException("El jugador no posee la llave requerida.");
            }
        }
        
        puerta.abrir();
        jugador.setHabitacionId(puerta.getHabitacionDestinoId());
        jugador.setFila(0);
        jugador.setColumna(0);
        
        if (gestorTurnos != null) {
            gestorTurnos.finalizarTurno();
        }
    }
}
