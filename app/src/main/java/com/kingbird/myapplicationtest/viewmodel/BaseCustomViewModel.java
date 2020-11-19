package com.kingbird.myapplicationtest.viewmodel;

import java.io.Serializable;

/**
 *  网络异常、消息异常提示
 *
 * @author panyingdao
 * @date 2020/10/3.
 */
public class BaseCustomViewModel implements Serializable {

    public int imageHint;
    public String textHint;

    public int getImageHint() {
        return imageHint;
    }

    public void setImageHint(int imageHint) {
        this.imageHint = imageHint;
    }

    public String getTextHint() {
        return textHint;
    }

    public void setTextHint(String textHint) {
        this.textHint = textHint;
    }
}
