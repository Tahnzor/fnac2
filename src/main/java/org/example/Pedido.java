package org.example;

public class Pedido {
    private int codigoCliente;
    private Producto[] productos;
    private double totalPedidos;

    public Pedido() {
    }

    public Pedido(int codigoCliente, Producto[] productos) {
        this.codigoCliente = codigoCliente;
        this.productos = productos;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }

    public double getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(double totalPedidos) {
        this.totalPedidos = totalPedidos;
    }
}
