package com.example.joao_bovoloni.aps2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.os.Vibrator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    public static int[] eatFoodyImages = {
            R.drawable.cafe, R.drawable.danone, R.drawable.manteiga, R.drawable.miojo,
            R.drawable.nescau, R.drawable.bobom,

    };
    public static String[] nomes = {
            "João Otávio", "Gabriel", "Ennnnnfraim", "Marquiiinhos",


    };
    Button cliente;
    Button atender;
    CountDownTimer Count;
    Runnable mPendingRunnable;
    final Handler processaPistaHandler = new Handler();
    Runnable mPendingRunnable1;
    final Handler processaPistaHandler1 = new Handler();
    int maximoCliente = 15;
    private TextView fila;
    int count = 0;
    ArrayList<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clientes = new ArrayList<>();
        gridView = (GridView) findViewById(R.id.gridview);
        cliente = (Button) findViewById(R.id.button2);
        atender = (Button) findViewById(R.id.button);
        fila = (TextView) findViewById(R.id.fila);
        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processaPistaHandler.post(mPendingRunnable);


            }
        });
        atender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processaPistaHandler1.post(mPendingRunnable1);


            }
        });
        gridView.setAdapter(new ImageAdapter(this, eatFoodyImages));

        mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                int SOMA = 0;
                if (maximoCliente > 0) {
                    final int min = 1;
                    final int max = 6;
                    final int randomProdutos = new Random().nextInt((max - min) + 1) + min;
                    for (int i = 0; i < randomProdutos; i++) {
                        final int max2 = 10;
                        final int randomQtd = new Random().nextInt((max2 - min) + 1) + min;
                        SOMA = SOMA + randomQtd;
                        View child = gridView.getChildAt(i);
                        TextView itemText = (TextView) child.findViewById(R.id.grid_item_label);
                        int total = Integer.parseInt(itemText.getText().toString()) - randomQtd;
                        if (total <= 0) {
                            total = 0;
                        }
                        itemText.setText(String.valueOf(total));


                    }

                    maximoCliente--;
                    count++;
                    final int minNome = 0;
                    final int maxNome = 3;
                    final int nome = new Random().nextInt((maxNome - minNome) + 1) + minNome;
                    clientes.add(new Cliente(nomes[nome], count, SOMA));
                    fila.setText("Fila: " + count);
                    if (clientes.size() > 10) {
                        clientes.remove(count - 1);
                        count--;
                        fila.setText("Fila: " + clientes.size());
                        notificacao();
                    }
                    if (clientes.size() == 10) {
                        chamarAtencao();
                    }


                    processaPistaHandler.postDelayed(this, 1000);
                }


            }
        };
        mPendingRunnable1 = new Runnable() {
            @Override
            public void run() {
                if (clientes.size() != 0) {
                    atendimento();
                    processaPistaHandler1.postDelayed(this, 1000);
                } else {
                    fila.setText("Fila: 0");
                }


            }
        };

    }

    void atendimento() {

        // Countdown

        Count = new CountDownTimer(500, 1000) {

            public void onTick(long millisUntilFinished) {

                Integer intMillis = (int) (long) millisUntilFinished;
                Integer intOcupada = (int) (long) 500;
                int total = (intOcupada - intMillis) * 100 / intOcupada;
                atender.setText("cliente sendo atendido");


            }

            public void onFinish() {
                if (clientes.size() != 0) {
                    clientes.remove(0);
                } else {
                    atender.setText("Atender");

                }

            }

        }.start();

    }

    void notificacao() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Aviso")
                        .setContentText("Um cliente saiu da fila");
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }

    void chamarAtencao() {

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        v.vibrate(400);
    }
}
