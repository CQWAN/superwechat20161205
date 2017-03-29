package cn.ucai.superwechat.db;

import android.content.Context;

import cn.ucai.superwechat.I;
import cn.ucai.superwechat.utils.OkHttpUtils;

/**
 * Created by LPP on 2017/3/29.
 */

public class UserModel implements IUserModel {
    @Override
    public void register(Context context, String userName, String userNick, String password, OnCompleteListener<String> completeListener) {
        OkHttpUtils<String> okHttpUtils = new OkHttpUtils(context);
        okHttpUtils.setRequestUrl(I.REQUEST_REGISTER)
                .addParam(I.User.USER_NAME,userName)
                .addParam(I.User.NICK,userNick)
                .addParam(I.User.PASSWORD,password)
                .post()
                .targetClass(String.class)
                .execute(completeListener);
    }

    @Override
    public void login(Context context, String userName, String password, OnCompleteListener<String> completeListener) {
        OkHttpUtils<String> okHttpUtils = new OkHttpUtils<>(context);
        okHttpUtils.setRequestUrl(I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME,userName)
                .addParam(I.User.PASSWORD,password)
                .targetClass(String.class)
                .execute(completeListener);
    }

    @Override
    public void unRegister(Context context, String userName, OnCompleteListener<String> completeListener) {
        OkHttpUtils<String> okHttpUtils = new OkHttpUtils<>(context);
        okHttpUtils.setRequestUrl(I.REQUEST_UNREGISTER)
                .addParam(I.User.USER_NAME,userName)
                .targetClass(String.class)
                .execute(completeListener);
    }

    @Override
    public void loadUserInfo(Context context, String userName, OnCompleteListener<String> completeListener) {
        OkHttpUtils<String> okHttpUtils = new OkHttpUtils<>(context);
        okHttpUtils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME,userName)
                .targetClass(String.class)
                .execute(completeListener);
    }
}
