public class Card {
    // フィールド
    private String num;
    private int num_i;
    private String mark;

    // コンストラクタ
    public Card( int randNum, int randMark ) {
        //num = Integer.toString(randNum);
        switch (randNum) {
            case 0:
                num = "A";
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
                num = Integer.toString(randNum + 1);
                num_i = randNum +1;
                break;
            case 10:
                num = "J";
                num_i = 10;
                break;
            case 11:
                num = "Q";
                num_i = 10;
                break;
            case 12:
                num = "K";
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
    public String getNum() {
        return num;
    }
    public String getMark() {
        return mark;
    }
    public int getNum_i() {
        return num_i;
    }
}

