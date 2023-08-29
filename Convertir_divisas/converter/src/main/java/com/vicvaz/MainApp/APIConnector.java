package com.vicvaz.MainApp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class APIConnector {

    private static final String BASE_URL = "http://localhost:5000/rate/";

    public static double fetchCurrentRate(String fromCurrency, String toCurrency) {
        double rate = 0.0;

        // Creando una instancia de OkHttpClient
        OkHttpClient client = new OkHttpClient();

        // Construyendo la petición
        Request request = new Request.Builder()
            .url(BASE_URL + fromCurrency + "/" + toCurrency)
            .get()
            .build();

        // Ejecutando la petición y obteniendo la respuesta
        try {
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            JSONObject json = new JSONObject(jsonData);
            rate = json.getDouble("rate");
        } catch (Exception e) {
            System.err.println("Error al obtener las tasas de cambio: " + e.getMessage());
        }

        return rate;
    }

}
