package server.modules.dbconnector;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import server.entities.FlashCard;
import server.entities.FlashCardBox;
import server.entities.FlashCardStatistics;
import server.entities.User;
import server.entities.repositories.FlashCardStatisticsRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FlashCardStatisticsConnector {
    private final FlashCardStatisticsRepository flashCardStatisticsRepository;

    public FlashCardStatisticsConnector(FlashCardStatisticsRepository flashCardStatisticsRepository) {
        this.flashCardStatisticsRepository = flashCardStatisticsRepository;
    }

    @Transactional
    public FlashCardStatistics getStatisticsByFlashCardAndUser(FlashCard flashCard, User user) {
        //innere Klassen sollten eigentlich nicht so genutzt werden ... aber was solls
        return flashCardStatisticsRepository.findByFlashCardUser(new FlashCardStatistics.FlashCardStatisticsPK(flashCard, user));
    }

    @Transactional
    public List<FlashCardStatistics> getStatisticsByUser(User user) {
        List<FlashCardStatistics> allStatistics = new ArrayList<>();
        flashCardStatisticsRepository.findAll().forEach(allStatistics::add);

        List<FlashCardStatistics> userStatistics = allStatistics.stream()
                .filter(statistics -> statistics.getFlashCardUser().getUser().equals(user))
                .filter(statistics -> userHasAccessToFlashCard(user, statistics))
                .collect(Collectors.toList());

        return userStatistics;
    }

    private static boolean userHasAccessToFlashCard(User user, FlashCardStatistics statistics) {
        FlashCardBox flashCardBox = statistics.getFlashCardUser().getFlashCard().getFlashcardBox();
        return flashCardBox.getOwner().equals(user) || flashCardBox.getSharedUserNames().contains(user.getUsername());
    }

    // Holt alle FlashCards aus der Box zu der es für den User bereits eine Statistik gibt
    @Transactional
    public Set<FlashCard> getAllFlashCardsWithStatisticsFromBoxAndUser(FlashCardBox flashCardBox, User user) {
        List<FlashCardStatistics> allStatistics = new ArrayList<>();
        flashCardStatisticsRepository.findAll().forEach(allStatistics::add);

        // wählt alle Statistiken zu einem User und den Karten aus der Box
        List<FlashCardStatistics> boxAndUserStatistics = allStatistics.stream()
                .filter(statistics -> statistics.getFlashCardUser().getFlashCard().getFlashcardBox().equals(flashCardBox))
                .filter(statistics -> statistics.getFlashCardUser().getUser().equals(user))
                .collect(Collectors.toList());

        Set<FlashCard> flashCards = new HashSet<>();
        boxAndUserStatistics.forEach(statistics -> flashCards.add(statistics.getFlashCardUser().getFlashCard()));
        return flashCards;
    }

    @Transactional
    public FlashCardStatistics saveStatistics(FlashCardStatistics statistics) {
        return flashCardStatisticsRepository.save(statistics);
    }
}
