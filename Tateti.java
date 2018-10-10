
package tateti;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JButton;

public class Tateti implements ActionListener{
    private JFrame interfaz;
    private Tablero elTablero;
    private Mensajero elMensajero;
    private Marcador elMarcador;
    private Jugador jugador1;
    private Jugador jugador2;
    private JButton botonReinicio;
    private JButton botonJugar;
    private int numeroDeJugada;
    private Arbitro elArbitro;
    
    public Tateti(){
        this.interfaz = new JFrame();
        this.interfaz.setLayout(null);
        this.interfaz.setTitle("Tateti by G@bit.");
        this.interfaz.setSize(400, 400);
        this.interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.interfaz.setLocationRelativeTo(null);
        this.interfaz.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.interfaz.setResizable(false);
        this.elMensajero = new Mensajero();
        this.elMarcador = new Marcador();
        this.jugador1 = new Jugador();
        this.jugador2 = new Jugador();
        this.elTablero = new Tablero();
        this.elTablero.setTableroListener(this);
        this.botonReinicio = new JButton("Reiniciar");
        this.botonJugar = new JButton("Jugar");
        this.elArbitro = new Arbitro(this.elTablero);
    }
    
    public void jugar(){
        this.interfaz.setVisible(true);
        this.prepararUnaPartida();
    }
    
    private void prepararUnaPartida(){
        this.prepararTablero();
        this.prepararMensajero();
        this.prepararMarcadores();
        this.prepararBotonReinicio();
        this.prepararBotonJugar();
        this.prepararJugadores("X", "O");
        this.elTablero.desHabilitarTablero();
        this.numeroDeJugada = 0;
    }
 
    private  void prepararTablero(){
        this.interfaz.add(this.elTablero.getCasillero(1, 1).getContenedor());
        this.interfaz.add(this.elTablero.getCasillero(1, 2).getContenedor());
        this.interfaz.add(this.elTablero.getCasillero(1, 3).getContenedor());
        this.interfaz.add(this.elTablero.getCasillero(2, 1).getContenedor());
        this.interfaz.add(this.elTablero.getCasillero(2, 2).getContenedor());
        this.interfaz.add(this.elTablero.getCasillero(2, 3).getContenedor());
        this.interfaz.add(this.elTablero.getCasillero(3, 1).getContenedor());
        this.interfaz.add(this.elTablero.getCasillero(3, 2).getContenedor());
        this.interfaz.add(this.elTablero.getCasillero(3, 3).getContenedor());
        this.elTablero.establecerCoordenadasTableroEnFrame400x400();
    }
    
    private  void prepararMensajero(){
        this.interfaz.add(this.elMensajero.getEstado());
        this.interfaz.add(this.elMensajero.getDisplay()); 
        this.elMensajero.avisoParaIniciarJuego();
    }
    
    private  void prepararMarcadores(){
        this.interfaz.add(this.elMarcador.getInfoJugadores());
        this.interfaz.add(this.elMarcador.getPuntajeJ1());
        this.interfaz.add(this.elMarcador.getPuntajeJ2());
        elMarcador.establecerCoordenadasMarcadoresEnFrame400x400();
    }
    
    private  void prepararBotonReinicio(){
        this.botonReinicio.setBounds(50, 330, 100, 30);
        this.botonReinicio.setBackground(Color.LIGHT_GRAY);
        this.botonReinicio.setEnabled(false);
        this.botonReinicio.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                elMarcador.reiniciarMarcadores();
                elMensajero.reiniciarMensajero();
                elTablero.reiniciarTablero();
                numeroDeJugada = 0;
                elTablero.habilitarTablero();
                elArbitro.habilitarArbitro();
                if(botonJugar.isEnabled()){
                    botonJugar.setEnabled(false);
                    botonJugar.setBackground(Color.LIGHT_GRAY);
                }  
            }
        });
        this.interfaz.add(botonReinicio);
    }
    
    private void prepararBotonJugar(){
        this.botonJugar.setBounds(250, 330, 100, 30);
        this.botonJugar.setBackground(Color.GREEN);
        this.botonJugar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(numeroDeJugada==0){
                    habilitarJuego();
                }
                else{
                    elArbitro.habilitarArbitro();
                    elTablero.reiniciarTablero();
                    elMensajero.reiniciarMensajero();
                    numeroDeJugada = 0;
                }
                desHabilitarBotonJugar();
            }
        });
        this.interfaz.add(botonJugar);
    }
    
    private  void habilitarJuego(){
        this.botonReinicio.setEnabled(true);
        this.elMarcador.habilitarMarcadores();
        this.elTablero.habilitarTablero();
        this.elMensajero.turnoJ1();
        this.botonReinicio.setBackground(Color.PINK);
        this.elArbitro.habilitarArbitro();
    }

    private void prepararJugadores(String fichaJ1, String fichaJ2) {
        this.jugador1.setFicha(fichaJ1);
        this.jugador2.setFicha(fichaJ2);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){  
            JButton temp = (JButton)e.getSource();
            if(this.elArbitro.seguirJugando()){
                realizarUnaJugada(this.numeroDeJugada, temp);
                if(!this.elArbitro.seguirJugando()){
                    this.habilitarBotonJugar();
                }
            }
        }
    }
    
    public void realizarUnaJugada(int numeroDeJugada, JButton temp){
        boolean casilleroOcupado = !(temp.getText().equals(""));
        if(!casilleroOcupado){
            Posicion laPosicion = this.elTablero.getPosicion(temp);
            this.numeroDeJugada++;
            this.jugarFicha(temp);
            this.elArbitro.auditarJugada(laPosicion.getCoordenadaHorizontal(), laPosicion.getCoordenadaVertical(), temp.getText());
            this.informarResultado();
        }    
        else{
          this.elMensajero.cassilleroOcupado();
        }  
    }         
    
    private void desHabilitarBotonJugar(){
        this.botonJugar.setEnabled(false);
        this.botonJugar.setBackground(Color.LIGHT_GRAY);
    }
    
    private void jugarFicha(JButton unCasillero){
        String unaFicha = "";
        if((this.numeroDeJugada%2)!=0){
                unaFicha = this.jugador1.getFicha();
                unCasillero.setText(unaFicha);
            }
            else{
                unaFicha = this.jugador2.getFicha();
                unCasillero.setText(unaFicha);
            }
    }
    
    public void informarResultado(){
        if(!this.elArbitro.seguirJugando()){
            this.mostrarFinalizacionDeLaPartida();
        }
        else{
            this.mostrarContinuacionDeLaPartida();
        }
    }

    private void mostrarFinalizacionDeLaPartida(){
        if(this.elArbitro.hayGanador()){
            if((this.numeroDeJugada%2)!=0){
                this.elMensajero.victoriaJ1();
                this.elMarcador.puntoParaJ1();
            }
            else{
                this.elMensajero.victoriaJ2();
                this.elMarcador.puntoParaJ2();
            }  
        }
        else{
            this.elMensajero.empate();
        }
        
    }
    private void mostrarContinuacionDeLaPartida(){
        if((this.numeroDeJugada%2)!=0){
            this.elMensajero.turnoJ2();
        }
        else{
            this.elMensajero.turnoJ1();
        }
    }
    
    private void habilitarBotonJugar() {
        this.botonJugar.setEnabled(true);
        this.botonJugar.setBackground(Color.GREEN);
        
    }
}
