package com.example.winkey.retrofitdemo.presentation.Enum;

/**
 * Created by Winkey on 2017/7/12.
 */

public enum SexEnum {
    Unkonwn(-1),
    /**
     * 男性
     */
    man(0),
    /**
     * 女性
     */
    women(1),
    /**
     * 人妖
     */
    hentai(2);

    private int value;

    SexEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public SexEnum getKey(int value){
        SexEnum key = null;
        switch (value){
            case 0:
                key = man;
                break;
            case 1:
                key = women;
                break;
            case 2:
                key = hentai;
                break;
            default:
                key = Unkonwn;
                break;
        }
        return key;
    }
}
