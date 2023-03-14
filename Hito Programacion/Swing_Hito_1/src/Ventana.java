import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Try yourself!"); // nombre del frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // default
        frame.setLayout(new BorderLayout()); // colocar un un BorderLayout
        frame.setSize(500,500); // tamaño del frame que queremos asignar
        frame.setLocationRelativeTo(null); // default
        frame.setVisible(true);

        JPanel checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // creamos un panel que le situaremos en el centro
        JCheckBox check1 = new JCheckBox("Katniss"); // creacion de checkbox
        JCheckBox check2 = new JCheckBox("Peeta");
        check1.setSelected(true); // usamos esto para que el primer checkbox este seleccionado
        checkBoxPanel.add(check1); // añadimos el check al panel en donde estos dos se encontraran
        checkBoxPanel.add(check2);
        frame.add(checkBoxPanel, BorderLayout.NORTH); // aqui le asignamos en que parte del frame queremos que vaya

        JPanel panelRadioButtons = new JPanel(); // creamos otro panel
        panelRadioButtons.setLayout(new BoxLayout(panelRadioButtons,BoxLayout.Y_AXIS)); // lo posicionamos para que vaya en columna
        JRadioButton[] radioButtons = {new JRadioButton("OPT 1", true), new JRadioButton("OPT 2"), new JRadioButton("OPT 3")}; // creacion de radioButton dentro de un array
        ButtonGroup group = new ButtonGroup(); // creacion de un grupo en el cual estos 3 radios iran
        panelRadioButtons.add(Box.createVerticalGlue());
        for (JRadioButton r : radioButtons) { // recorre el array y los añade al grupo y tambien al panel
            group.add(r);
            panelRadioButtons.add(r);
        }
        panelRadioButtons.add(Box.createVerticalGlue());
        frame.add(panelRadioButtons, BorderLayout.EAST); // añadimos la frame el panel y lo situamos en la parte derechaa

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT)); // creamos un panel que vaya a la izquierda
        JButton button1 = new JButton("But 1"); // creacion de boton
        JButton button2 = new JButton("But 2");
        button1.setSize(100, 100); // le asignamos un tamaño
        button2.setSize(100, 100);
        panelButtons.add(button1); // lo añadimos al panel creado
        panelButtons.add(button2);
        frame.add(panelButtons, BorderLayout.SOUTH); // le asignamos en que parte del frame irá

        JPanel imagesPanel = new JPanel(new GridLayout(2,2,0,0)); // creamos de un panel con un grid de 2 x 2
        ImageIcon imageIcon = new ImageIcon("ei.jpg"); // le pasamos la imagen que queremos
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // para poder cambiar tamaño a la imagen
        ImageIcon newImageIcon = new ImageIcon(image);
        JLabel label1 = new JLabel(newImageIcon);
        JLabel label2 = new JLabel(newImageIcon);
        JLabel label3 = new JLabel(newImageIcon);
        JLabel label4 = new JLabel(newImageIcon);

        imagesPanel.add(label1);
        imagesPanel.add(label2);
        imagesPanel.add(label3);
        imagesPanel.add(label4);

        frame.add(imagesPanel, BorderLayout.CENTER);
    }
}
