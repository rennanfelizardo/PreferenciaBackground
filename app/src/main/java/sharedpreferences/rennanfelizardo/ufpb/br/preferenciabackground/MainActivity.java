package sharedpreferences.rennanfelizardo.ufpb.br.preferenciabackground;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    private RadioGroup radioGroup;
    private RadioButton radioButtonEscolhido;
    private Button botaoMudar;
    private RelativeLayout layout;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPrefencia";

    private static final String BRANCO = "#FFFFFF";
    private static final String VERMELHO = "#FFF68587";
    private static final String AZUL = "#FFB7EBF9";
    private static final String VERDE = "#FFC8F9B7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupId);
        botaoMudar = (Button) findViewById(R.id.botaoMudarId);
        layout = (RelativeLayout) findViewById(R.id.layoutId);

        botaoMudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = radioGroup.getCheckedRadioButtonId();

                if (id > 0){
                    radioButtonEscolhido = (RadioButton) findViewById(id);

                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    String corEscholhida = radioButtonEscolhido.getText().toString();
                    setBackground(corEscholhida);
                    editor.putString("cor", corEscholhida).apply();
                }
            }
        });

        //recuperando preferênias salvas 
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        String cor = BRANCO;
        if (sharedPreferences.contains("cor")){
            cor = sharedPreferences.getString("cor", "cor não definida");
            setBackground(cor);
        }else{
            setBackground(cor);
        }
    }

    private void setBackground(String corEscholhida) {
        if (TextUtils.equals(corEscholhida, "Azul")){
            layout.setBackgroundColor(Color.parseColor(AZUL));
        }else if (TextUtils.equals(corEscholhida, "Verde")) {
            layout.setBackgroundColor(Color.parseColor(VERDE));
        }else if (TextUtils.equals(corEscholhida, "Vermelho")){
            layout.setBackgroundColor(Color.parseColor(VERMELHO));
        }else{
            layout.setBackgroundColor(Color.parseColor(BRANCO));
        };

    }
}
