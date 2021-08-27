package com.example.chap01_userinfo;

public enum Level {
    GOLD(3, null),
    SILVER(2, GOLD),
    BASIC(1,SILVER);
    //Enum선언에 DB에 저장할 값과 함께 다음 단계의 레벨 정보도 추가한다.


    private final int value;
    private final Level next;
    //다음 단계의 레벨 정보를 스스로 갖고 있도록 Level 타입의 next변수를 추가한다.

    Level(int value, Level next) {
        this.value = value;
        this.next = next;
    }

    public int intValue() {
        return value;
    }

    public Level nextLevel() {
        return this.next;
    }

    public static Level valueOf(int value) {
        switch (value) {
            case 1:
                return BASIC;
            case 2:
                return SILVER;
            case 3:
                return GOLD;
            default:
                throw new AssertionError("Unknown value: " + value);
        }
    }
}
