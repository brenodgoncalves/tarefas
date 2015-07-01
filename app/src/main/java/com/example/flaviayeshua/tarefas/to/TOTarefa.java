package com.example.flaviayeshua.tarefas.to;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yeshua on 28/06/2015.
 */
public class TOTarefa {

    private int _id;
    private String descricao;
    private String data;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
