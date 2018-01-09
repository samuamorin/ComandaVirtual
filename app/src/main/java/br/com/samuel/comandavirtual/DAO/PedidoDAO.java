package br.com.samuel.comandavirtual.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.samuel.comandavirtual.modelo.Comanda;
import br.com.samuel.comandavirtual.modelo.Pedido;

/**
 * Created by samue on 17/05/2016.
 */
public class PedidoDAO extends SQLiteOpenHelper {
    public PedidoDAO(Context context     ) {
        super(context, "ComandaVirtual", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql ="CREATE TABLE IF NOT EXISTS comanda( "+
                        "id_comanda INTEGER  PRIMARY KEY AUTOINCREMENT,"+
                        "nome_comanda TEXT NOT NULL,"+
                        "data_comanda TEXT NOT NULL," +
                        "aberta_comanda TEXT NOT NULL," +
                        "valor_comanda REAL);";
        String sql1=        "CREATE TABLE IF NOT EXISTS pedido( " +

                    "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "id_comanda_fk INTEGER,"+
                    "produto_pedido TEXT NOT NULL,"+
                    "preco_pedido REAL NOT NULL,"+
                    "unidade_pedido INTEGER NOT NULL,"+
                    "total_pedido REAL,"+
                    "FOREIGN KEY(id_comanda_fk) REFERENCES comanda (id_comanda));";
        db.execSQL(sql);
        db.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="DROP TABLE IF EXISTS comanda; DROP TABLE IF EXISTS pedido;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insereComanda(Comanda comanda){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome_comanda", comanda.getNome());
        dados.put("data_comanda", comanda.getData());
        dados.put("aberta_comanda", comanda.getComandaAberta());


        db.insert("comanda", null,dados );
    }

    public void inserePedido(Pedido pedido) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("produto_pedido",pedido.getProduto());
        dados.put("preco_pedido", pedido.getValor());
        dados.put("unidade_pedido", pedido.getUnidade());
        dados.put("id_comanda_fk", pedido.getIdComanda());
        dados.put("total_pedido", pedido.getTotal());



        db.insert("pedido", null, dados);
    }

    public Cursor UltimaComanda() {
        Cursor cursor;
        String[] registro = {"id_comanda", "nome_comanda", "data_comanda"};
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.query("comanda", registro, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToLast();
        }

        return cursor;
    }

    public Cursor LerPedidos(int id){

        Cursor cursor;

        SQLiteDatabase db=getReadableDatabase();
        String[]registro =new String[] {"id_pedido AS _id", "produto_pedido", "unidade_pedido", "preco_pedido"};
        String where = "id_comanda_fk="+id;
        cursor=db.query("pedido",registro,where,null,null,null,null );

        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor TotalPedidos(int id){

        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM pedido WHERE id_comanda_fk="+id+";", null);
        Double soma=0.0;
        if(cursor!=null) cursor.moveToFirst();
        return cursor;
    }

    public void EncerrarComanda(int id, Double valor){


        ContentValues values;
        values = new ContentValues();
        values.put("aberta_comanda",1);
        values.put("valor_comanda", valor);
        String where = "id_comanda="+id;
        SQLiteDatabase db = getWritableDatabase();
        db.update("comanda", values, where,null);

    }

    public Cursor LerComanda(){

        Cursor cursor;

        SQLiteDatabase db=getReadableDatabase();
        String[]registro =new String[] {"id_comanda AS _id", "data_comanda", "nome_comanda", "valor_comanda"};
        String where = "aberta_comanda=1";
        cursor=db.query("comanda",registro,where,null,null,null,null );

        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void ExcluirTodos(){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM comanda; VACUUM; DELETE FROM pedido; VACUUM;");
    }

    public boolean ExisteComanda() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT COUNT(*) FROM comanda", null);
        boolean existe = true;
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getInt(0) == 0) {

                existe = false;

            } else {

                existe = true;
            }


        }

        return existe;
    }

    public boolean ComandaAberta(){

        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT COUNT(*) FROM comanda WHERE aberta_comanda=0", null);

        boolean existe = true;
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getInt(0) == 0) {

                existe = false;

            } else {

                existe = true;
            }


        }

        return existe;
    }

    public boolean ExistePedido(int id){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT COUNT(*) FROM pedido WHERE id_comanda_fk="+id+";", null);
        boolean existe = true;
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getInt(0) == 0) {

                existe = false;

            } else {

                existe = true;
            }


        }

        return existe;

    }

    public void ExcluirComanda(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM comanda WHERE id_comanda="+id+"; VACUUM;");
    }

    public Cursor LerPedidoParaEditar(int id){
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor;
        String[] registro = new String[] {"produto_pedido","unidade_pedido","preco_pedido"};
        String where = "id_pedido="+id;
        cursor=db.query("pedido",registro,where,null,null,null,null );
        if(cursor!=null){
            cursor.moveToFirst();
        }

        return cursor;

    }

    public void AlterarPedidos(Pedido pedido, int id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("produto_pedido",pedido.getProduto());
        values.put("preco_pedido",pedido.getValor());
        values.put("unidade_pedido",pedido.getUnidade());
        values.put("total_pedido",pedido.getTotal());
        String where = "id_pedido="+id;

        db.update("pedido", values,where,null);


    }

    public void ExcluirPedido(int id){

        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM pedido WHERE id_pedido="+id;
        db.execSQL(sql);
    }
}

