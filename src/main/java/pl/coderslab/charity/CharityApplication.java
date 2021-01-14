package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.coderslab.charity.domain.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class CharityApplication {

    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;

    @PostConstruct
    void createSampleData() {

        // fixed institutions
        log.info("generating institutions");
        institutionRepository.save(Institution.builder()
                .name("Fundacja “Dla dzieci\"")
                .description("Cel i misja: Pomoc dzieciom z ubogich rodzin.")
                .build());
        institutionRepository.save(Institution.builder()
                .name("Fundacja \"A kogo\"")
                .description("Cel i misja: Pomoc w wybudzaniu dzieci ze śpiączki.")
                .build());
        institutionRepository.save(Institution.builder()
                .name("Fundacja \"Dbam o Zdrowie\"")
                .description("Cel i misja: Pomoc zdrowotna osobom znajdującym się w trudnej sytuacji życiowej.")
                .build());
        institutionRepository.save(Institution.builder()
                .name("Fundacja \"Bez domu\"")
                .description("Cel i misja: Pomoc dla osób nie posiadających miejsca zamieszkania.")
                .build());

        // fixed categories
        log.info("generating categories");
        categoryRepository.save(Category.builder()
                .name("ubrania")
                .build());
        categoryRepository.save(Category.builder()
                .name("zabawki")
                .build());
        categoryRepository.save(Category.builder()
                .name("sprzęt domowy")
                .build());
        categoryRepository.save(Category.builder()
                .name("środki czystości")
                .build());
        categoryRepository.save(Category.builder()
                .name("żywność")
                .build());
        categoryRepository.save(Category.builder()
                .name("przybory szkolne")
                .build());

        // random donation - setup part
        log.info("randomizing donations");
        List<Category> categoryList = categoryRepository.findAll();
        List<Institution> institutionList = institutionRepository.findAll();
        Random random = new Random();
        String[] cities = {"Wrocław", "Warszawa", "Kraków", "Poznań", "Gdańsk", "Łódź"};
        String[] streets = {"Mickiewicza", "Kopernika", "Skłodowskiej", "Rynek", "Wiśniowa", "Czekoladowa",
                "Lipowa", "Grodzka", "Północna", "Słoneczna"};
        String[] comments = {"", "ostrożnie!", "", "zostawię przy bramie", "", "dziękuję :-)", "",
                "proszę zadzwonić parę minut wcześniej"};

        // random donation - randomization part
        for (int i = 0; i < 30; i++) {

            // random donation - one-category donation
            donationRepository.save(Donation.builder()
                    .quantity(random.nextInt(random.nextInt(4) + 1))
                    .categories(List.of(categoryList.get(random.nextInt(categoryList.size()))))
                    .institution(institutionList.get(random.nextInt(institutionList.size())))
                    .street(streets[random.nextInt(streets.length)] + " " + String.valueOf(random.nextInt(100)))
                    .city(cities[random.nextInt(cities.length)])
                    .zipCode("00-000")
                    .pickUpDate(LocalDate.now().plusDays(random.nextInt(10)))
                    .pickUpTime(LocalTime.now().plusHours(random.nextInt(11) - 5).plusMinutes(random.nextInt(21) - 10))
                    .pickUpComment(comments[random.nextInt(comments.length)])
                    .build());

            // random donation - two-category donation
            donationRepository.save(Donation.builder()
                    .quantity(random.nextInt(random.nextInt(4) + 1))
                    .categories(List.of(categoryList.get(random.nextInt(categoryList.size())),
                            categoryList.get(random.nextInt(categoryList.size()))))
                    .institution(institutionList.get(random.nextInt(institutionList.size())))
                    .street(streets[random.nextInt(streets.length)] + " " + String.valueOf(random.nextInt(100)))
                    .city(cities[random.nextInt(cities.length)])
                    .zipCode("00-000")
                    .pickUpDate(LocalDate.now().plusDays(random.nextInt(10)))
                    .pickUpTime(LocalTime.now().plusHours(random.nextInt(11) - 5).plusMinutes(random.nextInt(21) - 10))
                    .pickUpComment(comments[random.nextInt(comments.length)])
                    .build());

            // random donation - three-category donation
            donationRepository.save(Donation.builder()
                    .quantity(random.nextInt(random.nextInt(4) + 1))
                    .categories(List.of(categoryList.get(random.nextInt(categoryList.size())),
                            categoryList.get(random.nextInt(categoryList.size())),
                            categoryList.get(random.nextInt(categoryList.size()))))
                    .institution(institutionList.get(random.nextInt(institutionList.size())))
                    .street(streets[random.nextInt(streets.length)] + " " + String.valueOf(random.nextInt(100)))
                    .city(cities[random.nextInt(cities.length)])
                    .zipCode("00-000")
                    .pickUpDate(LocalDate.now().plusDays(random.nextInt(10)))
                    .pickUpTime(LocalTime.now().plusHours(random.nextInt(11) - 5).plusMinutes(random.nextInt(21) - 10))
                    .pickUpComment(comments[random.nextInt(comments.length)])
                    .build());

        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CharityApplication.class, args);
    }

}
