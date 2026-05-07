package logica;

import modelo.Entidad;

public class CombateService {

    public int calcularDanio(Entidad atacante, Entidad defensor) {
        double factor = Math.random() * 2;
        int danio = (int) Math.max(0, atacante.getAtaque() * factor - defensor.getDefensa());
        return danio;
    }

    public void ejecutarAtaque(Entidad atacante, Entidad defensor) {
        if (atacante.isMuerto() || defensor.isMuerto()) {
            return;
        }
        int danio = calcularDanio(atacante, defensor);
        defensor.setVida(defensor.getVida() - danio);
        SistemaLog.getInstance().registrarEvento(atacante.getClass().getSimpleName() + " atacó a " + defensor.getClass().getSimpleName() + " causando " + danio + " de daño.");
        if (defensor.getVida() <= 0) {
            defensor.setMuerto(true);
            SistemaLog.getInstance().registrarEvento(defensor.getClass().getSimpleName() + " ha sido derrotado.");
        }
    }
}
