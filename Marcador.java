
package tateti;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Muestra la identificacion de los 2 jugadores junto con su puntuacion
 * segun las victorias en el juego.
 */
public class Marcador {
    private JLabel labJugadores;
    private JTextField puntajeJ1;
    private JTextField puntajeJ2;
    
    public Marcador(){
        this.labJugadores = new JLabel("JUGADOR 1                  Vs.                  JUGADOR 2");
        this.puntajeJ1 = new JTextField("-");
        this.puntajeJ2 = new JTextField("-");
        
        this.puntajeJ1.setEnabled(false);
        this.puntajeJ2.setEnabled(false);
        this.puntajeJ1.setHorizontalAlignment(JTextField.CENTER);
        this.puntajeJ2.setHorizontalAlignment(JTextField.CENTER);
    }
    
    public JLabel getInfoJugadores(){
        return this.labJugadores;
    }
    
    public JTextField getPuntajeJ1(){
        return this.puntajeJ1;
    }
    
    public JTextField getPuntajeJ2(){
        return this.puntajeJ2;
    }
    
    public void puntoParaJ1(){
        int nuevoPuntaje = Integer.parseInt(this.puntajeJ1.getText())+1;
        this.puntajeJ1.setText(String.valueOf(nuevoPuntaje));
    }
    
    public void puntoParaJ2(){
        int nuevoPuntaje = Integer.parseInt(this.puntajeJ2.getText())+1;
        this.puntajeJ2.setText(String.valueOf(nuevoPuntaje));
    }
 
    public void reiniciarMarcadores(){
        this.puntajeJ1.setText("0");
        this.puntajeJ2.setText("0");
    }
    
    public void establecerCoordenadasMarcadoresEnFrame400x400(){
        this.labJugadores.setBounds(70, 50, 260, 30);  
        this.puntajeJ1.setBounds(90, 80, 30, 30);
        this.puntajeJ2.setBounds(280, 80, 30, 30);
    }
    
    public void habilitarMarcadores(){
        this.puntajeJ1.setText("0");
        this.puntajeJ2.setText("0");
        this.puntajeJ1.setDisabledTextColor(Color.BLACK);
        this.puntajeJ2.setDisabledTextColor(Color.BLACK);
    }
    
    public void desHabilitarMarcadores(){
        this.puntajeJ1.setText("-");
        this.puntajeJ2.setText("-");
        this.puntajeJ1.setDisabledTextColor(Color.LIGHT_GRAY);
        this.puntajeJ2.setDisabledTextColor(Color.LIGHT_GRAY);
    }
}
