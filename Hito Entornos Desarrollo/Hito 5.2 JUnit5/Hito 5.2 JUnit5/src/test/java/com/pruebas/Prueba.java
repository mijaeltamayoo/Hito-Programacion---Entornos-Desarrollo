package com.pruebas;

import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class Prueba {

    @Test
    public void countSmartphone(){
        SmartPhoneServiceImpl s1 = new SmartPhoneServiceImpl();
        assertAll(() -> assertNotNull(s1.count(),"Este valor es nulo"));
        assertAll(() -> assertTrue(s1.count() > 0, "Este numero debe ser mayor que 0"));
        assertAll(() ->assertEquals(3, s1.count(),"El numero debe ser 3"));

    }
    //    public void check1(){
    //        SmartPhoneServiceImpl s1 = new SmartPhoneServiceImpl();
    //        Assertions.assertNotNull(s1.count(),"Este valor es nulo");
    //        Assertions.assertTrue(s1.count() > 0, "Este numero debe ser mayor que 0");
    //        Assertions.assertEquals(3, s1.count(),"El numero debe ser 3");
    //
    //}
    @Test
    public void nullSmartphone(){
        SmartPhoneServiceImpl s1 = new SmartPhoneServiceImpl();
        Long id = null;
        assertThrows(IllegalArgumentException.class, () -> {
            s1.findOne(id);
        });
    }

}


