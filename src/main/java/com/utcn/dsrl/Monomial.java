package com.utcn.dsrl;

public class Monomial{

    private Integer coef, exp;

    public Monomial(Integer coef, Integer exp){
        this.coef = coef;
        this.exp = exp;
    }

    public Integer getCoef() {
        return coef;
    }

    public void setCoef(Integer coef) {
        this.coef = coef;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }
}
