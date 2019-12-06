package simple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class makepost {
    public makepost(String s, String s1, String conveyorA) {
    }

    public static String main(String time, String t_time, String conveyor_name ) throws Exception {
        //String payload = "data={" +
        //        "\"Conveyor\": + conveyor_name +, " +
        //        "\"time\": t_time, " +
        //        "\"exit_time\": time" +
        //        "}";
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("conveyor_name", conveyor_name);
        payload.put("read_time", time);
        payload.put("read_value", t_time);
        String json = new ObjectMapper().writeValueAsString(payload);

        HttpClient httpClient = HttpClientBuilder.create().build();
        System.out.println("starting HTTP request");
        //HttpPost request = new HttpPost("http://localhost:8080/pmapi/conveyor_list");
        //request.setEntity(entity);
        String path = "http://localhost:8080/pmapi/conveyor_list/?param=${conveyor_name},${read_time},${read_value}";
        String formated_path = StrSubstitutor.replace(path, payload);
        HttpGet request = new HttpGet(formated_path);

        HttpResponse response = httpClient.execute(request);

        System.out.println(response.getStatusLine().getStatusCode());
        //System.out.println(EntityUtils.toString(response.getEntity()));
        String request_response = EntityUtils.toString(response.getEntity());
        return request_response;

    }
}
