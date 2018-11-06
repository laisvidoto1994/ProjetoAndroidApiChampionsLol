package com.android.joaocdecastilho.championslol;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.joaocdecastilho.championslol.configBanco.BancoController;
import com.android.joaocdecastilho.championslol.configBanco.CriaBanco;
import com.android.joaocdecastilho.championslol.models.Champion;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FavoritosChampionsActivity extends AppCompatActivity
{
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos_champions);

        TextView txtNome  = (TextView)findViewById(R.id.txtName);
        TextView txtTitle = (TextView)findViewById(R.id.txtTitles);

        final BancoController dbController = new BancoController(getBaseContext());
        final Cursor cursor = dbController.carregarDados();

        String[] nomeCampos = new String[] {CriaBanco.ICON,CriaBanco.NAME,CriaBanco.TITLE};
        int[] idViews = new int[] {R.id.image_favoritos,R.id.nomeChampion,R.id.titleChampion};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),R.layout.activity_itens_favoritos,cursor,nomeCampos,idViews,0);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder()
        {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex)
            {
                if(view.getId() == R.id.image_favoritos)
                {
                    String imgText = cursor.getString(cursor.getColumnIndex(CriaBanco.ICON));

                    ImageView img = view.findViewById(R.id.image_favoritos);

                    Picasso.get().load(imgText).resize(300, 300).into(img);
                    return true;
                }
                return  false;
            }
        });

       lista = (ListView)findViewById(R.id.listaFavoritos);

       lista.setAdapter(adapter);

        /*
            Opção do usuario de visualizar detalhe do item clicado
        */
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(FavoritosChampionsActivity.this, DetalheChampionActivity.class);

                String nome       = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NAME));
                String titulo     = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITLE));
                String key        = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.KEY));
                String icone      = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ICON));
                String tags       = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TAGS));
                double hp         = cursor.getDouble(cursor.getColumnIndexOrThrow(CriaBanco.HP));
                double mp         = cursor.getDouble(cursor.getColumnIndexOrThrow(CriaBanco.MP));
                String descricao  = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.DESCRIPTION));

                intent.putExtra("chaveNome", nome);
                intent.putExtra("chaveTitulo", titulo);
                intent.putExtra("chaveKey", key);
                intent.putExtra("chaveIcone", icone);
                intent.putExtra("chaveTags",  tags);
                intent.putExtra("chaveHp", String.valueOf(hp));
                intent.putExtra("chaveMp", String.valueOf(mp));
                intent.putExtra("chaveDescricao", descricao);

                startActivity(intent);
            }
        });

        /*
            Opção de quando o usuario realiza o click longo no item da lista de favoritos
        */
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                final String codigo;
                codigo = ((Cursor)parent.getItemAtPosition(position)).getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));

                AlertDialog.Builder builder = new AlertDialog.Builder(FavoritosChampionsActivity.this);
                builder.setMessage("Deseja Deletar o Champion ?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbController.deletarRegistro(Integer.parseInt(codigo));
                                Toast.makeText(getApplicationContext(),"Champion deletado com sucesso !!",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(FavoritosChampionsActivity.this, AndroidTabLayoutActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Deletar Champion");
                alert.setIcon(R.drawable.ic_launcher_background);
                alert.show();
                return true;
            }
        });

    }

    /*
             Responsável pela formatação da tag(lista), para passar para tela já ajustada
          */
    public String formatacaoTag(String nome, List<String> tags)
    {
        String tag = "";
        for (int i = 0; i < tags.size(); i++)
        {
            if(i > 0)
            {
                tag =  tag + ", "+ tags.get(i) ;
            }
            else
            {
                tag =  tag + tags.get(i);
            }
        }
        return tag;
    }
}
