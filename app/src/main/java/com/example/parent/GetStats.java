package com.example.parent;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetStats extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://ehealthtest.000webhostapp.com/GetStats.php";
    private Map<String,String> params;

    public GetStats(String date, String childName, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("childName",childName);
        params.put("date",date);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
