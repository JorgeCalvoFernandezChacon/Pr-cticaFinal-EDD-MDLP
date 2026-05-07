package modelo;

public class Equipable extends Objeto {
    private int bonoAtaque;
    private int bonoDefensa;

    public Equipable(String nombre, String descripcion, int bonoAtaque, int bonoDefensa) {
        super(nombre, descripcion);
        this.bonoAtaque = bonoAtaque;
        this.bonoDefensa = bonoDefensa;
    }

    public int getBonoAtaque() {
        return bonoAtaque;
    }

    public int getBonoDefensa() {
        return bonoDefensa;
    }

    @Override
    public void usar(Jugador jugador) {
        jugador.equipar(this);
    }
}
