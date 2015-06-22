package com.example.nick.somethingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class AlertTranspatrentActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String request = intent.getStringExtra("request");

        setContentView(R.layout.activity_alert_transpatrent);
        showCustomDialog(request);


    }



    private void showCustomDialog(String request) {


        View view = View.inflate(this, R.layout.custom_dialog, null);

        final EditText editText = (EditText)view.findViewById(R.id.search_line);
        ImageButton buttonClr = (ImageButton) view.findViewById(R.id.clear_ln_btn);
        buttonClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        ImageButton buttonSearch = (ImageButton) view.findViewById(R.id.image_btn_search);


        if(request!=null) {
            editText.setText(request);
        }

        final int[] selectedItem = {0};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setView(view)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(editText.getText().toString()!=null){
                googleSearch(editText.getText().toString());}
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                hideAlertDialog();
            }
        }).setSingleChoiceItems(R.array.hello_world, selectedItem[0],
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = which;
                    }
                })
        .setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                hideAlertDialog();
            }
        });

        final AlertDialog ld = builder.create();
        ld.show();
}

    void hideAlertDialog(){
        AlertTranspatrentActivity.this.finish();
    }

    void googleSearch(String query){
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        startActivity(intent);
    }

    


}
//TODO: Сделать чтобы закрывалось, активити