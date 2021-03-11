package com.utcn.dsrl;

public class Monomial{

    private double coef, exp;

    public Monomial(){
        this(1,0);
    }

    public Monomial(double coef, double exp){
        this.coef = coef;
        this.exp = exp;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }
}
