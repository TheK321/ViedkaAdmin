package com.example.viedkaadmin;

public class Contable {

    private String
            fecha,
            concepto,
            monto,
            total;

    public Contable(String f, String c, String g, String m, String t){
        fecha = f;
        concepto = c;
        monto = m;
        total = t;
    }

    public String getFecha() {
        return fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
