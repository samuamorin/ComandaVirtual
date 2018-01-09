package br.com.samuel.comandavirtual;

import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import br.com.samuel.comandavirtual.modelo.Comanda;

/**
 * Created by samue on 17/05/2016.
 */
public class NovaComandaFormulario {

    EditText campoNome;
    public final long tempo = System.currentTimeMillis();

    public NovaComandaFormulario(NovaComanda activity){

        campoNome = (EditText) activity.findViewById(R.id.nomeBar);

    }


    public Comanda pegaComanda(){
        Comanda comanda = new Comanda();
        comanda.setNome(campoNome.getText().toString());
        comanda.setData(new SimpleDateFormat("d/M/yyyy").format(tempo));
        return comanda;

    }

}
