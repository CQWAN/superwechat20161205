package cn.ucai.superwechat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.ucai.superwechat.R;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
    public void login(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }
    public void register(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}
