import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException {

        PictureViewer p1 = new PictureViewer();
    }
}

// ejecutas esta consulta SSELECT * FROM  pictures INNER JOIN photographers on pictures.id = photographers.id where photographer.name = Name que te llega del input
// para añadir el nombre coges el string de la consulat y le sumas el nombre del photografo
// luego tiene que hacer un JXDatePicker y añadirle los valores de la data que se te de cada imagen
// createDateCombo(frame, arrayDeDates)
// luego tienes que crear un Jlist y en el JList añadir los nombres de los valores de cada iamge
// createList(frame, arrayDeNombresDeImagenes)
// añadirle un evnet listener a cada li de la lista que cuando hagas doble click hagas llamada a la base de datos
// cuando le has dado doble click haces setVistis(getVisits() + 1)
// UPDATE Pictures Set Visits = getVisits() WHERE title = imagenTitle
// SELECT File from pictures where title = [el titulo de la imagen en la que has hecho click]
// showImage(file)




