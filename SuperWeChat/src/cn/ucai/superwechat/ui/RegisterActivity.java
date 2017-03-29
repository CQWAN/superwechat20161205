/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ucai.superwechat.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.db.IUserModel;
import cn.ucai.superwechat.db.OnCompleteListener;
import cn.ucai.superwechat.db.UserModel;
import cn.ucai.superwechat.utils.CommonUtils;
import cn.ucai.superwechat.utils.MD5;
import cn.ucai.superwechat.utils.MFGT;
import cn.ucai.superwechat.utils.Result;
import cn.ucai.superwechat.utils.ResultUtils;

/**
 * register screen
 */
public class RegisterActivity extends BaseActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    @BindView(R.id.username)
    EditText mEtUsername;
    @BindView(R.id.usernick)
    EditText mEtUsernick;
    @BindView(R.id.password)
    EditText mEtPassword;
    @BindView(R.id.confirm_password)
    EditText mEtConfirmPassword;

    String username;
    String userNick;
    String password;
    String confirmPassword;

    ProgressDialog pd;
    IUserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userModel = new UserModel();
        setContentView(R.layout.em_activity_register);
        ButterKnife.bind(this);
    }

    /**
     * 检查用户输入
     */
    private boolean checkedInput(){
        username = mEtUsername.getText().toString().trim();
        userNick = mEtUsernick.getText().toString().trim();
        password = mEtPassword.getText().toString().trim();
        confirmPassword = mEtConfirmPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
            mEtUsername.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(userNick)) {
            Toast.makeText(this, getResources().getString(R.string.toast_nick_not_isnull), Toast.LENGTH_SHORT).show();
            mEtUsernick.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            mEtPassword.requestFocus();
            return false;
        }else if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, getResources().getString(R.string.Confirm_password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            mEtConfirmPassword.requestFocus();
            return false;
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(this, getResources().getString(R.string.Two_input_password), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 环信端注册
     */
    public void registerHxServer() {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        // call method in SDK
                        EMClient.getInstance().createAccount(username, MD5.getMessageDigest(password));
                        runOnUiThread(new Runnable() {
                            public void run() {
                                if (!RegisterActivity.this.isFinishing())
                                    pd.dismiss();
                                // save current user
                                SuperWeChatHelper.getInstance().setCurrentUserName(username);
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registered_successfully), Toast.LENGTH_SHORT).show();
                                MFGT.gotoLogin(RegisterActivity.this);
                            }
                        });
                    } catch (final HyphenateException e) {
                        unregister();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                if (!RegisterActivity.this.isFinishing())
                                    pd.dismiss();
                                int errorCode = e.getErrorCode();
                                if (errorCode == EMError.NETWORK_ERROR) {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_anomalies), Toast.LENGTH_SHORT).show();
                                } else if (errorCode == EMError.USER_ALREADY_EXIST) {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.User_already_exists), Toast.LENGTH_SHORT).show();
                                } else if (errorCode == EMError.USER_AUTHENTICATION_FAILED) {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.registration_failed_without_permission), Toast.LENGTH_SHORT).show();
                                } else if (errorCode == EMError.USER_ILLEGAL_ARGUMENT) {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.illegal_user_name), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registration_failed), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }).start();
    }

    private void unregister() {
        userModel.unRegister(RegisterActivity.this, username, new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "result=" + result);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    /**
     * 显示Dialog
     */
    private void showDialog() {
        pd = new ProgressDialog(this);
        pd.setMessage(getResources().getString(R.string.Is_the_registered));
        pd.show();
    }

    public void back(View view) {
        finish();
    }

    /**
     * 注册按钮的点击事件
     */
    @OnClick(R.id.register)
    public void onClick() {
        if (checkedInput()) {
            registerMyServer();
        }
    }

    private void registerMyServer() {
        showDialog();
        userModel.register(RegisterActivity.this, username, userNick, MD5.getMessageDigest(password),
                new OnCompleteListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        boolean isSuccess = false;
                        if (s != null) {
                            Result result = ResultUtils.getListResultFromJson(s, String.class);
                            if (result != null) {
                                if (result.isRetMsg()) {
                                    isSuccess = true;
                                    registerHxServer();
                                } else if (result.getRetCode() == I.MSG_REGISTER_USERNAME_EXISTS) {
                                    CommonUtils.showShortToast(R.string.User_already_exists);
                                } else {
                                    CommonUtils.showShortToast(R.string.Registration_failed);
                                }
                            }
                        }
                        if (!isSuccess) {
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        pd.dismiss();
                        CommonUtils.showShortToast(R.string.Registration_failed);
                    }
                });
    }
}