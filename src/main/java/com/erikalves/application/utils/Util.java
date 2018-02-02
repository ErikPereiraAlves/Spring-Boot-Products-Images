package com.erikalves.application.utils;

import com.erikalves.application.exceptions.ApplicationException;
import com.erikalves.application.model.Image;
import com.erikalves.application.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Util {

    private static final Type IMAGE_COLLECTION_TYPE_TOKEN = new TypeToken<Collection<Image>>() {
    }.getType();

    private static final Type PRODUCT_COLLECTION_TYPE_TOKEN = new TypeToken<Collection<Product>>() {
    }.getType();

    private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

    private static final Gson GSON = new Gson();


    public static boolean isJSONValid(String jsonInString) {

        try {
            GSON.fromJson(jsonInString, Object.class);
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

    public static String readJsonFromRequest(HttpServletRequest request) throws ApplicationException {

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            LOGGER.error("Unable to read Json from Request!", e);
            throw new ApplicationException("Error while parsing json from request, please check format. Error Message:" + e.getMessage());

        }
    }

    public static Collection<Image> deserializeImageCollection( String jsonAsString) throws ApplicationException {

        return deserialize(IMAGE_COLLECTION_TYPE_TOKEN, jsonAsString);
    }

    public static Collection<Product> deserializeProductCollection(String jsonAsString) throws ApplicationException {

        return deserialize(PRODUCT_COLLECTION_TYPE_TOKEN, jsonAsString);
    }


    public static Collection deserialize(Type type, String jsonAsString) throws ApplicationException {

        if (StringUtils.isNotBlank(jsonAsString)) {
            String trimmedJsonLogs = jsonAsString.trim();
            try {
                if(type == IMAGE_COLLECTION_TYPE_TOKEN) {
                    return GSON.fromJson(trimmedJsonLogs, IMAGE_COLLECTION_TYPE_TOKEN);
                }
                else if(type == PRODUCT_COLLECTION_TYPE_TOKEN) {
                    return GSON.fromJson(trimmedJsonLogs, PRODUCT_COLLECTION_TYPE_TOKEN);
                }
            } catch (Exception e) {
                LOGGER.error("Error while parsing [{}]", trimmedJsonLogs, e);
                throw new ApplicationException("Error while parsing json: " + trimmedJsonLogs + " Error Message:" + e.getMessage());
            }
        }
        return Collections.emptyList();
    }



    public static java.sql.Date getCurrentDate(){

        return new java.sql.Date(System.currentTimeMillis());
    }
    public static java.sql.Timestamp getCurrentTs(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static String StringfyId(Long id){

        return Long.toString(id);
    }

    public static Long LongfyId(String id){

        return Long.getLong(id);
    }
}
