package com.example.analyticandroid;

import com.example.analyticandroid.models.RequestModel;
import com.example.analyticandroid.network.RequestPriority;

import org.json.JSONObject;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by BashirAltereh on 10/29/2019.
 */

public class Tasks {
    public static PriorityQueue<RequestModel> requestQueue = new PriorityQueue<>();

    public static void addRequestToQueue(String url, Map<String, String> header, JSONObject body, RequestPriority priority) {
        requestQueue.add(new RequestModel(url, header, body, priority));
    }

    public static RequestModel getRequestFromQueue() {
        return requestQueue.peek();
    }

    public static void removeRequestFromQueue() {
        requestQueue.remove();
    }

    public static boolean isEmpty(){
        return requestQueue.isEmpty();
    }

    public static void saveQueueInSharedPreferences() {
         //todo: save queue in sharedPreference
    }

}
