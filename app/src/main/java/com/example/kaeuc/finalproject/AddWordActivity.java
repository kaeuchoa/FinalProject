package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaeuc.finalproject.Database.WordDataBaseHelper;
import com.example.kaeuc.finalproject.Extras.Constants;

/**
 * Created by kaeuc on 10/22/2015.
 */
public class AddWordActivity extends Activity {

    public static final String CATEGORY_ADDWORD = "personalDictionary.CATEGORY_ADDWORD";
    public static final String ACTION_ADDWORD = "personalDictionary.ACTION_ADDWORD";
    public static int _wordLanguage;

    private Button btnBack;
    private Button btnAddWord;
    private EditText edtWordBox, edtDefinitionBox,edtExSentenceBox;
    private WordDataBaseHelper helper;
    private final Intent previousIntent = getIntent();
    private static final Constants CONSTANTS = new Constants();
    private String langID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_word);

        //INICIALIZANDO AS VIEWS E O BD HELPER
        btnBack= (Button) findViewById(R.id.btn_back);
        btnAddWord = (Button) findViewById(R.id.btn_addWord);
        edtDefinitionBox = (EditText) findViewById(R.id.edt_definitionBox);
        edtExSentenceBox= (EditText) findViewById(R.id.edt_exSentenceBox);
        edtWordBox = (EditText) findViewById(R.id.edt_wordBox);
        helper = new WordDataBaseHelper(this);

        //RECUPERANDO OS DADOS DA INTENT PASSADA QUE ARMAZENA O NOME DA LINGUA
        langID = previousIntent.getStringExtra(CONSTANTS.LANG_ID);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v = (Button)v;
                if (v.getId() == R.id.btn_addWord){
                    addWord();

                }else{
                    finish();
                }
            }
        };
        btnBack.setOnClickListener(clickListener);
        btnAddWord.setOnClickListener(clickListener);
    }

    private void addWord(){
        String[] columns = {edtWordBox.getText().toString(),
                            edtDefinitionBox.getText().toString(),
                            edtExSentenceBox.getText().toString(),langID};

        helper.addWord(columns,AddWordActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }
}
