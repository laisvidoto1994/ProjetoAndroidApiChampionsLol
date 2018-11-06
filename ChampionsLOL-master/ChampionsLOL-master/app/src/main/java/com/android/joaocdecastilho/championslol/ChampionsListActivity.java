package com.android.joaocdecastilho.championslol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.android.joaocdecastilho.championslol.models.Champion;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChampionsListActivity extends AppCompatActivity
{
    private static final String TAG = "LAIS";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions_list);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChampionService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChampionService service = retrofit.create(ChampionService.class);
        Call<List<Champion>> requestChampions = service.listChampion();

        requestChampions.enqueue(new Callback<List<Champion>>() {
            @Override
            public void onResponse(Call<List<Champion>> call, Response<List<Champion>> response)
            {
                if(!response.isSuccessful())
                {
                    Toast.makeText(ChampionsListActivity.this, "Erro ao carregar dados!", Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(ChampionsListActivity.this, "Dados carregados!", Toast.LENGTH_SHORT).show();

                    ListView listaDeChampions = (ListView) findViewById(R.id.lista);
                    List<Champion> champions = response.body();

                    AdapterChampionsPersonalizado adapter = new AdapterChampionsPersonalizado(champions,ChampionsListActivity.this);
                    listaDeChampions.setAdapter(adapter);

                    for(Champion c : champions)
                    {
                        Log.i(TAG, String.format( "%s: %s: %s: %s", c.getName(),c.getKey(), c.getIcon(), c.getStats().getHp() ));
                    }

                     /*
                        se eu clicar em um item da lista, consigo saber qual á posição dele e mostrar na tela
                    */
                    listaDeChampions.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                        {
                            Intent intent = new Intent(ChampionsListActivity.this, DetalheChampionActivity.class);

                            String nome       = ((Champion) parent.getItemAtPosition(position)).getName();
                            String titulo     = ((Champion) parent.getItemAtPosition(position)).getTitle();
                            String key        = ((Champion) parent.getItemAtPosition(position)).getKey();
                            String icone      = ((Champion) parent.getItemAtPosition(position)).getIcon();
                            String tags       = formatacaoTag(((Champion) parent.getItemAtPosition(position)).getName(), ((Champion) parent.getItemAtPosition(position)).getTags() );
                            double hp         = ((Champion) parent.getItemAtPosition(position)).getStats().getHp();
                            double mp         = ((Champion) parent.getItemAtPosition(position)).getStats().getMp();
                            String descricao  = ((Champion) parent.getItemAtPosition(position)).getDescription();

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

                }
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

                Log.i(TAG, String.format( "%s: %s", nome, tag ));
                return tag;
            }

            @Override
            public void onFailure(Call<List<Champion>> call, Throwable t) {
                Log.e(TAG, "Erro: " + t.getMessage());
            }
        });
    }
}
