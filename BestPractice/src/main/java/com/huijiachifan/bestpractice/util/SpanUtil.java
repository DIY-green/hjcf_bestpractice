/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;

import com.huijiachifan.bestpractice.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Description:
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月29日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class SpanUtil {

    public static final TypefaceSpan TYPEFACESPAN_DEFAULT = new TypefaceSpan("default");
    public static final TypefaceSpan TYPEFACESPAN_DEFAULT_BOLD = new TypefaceSpan("default-bokd");
    public static final TypefaceSpan TYPEFACESPAN_MONOSPACE = new TypefaceSpan("monospace"); // 等宽字体
    public static final TypefaceSpan TYPEFACESPAN_SERIF = new TypefaceSpan("serif"); // 衬线字体
    public static final TypefaceSpan TYPEFACESPAN_SANS_SERIF = new TypefaceSpan("sans-serif"); // 无衬线字体

    public static final StyleSpan STYLESPAN_NORMAL = new StyleSpan(android.graphics.Typeface.NORMAL); // 正常
    public static final StyleSpan STYLESPAN_BOLD = new StyleSpan(android.graphics.Typeface.BOLD); // 粗体
    public static final StyleSpan STYLESPAN_ITALIC = new StyleSpan(android.graphics.Typeface.ITALIC); // 斜体
    public static final StyleSpan STYLESPAN_BOLD_ITALIC = new StyleSpan(android.graphics.Typeface.BOLD_ITALIC); // 粗斜体

    public static final String URLSPAN_TEL = "tel:"; // 电话
    public static final String URLSPAN_MAILTO = "mailto:"; // 邮件
    public static final String URLSPAN_HTTP = "http:"; // 网络
    public static final String URLSPAN_SMSTO = "smsto:"; // 短信
    public static final String URLSPAN_MMSTO = "mmsto:"; // 彩信
    public static final String URLSPAN_GEO = "geo:"; // 地图

    /**
     * 设置字体(default,default-bold,monospace,serif,sans-serif)
     * @param string
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getTypefaceSpan(String string, @NonNull TypefaceSpan typefaceSpan, int start, int end) {
        SpannableString SpannableString = createSpannableString(string);
        SpannableString.setSpan(typefaceSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return SpannableString;
    }

    /**
     * 设置字体大小（绝对值,单位：像素）
     * @param string
     * @param fontSize
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getAbsoluteSizeSpan(String string, int fontSize, int start, int end) {
        SpannableString SpannableString = createSpannableString(string);
        SpannableString.setSpan(new AbsoluteSizeSpan(fontSize), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return SpannableString;
    }

    /**
     * 设置字体大小（绝对值）
     * @param string
     * @param fontSize
     * @param dip 如果为true，表示前面的字体大小单位为dip，否则为像素
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getAbsoluteSizeSpan(String string, int fontSize, boolean dip, int start, int end) {
        SpannableString SpannableString = createSpannableString(string);
        SpannableString.setSpan(new AbsoluteSizeSpan(fontSize, dip), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return SpannableString;
    }

    /**
     * 设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍
     * @param string
     * @param fontSize
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getRelativeSizeSpan(String string, float fontSize, int start, int end) {
        SpannableString SpannableString = createSpannableString(string);
        SpannableString.setSpan(new RelativeSizeSpan(fontSize), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return SpannableString;
    }

    /**
     * 设置字体前景色
     * @param string
     * @param color
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getForegroundColorSpan(String string, int color, int start, int end) {
        SpannableString spannableString = createSpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 设置字体背景色
     * @param string
     * @param color
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getBackgroundColorSpan(String string, int color, int start, int end) {
        SpannableString spannableString = createSpannableString(string);
        spannableString.setSpan(new BackgroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 设置字体样式正常，粗体，斜体，粗斜体
     * @param string
     * @param styleSpan
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getStyleSpan(String string, @NonNull StyleSpan styleSpan, int start, int end) {
        SpannableString spannableString = createSpannableString(string);
        spannableString.setSpan(styleSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 设置下划线
     * @param string
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getUnderlineSpan(String string, int start, int end) {
        SpannableString spannableString = createSpannableString(string);
        spannableString.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 设置删除线
     * @param string
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getStrikethroughSpan(String string, int start, int end) {
        SpannableString spannableString = createSpannableString(string);
        spannableString.setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 设置下标
     * @param string
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getSubscriptSpan(String string, int start, int end) {
        SpannableString spannableString = createSpannableString(string);
        spannableString.setSpan(new SubscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 设置上标
     * @param string
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getSuperscriptSpan(String string, int start, int end) {
        SpannableString spannableString = createSpannableString(string);
        spannableString.setSpan(new SuperscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 超级链接（需要添加setMovementMethod方法附加响应）
     *      例 ：mTextView.setMovementMethod(LinkMovementMethod.getInstance());
     * @param string 多个参数，使用英文逗号分隔
     * @param urlType 如：URLSPAN_TEL
     * @param target 链接的目的地
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getURLSpan(String string, String target, String urlType, int start, int end) {
        SpannableString spannableString = createSpannableString(string);
        spannableString.setSpan(new URLSpan(urlType + target), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 设置字体大小（相对值,单位：像素）
     * @param string
     * @param scaleX 参数表示为默认字体宽度的多少倍(X轴方向缩放，高度不变)
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getScaleXSpan(String string, float scaleX, int start, int end) {
        SpannableString spannableString = createSpannableString(string);
        spannableString.setSpan(new ScaleXSpan(scaleX), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     *设置项目符号
     * @param string
     * @param gapWidth 项目符号占用的宽度，可参考 android.text.style.BulletSpan.STANDARD_GAP_WIDTH
     * @param color
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getBulletSpan(String string, int gapWidth, int color, int start, int end) {
        SpannableString spannableString = createSpannableString(string);
        spannableString.setSpan(new BulletSpan(gapWidth, color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 设置图片
     * @param string
     * @param drawable
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getImageSpan(String string, Drawable drawable, int start, int end) {
        SpannableString spannableString = createSpannableString(string);
        spannableString.setSpan(new ImageSpan(drawable), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @NonNull
    private static SpannableString createSpannableString(@NonNull String string) {
        return new SpannableString(string);
    }

    /**
     * 该方法仅供综合使用各种Span测试使用
     * @return
     */
    public static SpannableString getSynthesizeSpan(Context context) {
        // 创建一个 SpannableString对象
        SpannableString spannableString = new SpannableString("字体测试字体大小一半两倍前景色背景色正常粗体斜体粗斜体下划线删除线x1x2电话邮件网站短信彩信地图X轴综合/bot");

        // 设置字体(default,default-bold,monospace,serif,sans-serif)
        spannableString.setSpan(new TypefaceSpan("monospace"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TypefaceSpan("serif"), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置字体大小（绝对值,单位：像素）
        spannableString.setSpan(new AbsoluteSizeSpan(20), 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(20, true), 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  // 第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。

        // 设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍
        spannableString.setSpan(new RelativeSizeSpan(0.5f), 8, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  // 0.5f表示默认字体大小的一半
        spannableString.setSpan(new RelativeSizeSpan(2.0f), 10, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  // 2.0f表示默认字体大小的两倍

        // 设置字体前景色
        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 12, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  // 设置前景色为洋红色

        // 设置字体背景色
        spannableString.setSpan(new BackgroundColorSpan(Color.CYAN), 15, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  // 设置背景色为青色

        // 设置字体样式正常，粗体，斜体，粗斜体
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.NORMAL), 18, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  // 正常
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 20, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  // 粗体
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 22, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  // 斜体
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 24, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  // 粗斜体

        // 设置下划线
        spannableString.setSpan(new UnderlineSpan(), 27, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置删除线
        spannableString.setSpan(new StrikethroughSpan(), 30, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置上下标
        spannableString.setSpan(new SubscriptSpan(), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     // 下标
        spannableString.setSpan(new SuperscriptSpan(), 36, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   // 上标

        // 超级链接（需要添加setMovementMethod方法附加响应）
        spannableString.setSpan(new URLSpan("tel:4155551212"), 37, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     // 电话
        spannableString.setSpan(new URLSpan("mailto:webmaster@google.com"), 39, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     // 邮件
        spannableString.setSpan(new URLSpan("http://www.baidu.com"), 41, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     // 网络
        spannableString.setSpan(new URLSpan("sms:4155551212"), 43, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     // 短信   使用sms:或者smsto:
        spannableString.setSpan(new URLSpan("mms:4155551212"), 45, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     // 彩信   使用mms:或者mmsto:
        spannableString.setSpan(new URLSpan("geo:38.899533,-77.036476"), 47, 49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     // 地图

        // 设置字体大小（相对值,单位：像素） 参数表示为默认字体宽度的多少倍
        spannableString.setSpan(new ScaleXSpan(2.0f), 49, 51, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 2.0f表示默认字体宽度的两倍，即X轴方向放大为默认字体的两倍，而高度不变

        // 设置字体（依次包括字体名称，字体大小，字体样式，字体颜色，链接颜色）
        ColorStateList csllink = null;
        ColorStateList csl = null;
        XmlResourceParser xppcolor = context.getResources().getXml(R.color.color);
        try {
            csl = ColorStateList.createFromXml(context.getResources(), xppcolor);
        } catch (XmlPullParserException e) {
            Logger.e(e);
        } catch (IOException e) {
            Logger.e(e);
        }

        XmlResourceParser xpplinkcolor = context.getResources().getXml(R.color.linkcolor);
        try {
            csllink = ColorStateList.createFromXml(context.getResources(), xpplinkcolor);
        } catch (XmlPullParserException e) {
            Logger.e(e);
        } catch (IOException e) {
            Logger.e(e);
        }
        spannableString.setSpan(new TextAppearanceSpan("monospace", android.graphics.Typeface.BOLD_ITALIC, 30, csl, csllink), 51, 53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置项目符号
        spannableString.setSpan(new BulletSpan(android.text.style.BulletSpan.STANDARD_GAP_WIDTH, Color.GREEN), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 第一个参数表示项目符号占用的宽度，第二个参数为项目符号的颜色

        // 设置图片
        Drawable drawable = context.getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannableString.setSpan(new ImageSpan(drawable), 53, 57, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}
