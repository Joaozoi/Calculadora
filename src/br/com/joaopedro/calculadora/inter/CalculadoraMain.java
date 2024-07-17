package br.com.joaopedro.calculadora.inter;

import javax.swing.SwingUtilities;

public class CalculadoraMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraGUI calculadora = new CalculadoraGUI();
            calculadora.setVisible(true);
        });
    }
}
