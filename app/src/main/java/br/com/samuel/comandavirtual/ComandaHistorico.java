package br.com.samuel.comandavirtual;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.samuel.comandavirtual.DAO.PedidoDAO;
import br.com.samuel.comandavirtual.modelo.Pedido;

public class ComandaHistorico extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda_historico);

        lista = (ListView) findViewById(R.id.comanda_histo);

        LerComandas();

    }


    private void LerComandas(){
        PedidoDAO bd= new PedidoDAO(getBaseContext());
        Cursor dados=bd.LerComanda();
        dados.moveToFirst();

// Setup cursor adapter using cursor from last step
        ComandaAdapter todoAdapter = new ComandaAdapter(this, dados,0);
// Attach cursor adapter to the ListView
        lista.setAdapter(todoAdapter);


    }

    public void Excluir (View view){
        PedidoDAO bd=new PedidoDAO(getBaseContext());
        bd.ExcluirTodos();
        startActivity(new Intent(getBaseContext(),TelaInicial.class));

    }



}
