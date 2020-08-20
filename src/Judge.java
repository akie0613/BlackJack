import java.util.LinkedList;
import java.util.List;

public class Judge {
    public static int judge() {
        Boolean p_isWin;
        if(Hand.getPlayersHandsSum()>21){
            System.out.println(GameMsg.YOU_LOSE);
            return GameMsg.LOSE;
        }
        else if(Hand.getDealersHandsSum()>21){
            System.out.println(GameMsg.YOU_WIN);
            return GameMsg.WIN;
        }

        if(Hand.getPlayersHandsSum()>Hand.getDealersHandsSum()){
            System.out.println(GameMsg.YOU_WIN);
            return GameMsg.WIN;
        }
        else if(Hand.getPlayersHandsSum()<Hand.getDealersHandsSum()){
            System.out.println(GameMsg.YOU_LOSE);
            return GameMsg.LOSE;
        }
        //同点の場合の処理
        else {
            if(Hand.getPlayersHandsSum()==21){
                if(Hand.getPlayersHands().size()==2&&Hand.getDealersHands().size()>=3){
                    System.out.println(GameMsg.YOU_WIN);
                    return GameMsg.WIN;
                }
                else if(Hand.getPlayersHands().size()>=3&&Hand.getDealersHands().size()==2){
                    System.out.println(GameMsg.YOU_LOSE);
                    return GameMsg.LOSE;
                }
                else{
                    System.out.println(GameMsg.YOU_DRAW);
                    return GameMsg.DRAW;
                }
            }
            else {
                System.out.println(GameMsg.YOU_DRAW);
                return GameMsg.DRAW;
            }
        }
    }
}
