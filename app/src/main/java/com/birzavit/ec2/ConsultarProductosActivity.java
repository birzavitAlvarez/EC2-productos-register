package com.birzavit.ec2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.birzavit.ec2.DAO.PRODUCTOS_DAO;
import com.birzavit.ec2.databinding.ActivityConsultarProductosBinding;

import java.util.List;

public class ConsultarProductosActivity extends AppCompatActivity {

    ActivityConsultarProductosBinding binding;
    PRODUCTOS_DAO dao = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConsultarProductosBinding.inflate(getLayoutInflater());
        View vista = binding.getRoot();
        setContentView(vista);

        MostrarProductos();

        binding.btnBuscarQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarProductosConsulta();
            }
        });

        binding.btnEliminarQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = binding.lvlProductosQuery.getCheckedItemPosition();
                if (position != ListView.INVALID_POSITION) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ConsultarProductosActivity.this);
                    builder.setMessage("¿Estás seguro de eliminar el producto seleccionado?")
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    eliminarProductoSeleccionado(position);
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Toast.makeText(ConsultarProductosActivity.this, "Seleccione un producto para eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void MostrarProductos(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ConsultarProductosActivity.this,
                android.R.layout.simple_list_item_1,
                PRODUCTOS_DAO.vProductos
        );
        binding.spinnerProductosQuery.setAdapter(adapter);
    }

    void MostrarProductosConsulta() {
        String nombre = binding.spinnerProductosQuery.getSelectedItem().toString();
        int stockMinimo = 0;
        try {
            stockMinimo = Integer.parseInt(binding.edtStockQuery.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese un valor numérico válido para el stock mínimo", Toast.LENGTH_SHORT).show();
            return;
        }

        dao = new PRODUCTOS_DAO();
        List<String> mi_lista = dao.ListarProductosCategoriaStock(nombre, stockMinimo);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                ConsultarProductosActivity.this,
                android.R.layout.select_dialog_singlechoice,
                mi_lista
        );
        binding.lvlProductosQuery.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        binding.lvlProductosQuery.setAdapter(adapter);
        Toast.makeText(this, "Cantidad de Productos: " + mi_lista.size(), Toast.LENGTH_SHORT).show();
    }

    private void eliminarProductoSeleccionado(int position) {

        try {
            dao = new PRODUCTOS_DAO();
            int id_prod = dao.ListarProductos().get(position).getId_prod();
            String nom_pro = dao.ListarProductos().get(position).getNom_prod();
            String productoSeleccionado = dao.EliminarProducto(id_prod);
            dao = null;
            Toast.makeText(ConsultarProductosActivity.this, "Producto eliminado: " + nom_pro, Toast.LENGTH_SHORT).show();
            onResume();
        } catch (Exception e) {
            Toast.makeText(ConsultarProductosActivity.this, "Error Seleccione un Producto..1", Toast.LENGTH_SHORT).show();
            onResume();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MostrarProductosConsulta();
    }



}