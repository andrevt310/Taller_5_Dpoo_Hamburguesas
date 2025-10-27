package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {

    private Restaurante andrePapas;

    @BeforeEach
    void setUp() {
    	andrePapas = new Restaurante();
    }
    
    @Test
    void testIniciarPedido() throws Exception {
        assertDoesNotThrow(() -> {andrePapas.iniciarPedido("Andrea", "Calle 19");
        }, "No debería lanzar excepción al iniciar el primer pedido.");

        Pedido pedido = andrePapas.getPedidoEnCurso();
        assertNotNull(pedido, "Después de iniciar un pedido, debe existir un pedido en curso.");
        assertEquals("Andrea", pedido.getNombreCliente(), "El nombre del cliente debe ser Andrea.");


        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {andrePapas.iniciarPedido("Carlos", "Calle 10");
        }, "Debe lanzarse YaHayUnPedidoEnCursoException cuando hay un pedido activo.");
    }
    
    @Test
    void testCerrarYGuardarPedido() throws Exception {
        assertThrows(NoHayPedidoEnCursoException.class, () -> {andrePapas.cerrarYGuardarPedido();
        }, "Debe lanzar NoHayPedidoEnCursoException si no hay pedido activo.");

        andrePapas.iniciarPedido("Andrea", "Calle 19");

        assertDoesNotThrow(() -> {andrePapas.cerrarYGuardarPedido();
        }, "No debería lanzar excepción al cerrar un pedido en curso valido.");

        assertNull(andrePapas.getPedidoEnCurso(),"Después de cerrar y guardar un pedido, no debe haber un pedido activo.");
    }
    
    @Test
    void testGetPedidos() throws Exception {
        assertTrue(andrePapas.getPedidos().isEmpty(),
            "Al iniciar, la lista de pedidos cerrados debe estar vacía.");
        
        andrePapas.iniciarPedido("Andrea", "Calle 19");
        andrePapas.cerrarYGuardarPedido();

        ArrayList<Pedido> pedidos = andrePapas.getPedidos();
        assertTrue(pedidos.isEmpty(), "La lista de pedidos debe seguir vacía");
    }
    
    @Test
    void testCargarIngredientes() throws IOException {
        File archivoIngredientes = crearArchivoTemporal("Tomate;500\nTomate;600\n");
        File archivoMenu = crearArchivoTemporal("Andreburger;15000\n");
        File archivoCombos = crearArchivoTemporal("");

        assertThrows(IngredienteRepetidoException.class, () -> {andrePapas.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
        }, "Debe lanzarse IngredienteRepetidoException cuando hay ingredientes duplicados.");
    }

    
    private File crearArchivoTemporal(String contenido) throws IOException {
        File prueba = File.createTempFile("ingredientes", ".txt");
        try (FileWriter writer = new FileWriter(prueba)) {
            writer.write(contenido);
        }
        return prueba;
    }


    @Test
    void testCargarInformacionRestaurante() throws Exception {
        File ingredientes = new File("data/ingredientes.txt");
        File menu = new File("data/menu.txt");
        File combos = new File("data/combos.txt");

        andrePapas.cargarInformacionRestaurante(ingredientes, menu, combos);

        assertFalse(andrePapas.getIngredientes().isEmpty(), "Los ingredientes no deben estar vacíos.");
        assertFalse(andrePapas.getMenuBase().isEmpty(), "El menú base no debe estar vacío.");
        assertFalse(andrePapas.getMenuCombos().isEmpty(), "El menú de combos no debe estar vacío.");
    }
}
