package com.android.joaocdecastilho.championslol;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.joaocdecastilho.championslol.configBanco.BancoController;
import com.squareup.picasso.Picasso;

public class DetalheChampionActivity extends AppCompatActivity
{
    private String nome;
    private String titulo;
    private String key;
    private String imagem;
    private String tags;
    private String hp;
    private String mp;
    private String descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_champion);

        final Intent intent = getIntent();
        nome      = intent.getStringExtra("chaveNome");
        titulo    = intent.getStringExtra("chaveTitulo");
        key       = intent.getStringExtra("chaveKey");
        imagem    = intent.getStringExtra("chaveIcone");
        tags      = intent.getStringExtra("chaveTags");
        hp        = intent.getStringExtra("chaveHp");
        mp        = intent.getStringExtra("chaveMp");
        descricao = intent.getStringExtra("chaveDescricao");

        TextView telaNome      = (TextView) findViewById(R.id.textView_name);
        TextView telaTitulo    = (TextView) findViewById(R.id.textView_title);
        TextView telaKey       = (TextView) findViewById(R.id.textView_key);
        ImageView telaImagem   = (ImageView) findViewById(R.id.img_icon);
        TextView telaTags      = (TextView) findViewById(R.id.textView_tags);
        TextView telaHp        = (TextView) findViewById(R.id.textView_hp);
        TextView telaMp        = (TextView) findViewById(R.id.textView_mp);
        TextView telaDescricao = (TextView) findViewById(R.id.textView_description);
        FloatingActionButton telaFavoritos = (FloatingActionButton) findViewById(R.id.floatingActionButton_favoritos);

        // Picasso - Responsavel por reenderizar imagens da aplicação
        Picasso.get().load( imagem ).resize(300, 300).into(telaImagem);

        telaNome.setText(String.valueOf(nome));
        telaTitulo.setText(String.valueOf(titulo));
        telaKey.setText(String.valueOf(key));
        telaTags.setText(String.valueOf(tags));
        telaHp.setText(String.valueOf(hp));
        telaMp.setText(String.valueOf(mp));
        telaDescricao.setText(String.valueOf(descricao));


        /*
            Click do botâo de favoritos
        */
        telaFavoritos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                BancoController bdController = new BancoController(getBaseContext());
                String resultado;

                //insere o champion na lista de favoritos
                resultado = bdController.insereDados(key,nome,titulo,tags,Double.parseDouble(hp),Double.parseDouble(mp),imagem,descricao);

                if(resultado.equals("Registro inserido com sucesso")){

                    Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
                 }else{
                    Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
                 }
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(this,"Apertou no botao Back",Toast.LENGTH_LONG);

        Intent intent1 = new Intent(getBaseContext(), AndroidTabLayoutActivity.class);
        startActivity(intent1);
    }
}
