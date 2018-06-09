package com.example.administrator.simulation_weekone;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/6/9.
 */

public class HeadText extends LinearLayout implements View.OnClickListener{

    private TextView title_text;
    private Button left_btn;
    private Button right_btn;
    private Custom_HeadText cc;
    private TextView textView;
    private int context=0;
    private int width;

    public HeadText(Context context) {
        super(context);
        initView(context,null);
    }

    public HeadText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View inflate = inflate(context, R.layout.headtext_layout, this);
        cc = inflate.findViewById(R.id.custom_headtext);
        title_text = inflate.findViewById(R.id.title_text);
        left_btn = inflate.findViewById(R.id.left_btn);
        right_btn = inflate.findViewById(R.id.right_btn);
        width = AppUtils.screenWidth(getContext());
        left_btn.setOnClickListener(this);
        right_btn.setOnClickListener(this);
        title_text.setOnClickListener(this);
        Custom_HeadText custom_headText = new Custom_HeadText(getContext());

        if(attrs==null){
            return;
        }
        initAttrs(context,attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadText);

        title_text.setText(typedArray.getString(R.styleable.HeadText_title_text));
        left_btn.setText(typedArray.getString(R.styleable.HeadText_left_btn));
        right_btn.setText(typedArray.getString(R.styleable.HeadText_right_btn));

        title_text.setTextSize(typedArray.getDimension(R.styleable.HeadText_title_text_size,16));
        left_btn.setTextSize(typedArray.getDimension(R.styleable.HeadText_left_btn_size,16));
        right_btn.setTextSize(typedArray.getDimension(R.styleable.HeadText_right_btn_size,16));

        title_text.setTextColor(typedArray.getColor(R.styleable.HeadText_title_text_color, Color.BLACK));
        left_btn.setTextColor(typedArray.getColor(R.styleable.HeadText_left_btn_color,Color.BLACK));
        right_btn.setTextColor(typedArray.getColor(R.styleable.HeadText_right_btn_color,Color.BLACK));

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            left_btn.setBackground(typedArray.getDrawable(R.styleable.HeadText_left_btn_bg_color));
            right_btn.setBackground(typedArray.getDrawable(R.styleable.HeadText_right_btn_bg_color));
        }else{
            left_btn.setBackgroundDrawable(typedArray.getDrawable(R.styleable.HeadText_left_btn_bg_color));
            right_btn.setBackgroundDrawable(typedArray.getDrawable(R.styleable.HeadText_right_btn_bg_color));
        }

    }


    @Override
    public void onClick(View v) {
        context++;

            if(v.equals(left_btn)){

                textView = new TextView(v.getContext());
                    textView.setWidth(width/2);
                    textView.setHeight(100);
                    textView.setText(""+context);
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(Color.BLUE);
                    textView.setBackgroundColor(Color.YELLOW);
                    cc.addView(textView);

            }else if(v.equals(right_btn)){

            }else if(v.equals(title_text)){
               cc.removeAllViews();
               context=0;
            }

    }
}
