package com.kingbird.myapplicationtest;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

/**
 * 说明：
 *
 * @author :Pan Yingdao
 * @date : on 2020/11/18 0018 14:28:41
 */
public class ViewUtils {

   public static Drawable[] getDrawable(View view) {
        StateListDrawable drawable = (StateListDrawable) view.getBackground();
        DrawableContainer.DrawableContainerState dcs = (DrawableContainer.DrawableContainerState) drawable.getConstantState();
        if (dcs != null) {
            return dcs.getChildren();
        }
        return null;
    }
}
