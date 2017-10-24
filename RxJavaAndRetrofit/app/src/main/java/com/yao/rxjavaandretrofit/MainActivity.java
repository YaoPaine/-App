package com.yao.rxjavaandretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yao.lib_mvp.BaseActivity;
import com.yao.moduleb.ModuleBActivity;
import com.yao.modulec.ModuleCActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ModuleBActivity.class);
                intent.putExtra("module", "my name is he yao ");
                startActivity(intent);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ModuleCActivity.class);
                intent.putExtra("module", "my name is he yao ");
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void clickEvent(View view) {
        Log.e(TAG, "clickEvent: ");
        Intent intent = new Intent(getApplicationContext(), ModuleBActivity.class);
        switch (view.getId()) {
            case R.id.button1:
                startActivity(intent);
                break;
            case R.id.button2:
                intent.putExtra("module", "name");
                startActivity(intent);
                break;
            case R.id.button3:

                break;
        }
    }
}
