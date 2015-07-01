package com.example.flaviayeshua.tarefas.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.flaviayeshua.tarefas.R;
import com.example.flaviayeshua.tarefas.to.TOTarefa;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Yeshua on 28/06/2015.
 */
public class TarefaAdapter extends BaseAdapter{

    private List<TOTarefa> tarefas;
    private Context context;

    public TarefaAdapter(List<TOTarefa> tarefas, Context context)
    {
        this.tarefas = tarefas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TOTarefa tarefa = tarefas.get(position);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.list_item, null);
        TextView txtTarefa = (TextView)v.findViewById(R.id.txtTarefa);
        TextView txtData = (TextView)v.findViewById(R.id.txtData);

        txtTarefa.setText(tarefa.getDescricao());
        //String dateformat = new SimpleDateFormat("dd/MM/yy - HH:mm").format(tarefa.getData());
        //txtData.setText(dateformat);
        txtData.setText(tarefa.getData());

        return v;
    }
}
