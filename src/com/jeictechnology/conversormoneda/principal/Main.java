package com.jeictechnology.conversormoneda.principal;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jeictechnology.conversormoneda.modelos.Moneda;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        HashMap<String, Moneda> monedas = new HashMap<>();

        monedas.put("Estados Unidos", new Moneda("Estados Unidos", "USD"));
        monedas.put("Canadá", new Moneda("Canadá", "CAD"));
        monedas.put("México", new Moneda("México", "MXN"));
        monedas.put("Reino Unido", new Moneda("Reino Unido", "GBP"));
        monedas.put("Japón", new Moneda("Japón", "JPY"));
        monedas.put("China", new Moneda("China", "CNY"));
        monedas.put("Colombia", new Moneda("Colombia", "COP"));
        monedas.put("Brasil", new Moneda("Brasil", "BRL"));
        monedas.put("Argentina", new Moneda("Argentina", "ARS"));
        monedas.put("España", new Moneda("España", "EUR"));

        List<String> lista = new ArrayList<>(monedas.keySet());

        System.out.println("*********************************************************************************************");
        System.out.println("Bienvenid@, conversor de moneda, por favor selecciona el número de la moneda que desea convertir"+"\n");

        final int[] contador = {1};
        monedas.forEach((pais, mon) -> {
            System.out.println(contador[0] + " "+pais + " "+mon.getCodigo());
            contador[0]++;
        });

        int opcion = scanner.nextInt();

        System.out.println("-----------------------------------------------");
        System.out.println("Ingresa la cantidad a convertir: "+"\n");

        double cantidadAConvertir = scanner.nextDouble();

        System.out.println("-----------------------------------------------");
        System.out.println("Selecciona la moneda de conversión"+"\n");

        final int[] contadorConvertidor = {1};
        monedas.forEach((pais, mon) -> {
            System.out.println(contadorConvertidor[0] + " "+pais + " "+mon.getCodigo());
            contadorConvertidor[0]++;
        });

        int opcionConvertir = scanner.nextInt();

        String paisSeleccionado = lista.get(opcion - 1);
        String paisSeleccionadoAConvertir = lista.get(opcionConvertir - 1);

        String url = "https://v6.exchangerate-api.com/v6/";
        String apiKey = "ad70ff102a291a8321ed3820";
        String complemento = "/latest/"+monedas.get(paisSeleccionado).getCodigo();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url+apiKey+complemento)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        String json = httpResponse.body();

        //System.out.println(json);

        Gson gson = new Gson();
        JsonElement jsonElement = JsonParser.parseString(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

        List<String> listaCodigos = new ArrayList<>();
        conversionRates.keySet().forEach(listaCodigos::add);

        //System.out.println(lista);
        //System.out.println(monedas.get(paisSeleccionado).getCodigo());
        //System.out.println(monedas.get(paisSeleccionadoAConvertir).getCodigo());
        //System.out.println(listaCodigos);
        //System.out.println(conversionRates);
        System.out.println(listaCodigos.contains(monedas.get(paisSeleccionadoAConvertir).getCodigo()));
        System.out.println(conversionRates.get(monedas.get(paisSeleccionadoAConvertir).getCodigo()).getAsDouble());
        System.out.println((conversionRates.get(monedas.get(paisSeleccionadoAConvertir).getCodigo()).getAsDouble())*cantidadAConvertir);

        switch (opcion){
            case 1:
                if (listaCodigos.contains(monedas.get(paisSeleccionadoAConvertir).getCodigo())){
                    double resultado = conversionRates.get(monedas.get(paisSeleccionadoAConvertir).getCodigo()).getAsDouble();
                    System.out.println("Actualmente " + cantidadAConvertir+ " "+
                            monedas.get(paisSeleccionado).getCodigo()+ " Equivalen a: "+
                            cantidadAConvertir*resultado+" "+monedas.get(paisSeleccionadoAConvertir).getCodigo());
                break;
                }

        }



    }
}