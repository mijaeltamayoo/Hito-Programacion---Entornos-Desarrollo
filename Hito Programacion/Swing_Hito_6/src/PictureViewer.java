import org.jdesktop.swingx.JXDatePicker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PictureViewer extends JFrame {
    public PictureViewer() throws SQLException {
        JFrame frame = new JFrame("Photography");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(new GridLayout(2,2));
        frame.setLocationRelativeTo(null);


        Conexion conexion = new Conexion();
        Statement stm;
        ResultSet rs;

        JPanel box = new JPanel();
        ArrayList<String> options = new ArrayList<String>();
        stm = conexion.getCon().createStatement();
        rs = stm.executeQuery("Select picname from photographers");
        while(rs.next()){
            options.add(rs.getString("picname"));
        }
        System.out.println(options); // comprobamos de que Arraylist loaded
        JLabel text = new JLabel("Photographer:");
        JComboBox combo = new JComboBox<>(options.toArray());
        combo.setMaximumSize(new Dimension(100,20));
        box.add(text);
        box.add(combo);
        frame.add(box);

        JPanel calendar = new JPanel();
        JLabel textdate = new JLabel("Photos After:");
        JXDatePicker date = new JXDatePicker();
        calendar.add(textdate);
        calendar.add(date);
        frame.add(calendar);

        JPanel lista = new JPanel();
        JList list = new JList();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        list.setModel(listModel);
        list.setPreferredSize(new Dimension(200,200));
        lista.add(list);
        frame.add(lista);

        JPanel image = new JPanel();
        JLabel img = new JLabel();
        image.add(img);
        frame.add(image);

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String selected = (String) combo.getSelectedItem();
                    String fecha;
                    System.out.println(selected); // para comprobar si ha sido seleccionado
                    Statement stm;
                    ResultSet rs;
                    listModel.removeAllElements();
                    stm = conexion.getCon().createStatement();

                    if (date.getDate() == null){
                        rs = stm.executeQuery("Select title from pictures where photographerid in (Select photographerid from photographers where picname = '"+selected+"')");
                    }else{
                        fecha = new SimpleDateFormat("yyyy-MM-dd").format(date.getDate());
                        rs = stm.executeQuery("SELECT title FROM pictures WHERE picturedate >= '" + fecha + "' and photographerid in (SELECT photographerid FROM photographers WHERE picname = '" + selected + "');");
                    }
                    while (rs.next()){
                        listModel.addElement(rs.getString("title"));
                        System.out.println(rs.getString("title")); // para comprobar si printea las imagenes del photographer
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    try{
                        String file = "";
                        String titulo = list.getSelectedValue().toString();
                        Statement stm;
                        ResultSet rs;
                        stm = conexion.getCon().createStatement();
                        rs = stm.executeQuery("select picfile from pictures where title = '" + titulo + "'");
                        while (rs.next()) {
                            file = rs.getString("picfile");
                            System.out.println(rs.getString("picfile")); //comprobar si la imagen se ha seleccionado
                        }

                        ImageIcon imageIcon = new ImageIcon(file);
                        Image image = imageIcon.getImage();
                        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);

                        incrementVista(file);// metodo que incrementa el numero de vistas de la imagen cada vez que hacemos click

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        frame.setVisible(true);
    }
    public void incrementVista(String file) throws SQLException {
        Conexion c = new Conexion();
        System.out.println("Updating: " + file);
        Statement stm;
        stm = c.getCon().createStatement();
        int rs = stm.executeUpdate("Update pictures SET visits = visits + 1 where picfile = '" + file + "'");
    }

}
