package com.example.flaviayeshua.tarefas.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yeshua on 29/06/2015.
 */
public class BdCore extends SQLiteOpenHelper {

    private static final String NOME_BD = "bd_tarefa";
    private static final int VERSAO_BD = 2;

    public BdCore (Context context)
    {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table tarefa(_id integer primary key autoincrement, descricao text not null, dataehora text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("drop table tarefa;");
        onCreate(bd);
    }
}
