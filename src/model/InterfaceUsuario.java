package model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.Scanner;

public class InterfaceUsuario {
    boolean ativador = true;
    Scanner scanner = new Scanner(System.in);
    public void menu() {
        while (ativador) {
            System.out.println("""
                ****************************************************
                $$ Damos as boas-vindas ao Conversor de Moedas $$
                                
                Escolha uma opção de conversão:

                1) Dólar (USD) → Peso Argentino (ARS)
                2) Peso Argentino (ARS) → Dólar (USD)
                3) Dólar (USD) → Real Brasileiro (BRL)
                4) Real Brasileiro (BRL) → Dólar (USD)
                5) Dólar (USD) → Peso Colombiano (COP)
                6) Peso Colombiano (COP) → Dólar (USD)
                7) Sair
                                
                ****************************************************
                """);
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    this.coletaValor("USD", "BRL");
                    break;

                case "2":
                    this.coletaValor("EUR", "BRL");
                    break;

                case "3":
                    this.coletaValor("ARS", "BRL");
                    break;

                case "4":
                    this.coletaValor("BRL", "USD");
                    break;

                case "5":
                    this.coletaValor("BRL", "EUR");
                    break;

                case "6":
                    this.coletaValor("BRL", "ARS");
                    break;

                default:
                    if (escolha.equals("7")) {
                        System.out.println("Saindo...");
                        ativador = false;
                    } else {
                        System.out.println("Opção Inválida! " +
                                "Selecione uma opção valida. ");
                    }
                    break;
            }
        }
    }
    public void coletaValor(String moeda1, String moeda2){
        System.out.println("Digite o valor a ser convertido: ");
        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();
            Conversor conversor = new Conversor(moeda1, moeda2, valor);

            ConexaoApi conexaoApi = new ConexaoApi(conversor);
            System.out.println(conexaoApi.ConsumoApi());

        } catch (Exception e){
            System.out.println("Valor digitado inválido. Tente novamente!");
        }
    }
}

