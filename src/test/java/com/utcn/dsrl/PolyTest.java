package com.utcn.dsrl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolyTest {

    @Test
    void add_mixedCoeffAndDuplicateExpPolynomials() {

        Polynomial a = new Polynomial("3x^2-6x+1+12x");
        Polynomial b = new Polynomial("5x^3+5x-10+2x^3");
        String result = "7x^3+3x^2+11x-9";

        assertEquals(result, Controller.add(a, b).toString());
    }

    @Test
    void subtract() {
        Polynomial a = new Polynomial("3x^2-6x+1+12x");
        Polynomial b = new Polynomial("5x^3+5x-10+2x^3");
        String result = "-7x^3+3x^2+x+11";

        assertEquals(result, Controller.subtract(a, b).toString());
    }

    @Test
    void modulo() {
        Polynomial a = new Polynomial("2x^3+3x^2-x+5");
        Polynomial b = new Polynomial("x^2-x+1");
        String result = "2x";

        assertEquals(result, Controller.modulo(a, b).toString());
    }

    @Test
    void divide() {
        Polynomial a = new Polynomial("2x^3+3x^2-x+5");
        Polynomial b = new Polynomial("x^2-x+1");
        String result = "2x+5";

        assertEquals(result, Controller.divide(a, b).toString());
    }

    @Test
    void modulo_divisionByZero() {
        Polynomial a = new Polynomial("2x^3+3x^2-x+5");
        Polynomial b = new Polynomial("0");

        assertNull(Controller.modulo(a, b));
    }

    @Test
    void divide_divisionByZero() {
        Polynomial a = new Polynomial("2x^3+3x^2-x+5");
        Polynomial b = new Polynomial("0");

        assertNull(Controller.divide(a, b));

    }

    @Test
    void multiply() {
        Polynomial a = new Polynomial("3x^5+12x-9");
        Polynomial b = new Polynomial("x^2-1");
        String result = "3x^7-3x^5+12x^3-9x^2-12x+9";

        assertEquals(result, Controller.multiply(a, b).toString());

    }

    @Test
    void multiply_multiplyWithZero() {
        Polynomial a = new Polynomial("3x^5+12x-9");
        Polynomial b = new Polynomial("0");
        String result = "0";

        assertEquals(result, Controller.multiply(a, b).toString());

    }

    @Test
    void differentiate() {
        Polynomial a = new Polynomial("3x^5+12x-9");
        String result = "15x^4+12";

        assertEquals(result, Controller.differentiate(a).toString());
    }

    @Test
    void integrate() {
        Polynomial a = new Polynomial("3x^5+12x-9");
        String result = "0.50x^6+6x^2-9x";

        assertEquals(result, Controller.integrate(a).toString());

    }

    @Test
    void isGoodInput() {
        String a = "3x^5+12x-9";
        String b = "3x^&5+12x-9";
        String c = "3x^5+12x--9";
        String d = "3^5+x-9";

        assertTrue(Controller.isGoodInput(a));
        assertFalse(Controller.isGoodInput(b));
        assertFalse(Controller.isGoodInput(c));
        assertFalse(Controller.isGoodInput(d));
    }
}