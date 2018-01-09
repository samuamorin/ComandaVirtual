package br.com.samuel.comandavirtual;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by samue on 28/05/2016.
 */
public class ComandaAdapter extends CursorAdapter {

    public ComandaAdapter(Context context, Cursor cursor, int flags) {
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
        TextView tvdata = (TextView) view.findViewById(R.id.unidade);
        TextView tvnome= (TextView) view.findViewById(R.id.descricao);
        TextView tvvalor= (TextView) view.findViewById(R.id.valor);

        // Extract properties from cursor
        String data = cursor.getString(cursor.getColumnIndexOrThrow("data_comanda"));
        String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome_comanda"));
        Double valor =  cursor.getDouble(cursor.getColumnIndexOrThrow("valor_comanda"));

        // Populate fields with extracted properties
        tvdata.setText(String.valueOf(data));
        tvnome.setText(nome);
        tvvalor.setText(String.valueOf(nf.format(valor)));

    }
}
