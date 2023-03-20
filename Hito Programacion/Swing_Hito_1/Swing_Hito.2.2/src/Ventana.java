import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ventana extends JFrame{

    public static void main(String[] args) {
        // frame
        JFrame frame = new JFrame("Test Events: Files");
        frame.setLayout(new BorderLayout());
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // default
        frame.setLocationRelativeTo(null); // default

        // Panel para el ComboBox y Boton clear

        JPanel box = new JPanel();
        String[] file = {"python.txt", "c.txt", "java.txt"};
        JComboBox comboBox = new JComboBox<>(file);
        comboBox.setPreferredSize(new Dimension(150,20));
        JTextArea textarea = new JTextArea("");
        textarea.setEditable(false);
        box.add(comboBox);
        frame.add(box, BorderLayout.WEST);

        JButton boton = new JButton("Clear");
        box.add(boton);
        JScrollPane panel = new JScrollPane(textarea);
        panel.setPreferredSize(new Dimension(300,300));
        JOptionPane ads = new JOptionPane();

        // TextArea y boton close
        JPanel text = new JPanel();
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        JButton boton2 = new JButton("Close");
        text.add(panel);
        text.add(boton2);
        frame.add(text);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String file = (String) comboBox.getSelectedItem();
                try {
                    String content = new String(Files.readAllBytes(Paths.get(file)));
                    textarea.setText(content);
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(ads, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("");
            }
        });


        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
