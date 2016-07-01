package com.sina.home.testfor_newclient.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Walter on 2016/2/15.
 * 键盘的工具类 打开和关闭软键盘
 */
public class KeyBoardUtil {
    private static final String TAG = "KeyBoardUtil";

    //打卡软键盘
    public static void openKeyBoard(EditText mEditText, Context mContext){
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    //关闭软键盘
    public static void closeKeyBoard(EditText mEditText, Context mContext){
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
