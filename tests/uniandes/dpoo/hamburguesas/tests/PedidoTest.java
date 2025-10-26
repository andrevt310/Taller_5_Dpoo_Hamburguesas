package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//hola

import uniandes.dpoo.hamburguesas.mundo.Pedido;

public class PedidoTest {
	
	private Pedido pedido1;
	private Pedido pedido2;
	
	
	@BeforeEach
    void setUp( ) throws Exception{
		java.lang.reflect.Field field = Pedido.class.getDeclaredField("numeroPedidos");
	    field.setAccessible(true);
	    field.setInt(null, 0);
		pedido1 = new Pedido( "Andrea", "Calle 19 #2a-10" );
		pedido2 = new Pedido( "Isabel", "Calle 150" );
    }
	
	 @AfterEach
	 void tearDown( ) throws Exception{
		 
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
		 
		 
	 }
	 
	 @Test
	 void testGetPrecioTotalPedido() {
		 
	 }
	 
	 @Test
	 void testGetPrecioNetoPedido() {
		 
	 }
	 
	 @Test
	 void testGetPrecioIVAPedido() {
		 
	 }
	 
	 @Test
	 void testGenerarTextoFactura() {
		 
	 }
	 
	 @Test
	 void testGuardarFactura() {
		 
	 }
	
	
	 
	
	

}
