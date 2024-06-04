package com.cristhyangoes.aluno.moedas;

import com.google.gson.Gson;

public class Moedas {

    private double USD;
    private double ARS;
    private double BRL;
    private double COP;

    public Moedas(ConversionRates conversionRates){
        this.USD = conversionRates.USD();
        this.ARS = conversionRates.ARS();
        this.BRL = conversionRates.BRL();
        this.COP = conversionRates.COP();

    }

    public double getARS() {
        return ARS;
    }

    public double getUSD() {
        return USD;
    }

    public double getBRL() {
        return BRL;
    }

    public double getCOP() {
        return COP;
    }

    public String toString(){
        return "USD: "+USD+" ARS: "+ARS+" BRL: "+BRL+" COP: "+COP;
    }
}
