package com.example.dangdinhtien_ps10163_asm.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.dangdinhtien_ps10163_asm.DAO.ProductDAO;
import com.example.dangdinhtien_ps10163_asm.Fragment_Baihat;
import com.example.dangdinhtien_ps10163_asm.R;
import com.example.dangdinhtien_ps10163_asm.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Product> products;
    Fragment_Baihat fragment_baihat;

    TextView tvname, tvsinger, tvlink, edtname, edtsinger, edtlink;
    Button btnxoa, btnupdate, btnxacnhan, btnhuy;
    ProductDAO productDAO;

    public ProductAdapter(@NonNull Context context, ArrayList<Product> product , Fragment_Baihat fragment_baihat){
        super(context, 0,product);
        this.context = context;
        this.products = product;
        this.fragment_baihat = fragment_baihat;
    }
    @Override
    public View getView(final int position, final View convertview, ViewGroup parent) {
        View v = convertview;

        if (v == null){
            v = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        }
        final Product product = products.get(position);
        if (product != null){
        //anh xa
            tvname = v.findViewById(R.id.tvname);
            tvsinger = v.findViewById(R.id.tvsinger);
            tvlink = v.findViewById(R.id.tvlink);
            btnxoa = v.findViewById(R.id.btnxoa);
            btnupdate = v.findViewById(R.id.btnupdate);

            //set data len layout
            tvname.setText(product.name);
            tvsinger.setText(product.singer);
            tvlink.setText(product.link);
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "bạn vừa chọn"+ position, Toast.LENGTH_SHORT).show();
//                ((AddProduct)context).edtname.setText(product.name);
//                ((AddProduct)context).edtauthor.setText(product.author);
//                ((AddProduct)context).edtlink.setText(product.link);
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Truy cap den bien nao do trong MainActivity ((MainActivity)context)
                fragment_baihat.xoaSp(product.id);

            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "update thành công", Toast.LENGTH_SHORT).show();

                 view = LayoutInflater.from(getContext()).inflate(R.layout.layout_update,null);
                edtname = view.findViewById(R.id.edtname);
                edtsinger = view.findViewById(R.id.edtsinger);
                edtlink = view.findViewById(R.id.edtlink);
                btnxacnhan = view.findViewById(R.id.updateproduct);
                btnhuy = view.findViewById(R.id.huy);
                Product product1 = products.get(position);
                //set date
                edtname.setText(product1.name);
                edtsinger.setText(product1.singer);
                edtlink.setText(product1.link);

                btnxacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ProductDAO spDao = new ProductDAO(context,fragment_baihat);
                        product.name = edtname.getText().toString();
                        product.singer = edtsinger.getText().toString();
                        product.link = edtlink.getText().toString();
                        Toast.makeText(context,"Edit Success",Toast.LENGTH_SHORT).show();
                    }
                });
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtname.setText("");
                        edtsinger.setText("");
                        edtlink.setText("");
                    }
                });
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(view);
                builder.show();

            }
        });

        return v;
    }
}
