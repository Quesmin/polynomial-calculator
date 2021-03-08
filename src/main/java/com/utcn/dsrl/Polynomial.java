package com.utcn.dsrl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

public class Polynomial {

    private ArrayList<Monomial> polynomial;

    public Polynomial() {
        this.polynomial = new ArrayList<Monomial>();
    }

    public Polynomial(ArrayList<Monomial> newPol) {
        this.polynomial = newPol;
    }

    public Polynomial(Monomial m) {
        this.polynomial = new ArrayList<Monomial>();
        this.polynomial.add(m);
    }

    public Polynomial(Polynomial that) {
        this.polynomial = new ArrayList<Monomial>();

        for (Monomial m : that.getPolynomial()) {
            this.polynomial.add(new Monomial(m.getCoef(), m.getExp()));
        }
    }

    public Polynomial(String input) {
        input = input.replace("-", "+-");
        String[] inputArray = input.split("\\+");
        this.polynomial = new ArrayList<Monomial>();

        for (String mon : inputArray) {
            Integer coef, exp = 0;

            if(mon.contains("^")){
                exp = Integer.parseInt(mon.substring(mon.lastIndexOf("^") + 1));
            } else if(mon.contains("x")){
                exp = 1;
            }

            if(mon.contains("x")){
                if(mon.substring(0, 2).equals("-x")){
                    coef = -1;
                } else if (mon.charAt(0) == 'x'){
                    coef = 1;
                }else {
                    coef = Integer.parseInt(mon.substring(0, mon.indexOf("x")));
                }
            } else {
                coef = Integer.parseInt(mon);
            }

            this.polynomial.add(new Monomial(coef, exp));
        }
    }

    public ArrayList<Monomial> getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(ArrayList<Monomial> polynomial) {
        this.polynomial = polynomial;
    }

    public void addToPolynomial(Monomial newMon) {
        boolean found = false;

        for (Monomial m : this.polynomial) {
            if (m.getExp().equals(newMon.getExp())) {
                found = true;
                m.setCoef(m.getCoef() + newMon.getCoef());
            }
        }

        if (!found) {
            this.polynomial.add(newMon);
        }
    }

    public Monomial getDegree() {
        Monomial maxDegMon = new Monomial(-1, -1);


        for (Monomial m : this.polynomial) {
            if (m.getExp() > maxDegMon.getExp()) {
                maxDegMon = new Monomial(m.getCoef(), m.getExp());
            }
        }

        return maxDegMon;
    }

    @Override
    public String toString() {

        String output = "";
        this.polynomial.sort(Comparator.comparing(Monomial::getExp));
        ListIterator<Monomial> li = this.polynomial.listIterator(this.polynomial.size());

        Monomial mon = li.hasPrevious() ? li.previous() : null;
        while (mon != null) {

            if (mon.getCoef() > 0) {
                output += "+";
            }
            output += mon.getCoef();

            if((mon.getCoef() == 1 || mon.getCoef() == -1) && mon.getExp() > 0){
                output = output.substring(0, output.length() -1);
            }

            if (mon.getExp() > 0) {
                output += "x";
                if (mon.getExp() != 1) {
                    output += "^" + mon.getExp();
                }
            }
            mon = li.hasPrevious() ? li.previous() : null;
        }

        return output.charAt(0) == '+' ? output.substring(1) : output;

    }
}
