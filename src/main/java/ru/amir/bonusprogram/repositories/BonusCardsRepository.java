package ru.amir.bonusprogram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.amir.bonusprogram.models.BonusCard;

@Repository
public interface BonusCardsRepository extends JpaRepository<BonusCard, Integer> {
}
