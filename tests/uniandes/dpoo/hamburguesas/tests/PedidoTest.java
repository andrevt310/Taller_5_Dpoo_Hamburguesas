package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//pueba

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	
	private Pedido pedido1;
	private Pedido pedido2;
	
	private ProductoMenu producto1;
    private ProductoMenu producto2;
	
	
	
	
	@BeforeEach
    void setUp( ) throws Exception{
		java.lang.reflect.Field field = Pedido.class.getDeclaredField("numeroPedidos");
	    field.setAccessible(true);
	    field.setInt(null, 0);
		pedido1 = new Pedido( "Andrea", "Calle 19 #2a-10" );
		pedido2 = new Pedido( "Isabel", "Calle 150" );
		
		producto1 = new ProductoMenu("Helado", 12500);
        producto2 = new ProductoMenu("Papas", 5000);
    }
	
	 @AfterEach
	 void tearDown( ) throws Exception{
		 pedido1 = null;
	     pedido2 = null; 
	 }
	 
	 
	 @Test
	 void testGetIdPedido() {
		 assertEquals(0, pedido1.getIdPedido( ), "El ID del pedido no es el esperado." );	 
		 assertEquals(1, pedido2.getIdPedido( ), "El ID del pedido no es el esperado." );	 
	 }
	
	 @Test
	 void testGetNombreCliente() {
		 assertEquals("Andrea", pedido1.getNombreCliente( ), "El nombre del cliente no es el esperado." );	 
		 assertEquals("Isabel", pedido2.getNombreCliente( ), "El nombre del cliente no es el esperado." );		 
	 }
	 
	 @Test
	 void testAgregarProducto() {
		 pedido1.agregarProducto(producto1);
	     pedido1.agregarProducto(producto2);

	     String factura = pedido1.generarTextoFactura();
	     assertTrue(factura.contains("Helado"));
	     assertTrue(factura.contains("Papas"));
 
	 }
	 
	 @Test
	 void testGetPrecioTotalPedido() {
		 pedido1.agregarProducto(producto1);
	     pedido1.agregarProducto(producto2);

	     int total = producto1.getPrecio() + producto2.getPrecio();
	     int iva = (int) (total * 0.19); 
	     int totalEsperado = total + iva; 
	     assertEquals(totalEsperado, pedido1.getPrecioTotalPedido(), "El precio total del pedido no es el esperado.");		 
	 }
	 
	 


	 
	
	 
	 @Test
	 void testGenerarTextoFactura() {
		 pedido1.agregarProducto(producto1);
	     pedido1.agregarProducto(producto2);

	     String factura = pedido1.generarTextoFactura();

	     assertTrue(factura.contains("Cliente: Andrea"));
	     assertTrue(factura.contains("Dirección: Calle 19 #2a-10"));
	     assertTrue(factura.contains("Precio Neto:"));
	     assertTrue(factura.contains("IVA:"));
	     assertTrue(factura.contains("Precio Total:"));
		 
	 }
	 
	 @Test
	 void testGuardarFactura() throws IOException {
		 pedido1.agregarProducto(producto1);
	     pedido1.agregarProducto(producto2);

	     File archivo = new File("factura_test.txt");
	     pedido1.guardarFactura(archivo);

	     assertTrue(archivo.exists());

	     String contenido = Files.readString(archivo.toPath());
	     assertTrue(contenido.contains("Cliente: Andrea"));
	     assertTrue(contenido.contains("Precio Total:"));

	     archivo.delete();
		 
	 }	

}
