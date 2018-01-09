package br.com.samuel.comandavirtual;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.samuel.comandavirtual.DAO.PedidoDAO;
import br.com.samuel.comandavirtual.modelo.Comanda;
import br.com.samuel.comandavirtual.modelo.Pedido;

public class ComandaAberta extends AppCompatActivity {

    TextView Data;
    TextView NomeB;
    int idComanda;
    String NomeComanda;
    String DataComanda;
    Double Parcial=0.0;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda_aberta);

        //Recupera o dia  do  sistema e exibe na textview
        NomeB = (TextView) findViewById(R.id.bar);
        Data = (TextView) findViewById(R.id.data);

        //Parcial=getIntent().getExtras().getDouble("valor");

        lista=(ListView)findViewById(R.id.pedidosabertos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // pega o o item selecionado com os dados da pessoa


                // cria a intent
                Intent intent = new Intent(ComandaAberta.this, EditaPedidos.class);
                // seta o parametro do medico a exibir os dados
                Bundle bundle = new Bundle();
                bundle.putString("PEDIDO",String.valueOf(id));
                intent.putExtras(bundle);
                //  chama a Activity que mostra os detalhes
                startActivity(intent);

            }

        });

        Consultar();

       LerPedidos(idComanda);











    }

    public void onResume(){
        super.onResume();
        LerPedidos(idComanda);

    }

    public void onBackPressed()  {


        AlertDialog alertDialog = new AlertDialog.Builder(
                ComandaAberta.this).create(); // Read
        // Update
        alertDialog.setTitle("COMANDA VIRTUAL");
        alertDialog
                .setMessage("Por favor, feche a comanda atual antes de abrir uma nova.");
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                                           }


                });
        /*alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancelar",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub


                    }
              });*/
        alertDialog.show();

    }

    public void AdicionarPedidos(View view){

        Intent intent = new Intent(this,AdicionaPedidos.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", idComanda);
        bundle.putDouble("valor", Parcial);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void Consultar() {


        PedidoDAO bd = new PedidoDAO(getBaseContext());

        Cursor dados = bd.UltimaComanda() ;

        if(dados.moveToLast()){
            idComanda= dados.getInt(0);
            NomeComanda = dados.getString(1);
            DataComanda = dados.getString(2);
            NomeB.setText( "Estabelecimento: "+NomeComanda );
            Data.setText( DataComanda );


    }}


    public void FecharComanda(View view) {

        PedidoDAO pedidoDAO = new PedidoDAO(getBaseContext());

        if (pedidoDAO.ExistePedido(idComanda)) {

            Intent intent = new Intent(this, FecharComanda.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", idComanda);
            bundle.putString("nome", NomeComanda);
            bundle.putString("data", DataComanda);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();

        } else {
            pedidoDAO.ExcluirComanda(idComanda);
            startActivity(new Intent(getBaseContext(), TelaInicial.class));
        }
    }

    private void LerPedidos(int id){
        PedidoDAO bd= new PedidoDAO(getBaseContext());
        Cursor dados=bd.LerPedidos(id);
        dados.moveToFirst();

// Setup cursor adapter using cursor from last step
        PedidosAdapter todoAdapter = new PedidosAdapter(this, dados,0);
// Attach cursor adapter to the ListView
        lista.setAdapter(todoAdapter);

    }



}
