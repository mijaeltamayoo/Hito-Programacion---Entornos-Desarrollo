package com.pruebas;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Prueba_MethodOrder {

    @Test
    @Order(1)
    public void testConteoDeSmartPhones(){
        SmartPhoneServiceImpl s1 = new SmartPhoneServiceImpl();
        assertAll("Conteo de SmartPhones",
                () -> assertNotNull(s1.count(),"Este valor es nulo"),
                () -> assertTrue(s1.count() > 0, "Este numero debe ser mayor que 0"),
                () -> assertEquals(3, s1.count(),"El numero debe ser 3")
        );
    }

    @Test
    @Order(2)
    public void testEncontrarSmartPhoneConIdNulo(){
        SmartPhoneServiceImpl s1 = new SmartPhoneServiceImpl();
        Long id = null;
        assertThrows(IllegalArgumentException.class, () -> {
            s1.findOne(id);
        });
    }
}
