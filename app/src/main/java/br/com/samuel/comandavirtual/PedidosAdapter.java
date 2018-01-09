package br.com.samuel.comandavirtual;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by samue on 22/05/2016.
 */
public class PedidosAdapter extends CursorAdapter {

    public PedidosAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.lista_pedidos, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        NumberFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

        // Find fields to populate in inflated template
        TextView tvunidade = (TextView) view.findViewById(R.id.unidade);
        TextView tvproduto= (TextView) view.findViewById(R.id.descricao);
        TextView tvpreco= (TextView) view.findViewById(R.id.valor);

        // Extract properties from cursor
        String produtos = cursor.getString(cursor.getColumnIndexOrThrow("produto_pedido"));
        int unidade = cursor.getInt(cursor.getColumnIndexOrThrow("unidade_pedido"));
        Double valor =  cursor.getDouble(cursor.getColumnIndexOrThrow("preco_pedido"));

        // Populate fields with extracted properties
        tvunidade.setText(String.valueOf(unidade));
        tvproduto.setText(produtos);
        tvpreco.setText(String.valueOf(nf.format(valor*unidade)));

    }


}
