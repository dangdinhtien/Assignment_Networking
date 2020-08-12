package com.example.dangdinhtien_ps10163_asm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dangdinhtien_ps10163_asm.Adapter.ProductAdapter;
import com.example.dangdinhtien_ps10163_asm.Callback.VolleyCallback;
import com.example.dangdinhtien_ps10163_asm.DAO.ProductDAO;
import com.example.dangdinhtien_ps10163_asm.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Baihat extends Fragment {
    ProductAdapter adapter;
    ArrayList<Product> list;
    ProductDAO spDao = new ProductDAO(getContext(),Fragment_Baihat.this);
    public String id;
    ListView lv;
    Product sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_baihat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("bài hát");
        lv = view.findViewById(R.id.lv);
        spDao = new ProductDAO(getContext(),Fragment_Baihat.this);
        capnhatLV();
    }

    public void xoaSp(String id){

        spDao.delete(id);
    }
    public void capnhatLV(){

        spDao.getAll(new VolleyCallback<List<Product>>() {
            @Override
            public void onReponse(List<Product> reponse) {
                list = (ArrayList<Product>)reponse;
                adapter = new ProductAdapter(getContext(), list,Fragment_Baihat.this);
                lv.setAdapter(adapter);
            }

            @Override
            public void onError(String err) {
                list = new ArrayList<Product>();
                adapter = new ProductAdapter(getContext(), list,Fragment_Baihat.this);
                lv.setAdapter(adapter);
            }
        });
    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_baihat,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.item_add:
//                intent = new Intent(getActivity(), AddProduct.class);
//                startActivity(intent);
                Add();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void Add(){
    View view = LayoutInflater.from(getContext()).inflate(R.layout.addproduct_layout,null);
    final TextView edtname = view.findViewById(R.id.edtname);
    final EditText edtsinger = view.findViewById(R.id.edtsinger);
    final EditText edtlink = view.findViewById(R.id.edtlink);
    final Button btnAddproduct = view.findViewById(R.id.Addproduct);
    final Button huy = view.findViewById(R.id.huy);

    btnAddproduct.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sp = new Product();
            sp.name=edtname.getText().toString();
            sp.singer=edtsinger.getText().toString();
            sp.link=edtlink.getText().toString();
            spDao.insert(sp);
        }
    });
    huy.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });
    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setView(view);
                    builder.show();
    }
}
