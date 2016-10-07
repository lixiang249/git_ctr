package com.example.lixiang.softlogin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import android.widget.TextView;

/**
 * 自定义一个TextView，使他天生就有焦点
 * @author Administrator
 *
 */
public class marqueeTextview extends TextView {

    public marqueeTextview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public marqueeTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public marqueeTextview(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    /**
     * 欺骗Android系统，让当前没有焦点的判断为true，实现button效果
     */
    @Override
    @ExportedProperty(category = "focus")
    public boolean isFocused() {
        // TODO Auto-generated method stub
        return true;
    }
}