import java.util.ArrayList;
import java.util.List;

public class Hand {
    // フィールド
    private static List<Card> playersHands = new ArrayList<>();
    private static int playersHandsSum ;
    private static List<Card> dealersHands = new ArrayList<>();
    private static int dealersHandsSum ;
    private static int point =1000;
    private static int bet ;


    public static void addPlayersHands(Card card) {

        playersHands.add(card);

        //カードを引くと同時にハンドの合計を記録する
        playersHandsSum =0;
        boolean containsA = false;
        for (int i = 0; i<playersHands.size();i++){
            playersHandsSum = playersHandsSum + playersHands.get(i).getNum_i();
            if(playersHands.get(i).getNum_i() == 1){
                containsA = true;
            }
        }
        //ハンドにAが含まれている場合の処理
        if(containsA){
            if(playersHandsSum<=11){
                playersHandsSum = playersHandsSum +10;
            }
        }
    }

    public static void addDealersHands(Card card) {

        dealersHands.add(card);

        //カードを引くと同時にハンドの合計を記録する
        dealersHandsSum =0;
        boolean containsA = false;
        for (int i = 0; i<dealersHands.size();i++){
            dealersHandsSum = dealersHandsSum + dealersHands.get(i).getNum_i();
            if(dealersHands.get(i).getNum_i() == 1){
                containsA = true;
            }
        }
        //ハンドにAが含まれている場合の処理
        if(containsA){
            if(dealersHandsSum<=11){
                dealersHandsSum = dealersHandsSum +10;
            }
        }
    }

    public static void calculatePoint(int result) {
        if(result==GameMsg.WIN){
            //ブラックジャックの場合
            if(playersHands.size()==2){
                if(playersHandsSum==21){
                    if(containsNum(Hand.getPlayersHands(),"J")){
                        //スペードのAとJのブラックジャック
                        if((Hand.getPlayersHands().get(0).getNum().equals("A")&&Hand.getPlayersHands().get(0).getMark().equals("スペード"))
                                ||(Hand.getPlayersHands().get(1).getNum().equals("A")&&Hand.getPlayersHands().get(1).getMark().equals("スペード")))
                        {
                            System.out.println(GameMsg.BJ_A);
                            point = point+ (bet*15);
                        }
                        //スペード以外のAとJのブラックジャック
                        else{
                            System.out.println(GameMsg.BJ_B);
                            point = point+ (bet*5);
                        }
                    }
                    else {
                        //AとJ以外のブラックジャック
                        System.out.println(GameMsg.BJ_C);
                        point = (int) (point + (bet*2.5));
                    }
                    return;
                }
            }
            //手札が７，７，７の場合
            else if(playersHands.size()==3){
                if(playersHands.get(0).getNum().equals("7")
                && playersHands.get(1).getNum().equals("7")
                && playersHands.get(2).getNum().equals("7")
                ){
                    System.out.println(GameMsg.THREE_SEVEN);
                    point = point+ (bet*10);
                    return;
                }
            }
            //手札が6枚の場合
            else if(playersHands.size()==6){
                System.out.println(GameMsg.SIX_CARD);
                point = point+ (bet*5);
                return;
            }
            //手札が7枚の場合
            else if(playersHands.size()>=7){
                System.out.println(GameMsg.SEVEN_CARD);
                point = point+ (bet*10);
                return;
            }
            //その他勝ちの場合
            point = point + bet;

        }
        else if(result==GameMsg.LOSE){
            point = point-bet;
        }
    }

    public static List<Card> getPlayersHands() {
        return playersHands;
    }
    public static int getPlayersHandsSum() {
        return playersHandsSum;
    }
    public static List<Card> getDealersHands() {
        return dealersHands;
    }
    public static int getDealersHandsSum() {
        return dealersHandsSum;
    }
    public static int getPoint() {
        return point;
    }
    public static void setBet(int bet) {
        Hand.bet = bet;
    }

    //手札に指定のナンバーがあるかどうかを調べるメソッド
    public static boolean containsNum(List<Card> hands, String str){
        return hands.stream().filter(o -> o.getNum().equals(str)).findFirst().isPresent();
    }

    //手札の情報をクリア
    public static void clearHandsInfo() {
        playersHands.clear();
        dealersHands.clear();
    }
}
