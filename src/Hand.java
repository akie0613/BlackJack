import java.util.List;

public class Hand {
    // フィールド
    private int point ;
    private List<Card> hands;
    private int bet ;
    private int handsSum;
    // コンストラクタ
    public Hand( List<Card> handsList ) {
        hands = handsList;
    }

    public  void addHands(Card card) {
        hands.add(card);
        //カードを引くと同時にハンドの合計を記録する
        handsSum =0;
        boolean containsA = false;
        for (int i = 0; i<hands.size();i++){
            handsSum = handsSum + hands.get(i).getNum_i();
            if(hands.get(i).getNum_i() == 1){
                containsA = true;
            }
        }
        //ハンドにAが含まれている場合の処理
        if(containsA){
            if(handsSum<=11){
                handsSum = handsSum +10;
            }
        }
    }

    public void calculatePoint(int result,Hand hands) {
        List<Card> handsList = hands.getHands();
        int handSum = hands.getHandsSum();
        int bet = hands.getBet();
        int point = hands.getPoint();
        if(result==GameMsg.WIN){
            //ブラックジャックの場合
            if(handsList.size()==2){
                if(handSum==GameMsg.BURST_NUM){
                    if(containsNum(handsList,"J")){
                        //スペードのAとJのブラックジャック
                        if((handsList.get(0).getNum_s().equals("A")&&handsList.get(0).getMark().equals("スペード"))
                                ||(handsList.get(1).getNum_s().equals("A")&&handsList.get(1).getMark().equals("スペード")))
                        {
                            System.out.println(GameMsg.BJ_A);
                            hands.point = point+ (bet*15);
                        }
                        //スペード以外のAとJのブラックジャック
                        else{
                            System.out.println(GameMsg.BJ_B);
                            hands.point = point+ (bet*5);
                        }
                    }
                    else {
                        //AとJ以外のブラックジャック
                        System.out.println(GameMsg.BJ_C);
                        hands.point = (int) (point + (bet*2.5));
                    }
                    return;
                }
            }
            //手札が７，７，７の場合
            else if(handsList.size()==3){
                if(handsList.get(0).getNum_s().equals("7")
                        && handsList.get(1).getNum_s().equals("7")
                        && handsList.get(2).getNum_s().equals("7")
                ){
                    System.out.println(GameMsg.THREE_SEVEN);
                    hands.point = point+ (bet*10);
                    return;
                }
            }
            //手札が6枚の場合
            else if(handsList.size()==6){
                System.out.println(GameMsg.SIX_CARD);
                hands.point = point+ (bet*5);
                return;
            }
            //手札が7枚の場合
            else if(handsList.size()>=7){
                System.out.println(GameMsg.SEVEN_CARD);
                hands.point = point+ (bet*10);
                return;
            }
            //その他勝ちの場合
            hands.point = point + bet;

        }
        else if(result==GameMsg.LOSE){
            hands.point = point-bet;
        }
    }

    //手札に指定のナンバーがあるかどうかを調べるメソッド
    public static boolean containsNum(List<Card> hands, String str){
        return hands.stream().filter(o -> o.getNum_s().equals(str)).findFirst().isPresent();
    }

    //手札の情報をクリア
    public void clearHandsInfo() {
        hands.clear();
    }

    public void setPoint(int point) {
        this.point = point;
    }
    public int getPoint() {
        return point;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
    public int getBet() {
        return bet;
    }

    public List<Card> getHands() {
        return hands;
    }

    public int getHandsSum() {
        return handsSum;
    }
}
