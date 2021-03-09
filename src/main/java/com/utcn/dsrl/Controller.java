package com.utcn.dsrl;

import java.util.Scanner;

public class Controller {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        Polynomial a, b;
        String in = sc.nextLine();
        a = new Polynomial(in);
//        in = sc.nextLine();
//        b = new Polynomial(in);

        System.out.println(a.toString());
//        System.out.println(b.toString());

        Polynomial res = integrate(a);
        System.out.println(res.toString());


    }

    static Polynomial add(Polynomial a, Polynomial b){
        Polynomial result = new Polynomial();

        for (Monomial m : a.getPolynomial()){
            result.addToPolynomial(new Monomial(m.getCoef(), m.getExp()));
        }

        for (Monomial m : b.getPolynomial()){
            result.addToPolynomial(new Monomial(m.getCoef(), m.getExp()));
        }

        return result;

    }

    static Polynomial subtract(Polynomial a, Polynomial b){

        Polynomial result = new Polynomial();

        for (Monomial m : a.getPolynomial()){
            result.addToPolynomial(new Monomial(m.getCoef(), m.getExp()));
        }

        for (Monomial m : b.getPolynomial()){
            result.addToPolynomial(new Monomial(-(m.getCoef()), m.getExp()));
        }

        return result;

    }

    static Polynomial divide(Polynomial a, Polynomial b){

        Polynomial q = new Polynomial();
        Polynomial r = new Polynomial(a);
        Polynomial d = new Polynomial(b);

        while(!r.getPolynomial().isEmpty() && r.getDegree().getExp() >= d.getDegree().getExp()){

            double divCoef = r.getDegree().getCoef()/d.getDegree().getCoef();
            double divExp = r.getDegree().getExp()-d.getDegree().getExp();
            Monomial qMon = new Monomial(divCoef, divExp);
            q.addToPolynomial(qMon);
            Polynomial sub = new Polynomial(multiply(new Polynomial(qMon), d));
            r.setPolynomial(subtract(r, sub).getPolynomial());
        }

        return q;
    }

    static Polynomial multiply(Polynomial a, Polynomial b) {

        Polynomial result = new Polynomial();

        for(Monomial aMon : a.getPolynomial()){
            for(Monomial bMon : b.getPolynomial()){
                Monomial resMon = new Monomial(aMon.getCoef() * bMon.getCoef(), aMon.getExp() + bMon.getExp());
                result.addToPolynomial(resMon);
            }
        }

        return result;
    }

    static Polynomial differentiate(Polynomial a){
        Polynomial result = new Polynomial();

        for(Monomial m : a.getPolynomial()){
            if(m.getExp() != 0){
                result.addToPolynomial(new Monomial(m.getCoef() * m.getExp(), m.getExp() - 1));
            }
        }

        return result;
    }

    static Polynomial integrate(Polynomial a){
        Polynomial result = new Polynomial();

        System.out.println(a.toFormatedStrting());
        for(Monomial m : a.getPolynomial()){
            double newCoef = m.getCoef() / (m.getExp() + 1);

            result.addToPolynomial(new Monomial(newCoef == 0 ? 1 : newCoef, m.getExp() + 1));
        }

        return result;
    }
}
