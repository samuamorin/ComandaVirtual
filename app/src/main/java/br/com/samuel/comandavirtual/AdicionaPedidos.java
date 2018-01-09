package br.com.samuel.comandavirtual;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.samuel.comandavirtual.DAO.PedidoDAO;
import br.com.samuel.comandavirtual.modelo.Pedido;

public class AdicionaPedidos extends AppCompatActivity {

    public AdicionaPedidosFormulario formulario;
    int idComanda;
    double ValorTotal=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_pedidos);

        formulario = new AdicionaPedidosFormulario(this);
        idComanda =getIntent().getExtras().getInt("id");

    }


    public void RetornarComanda(View view) {

        Pedido pedido = formulario.pegaPedido();

        if (pedido.getProduto().trim().isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(AdicionaPedidos.this).
                    setMessage("Preencha o campo produto").show();
        } else if(pedido.getValor()==0) {

            AlertDialog alertDialog = new AlertDialog.Builder(AdicionaPedidos.this).
                    setMessage("Preencha o campo valor").show();
        } else if(pedido.getUnidade()==0) {

            AlertDialog alertDialog = new AlertDialog.Builder(AdicionaPedidos.this).
                    setMessage("Preencha o campo unidade").show();
        }
        else{

            //criar função que retorne o último id de comanda

            PedidoDAO dao = new PedidoDAO(this);
            pedido.setIdComanda(idComanda);
            dao.inserePedido(pedido);
            ValorTotal=ValorTotal+pedido.getUnidade()*pedido.getTotal();
            Intent intent = new Intent(getBaseContext(),ComandaAberta.class);
            Bundle bundle = new Bundle();
            bundle.putDouble("valor", ValorTotal);
            intent.putExtras(bundle);
            startActivity(intent);


        Toast.makeText(AdicionaPedidos.this, "Pedido incluído.", Toast.LENGTH_SHORT).show();

            finish();

    }

        }
    }
