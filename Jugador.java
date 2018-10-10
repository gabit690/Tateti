
package tateti;

/**
 * Uno de los participantes de juego que cuenta con un tipo de ficha 
 * para realizar sus jugadas.
 */
public class Jugador {
    private String ficha;
    
    public Jugador(){
        this.ficha = "";
    }
    
    public void setFicha(String unaFicha){
        this.ficha = unaFicha;
    }
    
    public String getFicha(){
        return this.ficha;
    }
}
