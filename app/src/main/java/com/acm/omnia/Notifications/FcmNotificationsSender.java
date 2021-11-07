package com.acm.omnia.Notifications;

import android.app.Activity;
import android.content.Context;

import com.acm.omnia.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FcmNotificationsSender {


    private final String Url = "https://fcm.googleapis.com/fcm/send";
    private final String ServerKey = "AAAA6pN8hLU:APA91bHo83DOiE_g8SdckfL1c36s8qsD-OBS89Ue9PD1l6Zhfvtb47p73ZL9vlXBdYJNzI2QI_3LuLJKvbSaBSJx1_zG2XmyGEA9REsgmFyrICSxiBG482vgFhqk0wG96Cm1amHM6nx5";
    String userFcmToken;
    String title;
    String body;
    Context context;
    Activity activity;
    private RequestQueue requestQueue;


    public FcmNotificationsSender(String userFcmToken, String title, String body, Context context, Activity activity) {
        this.userFcmToken = userFcmToken;
        this.title = title;
        this.body = body;
        this.context = context;
        this.activity = activity;
    }

    public void SendNotifications() {
        requestQueue = Volley.newRequestQueue(activity);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("to", userFcmToken);
            JSONObject notiObject = new JSONObject();
            notiObject.put("title", title);
            notiObject.put("body", body);
            notiObject.put("icon", R.drawable.acm_logo_white); // enter icon that exists in drawable only


            jsonObject.put("notification", notiObject);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    // code run is got response

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // code run is got error

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {


                    Map<String, String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=" + ServerKey);
                    return header;


                }
            };
            requestQueue.add(request);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
