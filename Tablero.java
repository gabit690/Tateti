
package tateti;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Grilla de casilleros que pueden contener una ficha de un jugador.
 * El tamanio del tablero es de 3x3 casilleros.
 */
public class Tablero{
    private Casillero[][] cuadricula;
    private int numFilas;
    private int numColumnas;
    
    public Tablero(){
        this.cuadricula = new Casillero[3][3];
        this.cuadricula[0][0] = new Casillero();
        
        this.cuadricula[0][1] = new Casillero();      
        
        this.cuadricula[0][2] = new Casillero();      
        
        this.cuadricula[1][0] = new Casillero();
        
        this.cuadricula[1][1] = new Casillero();
        
        this.cuadricula[1][2] = new Casillero();
        
        this.cuadricula[2][0] = new Casillero();
        
        this.cuadricula[2][1] = new Casillero();
        
        this.cuadricula[2][2] = new Casillero();
        
        this.numFilas = 3;
        this.numColumnas = 3;
    }
    
    public void establecerCoordenadasTableroEnFrame400x400(){
        this.cuadricula[0][0].posicionar(115, 130);
        this.cuadricula[0][1].posicionar(175, 130);
        this.cuadricula[0][2].posicionar(235, 130);
        this.cuadricula[1][0].posicionar(115, 190);
        this.cuadricula[1][1].posicionar(175, 190);
        this.cuadricula[1][2].posicionar(235, 190);
        this.cuadricula[2][0].posicionar(115, 250);
        this.cuadricula[2][1].posicionar(175, 250);
        this.cuadricula[2][2].posicionar(235, 250);
    }
    
    public Casillero getCasillero(int fila, int columna){
        return this.cuadricula[fila-1][columna-1];
    }
    
    public void reiniciarTablero(){
        this.cuadricula[0][0].vaciarCasillero();
        this.cuadricula[0][1].vaciarCasillero();
        this.cuadricula[0][2].vaciarCasillero();
        this.cuadricula[1][0].vaciarCasillero();
        this.cuadricula[1][1].vaciarCasillero();
        this.cuadricula[1][2].vaciarCasillero();
        this.cuadricula[2][0].vaciarCasillero();
        this.cuadricula[2][1].vaciarCasillero();
        this.cuadricula[2][2].vaciarCasillero();
    }
    
    public boolean casilleroLibre(int fila, int columna){
        return this.cuadricula[fila][columna].estaVacio();
    }
    
    public void habilitarTablero(){
        this.cuadricula[0][0].habilitarCasillero();
        this.cuadricula[0][1].habilitarCasillero();
        this.cuadricula[0][2].habilitarCasillero();
        this.cuadricula[1][0].habilitarCasillero();
        this.cuadricula[1][1].habilitarCasillero();
        this.cuadricula[1][2].habilitarCasillero();
        this.cuadricula[2][0].habilitarCasillero();
        this.cuadricula[2][1].habilitarCasillero();
        this.cuadricula[2][2].habilitarCasillero();
    }
    
    public void desHabilitarTablero(){
        this.cuadricula[0][0].desHabilitarCasillero();
        this.cuadricula[0][1].desHabilitarCasillero();
        this.cuadricula[0][2].desHabilitarCasillero();
        this.cuadricula[1][0].desHabilitarCasillero();
        this.cuadricula[1][1].desHabilitarCasillero();
        this.cuadricula[1][2].desHabilitarCasillero();
        this.cuadricula[2][0].desHabilitarCasillero();
        this.cuadricula[2][1].desHabilitarCasillero();
        this.cuadricula[2][2].desHabilitarCasillero();
    }
    
    public void setTableroListener(ActionListener unObjeto){
        this.cuadricula[0][0].getContenedor().addActionListener(unObjeto);
        this.cuadricula[0][1].getContenedor().addActionListener(unObjeto);
        this.cuadricula[0][2].getContenedor().addActionListener(unObjeto);
        this.cuadricula[1][0].getContenedor().addActionListener(unObjeto);
        this.cuadricula[1][1].getContenedor().addActionListener(unObjeto);
        this.cuadricula[1][2].getContenedor().addActionListener(unObjeto);
        this.cuadricula[2][0].getContenedor().addActionListener(unObjeto);
        this.cuadricula[2][1].getContenedor().addActionListener(unObjeto);
        this.cuadricula[2][2].getContenedor().addActionListener(unObjeto);
    }
    
    public Posicion getPosicion(JButton unContenedor){
        boolean casilleroEncontrado = false;
        int i =0;
        Posicion laPosicion = new Posicion(0,0);
        
        do{
          
          for(int j = 0; j<3; j++){
            casilleroEncontrado = (unContenedor==this.cuadricula[i][j].getContenedor());
            if(casilleroEncontrado){
                laPosicion.setCoordenadaHorizontal(i+1);
                laPosicion.setCoordenadaVertical(j+1);
                break;
            }
          }
          i++;  
        }while(!casilleroEncontrado);

        return laPosicion;
    }
    
    public boolean tablaLlena(){
        int i = 0;
        boolean estaLlena = false;
        do{
          for(int j = 0; j<3; j++){
            estaLlena = !this.cuadricula[i][j].getContenido().equals("");
            if(!estaLlena){
                break;
            } 
          }
         i++;  
        }while(estaLlena&(i<3));
        
        return estaLlena;
    }
    
    public int getNumeroDeFilas(){
        return  this.numFilas;
    }
    
    public int getNumeroDeColumnas(){
        return  this.numColumnas;
    }
}