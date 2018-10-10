
package tateti;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/**
 * Objeto que puede contener una ficha de un juego que sea del tipo String.
 */
public class Casillero {
    private JButton contenedor;
    
    public Casillero(){
        this.contenedor = new JButton();
        this.contenedor.setFont(new Font("Arial", 0, 21));
        this.contenedor.setEnabled(false);
    }
    
    public void posicionar(int x, int y){
        this.contenedor.setBounds(x, y, 50, 50);
    }
    
    public void habilitarCasillero(){
        this.contenedor.setEnabled(true);
    }
    
    public void desHabilitarCasillero(){
        this.contenedor.setEnabled(false);
    }
    
    public String getContenido(){
        return this.contenedor.getText();
    }
    
    public void vaciarCasillero(){
        JButton botonVacio = new JButton();
        Color colorVacio = botonVacio.getBackground();
        this.contenedor.setText("");
        this.contenedor.setBackground(colorVacio);
        this.habilitarCasillero();
    }
    
    public JButton getContenedor(){
        return this.contenedor;
    }
    
    public boolean estaVacio(){
        return (this.getContenido().equals(""));
    }
}
