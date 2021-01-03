package Tasks;

import DAOLayer.DAOSumInCardService;
import EntityLayer.SumInCard;
import ServiceLayer.SumInCardService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

public class DeleteEventJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        DAOSumInCardService cardService = new SumInCardService();
        List<SumInCard> cardList = cardService.getAllSum();
        for (SumInCard card: cardList
                ) {
            if (card.getGift_sum()!=0){
                card.setGift_sum(card.getGift_sum()-0);
            }
            cardService.updateSumInCard(card);
        }
    }
}
