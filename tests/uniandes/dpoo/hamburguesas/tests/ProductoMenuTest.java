package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {

    private ProductoMenu hamburguesa;
    private ProductoMenu papas;

    @BeforeEach
    void setUp() {
        hamburguesa = new ProductoMenu("Hamburguesa", 15000);
        papas = new ProductoMenu("Papas", 5000);
    }

    @Test
    void testGetNombre() {
        assertEquals("Hamburguesa", hamburguesa.getNombre());
        assertEquals("Papas", papas.getNombre());
    }

    @Test
    void testGetPrecio() {
        assertEquals(15000, hamburguesa.getPrecio());
        assertEquals(5000, papas.getPrecio());
        assertNotEquals(0, hamburguesa.getPrecio(), "El precio no debería ser 0");
    }

    @Test
    void testGenerarTextoFactura() {
        String factura = hamburguesa.generarTextoFactura();
        assertTrue(factura.contains("Hamburguesa"));
        assertTrue(factura.contains(String.valueOf(hamburguesa.getPrecio())));
        assertTrue(factura.endsWith("\n"), "La factura debería terminar con un salto de línea.");
    }
}