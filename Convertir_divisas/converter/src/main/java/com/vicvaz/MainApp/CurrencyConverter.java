package com.vicvaz.MainApp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class CurrencyConverter {

    private static final String API_URL = "http://api.exchangeratesapi.io/v1/latest?access_key=151c2d2fb922297569a97ee761775410";

    public double convert(String from, String to, double amount) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(API_URL).build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            JSONObject json = new JSONObject(jsonData);
            double fromRate = json.getJSONObject("rates").getDouble(from);
            double toRate = json.getJSONObject("rates").getDouble(to);
            return (amount / fromRate) * toRate;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch rates", e);
        }
    }
}
