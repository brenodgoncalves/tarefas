package com.example.flaviayeshua.tarefas.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.flaviayeshua.tarefas.to.TOTarefa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yeshua on 29/06/2015.
 */
public class BD {

    private SQLiteDatabase bd;

    public BD(Context context)
    {
        BdCore auxBd = new BdCore(context);
        bd = auxBd.getWritableDatabase();
    }

    public void inserir(TOTarefa tarefa)
    {
        ContentValues valores = new ContentValues();
        valores.put("descricao", tarefa.getDescricao());
        valores.put("dataehora", tarefa.getData());
        bd.insert("tarefa",null, valores);
    }

    public void atualizar(TOTarefa tarefa)
    {
        ContentValues valores = new ContentValues();
        valores.put("descricao", tarefa.getDescricao());
        valores.put("dataehora", tarefa.getData());
        bd.update("tarefa", valores, "_id = ?", new String[]{"" + tarefa.getId()});
    }

    public void excluir(TOTarefa tarefa)
    {
        TOTarefa tarefa1 = tarefa;
        bd.delete("tarefa", "_id = " + tarefa.getId(), null);
    }

    public List<TOTarefa> buscar()
    {
        List<TOTarefa> list = new ArrayList<TOTarefa>();
        String[] colunas = new String[]{"_id", "descricao", "dataehora"};
        Cursor cursor = bd.query("tarefa",colunas, null, null, null, null, null);

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();

            do
            {
                TOTarefa tarefa = new TOTarefa();
                tarefa.setId(cursor.getInt(0));
                tarefa.setDescricao(cursor.getString(1));
                tarefa.setData(cursor.getString(2));
                list.add(tarefa);

            }while (cursor.moveToNext());
        }
        return (list);
    }
}
