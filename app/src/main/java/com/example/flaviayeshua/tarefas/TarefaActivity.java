package com.example.flaviayeshua.tarefas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.flaviayeshua.tarefas.bd.BD;
import com.example.flaviayeshua.tarefas.bd.BdCore;
import com.example.flaviayeshua.tarefas.to.TOTarefa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TarefaActivity extends ActionBarActivity implements View.OnClickListener{

    private int status_activity = 0;
    private int _id;

    private EditText editTextTarefa;
    private DatePicker datePicker;
    private TimePicker timePicker;

    private Button btnSalvar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        editTextTarefa = (EditText) findViewById(R.id.editTextTarefa);

        datePicker = (DatePicker)findViewById(R.id.datePicker);
        datePicker.setCalendarViewShown(false);

        timePicker = (TimePicker) findViewById(R.id.timePicker);

        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);

        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null)
        {
            if (bundle.containsKey("_id")) {
                status_activity = 1;
                _id = bundle.getInt("_id");

                editTextTarefa.setText(bundle.getString("descricao"));

                String sDia = bundle.getString("data").substring(0, 2);
                String sMes = bundle.getString("data").substring(3, 5);
                String sAno = bundle.getString("data").substring(6, 10);
                String sHora = bundle.getString("data").substring(17, 19);
                String sMin = bundle.getString("data").substring(20, 22);

                datePicker.init(Integer.parseInt(sAno), Integer.parseInt(sMes)-1, Integer.parseInt(sDia), null);
                timePicker.setCurrentHour(Integer.parseInt(sHora));
                timePicker.setCurrentMinute(Integer.parseInt(sMin));
            }
        }
    }

    private void criarOuAtualizarAtividade()
    {
        BD bd = new BD(this);

        TOTarefa tarefa = new TOTarefa();
        tarefa.setDescricao(editTextTarefa.getText().toString());
        Date data = new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
        //Formatacao de data
        data.setYear(datePicker.getYear() - 1900);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy   -   HH:mm");

        tarefa.setData(sdf.format(data).toString());

        if(status_activity == 1 )
        {
            tarefa.setId(_id);
            bd.atualizar(tarefa);
        }else {
            bd.inserir(tarefa);
        }
    }

    private void showMainActivity( )
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCancelar)
        {
            showMainActivity();
        }
        else
        {
            criarOuAtualizarAtividade();

            showMainActivity();
        }
    }
}
