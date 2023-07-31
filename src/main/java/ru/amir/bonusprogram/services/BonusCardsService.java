package ru.amir.bonusprogram.services;

import org.springframework.stereotype.Service;
import ru.amir.bonusprogram.models.BonusCard;
import ru.amir.bonusprogram.repositories.BonusCardsRepository;

import java.util.List;

@Service
public class BonusCardsService {
    private final BonusCardsRepository bonusCardsRepository;

    public BonusCardsService(BonusCardsRepository bonusCardsRepository) {
        this.bonusCardsRepository = bonusCardsRepository;
    }

    public List<BonusCard> findAll() {
        return bonusCardsRepository.findAll();
    }
}
