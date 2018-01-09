package br.com.samuel.comandavirtual;

import android.app.Activity;
import android.widget.EditText;

import br.com.samuel.comandavirtual.modelo.Pedido;

/**
 * Created by samue on 17/05/2016.
 */
public class AdicionaPedidosFormulario {
    private final EditText campoProduto;
    private final EditText campoValor;
    private final EditText campoUnidade;

    public AdicionaPedidosFormulario(AdicionaPedidos activity){


        campoProduto = (EditText) activity.findViewById(R.id.desc_produto);
        campoValor = (EditText) activity.findViewById(R.id.valor_produto);
        campoUnidade = (EditText) activity.findViewById(R.id.qtde_produto);    }



    public Pedido pegaPedido(){
        Pedido pedido = new Pedido();
        pedido.setProduto(campoProduto.getText().toString());
        if ("".equals(campoUnidade.getText().toString()))
            pedido.setUnidade(0);
        else
            pedido.setUnidade(Integer.parseInt(campoUnidade.getText().toString()));
        if("".equals(campoValor.getText().toString()))
            pedido.setValor(0);
        else
            pedido.setValor(Double.parseDouble(campoValor.getText().toString()));

        pedido.setTotal(pedido.getUnidade()*pedido.getValor());

        return pedido;

    }
}