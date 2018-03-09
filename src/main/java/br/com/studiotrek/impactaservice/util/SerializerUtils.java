package br.com.studiotrek.impactaservice.util;

import com.google.gson.Gson;

public class SerializerUtils {

    private static Gson gson = new Gson();

    public static <T> T JsonStringToObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
