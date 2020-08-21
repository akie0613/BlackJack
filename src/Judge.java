public class Judge {
    //勝敗判定
    public static int judge(Hand playersHands, Hand dealersHands ) {
        int playersHandsSum = playersHands.getHandsSum();
        int dealersHandsSum = dealersHands.getHandsSum();
        if(playersHandsSum>GameMsg.BURST_NUM){
            System.out.println(GameMsg.YOU_LOSE);
            return GameMsg.LOSE;
        }
        else if(dealersHandsSum>GameMsg.BURST_NUM){
            System.out.println(GameMsg.YOU_WIN);
            return GameMsg.WIN;
        }

        if(playersHandsSum>dealersHandsSum){
            System.out.println(GameMsg.YOU_WIN);
            return GameMsg.WIN;
        }
        else if(playersHandsSum<dealersHandsSum){
            System.out.println(GameMsg.YOU_LOSE);
            return GameMsg.LOSE;
        }
        //同点の場合の処理
        else {
            if(playersHandsSum==GameMsg.BURST_NUM){
                if(playersHands.getHands().size()==2&&dealersHands.getHands().size()>=3){
                    System.out.println(GameMsg.YOU_WIN);
                    return GameMsg.WIN;
                }
                else if(playersHands.getHands().size()>=3&&dealersHands.getHands().size()==2){
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
