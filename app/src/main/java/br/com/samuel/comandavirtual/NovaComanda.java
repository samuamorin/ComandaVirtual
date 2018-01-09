package br.com.samuel.comandavirtual;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.samuel.comandavirtual.DAO.PedidoDAO;
import br.com.samuel.comandavirtual.modelo.Comanda;

public class NovaComanda extends AppCompatActivity {

    public NovaComandaFormulario formulario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_comanda);


        formulario = new NovaComandaFormulario(this);//cria objeto formulario para linkar com as ids



   }



    public void AbrirComanda(View view){

        Comanda comanda = formulario.pegaComanda();//recebe os dados da classe comanda

       if(!comanda.getNome().trim().isEmpty()) {

           comanda.setComandaAberta(0);

           PedidoDAO dao = new PedidoDAO(this);
           dao.insereComanda(comanda);
           dao.close();


           Intent intent = new Intent(getBaseContext(),ComandaAberta.class);
            startActivity(intent);
           finish();

            Toast.makeText(getApplicationContext(), "Nova comanda criada.", Toast.LENGTH_SHORT).show();


       }
        else{
           AlertDialog alert = new AlertDialog.Builder(NovaComanda.this)
                   .setMessage("Digite o nome do estabelecimento.").show();}
    }
}
