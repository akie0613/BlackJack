public class Card {
    //トランプの数宇
    private String num_s;
    //トランプの点数
    private int num_i;
    //トランプのマーク
    private String mark;

    public Card( int randNum, int randMark ) {
        switch (randNum) {
            case 0:
                num_s = "A";
                num_i =1;
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                num_s = Integer.toString(randNum + 1);
                num_i = randNum +1;
                break;
            case 10:
                num_s = "J";
                num_i = 10;
                break;
            case 11:
                num_s = "Q";
                num_i = 10;
                break;
            case 12:
                num_s = "K";
                num_i = 10;
                break;
        }
        switch (randMark) {
            case 0:
                mark="スペード";
                break;
            case 1:
                mark="クラブ";
                break;
            case 2:
                mark="ハート";
                break;
            case 3:
                mark="ダイヤ";
                break;
        }

    }
    public String getNum_s() {
        return num_s;
    }
    public String getMark() {
        return mark;
    }
    public int getNum_i() {
        return num_i;
    }
}

