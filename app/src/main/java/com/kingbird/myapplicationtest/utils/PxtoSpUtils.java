package com.kingbird.myapplicationtest.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * 说明：单位转换（px -> sp）
 *
 * @author :Pan Yingdao
 * @date : on 2020/11/20 0020 16:41:41
 */
public class PxtoSpUtils {

    public static float dpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static float spToPx(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }

    public static float pxToSp(float px) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (px / fontScale + 0.5f);
    }
}
