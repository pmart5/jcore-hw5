package jcorehw53;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import myclass.Employee;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String readString(String fileNameJson) {
        String json = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileNameJson))) {
            json = br.readLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return json;
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(json);
            JSONArray jsonObject = (JSONArray) obj;
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            for (Object jsOb : jsonObject) {
                Employee employee = gson.fromJson(gson.toJson(jsOb), Employee.class);
                list.add(employee);
            }
        } catch (JsonSyntaxException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        String fileNameJson = "data.json";
        String json = readString(fileNameJson);
        List<Employee> list = jsonToList(json);
        list.forEach(System.out::println);
    }
}
