package com.example.voicetuner;

public class Complex {
    private final double real, im;

    public Complex(double real, double im) {
        this.real = real;
        this.im = im;
    }

    public double getReal() {return real;}

    public double getIm() {return im;}

    public Complex plus(Complex c) {
        return new Complex(this.getReal() + c.getReal(), this.getIm() + c.getIm());
    }

    public Complex minus(Complex c) {
        return new Complex(this.getReal() - c.getReal(), this.getIm() - c.getIm());
    }

    public double abs() {
        return Math.hypot(getReal(), getIm());
    }

    public Complex times(Complex c) {
        double real = this.getReal() * c.getReal() - this.getIm() * c.getIm();
        double im = this.getReal() * c.getIm() + this.getIm() * c.getReal();
        return new Complex(real, im);
    }

}
