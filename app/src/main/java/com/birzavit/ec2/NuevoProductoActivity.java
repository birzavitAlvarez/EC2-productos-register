package com.birzavit.ec2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.birzavit.ec2.DAO.PRODUCTOS_DAO;
import com.birzavit.ec2.Entity.PRODUCTOS;
import com.birzavit.ec2.databinding.ActivityNuevoProductoBinding;
import com.google.android.material.snackbar.Snackbar;

public class NuevoProductoActivity extends AppCompatActivity {

    ActivityNuevoProductoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNuevoProductoBinding.inflate(getLayoutInflater());
        View vista = binding.getRoot();
        setContentView(vista);
        //
        binding.edtIdProd.requestFocus();
        //
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                NuevoProductoActivity.this,
                android.R.layout.simple_list_item_1,
                PRODUCTOS_DAO.vProductos
        );
        binding.spinnerProductos.setAdapter(adapter);

        binding.btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        binding.btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NuevoProductoActivity.this,
                        ConsultarProductosActivity.class);
                //
                startActivity(i);
            }
        });

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PRODUCTOS obj = new PRODUCTOS(
                        Integer.parseInt(binding.edtIdProd.getText()+""),
                        binding.edtNomProd.getText().toString(),
                        binding.spinnerProductos.getSelectedItem().toString(),
                        Integer.parseInt(binding.edtStock.getText()+""),
                        Double.parseDouble(binding.edtPrecio.getText()+"")
                );
                PRODUCTOS_DAO dao = new PRODUCTOS_DAO();
                String mensaje = dao.GrabarProducto(obj);
                dao = null;
                Toast.makeText(NuevoProductoActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                limpiar();
            }
        });
    }

    public void limpiar(){
        binding.edtIdProd.setText("");
        binding.edtNomProd.setText("");
        binding.spinnerProductos.setSelection(0);
        binding.edtStock.setText("");
        binding.edtPrecio.setText("");
        binding.edtIdProd.requestFocus();
    }

}