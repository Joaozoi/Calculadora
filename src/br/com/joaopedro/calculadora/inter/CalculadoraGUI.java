package br.com.joaopedro.calculadora.inter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame implements ActionListener {

    private JTextField display; // Para mostrar números e resultados
    private StringBuilder currentNumber;
    private double result;
    private String lastOperator;
    private Exprecoes exprecoes;

    public CalculadoraGUI() {
        exprecoes = new Exprecoes();

        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação do display para mostrar números
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        currentNumber = new StringBuilder();
        result = 0;
        lastOperator = "=";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            currentNumber.append(command);
            display.setText(currentNumber.toString());
        } else {
            if (command.equals("C")) {
                currentNumber.setLength(0);
                result = 0;
                lastOperator = "=";
                display.setText("");
            } else if (command.equals("=")) {
                if (currentNumber.length() > 0) {
                    calculate(Double.parseDouble(currentNumber.toString()));
                    currentNumber.setLength(0);
                }
                lastOperator = "=";
                display.setText(String.valueOf(result));
            } else {
                if (currentNumber.length() > 0) {
                    calculate(Double.parseDouble(currentNumber.toString()));
                    currentNumber.setLength(0);
                }
                lastOperator = command;
            }
        }
    }

    private void calculate(double number) {
        switch (lastOperator) {
            case "+":
                result = exprecoes.soma(result, number);
                break;
            case "-":
                result = exprecoes.subtracao(result, number);
                break;
            case "*":
                result = exprecoes.multiplicacao(result, number);
                break;
            case "/":
                if (number != 0) {
                    result = exprecoes.divisao(result, number);
                } else {
                    JOptionPane.showMessageDialog(this, "Divisão por zero não é permitida.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "=":
                result = number;
                break;
        }
        display.setText(String.valueOf(result));
    }
}
