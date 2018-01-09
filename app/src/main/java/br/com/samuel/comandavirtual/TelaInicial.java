package br.com.samuel.comandavirtual;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.samuel.comandavirtual.DAO.PedidoDAO;


public class TelaInicial extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
    }


   public void IniciarNovaComanda (View v){
        startActivity(new Intent(getBaseContext(),NovaComanda.class));
        ;

    }

    public void ContinuarComanda (View v){

        PedidoDAO pedidoDAO = new PedidoDAO(getBaseContext());
        if(pedidoDAO.ComandaAberta()) {
            startActivity(new Intent(getBaseContext(), ComandaAberta.class));
        }
        else {

            AlertDialog alertDialog = new AlertDialog.Builder(
                    TelaInicial.this).create(); // Read
            // Update
            alertDialog.setTitle("COMANDA VIRTUAL");
            alertDialog
                    .setMessage("Você não possui nenhuma comanda aberta no momento.");
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


    }

    public void AbrirHistorico (View v){
        PedidoDAO pedidoDAO = new PedidoDAO(getBaseContext());
        if(pedidoDAO.ExisteComanda()){
        startActivity(new Intent(getBaseContext(),ComandaHistorico.class));
        }
        else {
            AlertDialog alertDialog = new AlertDialog.Builder(
                    TelaInicial.this).create(); // Read
            // Update
            alertDialog.setTitle("COMANDA VIRTUAL");
            alertDialog
                    .setMessage("Você ainda não possui nenhuma comanda registrada.");
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
    }

    public void onBackPressed()  {

        AlertDialog alertDialog = new AlertDialog.Builder(
               TelaInicial.this).create(); // Read
        // Update
        alertDialog.setTitle("COMANDA VIRTUAL");
        alertDialog
                .setMessage("Deseja Sair do Aplicativo Comanda Virtual");
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);                    }


                });
        alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancelar",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub


                    }
                });
        alertDialog.show();

    }




}


