package modelo;


public class Jugador extends Entidad {
    private int velocidad;
    private int habitacionId;
    private Habitacion habitacion;
    private Inventario inventario;
    private Equipable equipo;

    public Jugador(int vida, int ataque, int defensa, int velocidad, int fila, int columna) {
        super(vida, ataque, defensa, fila, columna);
        this.velocidad = velocidad;
        this.inventario = new Inventario(10);
        this.equipo = null;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(int habitacionId) {
        this.habitacionId = habitacionId;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public void setFila(int fila) {
        if (habitacion != null && (fila < 0 || fila >= habitacion.getFilas())) {
            throw new IllegalArgumentException("Fila fuera de los límites de la habitación.");
        }
        super.setFila(fila);
    }

    @Override
    public void setColumna(int columna) {
        if (habitacion != null && (columna < 0 || columna >= habitacion.getColumnas())) {
            throw new IllegalArgumentException("Columna fuera de los límites de la habitación.");
        }
        super.setColumna(columna);
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void recogerObjeto(Objeto obj) {
        inventario.añadirObjeto(obj);
    }

    public void equipar(Equipable item) {
        if (this.equipo != null) {
            inventario.añadirObjeto(this.equipo);
        }
        this.equipo = item;
        inventario.eliminarObjeto(item);
    }

    public void curar(int cantidad) {
        this.vida = Math.min(100, this.vida + cantidad);
    }

    public int getAtaqueTotal() {
        int bonus = (equipo != null) ? equipo.getBonoAtaque() : 0;
        return ataque + bonus;
    }

    public int getDefensaTotal() {
        int bonus = (equipo != null) ? equipo.getBonoDefensa() : 0;
        return defensa + bonus;
    }

    @Override
    public void actuar(Habitacion habitacion, Jugador jugador) {
        // The player's action is typically handled by the controller via user input
    }
}
