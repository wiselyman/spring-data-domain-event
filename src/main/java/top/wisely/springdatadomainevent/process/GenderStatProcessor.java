package top.wisely.springdatadomainevent.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import top.wisely.springdatadomainevent.domain.GenderStat;
import top.wisely.springdatadomainevent.event.PersonSavedEvent;
import top.wisely.springdatadomainevent.repository.GenderRepository;

/**
 * Created by wangyunfei on 2017/6/19.
 */
@Component
public class GenderStatProcessor {
    @Autowired
    GenderRepository genderRepository;

    @Async
    @TransactionalEventListener
    public void handleAfterPersonSavedComplete(PersonSavedEvent event){

        GenderStat genderStat = genderRepository.findOne(1l);
        if(event.getGender()==1){
            genderStat.setMaleCount(genderStat.getMaleCount()+1);
        }else {
            genderStat.setFemaleCount(genderStat.getFemaleCount()+1);
        }
        genderRepository.save(genderStat);
    }
}
