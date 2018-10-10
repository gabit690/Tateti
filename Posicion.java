
package tateti;

/**
 * Es la ubicacion de un objeto en un par (x,y) que corresponde a una 
 * coordenada en horizontal y vertical respectivamente.
 */
public class Posicion {
    private int horizontal;
    private int vertical;
    
    public Posicion(int coordenadaX, int coordenadaY){
        this.horizontal = coordenadaX;
        this.vertical = coordenadaY;
    }
    
    public void setCoordenadaHorizontal(int unaCoordenada){
        this.horizontal = unaCoordenada;
    }
    
    public void setCoordenadaVertical(int unaCoordenada){
        this.vertical = unaCoordenada;
    }
    
    public int getCoordenadaHorizontal(){
        return this.horizontal;
    }
    
    public int getCoordenadaVertical(){
        return this.vertical;
    }
}
