
package tateti;

import java.awt.Color;

/**
 * Encargado de controlar las jugadas realizadas en un tablero
 * para determinar el estado de la partida (victoria, empate, seguir jugando).
 */
public class Arbitro {
    private Tablero tableroControlado;
    private boolean controlarJuego;
    private boolean hayGanador;
    
    public Arbitro(Tablero unTablero){
        this.tableroControlado = unTablero;
        this.controlarJuego = false;
        this.hayGanador = false;
    }
    
    public boolean hayEmpate(){
        return this.tableroControlado.tablaLlena();
    }
    
    public void auditarJugada(int posFila, int posColumna, String ficha){
        boolean jugadaGanadora = false;
        if(!jugadaGanadora){
            jugadaGanadora = enDiagonalBarra(posFila, posColumna, ficha);
            if(jugadaGanadora){
                pintarDiagonalBarra(posFila, posColumna, ficha);
            }
        }
        if(!jugadaGanadora){
            jugadaGanadora = enDiagonalContraBarra(posFila, posColumna, ficha);
            if(jugadaGanadora){
                pintarDiagonalContraBarra(posFila, posColumna, ficha);
            }
        }
        if(!jugadaGanadora){
            jugadaGanadora = enHorizontal(posFila, posColumna, ficha);
            if(jugadaGanadora){
                pintarHorizontal(posFila, posColumna, ficha);
            }
        }
        if(!jugadaGanadora){
            jugadaGanadora = enVertical(posFila, posColumna, ficha);
            if(jugadaGanadora){
                pintarVertical(posFila, posColumna, ficha);
            }
        }
        this.hayGanador = jugadaGanadora;
    }
    
    public void habilitarArbitro(){
        this.controlarJuego = true;
        this.hayGanador = false;
    }
    
    public void desHabilitarArbitro(){
        this.controlarJuego = false;
    }
    
    public boolean seguirJugando(){
        this.controlarJuego = (!this.hayEmpate())&(!this.hayGanador);
        return this.controlarJuego;
    }
    
    public boolean hayGanador(){
        return this.hayGanador;
    }

    private boolean enDiagonalBarra(int posFila, int posColumna, String ficha) {
        int conexiones = 0; // cuando hay 2 conexiones entre fichas la jugada es ganadora; 
        int conexionesDII = enDII(posFila, posColumna, ficha);
        int conexionesDSD = enDSD(posFila, posColumna, ficha);
        conexiones = (conexionesDII+conexionesDSD);
        return (conexiones==2);
    }

    private boolean posicionValida(int i, int j) {
        boolean filaValida = false;
        boolean columnaValida = false;
        int cantidadDeFilas = this.tableroControlado.getNumeroDeFilas();
        int cantidadDeColumnas = this.tableroControlado.getNumeroDeColumnas();
        filaValida = (0<i)&(i<=cantidadDeFilas);
        columnaValida = (0<j)&(j<=cantidadDeColumnas);
        
        return (filaValida)&(columnaValida);
    }

    private int enDII(int posFila, int posColumna, String ficha) {
        //recorrido en sentido diagonal inferior izquierdo (DII).
        int i = posFila, j = posColumna, conexiones = 0;
        boolean terminarRecorrido = false;  
        do{
            i++;
            j--;
            boolean esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                terminarRecorrido = !this.tableroControlado.getCasillero(i, j).getContenido().equals(ficha);
                if(!terminarRecorrido){
                    conexiones++;
                }
            }
            else{
                break;
            }
        }while(!terminarRecorrido);
        
        return conexiones;
    }

    private int enDSD(int posFila, int posColumna, String ficha) {
        //recorrido en sentido diagonal superior derecho (DSD).
        int i = posFila, j = posColumna, conexiones = 0;
        boolean terminarRecorrido = false;  
        do{
            i--;
            j++;
            boolean esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                terminarRecorrido = !this.tableroControlado.getCasillero(i, j).getContenido().equals(ficha);
                if(!terminarRecorrido){
                    conexiones++;
                }
            }
            else{
                break;
            }
        }while(!terminarRecorrido);
        return conexiones;
    }

    private boolean enDiagonalContraBarra(int posFila, int posColumna, String ficha) {
        int conexiones = 0; // cuando hay 2 conexiones entre fichas la jugada es ganadora; 
        int conexionesDSI = enDSI(posFila, posColumna, ficha);
        int conexionesDID = enDID(posFila, posColumna, ficha);
        conexiones = (conexionesDSI+conexionesDID);
        return (conexiones==2);
    }

    private int enDSI(int posFila, int posColumna, String ficha) {
        //recorrido en sentido diagonal superior izquierdo (DSI).
        int i = posFila, j = posColumna, conexiones = 0;
        boolean terminarRecorrido = false;  
        do{
            i--;
            j--;
            boolean esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                terminarRecorrido = !this.tableroControlado.getCasillero(i, j).getContenido().equals(ficha);
                if(!terminarRecorrido){
                    conexiones++;
                }
            }
            else{
                break;
            }
        }while(!terminarRecorrido);
        
        return conexiones;
    }

    private int enDID(int posFila, int posColumna, String ficha) {
        //recorrido en sentido diagonal inferior derecho (DID).
        int i = posFila, j = posColumna, conexiones = 0;
        boolean terminarRecorrido = false;  
        do{
            i++;
            j++;
            boolean esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                terminarRecorrido = !this.tableroControlado.getCasillero(i, j).getContenido().equals(ficha);
                if(!terminarRecorrido){
                    conexiones++;
                }
            }
            else{
                break;
            }
        }while(!terminarRecorrido);
        return conexiones;
    }

    private boolean enHorizontal(int posFila, int posColumna, String ficha) {
        int conexiones = 0; // cuando hay 2 conexiones entre fichas la jugada es ganadora; 
        int conexionesHI = enHI(posFila, posColumna, ficha);
        int conexionesHD = enHD(posFila, posColumna, ficha);
        conexiones = (conexionesHI+conexionesHD);
        return (conexiones==2);
    }

    private int enHI(int posFila, int posColumna, String ficha) {
        //recorrido en sentido horizontal izquierdo (HI).
        int i = posFila, j = posColumna, conexiones = 0;
        boolean terminarRecorrido = false;  
        do{
            j--;
            boolean esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                terminarRecorrido = !this.tableroControlado.getCasillero(i, j).getContenido().equals(ficha);
                if(!terminarRecorrido){
                    conexiones++;
                }
            }
            else{
                break;
            }
        }while(!terminarRecorrido);
        
        return conexiones;
    }

    private int enHD(int posFila, int posColumna, String ficha) {
        //recorrido en sentido horizontal derecho (HD).
        int i = posFila, j = posColumna, conexiones = 0;
        boolean terminarRecorrido = false;  
        do{
            j++;
            boolean esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                terminarRecorrido = !this.tableroControlado.getCasillero(i, j).getContenido().equals(ficha);
                if(!terminarRecorrido){
                    conexiones++;
                }
            }
            else{
                break;
            }
        }while(!terminarRecorrido);
        
        return conexiones;
    }
    
    private boolean enVertical(int posFila, int posColumna, String ficha) {
        int conexiones = 0; // cuando hay 2 conexiones entre fichas la jugada es ganadora; 
        int conexionesVS = enVS(posFila, posColumna, ficha);
        int conexionesVI = enVI(posFila, posColumna, ficha);
        conexiones = (conexionesVS+conexionesVI);
        return (conexiones==2);
    }

    private int enVS(int posFila, int posColumna, String ficha) {
        //recorrido en sentido vertical superior (VS).
        int i = posFila, j = posColumna, conexiones = 0;
        boolean terminarRecorrido = false;  
        do{
            i--;
            boolean esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                terminarRecorrido = !this.tableroControlado.getCasillero(i, j).getContenido().equals(ficha);
                if(!terminarRecorrido){
                    conexiones++;
                }
            }
            else{
                break;
            }
        }while(!terminarRecorrido);
        
        return conexiones;
    }

    private int enVI(int posFila, int posColumna, String ficha) {
        //recorrido en sentido vertical inferior (VI).
        int i = posFila, j = posColumna, conexiones = 0;
        boolean terminarRecorrido = false;  
        do{
            i++;
            boolean esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                terminarRecorrido = !this.tableroControlado.getCasillero(i, j).getContenido().equals(ficha);
                if(!terminarRecorrido){
                    conexiones++;
                }
            }
            else{
                break;
            }
        }while(!terminarRecorrido);
        
        return conexiones;
    }

    private void pintarDiagonalBarra(int posFila, int posColumna, String ficha) {
        //recorrido en sentido diagonal inferior izquierdo (DII).
        int i = posFila, j = posColumna;
        this.tableroControlado.getCasillero(i, j).getContenedor().setBackground(Color.ORANGE);
        boolean esUnCasillero= false;  
        do{
            i++;
            j--;
            esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                this.tableroControlado.getCasillero(i, j).getContenedor().setBackground(Color.ORANGE);
            }
            else{
                break;
            }
        }while(esUnCasillero);
        
        //recorrido en sentido diagonal superior derecho (DSD).
        i = posFila;
        j = posColumna;
        
        do{
            i--;
            j++;
            esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                this.tableroControlado.getCasillero(i, j).getContenedor().setBackground(Color.ORANGE);
            }
            else{
                break;
            }
        }while(esUnCasillero);

    }

    private void pintarDiagonalContraBarra(int posFila, int posColumna, String ficha) {
        //recorrido en sentido diagonal superior izquierdo (DSI).
        int i = posFila, j = posColumna;
        this.tableroControlado.getCasillero(i, j).getContenedor().setBackground(Color.RED);
        boolean esUnCasillero = false;  
        do{
            i--;
            j--;
            esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                this.tableroControlado.getCasillero(i, j).getContenedor().setBackground(Color.RED);
            }
            else{
                break;
            }
        }while(esUnCasillero);
        
        //recorrido en sentido diagonal inferior derecho (DID).
        i = posFila;
        j = posColumna;

        do{
            i++;
            j++;
            esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                this.tableroControlado.getCasillero(i, j).getContenedor().setBackground(Color.RED);
            }
            else{
                break;
            }
        }while(esUnCasillero);
    }

    private void pintarHorizontal(int posFila, int posColumna, String ficha) {
        //recorrido en sentido horizontal izquierdo (HI).
        int i = posFila, j = posColumna;
        this.tableroControlado.getCasillero(i, j).getContenedor().setBackground(Color.BLUE);
        boolean esUnCasillero = false;  
        do{
            j--;
            esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                this.tableroControlado.getCasillero(i, j).getContenedor().setBackground(Color.BLUE);
            }
            else{
                break;
            }
        }while(esUnCasillero);
        
        //recorrido en sentido horizontal derecho (HD).
        i = posFila;
        j = posColumna;
        do{
            j++;
            esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                this.tableroControlado.getCasillero(i, j).getContenedor().setBackground(Color.BLUE);
            }
            else{
                break;
            }
        }while(esUnCasillero);
    }

    private void pintarVertical(int posFila, int posColumna, String ficha) {
        //recorrido en sentido vertical superior (VS).
        int i = posFila, j = posColumna;
        this.tableroControlado.getCasillero(i, j).getContenedor().setBackground(Color.yellow);
        boolean esUnCasillero = false;  
        do{
            i--;
           esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                this.tableroControlado.getCasillero(i, j).getContenedor().setBackground(Color.yellow);
            }
            else{
                break;
            }
        }while(esUnCasillero);
        
        //recorrido en sentido vertical inferior (VI).
        i = posFila;
        j = posColumna;
        do{
            i++;
            esUnCasillero = posicionValida(i, j);
            if(esUnCasillero){
                this.tableroControlado.getCasillero(i, j).getContenedor().setBackground(Color.yellow);
            }
            else{
                break;
            }
        }while(esUnCasillero);
    }

}
