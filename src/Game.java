import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    public static void startGame() throws IOException {
        System.out.println(GameMsg.GAME_START);
        //Handオブジェクト作成
        List<Card> p_hands = new ArrayList<Card>();
        Hand playersHands = new Hand(p_hands);
        List<Card> d_hands = new ArrayList<Card>();
        Hand dealersHands = new Hand(d_hands);

        //初期ポイント設定
        playersHands.setPoint(GameMsg.START_POINT);
        while (playersHands.getPoint()>GameMsg.LOSE_POINT && playersHands.getPoint()<GameMsg.WIN_POINT){

            //掛けポイント入力
            betPoint(playersHands);

            //デッキ作成
            CardStuck.createDeck();

            //プレイヤーにカードを2枚配る
            System.out.println(GameMsg.GET_TWO_CARD);
            playersHands.addHands(CardStuck.drawCard());
            playersHands.addHands(CardStuck.drawCard());

            //ディーラーにカードを2枚配る
            dealersHands.addHands(CardStuck.drawCard());
            dealersHands.addHands(CardStuck.drawCard());

            //プレイヤーのカードを表示
            System.out.println(GameMsg.YOUR_CARDS);
            showHands(playersHands.getHands());
            System.out.println(GameMsg.SUM + playersHands.getHandsSum());

            //ディーラーのカードを表示
            System.out.println(GameMsg.DEALERS_CARDS);
            System.out.println(dealersHands.getHands().get(0).getMark()+dealersHands.getHands().get(0).getNum_s()+"\t*");

            //プレイヤーがカードを引くかどうか選択
            drawOrStand(playersHands);

            //ディーラーがカードを引く
            System.out.println(GameMsg.DEALERS_TURN);
            DealersAction(dealersHands);

            //勝敗判定
            int result = Judge.judge(playersHands,dealersHands);

            //元のポイントを保持
            int oldPoint = playersHands.getPoint();
            //点数計算
            playersHands.calculatePoint(result,playersHands);

            //結果のポイント
            System.out.println(GameMsg.MY_POINT+oldPoint+"->"+playersHands.getPoint());

            //手札情報をクリアする
            playersHands.clearHandsInfo();
            dealersHands.clearHandsInfo();
        }
        //ゲーム終了
        if(playersHands.getPoint()>=GameMsg.WIN_POINT){
            System.out.println(GameMsg.WIN_FINISH);
        }
        else if(playersHands.getPoint()<=GameMsg.LOSE_POINT){
            System.out.println(GameMsg.LOSE_FINISH);
        }
    }

    //手札出力メソッド
    private static void showHands(List<Card> hands){
        for (int i = 0; i< hands.size(); i++){
            System.out.print(hands.get(i).getMark()+hands.get(i).getNum_s()+"\t");
        }
        System.out.println();
    }

    //プレイヤーがカードを引くかどうか選択
    private static void drawOrStand(Hand hands) throws IOException {
        System.out.println(GameMsg.ASK_DRAW_CARD);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        while (true){
            String str = br.readLine();
            str = str.toUpperCase();
            if(str.equals(GameMsg.YES)){
                hands.addHands(CardStuck.drawCard());
                showHands(hands.getHands());
                System.out.println(GameMsg.SUM + hands.getHandsSum());
                if(hands.getHandsSum()<GameMsg.BURST_NUM){
                    System.out.println(GameMsg.ASK_DRAW_CARD);
                    continue;
                }
                else if(hands.getHandsSum()>GameMsg.BURST_NUM){
                    System.out.println(GameMsg.BURST);
                    return;
                }
                else if(hands.getHandsSum()==GameMsg.BURST_NUM){
                    return ;
                }
            }
            else if(str.equals(GameMsg.NO)){
                return ;
            }
            else {
                System.out.println(GameMsg.INPUT_ERROR);
            }
        }
    }

    //賭けポイント入力メソッド
    private static void betPoint(Hand hands) throws IOException {
        //賭けポイントを入力
        System.out.print(GameMsg.BET_POINT);
        System.out.println(GameMsg.MY_POINT+ hands.getPoint());
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int bet ;
        while (true) {
            String str = br.readLine();
            if(isNumber(str)){
                bet = Integer.parseInt(str);
                if(bet>=GameMsg.MIN_BET_POINT&&bet<=GameMsg.MAX_BET_POINT){
                    hands.setBet(bet);
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
    private static void DealersAction(Hand hands){
        showHands(hands.getHands());
        System.out.println(GameMsg.SUM + hands.getHandsSum());
        //手札の合計が17以上になるまで引く
        while (hands.getHandsSum()<GameMsg.STOP_DRAW){
            hands.addHands(CardStuck.drawCard());
            showHands(hands.getHands());
            System.out.println(GameMsg.SUM + hands.getHandsSum());
        }
        if(hands.getHandsSum()>GameMsg.BURST_NUM){
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
