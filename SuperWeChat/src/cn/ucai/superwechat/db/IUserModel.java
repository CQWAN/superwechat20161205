package cn.ucai.superwechat.db;

import android.content.Context;

/**
 * Created by LPP on 2017/3/29.
 */

public interface IUserModel {
    public void register(Context context, String userName, String userNick, String password, OnCompleteListener<String> completeListener);
    public void login(Context context,String userName,String password,OnCompleteListener<String> completeListener);
    public void unRegister(Context context, String userName, OnCompleteListener<String> onCompleteListener);
    public void loadUserInfo(Context context,String userName,OnCompleteListener<String> onCompleteListener);
}
