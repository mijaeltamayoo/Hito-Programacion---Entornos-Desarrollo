import org.jdesktop.swingx.JXDatePicker;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PictureViewer extends JFrame {
    public  PictureViewer() throws SQLException {
        JFrame frame = new JFrame("Photography");
        frame.setLayout(new GridLayout(2,2));
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        final String PASSWORD = "leajim01";
        String pic;
        String username = "root";
        String password = PASSWORD;
        String url = "jdbc:mysql://127.0.0.1:3306/picture";
        Connection con = DriverManager.getConnection(url, username, password);
        Statement stmt;
        ResultSet rs;
        stmt = con.createStatement();

        rs = stmt.executeQuery("SELECT picname FROM photographers ");
        ArrayList<String> options = new ArrayList<String>();
        while(rs.next()){// columna title
            options.add(rs.getString("picname"));
        }

        JPanel box = new JPanel();
        JLabel textbox = new JLabel("Photographer:");
        JComboBox combo = new JComboBox(options.toArray());
        combo.setMaximumSize(new Dimension(100, 20));
        box.add(textbox);
        box.add(combo);
        frame.add(box);

        JPanel calendar = new JPanel();
        JLabel textdate = new JLabel("Photos after");
        JXDatePicker date = new JXDatePicker();
        calendar.add(textdate);
        calendar.add(date);
        frame.add(calendar);

        JPanel lst = new JPanel();
        JList list = new JList();
        DefaultListModel<String> listModel = new DefaultListModel<>(); // create a new DefaultListModel
        list.setModel(listModel);
        list.setPreferredSize(new Dimension(200,200));
        lst.add(list);
        frame.add(lst);

        JPanel image = new JPanel();
        JLabel img = new JLabel();
        image.add(img);
        frame.add(image);

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selected = combo.getSelectedItem().toString();
                    String fecha;
                    System.out.println(selected);


                    PreparedStatement p;
                    ResultSet rs;

                    listModel.removeAllElements();

                    if (date.getDate() == null){
                         p = con.prepareStatement("SELECT title FROM pictures WHERE photographerid in (SELECT photographerid FROM photographers WHERE picname = '"+selected+"');");
                         rs = p.executeQuery();
                    }else{
                        fecha = new SimpleDateFormat("yyyy-MM-dd").format(date.getDate());
                        p = con.prepareStatement("SELECT title FROM pictures WHERE picturedate >= '"+fecha+"' and photographerid in (SELECT photographerid FROM photographers WHERE picname = '"+selected+"');");
                        rs = p.executeQuery();
                    }
                    while(rs.next()){
                        String pic = rs.getString("title");
                        listModel.addElement(pic);
                        System.out.println(rs.getString("title"));
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String file = "";
                    String titulo = (String) list.getSelectedValue();

                    try {
                        PreparedStatement p = con.prepareStatement("select picfile from pictures where title = '" + titulo + "'");
                        ResultSet rs = p.executeQuery();

                        while (rs.next()) {
                            file = rs.getString("picfile");
                            System.out.println(rs.getString("picfile"));
                        }

                        // increment visits
                        incrementVista(file);

                        ImageIcon imageIcon = new ImageIcon(file);
                        Image image = imageIcon.getImage();
                        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        img.setIcon(scaledIcon);

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });


        frame.setVisible(true);

    }

    public void incrementVista(String file) throws SQLException {
        // coger el file
        final String PASSWORD = "leajim01";
        String pic;
        String username = "root";
        String password = PASSWORD;
        String url = "jdbc:mysql://127.0.0.1:3306/picture";
        Connection con = DriverManager.getConnection(url, username, password);
        Statement stmt;
        ResultSet rs;

        System.out.println("File : " + file);

        PreparedStatement p = con.prepareStatement("SELECT visits FROM pictures WHERE picfile = '" + file + "'");
        ResultSet response = p.executeQuery();

        int views = 0;

        while(response.next()){
            views = response.getInt("visits");
        }

        views++;

        System.out.println("Updating : " + file);
        ResultSet rs2;

        PreparedStatement p2 = con.prepareStatement("UPDATE pictures SET visits = '" + views + "'  WHERE picfile = '" + file + "'");
        p2.executeUpdate();

        con.close();
    }
}
