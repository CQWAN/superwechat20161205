package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.view.View;

import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
    public void login(View view) {
        MFGT.gotoLogin(WelcomeActivity.this);
    }
    public void register(View view) {
        MFGT.gotoRegister(WelcomeActivity.this);
    }
}
