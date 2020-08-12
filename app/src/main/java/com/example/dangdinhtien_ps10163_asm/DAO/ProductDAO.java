package com.example.dangdinhtien_ps10163_asm.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dangdinhtien_ps10163_asm.Callback.VolleyCallback;
import com.example.dangdinhtien_ps10163_asm.Fragment_Baihat;
import com.example.dangdinhtien_ps10163_asm.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO {
    Context context;
    Fragment_Baihat fragment_baihat;
//    String insertUrl = "http://10.82.65.248/assignment/addproduct.php";
//    String getAllUrl = "http://10.82.65.248/assignment/getproduct.php";
//    String updateUrl = "http://10.82.65.248/assignment/updateproduct.php";
//    String deleteUrl = "http://10.82.65.248/assignment/deleteproduct.php";
//    String getSingerUrl = "http://10.82.65.248/assignment/getSinger.php";
//    String getProductSingerUrl = "http://10.82.65.248/assignment/getProductSinger.php";
    String deleteUrl = "http://10.82.70.50/assignment/deleteproduct.php";
    String updateUrl = "http://10.82.70.50/assignment/updateproduct.php";
    String insertUrl = "http://10.82.70.50/assignment/addproduct.php";
    String getAllUrl = "http://10.82.70.50/assignment/getproduct.php";
    String getSingerUrl = "http://10.82.70.50/assignment/getSinger.php";
    String getProductSingerUrl = "http://192.168.1.5/assignment/getProductSinger.php";

    List<Product> list = new ArrayList<Product>();
    String thanhcong="";
    VolleyCallback volleyCallback;
    public ProductDAO(Context context , Fragment_Baihat fragment_baihat){
        this.context = context;
        this.fragment_baihat = fragment_baihat;
    }
    public void  insert(final Product sp) {
        StringRequest stringrequest = new StringRequest(
                Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String thanhcong ="0";
                try {
                    JSONObject jsonobject=new JSONObject(response);
                    thanhcong=jsonobject.getString("thanhcong");
                    Log.d("test",thanhcong);
                    //doc tat ca du lieu tu json bo vao ArrayList

                    if(Integer.parseInt(thanhcong)==1)//thanh cong
                    {

                        Toast.makeText(context, "Add Done", Toast.LENGTH_SHORT).show();
                        //fragment_baihat.capnhatLV();

                    }
                    else //that bai
                    {

                        Toast.makeText(context, "Add Fail", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi",error.toString());

            }
        }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<String,String>();

                param.put("name",sp.name);
                param.put("singer",sp.singer);
                param.put("link",sp.link);


                return param;
            }
        };
        if(context != null){
        Volley.newRequestQueue(context).add(stringrequest);
        }
    }
    public void getAll(final VolleyCallback<List<Product>> volleyCallback){

        StringRequest stringrequest = new StringRequest(getAllUrl
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonobject=new JSONObject(response);
                    thanhcong=jsonobject.getString("thanhcong");
                    //doc tat ca du lieu tu json bo vao ArrayList
                    if(Integer.parseInt(thanhcong)==1)//thanh cong
                    {
                        Toast.makeText(context, "getAll Done", Toast.LENGTH_SHORT).show();

                        JSONArray sps=jsonobject.getJSONArray("sanpham");


                        for(int i=0;i<sps.length();i++) {
                            JSONObject spsJSONObject = sps.getJSONObject(i);

                            Product sp = new Product();
                            sp.id = spsJSONObject.getString("id");
                            sp.name = spsJSONObject.getString("name");
                            sp.singer = spsJSONObject.getString("singer");
                            sp.link = spsJSONObject.getString("link");
                            list.add(sp);
                        }
                        volleyCallback.onReponse(list);

                    }
                    else //that bai
                    {
                        Toast.makeText(context, "getAll Fail", Toast.LENGTH_SHORT).show();
                        volleyCallback.onError("Fail");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    volleyCallback.onError("Fail");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi",error.toString());
                volleyCallback.onError("Fail");
            }
        }
        ) ;
        if(context!=null) {
            Volley.newRequestQueue(context).add(stringrequest);
        }
    }

    public void update(final Product sp) {

        StringRequest stringrequest = new StringRequest(
                Request.Method.POST, updateUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonobject=new JSONObject(response);
                    String thanhcong=jsonobject.getString("thanhcong");

                    //doc tat ca du lieu tu json bo vao ArrayList
                    if(Integer.parseInt(thanhcong)==1)//thanh cong
                    {
                        Toast.makeText(context, "Update Done", Toast.LENGTH_SHORT).show();
//                        ((MainActivity)context).capnhatLV();

                    }
                    else //that bai
                    {
                        Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi",error.toString());

            }
        }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<String,String>();

                param.put("id",sp.id);
                param.put("name",sp.name);
                param.put("singer",sp.singer);
                param.put("link",sp.link);


                return param;
            }
        };
        Volley.newRequestQueue(context).add(stringrequest);

    }

    public void delete(final String id) {
        StringRequest stringrequest = new StringRequest(
                Request.Method.POST, deleteUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonobject=new JSONObject(response);
                    String thanhcong=jsonobject.getString("thanhcong");
                    //doc tat ca du lieu tu json bo vao ArrayList

                    if(Integer.parseInt(thanhcong)==1)//thanh cong
                    {

                        Toast.makeText(context, "Delete Done", Toast.LENGTH_SHORT).show();
                        fragment_baihat.capnhatLV();
                    }
                    else //that bai
                    {
                        Toast.makeText(context, "Delete Fail", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi",error.toString());

            }
        }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<String,String>();

                param.put("id",id);


                return param;
            }
        };
        if(context!=null) {
            Volley.newRequestQueue(context).add(stringrequest);
        }

    }
    public void getSinger(final VolleyCallback<List<Product>> volleyCallback){
        StringRequest stringrequest = new StringRequest(getSingerUrl
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonobject=new JSONObject(response);
                    thanhcong=jsonobject.getString("thanhcong");
                    //doc tat ca du lieu tu json bo vao ArrayList
                    if(Integer.parseInt(thanhcong)==1)//thanh cong
                    {
                        Toast.makeText(context, "getAll Done", Toast.LENGTH_SHORT).show();

                        JSONArray sps=jsonobject.getJSONArray("sanpham");


                        for(int i=0;i<sps.length();i++) {
                            JSONObject spsJSONObject = sps.getJSONObject(i);

                            Product sp = new Product();
                            sp.id = spsJSONObject.getString("id");
                            sp.name = spsJSONObject.getString("name");
                            sp.singer = spsJSONObject.getString("singer");
                            sp.link = spsJSONObject.getString("link");
                            list.add(sp);
                        }
                        volleyCallback.onReponse(list);

                    }
                    else //that bai
                    {
                        Toast.makeText(context, "getAll Fail", Toast.LENGTH_SHORT).show();
                        volleyCallback.onError("Fail");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    volleyCallback.onError("Fail");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Volley error", Toast.LENGTH_SHORT).show();
                Log.d("loi",error.toString());
                volleyCallback.onError("Fail");
            }
        }
        ) ;
        Volley.newRequestQueue(context).add(stringrequest);
    }
}
