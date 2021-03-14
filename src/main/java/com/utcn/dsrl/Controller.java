package com.utcn.dsrl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller extends Application {
    private static Scene scene;
    public static Polynomial pol1;
    public static Polynomial pol2;
    public static Polynomial result;


    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.setTitle("PolyCalc");
        stage.show();

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args){

        launch();
//        Scanner sc = new Scanner(System.in);
//        Polynomial a, b;
//        String in = sc.nextLine();
//        a = new Polynomial(in);
//        in = sc.nextLine();
//        b = new Polynomial(in);

//        while(true){
//            String in = sc.nextLine();
//            if(isGoodInput(in)){
//                a = new Polynomial(in);
//                System.out.println(a.toString());
//            } else {
//                System.out.println("nu i bun teso");
//            }
//
//        }
//        System.out.println(b.toString());

//        Polynomial res = divide(a,b);
//        System.out.println(res.toString());
//        res = modulo(a,b);
//        System.out.println(res.toString());


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

    static Polynomial divideOperation(Polynomial a, Polynomial b, int mode){

        Polynomial q = new Polynomial(new Monomial(0,0));
        Polynomial r = new Polynomial(a);
        if(b == null || b.getPolynomial().isEmpty())
            return null;
        Polynomial d = new Polynomial(b);

        while(!r.getPolynomial().isEmpty() && r.getDegree().getExp() >= d.getDegree().getExp()){

            double divCoef = r.getDegree().getCoef()/d.getDegree().getCoef();
            double divExp = r.getDegree().getExp()-d.getDegree().getExp();
            Monomial qMon = new Monomial(divCoef, divExp);
            q.addToPolynomial(qMon);
            Polynomial sub = new Polynomial(multiply(new Polynomial(qMon), d));
            r.setPolynomial(subtract(r, sub).getPolynomial());
        }

        System.out.println(q.toString() + " " + r.toString());

        if(mode == 0){
            return q;
        }else {
            return r;
        }

    }

    public static Polynomial modulo(Polynomial a, Polynomial b) {
        return divideOperation(a,b,1);
    }

    public static Polynomial divide(Polynomial a, Polynomial b) {
        return divideOperation(a,b,0);
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

    static boolean isGoodInput(String in) {
        return in.matches("([+-]?(?:(?:\\d+x)|(?:\\d+)|(?:x)|(?:\\d+x\\^\\d+)|(?:x\\^\\d+)))+");
    }
}
