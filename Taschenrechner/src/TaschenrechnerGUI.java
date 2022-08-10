import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Taschenrechner GUI
 *
 * 
 * @author  jenith jeyaranjan
 * 
 */
/**
 * The GUI is created and the main method is executed
 */
public class TaschenrechnerGUI
{
    public static void main(String[] args)
    {
        CalculatorFrame frame = new CalculatorFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/**
 * The CalculatorFrame class is a JFrame that contains a CalculatorPanel
 */
class CalculatorFrame extends JFrame
{
    // It creates a new CalculatorFrame object and sets the title of the frame.
    public CalculatorFrame()
    {
        setTitle("Taschernrechner");
        CalculatorPanel panel = new CalculatorPanel();
        add(panel);
        pack();
    }
}

class CalculatorPanel extends JPanel {

    // Creating a new JButton object and adding it to the panel.
    public CalculatorPanel() {
        setLayout(new BorderLayout());

        result = 0;
        lastCommand = "=";
        start = true;

        display = new JButton("0");
        add(display, BorderLayout.NORTH);

        ActionListener zahlen = new zahlenAction();
        ActionListener zeichen = new zeichenAction();
        ActionListener loeschen = new loeschenAction();

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5));
        addButton("exit", loeschen);
        addButton("wurzel", zahlen);
        addButton("hoch", zahlen);
        addButton("ggt", zahlen);

        addButton("7", zahlen);
        addButton("8", zahlen);
        addButton("9", zahlen);
        addButton("/", zeichen);

        addButton("4", zahlen);
        addButton("5", zahlen);
        addButton("6", zahlen);
        addButton("*", zeichen);

        addButton("1", zahlen);
        addButton("2", zahlen);
        addButton("3", zahlen);
        addButton("-", zeichen);

        addButton(".", zahlen);
        addButton("0", zahlen);
        addButton("=", zeichen);
        addButton("+", zeichen);


        add(panel, BorderLayout.CENTER);
    }

    /**
     * *Create a button with the given label and add it to the panel.*
     *
     * @param label    The text to display on the button.
     * @param listener The ActionListener that will be called when the button is pressed.
     */
    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }


    private class zahlenAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            if (start) {
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + input);
        }
    }

    /**
     * Diese Aktion führt den mit der Taste verbundenen
     * Befehl aus.
     */
    private class zeichenAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();

            // Füge den Präfix "-" an den String an wenn
            // es sich um den ersten Befehl handelt (negative Zahl)
            if (start) {
                if (command.equals("-")) {
                    display.setText(command);
                    start = false;
                } else
                    lastCommand = command;
            } else {
                // Berechnung ausführen
                calculate(Double.parseDouble(display.getText()));
                lastCommand = command;
                start = true;
            }
        }
    }

    private class loeschenAction implements ActionListener {


        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }

    }
    
    /**
     * Führt die anstehenden Berechnungen aus.
     *
     * @param x der mit dem vorherigen Ergebnis zu berechnende Wert
     */
    public void calculate(double x) {
        if (lastCommand.equals("+")) result += x;
        else if (lastCommand.equals("-")) result -= x;
        else if (lastCommand.equals("*")) result *= x;
        else if (lastCommand.equals("/")) result /= x;
        else if (lastCommand.equals("=")) result = x;
        else if (lastCommand.equals("wurzel")) result = Math.sqrt(x);
        display.setText("" + result);
        /*
        else if (lastCommand.equals("hoch")) if (0 == x) {
            result = 1;
        }
        double resultat = 1.0;
        while (x > 1) {
            if (gerade((int) x)) {
                y = y * y;
                x = x / 2;
            } else {
                resultat = resultat * y;
                y = y * y;
                x = (x - 1) / 2;
            }
        }
        result = resultat * y;


    }

    boolean gerade(int n) {
        return 0 == n % 2;

  */
    }





    // These are the fields of the class.

    private JButton display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;


}