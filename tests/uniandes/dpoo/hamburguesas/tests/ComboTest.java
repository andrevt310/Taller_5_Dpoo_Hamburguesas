package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ComboTest {

    private ProductoMenu perro;
    private ProductoMenu papas;
    private ArrayList<ProductoMenu> productos;

    @BeforeEach
    void setUp() {
        perro = new ProductoMenu("Perro", 9000);
        papas = new ProductoMenu("Papas", 4000);
        productos = new ArrayList<>();
        productos.add(perro);
        productos.add(papas);
    }


    @Test
    void testGetNombre() {
        Combo combo = new Combo("Combo EspeciPerro", 0.1, productos);
        assertEquals("Combo EspeciPerro", combo.getNombre());
    }

    @Test
    void testGetPrecioConDescuento() {
        Combo combo = new Combo("Combo EspeciPerro", 0.1, productos);
        int precioCalculado = combo.getPrecio();
        assertEquals(1300, precioCalculado, "El precio del combo con descuento del 10% debería ser 1300.");
    }



    @Test
    void testGenerarTextoFactura() {
        Combo combo = new Combo("Combo EspeciPerro", 0.1, productos);
        String factura = combo.generarTextoFactura();

        assertTrue(factura.contains("Combo EspeciPerro"), "Debe incluir el nombre del combo.");
        assertTrue(factura.contains(String.valueOf(combo.getPrecio())), "Debe incluir el precio del combo.");
    }
}

