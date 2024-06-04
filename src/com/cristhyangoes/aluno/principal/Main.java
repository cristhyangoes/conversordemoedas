package com.cristhyangoes.aluno.principal;

import com.cristhyangoes.aluno.moedas.ConversionRates;
import com.cristhyangoes.aluno.moedas.Moedas;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);

        String moeda = "";

        double valor = 0;

        Gson gson = new Gson();

        int opcao = 0;
        while(opcao == 0 || opcao != 7) {
            System.out.println("***********************************************************");
            System.out.println("Seja bem-vindo(a) ao Conversor de Moedas :)");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileiro");
            System.out.println("4) Real brasileiro =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Sair");
            System.out.println("Escolha uma opção válida:");
            System.out.println("***********************************************************");
            opcao = leitura.nextInt();
            boolean fazerCalculo = false;
            boolean dolarPesoArgentino = false;
            boolean pesoArgentinoDolar = false;
            boolean dolarRealBrasileiro = false;
            boolean realBrasileiroDolar = false;
            boolean dolarPesoColombiano = false;
            boolean pesoColombianoDolar = false;
            if(opcao == 1) {
                moeda = "USD";
                fazerCalculo = true;
                dolarPesoArgentino = true;
            }else if(opcao == 2) {
                moeda = "ARS";
                fazerCalculo = true;
                pesoArgentinoDolar = true;
            }else if(opcao == 3) {
                moeda = "USD";
                fazerCalculo = true;
                dolarRealBrasileiro = true;
            }else if(opcao == 4) {
                moeda = "BRL";
                fazerCalculo = true;
                realBrasileiroDolar = true;
            }else if(opcao == 5) {
                moeda = "USD";
                fazerCalculo = true;
                dolarPesoColombiano = true;
            }else if(opcao == 6) {
                moeda = "COP";
                fazerCalculo = true;
                pesoColombianoDolar = true;
            }else if(opcao == 7) {
                System.out.println("Saindo...");
                break;
            }else{
                System.out.println("Opção inválida");
                break;
            }

            if(fazerCalculo){


                String uri = "https://v6.exchangerate-api.com/v6/44a09254d3fad217bd8b9e2e/latest/"+moeda;

                HttpClient cliente = HttpClient.newHttpClient();
                HttpRequest requisicao = HttpRequest.newBuilder().uri(URI.create(uri)).build();
                HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

                String json = resposta.body();
                Map<String, Object> jsonRates = gson.fromJson(json, Map.class);
                Map<String, Object> conversion_rates = (Map<String, Object>) jsonRates.get("conversion_rates");
                ConversionRates rates = new ConversionRates((double) conversion_rates.get("USD"),
                        (double) conversion_rates.get("ARS"), (double) conversion_rates.get("BRL"),
                        (double) conversion_rates.get("COP"));
                Moedas moedas = new Moedas(rates);

                System.out.println("Digite o valor a ser convertido: ");
                valor = leitura.nextDouble();

                    if(dolarPesoArgentino){
                        System.out.println("Valor: "+valor+ " [USD] corresponde ao valor final de =>>> "+(valor*moedas.getARS())+" [ARS]");
                        opcao = 0;
                    } else if (pesoArgentinoDolar) {
                        System.out.println("Valor: "+valor+ " [ARS] corresponde ao valor final de =>>> "+(valor*moedas.getUSD())+" [USD]");
                        opcao = 0;
                    } else if (dolarRealBrasileiro) {
                        System.out.println("Valor: "+valor+ " [USD] corresponde ao valor final de =>>> "+(valor*moedas.getBRL())+" [BRL]");
                        opcao = 0;
                    } else if (realBrasileiroDolar) {
                        System.out.println("Valor: "+valor+ " [BRL] corresponde ao valor final de =>>> "+(valor*moedas.getUSD())+" [USD]");
                        opcao = 0;
                    } else if (dolarPesoColombiano) {
                        System.out.println("Valor: "+valor+ " [USD] corresponde ao valor final de =>>> "+(valor*moedas.getCOP())+" [COP]");
                        opcao = 0;
                    } else if (pesoColombianoDolar) {
                        System.out.println("Valor: "+valor+ " [COP] corresponde ao valor final de =>>> "+(valor*moedas.getUSD())+" [USD]");
                        opcao = 0;
                    }

            }


        }


    }
}