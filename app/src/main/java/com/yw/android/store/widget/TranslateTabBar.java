package com.yw.android.store.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yw.android.store.R;

import ww.com.core.ScreenUtil;

public class TranslateTabBar extends RelativeLayout implements View.OnClickListener, Animation
        .AnimationListener {
    private static final int MATCH_PARENT = -1;
    private Context mContext;
    private int btnCount;
    private int buttonWidth;
    private int buttonHeight;
    private int translateLineWidth;
    private int translateLineHeight;
    private int dividLineWidth;
    private int dividLineHeight;
    private int currentTranslateLineIndex;
    private int textSize;
    private int splitLineColor;
    private int translateLineColor;
    private int bottomLineColor;
    private LinearLayout buttonLayout;
    private Button[] tabButton;
    private String[] stringArr;
    private View translateLineView;
    private ColorStateList btnColorState;
    private TranslateTabBar.OnTabChangeListener onChangelistener;
    private int viewWidth;

    public TranslateTabBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public TranslateTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.btnCount = 4;
        this.buttonWidth = 0;
        this.buttonHeight = -1;
        this.translateLineWidth = -1;
        this.translateLineHeight = 10;
        this.dividLineWidth = 0;
        this.dividLineHeight = 64;
        this.currentTranslateLineIndex = 0;
        this.textSize = 40;
        this.splitLineColor = Color.parseColor("#ff1930");
        this.translateLineColor = Color.parseColor("#0b86ee");
        this.bottomLineColor = Color.parseColor("#f5f5f9");
        this.btnColorState = null;
        this.mContext = context;
        this.getResourceForXml(attrs);
        this.initView();
    }

    public void onClick(View v) {
        this.setCurrentIndex(v.getId());
    }

    private void initView() {
        this.buttonLayout = new LinearLayout(this.mContext);
        this.buttonLayout.setGravity(16);
        LayoutParams p = new LayoutParams(-1, -1);
        p.addRule(15);
        this.addView(this.buttonLayout, p);
        this.setGravity(16);
        this.buttonLayout.post(new Runnable() {
            public void run() {
                TranslateTabBar.this.setText(TranslateTabBar.this.stringArr);
                TranslateTabBar.this.initLine();
            }
        });
    }

    private void initLine() {
        if (-1 == this.translateLineWidth) {
            this.translateLineWidth = this.getWidth() / this.getCount();
        }

        LayoutParams p2 = new LayoutParams(this.translateLineWidth, this.translateLineHeight);
        p2.addRule(12);
        p2.leftMargin = this.getTranslateLineOffsetX(0);
        View tempView = this.createTranslateLine();
        this.addView(tempView, p2);
        LayoutParams p3 = new LayoutParams(-1, 2);
        p3.addRule(12);
        this.setCurrentIndex(0);
    }

    private void getResourceForXml(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = this.mContext.getTheme().obtainStyledAttributes(attrs, R.styleable
                    .TranslateTabBar, 0, 0);
            int textResId = array.getResourceId(R.styleable.TranslateTabBar_text, -1);
            this.translateLineWidth = array.getLayoutDimension(R.styleable
                    .TranslateTabBar_lineWidth, this.translateLineWidth);
            if (-1 != this.translateLineWidth) {
                this.translateLineWidth = ScreenUtil.getScalePxValue(this.translateLineWidth);
            }

            this.btnColorState = array.getColorStateList(R.styleable.TranslateTabBar_textColor);
            this.splitLineColor = array.getColor(R.styleable.TranslateTabBar_spliteLineColor,
                    this.splitLineColor);
            this.translateLineColor = array.getColor(R.styleable
                    .TranslateTabBar_translateLineColor, this.translateLineColor);
            this.bottomLineColor = array.getColor(R.styleable.TranslateTabBar_bottomLineColor,
                    this.bottomLineColor);
            this.textSize = array.getDimensionPixelSize(R.styleable.TranslateTabBar_textSize,
                    this.textSize);
            this.translateLineHeight = array.getDimensionPixelOffset(R.styleable
                    .TranslateTabBar_translateLineHeight, this.translateLineHeight);
            this.translateLineHeight = ScreenUtil.getScalePxValue(this.translateLineHeight);
            this.stringArr = this.getResources().getStringArray(textResId);
            array.recycle();
        }
    }

    private void setText(String[] str) {
        this.buttonLayout.removeAllViews();
        this.btnCount = str.length;
        this.tabButton = new Button[this.btnCount];
        this.buttonWidth = this.btnCount <= 1 ? this.getWidth() : (this.getWidth() - this
                .dividLineWidth * (this.btnCount - 1)) / this.btnCount;

        for (int i = 0; i < this.btnCount; ++i) {
            LayoutParams params = new LayoutParams(this.buttonWidth, this.buttonHeight);
            this.buttonLayout.addView(this.createButton(str[i], i), params);
            if (this.btnCount > 1 && i < this.btnCount - 1) {
                LayoutParams params2 = new LayoutParams(this.dividLineWidth, this.dividLineHeight);
                this.buttonLayout.addView(this.createLine(), params2);
            }
        }

    }

    private Button createButton(String str, int index) {
        Button v = new Button(this.mContext);
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            v.setBackground((Drawable) null);
        } else {
            v.setBackgroundDrawable((Drawable) null);
        }

        v.setOnClickListener(this);
        v.setId(index);
        v.setText(String.format(str, new Object[]{"0"}));
        v.setTextSize(0, (float) ScreenUtil.getScalePxValue(this.textSize));
        v.setTextColor(this.btnColorState);
        this.tabButton[index] = v;
        return v;
    }

    private View createLine() {
        View v = new View(this.mContext);
        v.setBackgroundColor(this.splitLineColor);
        return v;
    }

    private View createTranslateLine() {
        View v = new View(this.mContext);
        v.setBackgroundColor(this.translateLineColor);
        this.translateLineView = v;
        return v;
    }

    private View createBottomLine() {
        View v = new View(this.mContext);
        v.setBackgroundColor(this.bottomLineColor);
        return v;
    }

    private int getTranslateLineOffsetX(int index) {
        int offset = (this.buttonWidth - this.translateLineWidth) / 2 + this.buttonWidth * index;
        return offset;
    }

    private TranslateAnimation translateLine(int index) {
        int fromX = this.getTranslateLineOffsetX(this.currentTranslateLineIndex) - this
                .getTranslateLineOffsetX(0);
        int toX = this.getTranslateLineOffsetX(index) - this.getTranslateLineOffsetX(0);
        TranslateAnimation anim = new TranslateAnimation((float) fromX, (float) toX, 0.0F, 0.0F);
        anim.setDuration(250L);
        anim.setFillAfter(true);
        anim.setAnimationListener(this);
        this.currentTranslateLineIndex = index;
        return anim;
    }

    public void onAnimationStart(Animation animation) {
        Button[] var2 = this.tabButton;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            Button btn = var2[var4];
            btn.setClickable(false);
        }

    }

    public void onAnimationEnd(Animation animation) {
        Button[] var2 = this.tabButton;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            Button btn = var2[var4];
            btn.setClickable(true);
        }

    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void setText(int index, String str) {
        this.tabButton[index].setText(String.format(this.stringArr[index], new Object[]{str}));
    }

    public void setOnTabChangeListener(TranslateTabBar.OnTabChangeListener _li) {
        this.onChangelistener = _li;
    }

    public int getCount() {
        return this.btnCount;
    }

    public void setCurrentIndex(int index) {
        if (index != this.currentTranslateLineIndex && this.onChangelistener != null) {
            this.onChangelistener.onChange(index);
        }

        if (this.translateLineView != null) {
            this.translateLineView.startAnimation(this.translateLine(index));
            Button[] var2 = this.tabButton;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Button btn = var2[var4];
                btn.setSelected(false);
            }

            this.tabButton[index].setSelected(true);
            this.tabButton[index].setClickable(false);
        }
    }

    public interface OnTabChangeListener {
        void onChange(int var1);
    }
}