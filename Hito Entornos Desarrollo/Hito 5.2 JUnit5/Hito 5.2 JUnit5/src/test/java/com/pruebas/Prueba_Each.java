package com.pruebas;

import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Prueba_Each{
    @Before
    public void starting() {
        SmartPhoneServiceImpl smartPhoneService = new SmartPhoneServiceImpl();
        System.out.println("Inicializando la prueba...");
    }

    @Test
    public void countSmartphone(){
        SmartPhoneServiceImpl s1 = new SmartPhoneServiceImpl();
        assertAll(() -> assertNotNull(s1.count(),"Este valor es nulo"));
        assertAll(() -> assertTrue(s1.count() > 0, "Este numero debe ser mayor que 0"));
        assertAll(() ->assertEquals(3, s1.count(),"El numero debe ser 3"));

    }

    @Test
    public void nullSmartphone(){
        SmartPhoneServiceImpl s1 = new SmartPhoneServiceImpl();
        Long id = null;
        assertThrows(IllegalArgumentException.class, () -> {
            s1.findOne(id);
        });
    }

    @After
    public void ending() {
        Object smartPhoneService = null;
        System.out.println("Finalizando la prueba...");
    }
}