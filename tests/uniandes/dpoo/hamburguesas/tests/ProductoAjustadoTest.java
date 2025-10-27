package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {

    private ProductoMenu salchipapa;
    private ProductoAjustado productoAjustado;
    private Ingrediente maicitos;
    private Ingrediente tocineta;
    private Ingrediente cebolla;

    @BeforeEach
    void setUp() {
        salchipapa = new ProductoMenu("Salchipapa", 20000);
        productoAjustado = new ProductoAjustado(salchipapa);

        maicitos = new Ingrediente("Maicitos", 2500);
        tocineta = new Ingrediente("Tocineta", 3499);
        cebolla = new Ingrediente("Cebolla", 500);
    }

    @Test
    void testGetNombre() {
        assertEquals("Salchipapa", productoAjustado.getNombre());
    }

    @Test
    void testGetPrecio() {
    	productoAjustado.agregados.add(maicitos);
        productoAjustado.agregados.add(tocineta);

        // Precio esperado: 20000 + 2500 + 3499 = 25999
        int precio = productoAjustado.getPrecio();

        assertEquals(25999, precio, "No retorna el precio esperado");
    
        
    	
    	
    }
    
    @Test
    void testGenerarTextoFactura() {
        productoAjustado.agregados.add(maicitos);
        productoAjustado.agregados.add(tocineta);
        productoAjustado.eliminados.add(cebolla);

        String factura = productoAjustado.generarTextoFactura();

        assertTrue(factura.contains("ProductoMenu"), "Debe incluir el producto base en formato de texto.");
        assertTrue(factura.contains("+Maicitos"), "Debe incluir el ingrediente agregado Maicitos.");
        assertTrue(factura.contains("+Tocineta"), "Debe incluir el ingrediente agregado Tocineta.");
        assertTrue(factura.contains("-Cebolla"), "Debe incluir el ingrediente eliminado Cebolla.");
        assertTrue(factura.contains(String.valueOf(productoAjustado.getPrecio())),"Debe incluir el precio total del producto ajustado.");
        
    }
    
}
