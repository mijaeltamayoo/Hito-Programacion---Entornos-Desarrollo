package com.pruebas;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class Prueba_All{

    private static SmartPhoneServiceImpl s1;

    @BeforeAll
    public static void starting() {
        s1 = new SmartPhoneServiceImpl();
        System.out.println("Inicializando todas las pruebas...");
    }

    @AfterAll
    public static void ending() {
        s1 = null;
        System.out.println("Finalizando todas las pruebas...");
    }

    @Test
    public void testConteoDeSmartPhones() {
        assertAll("Conteo de SmartPhones",
                () -> assertNotNull(s1.count(), "Este valor es nulo"),
                () -> assertTrue(s1.count() > 0, "Este numero debe ser mayor que 0"),
                () -> assertEquals(3, s1.count(), "El numero debe ser 3")
        );
    }

    @Test
    public void testEncontrarSmartPhoneConIdNulo() {
        Long id = null;
        assertThrows(IllegalArgumentException.class, () -> {
            s1.findOne(id);
        });
    }
}