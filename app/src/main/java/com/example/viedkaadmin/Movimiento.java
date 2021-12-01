package com.example.viedkaadmin;

public class Movimiento {

    private int idMovimiento;
    private String
            Concepto,
            Categoria,
            PrecioUni,
            Cantidad,
            Total,
            Tipo,
            SaldoAnterior,
            SaldoActual,
            Fecha,
            idEmpleado,
            idPrenda;

    public Movimiento(int idMovimiento, String concepto, String categoria, String precioUni, String cantidad, String total, String tipo, String saldoAnterior, String saldoActual, String fecha, String idEmpleado, String idPrenda) {
        this.idMovimiento = idMovimiento;
        Concepto = concepto;
        Categoria = categoria;
        PrecioUni = precioUni;
        Cantidad = cantidad;
        Total = total;
        Tipo = tipo;
        SaldoAnterior = saldoAnterior;
        SaldoActual = saldoActual;
        Fecha = fecha;
        this.idEmpleado = idEmpleado;
        this.idPrenda = idPrenda;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getConcepto() {
        return Concepto;
    }

    public void setConcepto(String concepto) {
        Concepto = concepto;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getPrecioUni() {
        return PrecioUni;
    }

    public void setPrecioUni(String precioUni) {
        PrecioUni = precioUni;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getSaldoAnterior() {
        return SaldoAnterior;
    }

    public void setSaldoAnterior(String saldoAnterior) {
        SaldoAnterior = saldoAnterior;
    }

    public String getSaldoActual() {
        return SaldoActual;
    }

    public void setSaldoActual(String saldoActual) {
        SaldoActual = saldoActual;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(String idPrenda) {
        this.idPrenda = idPrenda;
    }
}
