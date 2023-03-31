import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ventana extends JFrame{

    public static void main(String[] args) throws IOException {

        String password = "damocles";
        String inputPassword = JOptionPane.showInputDialog(null, "Input password:");

        if (inputPassword.equals(password)) {

            JFrame frame = new JFrame("Swing - Example 2");
            frame.setLayout(new BorderLayout());
            frame.setSize(400,350);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            // ComboBox
            JPanel general = new JPanel();
            JPanel box = new JPanel();
            box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
            String[] file = {"blurryface.png", "trench.png", "twenty.png"};
            JComboBox<String> comboBox = new JComboBox<>(file);
            comboBox.setPreferredSize(new Dimension(200,50));
            box.add(comboBox);
            general.add(box);
            general.setSize(200,150);
            frame.add(general, BorderLayout.WEST);

            box.add(Box.createVerticalStrut(20));

            //Imagen
            Image image = new ImageIcon("blurryface.png").getImage();
            ImageIcon copyimage = new ImageIcon(image.getScaledInstance(160, 150, Image.SCALE_SMOOTH));
            JLabel labelImagen = new JLabel();
            labelImagen.setIcon(copyimage);
            JPanel panelImagen = new JPanel(new BorderLayout());
            panelImagen.add(labelImagen, BorderLayout.WEST);
            box.add(panelImagen); //agregar el panel que contiene la imagen y el checkbox

            //CheckBox
            JCheckBox check= new JCheckBox("save your comment");
            check.setSelected(true);
            panelImagen.add(check, BorderLayout.SOUTH); //agregar el checkbox al panel que contiene la imagen

            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String file = (String) comboBox.getSelectedItem();
                    loadCombo(file, labelImagen);
                }
            });

            //Boton Save
            JPanel buttom = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton save = new JButton("Save");
            save.setSize(150, 150);
            buttom.add(save);
            frame.add(buttom, BorderLayout.SOUTH);

            //Comentario
            JPanel comment  = new JPanel();
            JTextArea text = new JTextArea();
            JScrollPane scroll = new JScrollPane(text);
            scroll.setPreferredSize(new Dimension(150, 30));
            comment.add(scroll);
            comment.add(Box.createVerticalStrut(300));
            frame.add(comment,BorderLayout.EAST);

            FileWriter fw=new FileWriter("comentarios.txt");
            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = (String) comboBox.getSelectedItem();
                    if(check.isSelected()){
                        try {

                            BufferedWriter bw=new BufferedWriter(fw);
                            bw.write(name + ": " + " "+text.getText());
                            bw.newLine();
                            bw.flush();
                            JOptionPane.showMessageDialog(frame, "Saved comment");

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    frame.dispose();
                    JOptionPane.showMessageDialog(frame, "Adios");
                }
            });

            frame.setVisible(true);
        }else{
            JPanel error= new JPanel();
            JOptionPane.showMessageDialog(error,"Incorrect Password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void loadCombo(String name,JLabel label) {
        Image image = new ImageIcon(name).getImage();
        ImageIcon img2 = new ImageIcon(image.getScaledInstance(160,150,Image.SCALE_SMOOTH));
        label.setIcon(img2);
    }
}
