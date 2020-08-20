import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    public static void startGame() throws IOException {
        System.out.println(GameMsg.GAME_START);
        while (Hand.getPoint()>0 && Hand.getPoint()<2000){
            //掛けポイント入力
            betPoint();

            //デッキ作成
            LinkedList<Card> deck ;
            CardStuck.createDeck();

            //プレイヤーにカードを2枚配る
            System.out.println(GameMsg.GET_TWO_CARD);
            Hand.addPlayersHands(CardStuck.drawCard());
            Hand.addPlayersHands(CardStuck.drawCard());
            //ディーラーにカードを2枚配る
            Hand.addDealersHands(CardStuck.drawCard());
            Hand.addDealersHands(CardStuck.drawCard());

            //プレイヤーのカードを表示
            System.out.println(GameMsg.YOUR_CARDS);
            showHands(Hand.getPlayersHands());
            System.out.println("合計" + Hand.getPlayersHandsSum());

            //ディーラーのカードを表示
            System.out.println(GameMsg.DEALERS_CARDS);
            System.out.println(Hand.getDealersHands().get(0).getMark()+Hand.getDealersHands().get(0).getNum()+"\t*");

            //プレイヤーがカードを引くかどうか選択
            drawOrStand();

            //ディーラーがカードを引く
            System.out.println(GameMsg.DEALERS_TURN);
            DealersAction();

            //勝敗判定
            int result = Judge.judge();

            //元のポイントを保持
            int oldPoint = Hand.getPoint();
            //点数計算
            Hand.calculatePoint(result);

            //結果のポイント
            System.out.println("所持ポイント:"+oldPoint+"->"+Hand.getPoint());

            //手札情報をクリアする
            Hand.clearHandsInfo();
        }
        //ゲーム終了
        if(Hand.getPoint()>=2000){
            System.out.println(GameMsg.WIN_FINISH);
        }
        else if(Hand.getPoint()<=0){
            System.out.println(GameMsg.LOSE_FINISH);
        }
    }

    //手札出力メソッド
    private static void showHands(List<Card> hands){
        for (int i = 0; i< hands.size(); i++){
            System.out.print(hands.get(i).getMark()+hands.get(i).getNum()+"\t");
        }
        System.out.println();
    }

    //プレイヤーがカードを引くかどうか選択
    private static void drawOrStand() throws IOException {
        System.out.println(GameMsg.ASK_DRAW_CARD);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        while (true){
            String str = br.readLine();
            str = str.toUpperCase();
            if(str.equals("Y")){
                Hand.addPlayersHands(CardStuck.drawCard());
                showHands(Hand.getPlayersHands());
                System.out.println("合計" + Hand.getPlayersHandsSum());
                if(Hand.getPlayersHandsSum()<21){
                    System.out.println(GameMsg.ASK_DRAW_CARD);
                    continue;
                }
                else if(Hand.getPlayersHandsSum()>21){
                    System.out.println(GameMsg.BURST);
                    return;
                }
                else if(Hand.getPlayersHandsSum()==21){
                    return ;
                }
            }
            else if(str.equals("N")){
                return ;
            }
            else {
                System.out.println(GameMsg.INPUT_ERROR);
            }
        }
    }

    //賭けポイント入力メソッド
    private static void betPoint() throws IOException {
        //賭けポイントを入力
        System.out.print(GameMsg.BET_POINT);
        System.out.println("\t所持ポイント: "+ Hand.getPoint());
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int bet ;
        while (true) {
            String str = br.readLine();
            if(isNumber(str)){
                bet = Integer.parseInt(str);
                if(bet>=1&&bet<=100){
                    Hand.setBet(bet);
                    break;
                }
                else {
                    System.out.print(GameMsg.BET_ERROR);
                }
            }
            else{
                System.out.print(GameMsg.BET_ERROR);
            }
        }
    }

    //ディーラーの行動メソッド
    private static void DealersAction(){
        showHands(Hand.getDealersHands());
        System.out.println("合計" + Hand.getDealersHandsSum());
        //手札の合計が17以上になるまで引く
        while (Hand.getDealersHandsSum()<17){
            Hand.addDealersHands(CardStuck.drawCard());
            showHands(Hand.getDealersHands());
            System.out.println("合計" + Hand.getDealersHandsSum());
        }
        if(Hand.getDealersHandsSum()>21){
            System.out.println(GameMsg.BURST);
        }
    }

    //数値かどうかの判定処理
    public static boolean isNumber(String str){
        boolean result = true;
        if(str.length()==0){
            result = false;
        }
        for(int i = 0; i < str.length(); i++) {

            //i文字めの文字についてCharacter.isDigitメソッドで判定する
            if(Character.isDigit(str.charAt(i))) {
                //数字の場合は次の文字の判定へ
                continue;
            }else {
                //数字でない場合は検証結果をfalseに上書きする
                result =false;
                break;
            }
        }
        return result;
    }
}
