package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            try (Reader reader = new FileReader("src/main/java/org/example/pedido.json")) {
                Pedido pedido = gson.fromJson(reader, Pedido.class);

                Map<String, Producto> productosInfo = XmlProcessor.obtenerInformacionProductosDesdeXML("src/main/java/org/example/fnac.xml");

                for (Producto producto : pedido.getProductos()) {
                    String codigoProducto = producto.getCodigo();

                    if (productosInfo.containsKey(codigoProducto)) {
                        Producto nuevaInfo = productosInfo.get(codigoProducto);

                        producto.setNombre(nuevaInfo.getNombre());
                        producto.setPrecio(nuevaInfo.getPrecio());
                        producto.setImporte(nuevaInfo.getPrecio() * producto.getCantidad());
                    }
                }

                double totalPedidos = calcularTotalPedidos(pedido);
                System.out.println("Total de Pedidos: " + totalPedidos);

                try (FileWriter writer = new FileWriter("pedido_modificado.json")) {
                    gson.toJson(pedido, writer);
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double calcularTotalPedidos(Pedido pedido) {
        double totalPedidos = 0.0;

        for (Producto producto : pedido.getProductos()) {
            totalPedidos += producto.getImporte();
        }

        return totalPedidos;
    }
}
