
package tateti;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * Se encarga de mostrar la informacipon a través de una caja de texto.
 * La misma es referente a la partida que se esta jugando.
 */
public class Mensajero {
    private JLabel etiquetaEstado;
    private JTextField display;
    
    public Mensajero(){
        this.etiquetaEstado = new JLabel("Estado de juego: ");
        this.etiquetaEstado.setBounds(30, 10, 100, 30);
        this.display = new JTextField();
        this.display.setDisabledTextColor(Color.BLACK);
        this.display.setBounds(130, 10, 240, 30);
        this.display.setEnabled(false);
        this.display.setHorizontalAlignment(JTextField.CENTER);
    }
    
    public JLabel getEstado(){
        return this.etiquetaEstado;
    }
    
    public JTextField getDisplay(){
        return this.display;
    }
    
    public void avisoParaIniciarJuego(){
        this.display.setText("Pulsar 'Jugar' para comenzar el juego.");
    }
    
    public void turnoJ1(){
        this.display.setText("Turno del JUGADOR 1.");
    }
    
    public void turnoJ2(){
        this.display.setText("Turno del JUGADOR 2.");
    }
    
    public void cassilleroOcupado(){
        this.display.setText("El casillero esta ocupado. Elija otro.");
    }
    
    public void victoriaJ1(){
        this.display.setText("El JUGADOR 1 gana.");
    }
    
    public void victoriaJ2(){
        this.display.setText("El JUGADOR 2 gana.");
    }
    
    public void empate(){
        this.display.setText("La partida termina en empate.");
    }
    
    public void reiniciarMensajero(){
        this.turnoJ1();
    }
    
}
