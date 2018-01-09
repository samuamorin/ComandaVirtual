package br.com.samuel.comandavirtual;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import br.com.samuel.comandavirtual.DAO.PedidoDAO;
import br.com.samuel.comandavirtual.modelo.Pedido;

public class EditaPedidos extends AppCompatActivity {

    TextView tvNome, tvUnidade, tvValor;
    EditText etNome,etUnidade, etValor;
    Button btExcluir,btAlterar;
    int idPedido, UnidadePedido;
    Double ValorPedido;
    String ProdutoPedido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_pedidos);

        idPedido=Integer.parseInt(getIntent().getExtras().getString("PEDIDO"));



        AlertDialog alertDialog = new AlertDialog.Builder(
                EditaPedidos.this).create(); // Read
        // Update
        alertDialog.setTitle("COMANDA VIRTUAL");
        alertDialog
                .setMessage("Para editar algum pedido, basta inserir o novo valor no campo que você deseja alterar. Também é possível excluir" +
                        "o pedido diretamente no botão excluir pedido.");
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


        LerPedido(idPedido);



    }

   public void LerPedido(int id){//retorna os dados do pedido de id atual
       NumberFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

       PedidoDAO pedidoDAO = new PedidoDAO(getBaseContext());
       Cursor dados = pedidoDAO.LerPedidoParaEditar(id);

       tvNome=(TextView) findViewById(R.id.produto_edita);
       tvUnidade=(TextView) findViewById(R.id.unidade_edita);
       tvValor=(TextView)findViewById(R.id.valor_edita);

       ProdutoPedido = dados.getString(0);
       UnidadePedido = dados.getInt(1);
       ValorPedido = dados.getDouble(2);

       tvNome.setText(ProdutoPedido);
       tvUnidade.setText(String.valueOf(UnidadePedido));
       tvValor.setText(String.valueOf(nf.format(ValorPedido)));

   }

    public void Alterar(View view){//alterar o pedido com base no que foi digitado

        Pedido pedido = new Pedido();
        String auxNome;
        String auxUnidade;
        String auxValor;

        pedido.setId(idPedido);

        etNome = (EditText) findViewById(R.id.etproduto_edita);
        etUnidade = (EditText) findViewById(R.id.etunidade_edita);
        etValor = (EditText) findViewById(R.id.etvalor_edita);

        auxNome=etNome.getText().toString();
        auxUnidade=etUnidade.getText().toString();
        auxValor=etValor.getText().toString();

        if(auxNome.trim().isEmpty()){
            pedido.setProduto(ProdutoPedido);
        }
        else {
            pedido.setProduto(auxNome);
        }

        if(auxUnidade.trim().isEmpty()){
            pedido.setUnidade(UnidadePedido);
        }
        else {
            pedido.setUnidade(Integer.parseInt(auxUnidade));
        }
        if(auxValor.trim().isEmpty()){
            pedido.setValor(ValorPedido);
        }
        else {
            pedido.setValor(Double.parseDouble(auxValor));
        }

        pedido.setTotal(pedido.getUnidade()*pedido.getValor());

        PedidoDAO pedidoDAO = new PedidoDAO(getBaseContext());
        pedidoDAO.AlterarPedidos(pedido,idPedido);
        Toast.makeText(getApplicationContext(), "Pedido alterado.", Toast.LENGTH_SHORT).show();
        finish();



    }

    public void ExcluirPedidos(View view){//excluir o registro de pedido

        AlertDialog alertDialog = new AlertDialog.Builder(
                EditaPedidos.this).create(); // Read
        // Update
        alertDialog.setTitle("COMANDA VIRTUAL");
        alertDialog
                .setMessage("Deseja excluir o pedido atual?");
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        PedidoDAO pedidoDAO = new PedidoDAO(EditaPedidos.this);
                        pedidoDAO.ExcluirPedido(idPedido);
                        Toast.makeText(getApplicationContext(), "Pedido excluído.", Toast.LENGTH_SHORT).show();
                        finish();}


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



