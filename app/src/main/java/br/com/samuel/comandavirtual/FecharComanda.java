package br.com.samuel.comandavirtual;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import br.com.samuel.comandavirtual.DAO.PedidoDAO;
import br.com.samuel.comandavirtual.modelo.Pedido;

public class FecharComanda extends AppCompatActivity {

    TextView ComandaData,  Total, Individual;
    Switch TaxadeServico;
    int idComanda;
    Double TotalPedidos=0.0, TotalIndividual=0.0;
    String NomeComanda, DataComanda;




    NumberFormat nf = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols(new Locale ("pt", "BR")));





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fechar_comanda);

        ComandaData = (TextView) findViewById(R.id.comanda_data);
        TaxadeServico = (Switch) findViewById(R.id.taxa);
        Total = (TextView) findViewById(R.id.total_comanda);



        idComanda = getIntent().getExtras().getInt("id");
        NomeComanda = getIntent().getExtras().getString("nome");
        DataComanda = getIntent().getExtras().getString("data");

        ComandaData.setText(DataComanda+"       "+NomeComanda);







        TaxadeServico.setChecked(false);

        TotalPedidos = Double.valueOf(TotalPedidos(idComanda));

        TaxadeServico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Total.setText(String.valueOf(nf.format(TotalPedidos=TotalPedidos(idComanda)*1.1)));
                }else {
                    Total.setText(String.valueOf(nf.format(TotalPedidos=TotalPedidos(idComanda))));
                }
            }
        });

        if (TaxadeServico.isChecked()) Total.setText(String.valueOf(nf.format(TotalPedidos=TotalPedidos(idComanda)*1.1)));
        else Total.setText(String.valueOf(nf.format(TotalPedidos=TotalPedidos(idComanda))));








    }



    public Double TotalPedidos(int id){
        Double soma=0.0;
        PedidoDAO bd = new PedidoDAO(getBaseContext());
        Cursor dados = bd.TotalPedidos(id);
        dados.moveToFirst();
        do {
            soma = soma + dados.getDouble(5);
        }while (dados.moveToNext());

        return soma;
    }

    public void Fechar(View view){

        PedidoDAO bd = new PedidoDAO(getBaseContext());
        bd.EncerrarComanda(idComanda,TotalPedidos);
        Intent intent = new Intent(this,TelaInicial.class);
        startActivity(intent);
        finish();

    }





    }


