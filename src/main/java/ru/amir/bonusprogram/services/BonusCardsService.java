package ru.amir.bonusprogram.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.amir.bonusprogram.models.BonusCard;
import ru.amir.bonusprogram.models.Person;
import ru.amir.bonusprogram.repositories.BonusCardsRepository;

import java.util.List;

@Service
public class BonusCardsService {
    private final BonusCardsRepository bonusCardsRepository;

    public BonusCardsService(BonusCardsRepository bonusCardsRepository) {
        this.bonusCardsRepository = bonusCardsRepository;
    }


    @Transactional
    public void createCardAndAssign(Person person) {
        BonusCard nCard = new BonusCard();
        nCard.setCustomer(person);
        nCard.setDiscount(5);
        bonusCardsRepository.save(nCard);
    }
}
