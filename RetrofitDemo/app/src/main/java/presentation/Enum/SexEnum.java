package presentation.Enum;

/**
 * Created by Winkey on 2017/7/12.
 */

public enum SexEnum {
    Unkonwn(-1),
    /**
     * 男性
     */
    男性(0),
    /**
     * 女性
     */
    女性(1),
    /**
     * 人妖
     */
    人妖(2);

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
                key = 男性;
                break;
            case 1:
                key = 女性;
                break;
            case 2:
                key = 人妖;
                break;
            default:
                key = Unkonwn;
                break;
        }
        return key;
    }
}
