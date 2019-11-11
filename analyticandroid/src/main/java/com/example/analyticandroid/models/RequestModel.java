package com.example.analyticandroid.models;

import com.example.analyticandroid.network.RequestPriority;

import org.json.JSONObject;

import java.util.Map;

public class RequestModel implements Comparable<RequestModel> {
    private String url;
    private JSONObject body;
    private Map<String, String> header;
    private RequestPriority priority;

    public RequestModel(String url, Map<String, String> header, JSONObject body, RequestPriority priority) {
        this.url = url;
        this.header = header;
        this.body = body;
        this.priority = priority;
    }

    public String getUrl() {
        return url;
    }

    public JSONObject getBody() {
        return body;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public RequestPriority getPriority() {
        return priority;
    }

    private int getPriorityValue(RequestPriority priority) {
        switch (priority) {
            case LOW:
                return 1;
            case MEDIUM:
                return 2;
            case HIGH:
                return 3;
            case IMMEDIATE:
                return 4;

        }
        return 2;
    }

    @Override
    public int compareTo(RequestModel requestModel) {
        int p1 = getPriorityValue(requestModel.priority);
        int p2 = getPriorityValue(this.priority);
        if(p1 == p2)
            return 0;
        else if(p1 > p2)
            return 1;
        else
            return -1;
    }
}
