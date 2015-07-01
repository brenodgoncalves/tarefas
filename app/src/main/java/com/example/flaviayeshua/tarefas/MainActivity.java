package com.example.flaviayeshua.tarefas;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flaviayeshua.tarefas.adapter.TarefaAdapter;
import com.example.flaviayeshua.tarefas.bd.BD;
import com.example.flaviayeshua.tarefas.to.TOTarefa;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private List<TOTarefa> tarefas;

    private ListView listTarefas;
    private Button btnAdicionarTarefa;
    private BD bd;
    private TextView textSemTarefas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listTarefas = (ListView) findViewById(R.id.listTarefas);
        registerForContextMenu(listTarefas);
        tarefas = new ArrayList<>();

        bd = new BD(this);
        tarefas = bd.buscar();

        if(tarefas.size() > 0)
        {
            textSemTarefas = (TextView)findViewById(R.id.textSemTarefas);
            textSemTarefas.setVisibility(TextView.INVISIBLE);
        }

        TarefaAdapter adapter = new TarefaAdapter(tarefas, this);
        listTarefas.setAdapter(adapter);
        listTarefas.setOnItemClickListener(this);
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.adicionarTarefa)
        {
            Intent i = new Intent(this, TarefaActivity.class);
            startActivity(i);
        }
      return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TOTarefa tarefa = tarefas.get(position);

        Intent i = new Intent(this, TarefaActivity.class);

        i.putExtra("_id", tarefa.getId());
        i.putExtra("descricao", tarefa.getDescricao());
        i.putExtra("data", tarefa.getData());

        startActivity(i);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, R.string.menu_delete);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        MenuItem item1 =  item;
        int teste = item1.getItemId();
        if (item.getItemId() == 1)
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            TOTarefa tarefa = tarefas.get(info.position);
            bd.excluir(tarefa);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        return true;
    }

}
