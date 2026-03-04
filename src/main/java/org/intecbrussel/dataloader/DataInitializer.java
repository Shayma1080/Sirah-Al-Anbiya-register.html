package org.intecbrussel.dataloader;

import lombok.RequiredArgsConstructor;
import org.intecbrussel.model.Media;
import org.intecbrussel.model.Prophet;
import org.intecbrussel.model.QuizQuestion;
import org.intecbrussel.model.StoryPhase;
import org.intecbrussel.repository.MediaRepository;
import org.intecbrussel.repository.ProphetRepository;
import org.intecbrussel.repository.QuizQuestionRepository;
import org.intecbrussel.repository.StoryPhaseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer  {

    private final ProphetRepository prophetRepository;
    private final StoryPhaseRepository storyPhaseRepository;
    private final MediaRepository mediaRepository;
    private final QuizQuestionRepository quizQuestionRepository;

    @Bean
    CommandLineRunner loadData() {
        return args -> {

            if(prophetRepository.count() > 0){
                return;
            }

            // =========================
            // PROPHET Adem
            // =========================

            Prophet adem = new Prophet();
            adem.setName("Adem (AS)");
            adem.setPeriod("Eeerste mens");
            adem.setDescription(
                    "Adam (AS) werd geschapen door Allah als de eerste mens en profeet..."
            );



            // StoryPhase 1 - Calling the people

            StoryPhase adamPhase1 = new StoryPhase();
            adamPhase1.setTitle("De eerste mens");
            adamPhase1.setOrderNumber(1);
            adamPhase1.setProphet(adem);



            Media adamVideo = new Media();
            adamVideo.setType("VIDEO");
            adamVideo.setUrl("https://www.youtube.com/watch?v=05p8KzZixb4");
            adamVideo.setStoryPhase(adamPhase1);


            Media adamAudio = new Media();

            adamAudio.setType("AUDIO");

            adamAudio.setUrl("https://www.le-coran.com/coran-francais-sourate-7-0.html");

            adamAudio.setStoryPhase(adamPhase1);

            // Media toevoegen aan stroyPhase
            adamPhase1.setMedialist(List.of(adamVideo, adamAudio));

            // VRAAG 1
            QuizQuestion adamQuiz1 = new QuizQuestion();
            adamQuiz1.setQuestion("Wie is de eerste mens die Allah heeft geschapen?");
            adamQuiz1.setOptionsAnswer(List.of(
                    "Mohammed",
                    "Musa",
                    "Noeh",
                    "Adem"
            ));
            adamQuiz1.setCorrectAnswer("Adem");
            adamQuiz1.setStoryPhase(adamPhase1);

            // VRAAG 2
            QuizQuestion adamQuiz2 = new QuizQuestion();
            adamQuiz2.setQuestion("Allah geeft Adem op welk verschillende klei gemaakt?");
            adamQuiz2.setOptionsAnswer(List.of(
                    "zoute en zout water",
                    "klei van verschillende kleuren, harde en zachte klei",
                    "alle soorten planten ",
                    "huid van dieren"
            ));
            adamQuiz2.setCorrectAnswer("klei van verschillende kleuren, harde en zachte klei");
            adamQuiz2.setStoryPhase(adamPhase1);

            // VRAAG 3
            QuizQuestion adamQuiz3 = new QuizQuestion();
            adamQuiz3.setQuestion("Op welk dag werd Adam gemaakt?");
            adamQuiz3.setOptionsAnswer(List.of(
                    "Woensdag",
                    "vrijdag",
                    "Maandag",
                    "Zaterdag"
            ));
            adamQuiz3.setCorrectAnswer("vrijdag");
            adamQuiz3.setStoryPhase(adamPhase1);

            // VRAAG 4
            QuizQuestion adamQuiz4 = new QuizQuestion();
            adamQuiz4.setQuestion("Waarom werd Adam en Hawa weg van paradijs?");
            adamQuiz4.setOptionsAnswer(List.of(
                    "omdat ze van een boom hebben gegeten waarvan Allah heeft verboden",
                    "omdat ze niet naar Allah geloofden",
                    "omdat ze zichzelf verkeerd gedroegen",
                    " omdat ze niet naar de engelen luisterden"
            ));
            adamQuiz4.setCorrectAnswer("omdat ze van een boom hebben gegeten waarvan Allah heeft verboden");
            adamQuiz4.setStoryPhase(adamPhase1);

            // VRAAG 5
            QuizQuestion adamQuiz5 = new QuizQuestion();
            adamQuiz5.setQuestion("Waarom weigert iblis zich neer te knielen voor Adam?");
            adamQuiz5.setOptionsAnswer(List.of(
                    "omdat die mooier is dan Adam",
                    "omdat hij zichzelf beter achtte dan een mens",
                    "omdat hij de namen van alles weet",
                    " omdat hij slimmer is dan Adam"
            ));
            adamQuiz5.setCorrectAnswer("omdat hij zichzelf beter achtte dan een mens");
            adamQuiz5.setStoryPhase(adamPhase1);

            // Quiz toevoegen aan StoryPhase
            adamPhase1.setQuizQuestions(List.of(adamQuiz1,adamQuiz2,adamQuiz3, adamQuiz4,adamQuiz5));

            // StoryPhase toevoegen aan Profeet
            adem.setStoryPhases(List.of(adamPhase1));

            // Porfeet opslaan
            prophetRepository.save(adem);

            // =========================
            // PROPHET NUH
            // =========================

            Prophet noeh = new Prophet();
            noeh.setName("Noeh (AS)");
            noeh.setPeriod("Voor de Grote Vloed");
            noeh.setDescription(
                    "Profeet Nuh werd gezonden om zijn volk te leiden tot de aanbidding van Allah alleen. "
                            + "Ze hebben hem jarenlang afgewezen. Allah gaf hem de opdracht de Ark te bouwen."
            );



            // StoryPhase 1 - Calling the people

            StoryPhase noehPhase1 = new StoryPhase();
            noehPhase1.setTitle("De mensen oproepen tot de islam");
            noehPhase1.setOrderNumber(2);
            noehPhase1.setProphet(noeh);



            Media noehVideo = new Media();
            noehVideo.setType("VIDEO");
            noehVideo.setUrl("https://www.youtube.com/watch?v=tvWsAMUacQ4");
            noehVideo.setStoryPhase(noehPhase1);


            Media noehAudio = new Media();

            noehAudio.setType("AUDIO");

            noehAudio.setUrl("https://www.le-coran.com/coran-francais-sourate-71-0.html");

            noehAudio.setStoryPhase(noehPhase1);

            // Media toevoegen aan stroyPhase
            noehPhase1.setMedialist(List.of(noehVideo, noehAudio));

            // VRAAG 1
            QuizQuestion nuhQuiz1 = new QuizQuestion();
            nuhQuiz1.setQuestion("Wat heeft Allah gevraagt aan Noeh?");
            nuhQuiz1.setOptionsAnswer(List.of(
                    "de volk verwittigen dat ze Allah moeten aanbidden",
                    "de volk roepen om samen een ark te bouwen",
                    "de volk verwittigen dat ze zich moeten voorbereiden op de zondvloed",
                    "de volk verwittigen om het land te verlaten"
            ));
            nuhQuiz1.setCorrectAnswer("de volk verwittigen dat ze Allah moeten aanbidden");
            nuhQuiz1.setStoryPhase(noehPhase1);

            // VRAAG 2
            QuizQuestion nuhQuiz2 = new QuizQuestion();
            nuhQuiz2.setQuestion("Hoeveel jaar riep Profeet Noeh zijn volk op?");
            nuhQuiz2.setOptionsAnswer(List.of(
                    "950 jaar",
                    "100 jaar",
                    "500 jaar",
                    "200 jaar"
            ));
            nuhQuiz2.setCorrectAnswer("950 jaar");
            nuhQuiz2.setStoryPhase(noehPhase1);

            // VRAAG 3
            QuizQuestion nuhQuiz3 = new QuizQuestion();
            nuhQuiz3.setQuestion("Wat moest Profeet Noeh bouwen?");
            nuhQuiz3.setOptionsAnswer(List.of(
                    "Een paleis",
                    "Een ark",
                    "Een toren",
                    "Een huis"
            ));
            nuhQuiz3.setCorrectAnswer("Een ark");
            nuhQuiz3.setStoryPhase(noehPhase1);

            // VRAAG 4
            QuizQuestion nuhQuiz4 = new QuizQuestion();
            nuhQuiz4.setQuestion("Wat moest Profeet Noeh meenemen in zijn ark?");
            nuhQuiz4.setOptionsAnswer(List.of(
                    "zijn zoon",
                    "van elk dier een vrouwlijk en mannelijjk",
                    "goud",
                    " eten"
            ));
            nuhQuiz4.setCorrectAnswer("van elk dier een vrouwlijk en mannelijjk");
            nuhQuiz4.setStoryPhase(noehPhase1);

            // Quiz toevoegen aan StoryPhase
            noehPhase1.setQuizQuestions(List.of(nuhQuiz1,nuhQuiz2,nuhQuiz3, nuhQuiz4));

            // StoryPhase toevoegen aan Profeet
            noeh.setStoryPhases(List.of(noehPhase1));

            // Porfeet opslaan
            prophetRepository.save(noeh);


            // =========================
            // PROPHET MUSA
            // =========================

            Prophet musa = new Prophet();

            musa.setName("Musa (AS)");

            musa.setPeriod("Tijd van de Farao");

            musa.setDescription(
                    "De profeet Musa werd gezonden om de Israëlieten te bevrijden van de farao. "
                            + "Allah beschermde hem als baby en schonk hem later miracles."
            );



            // StoryPhase 1 - Birth of Musa

            StoryPhase musaPhase1 = new StoryPhase();

            musaPhase1.setTitle("Geboorte van Musa");

            musaPhase1.setOrderNumber(3);

            musaPhase1.setProphet(musa);



            Media musaVideo = new Media();

            musaVideo.setType("VIDEO");

            musaVideo.setUrl("https://www.youtube.com/watch?v=5HeiiplSlrA");

            musaVideo.setStoryPhase(musaPhase1);



            Media musaAudio = new Media();

            musaAudio.setType("AUDIO");

            musaAudio.setUrl("https://www.le-coran.com/coran-francais-sourate-28-0.html");

            musaAudio.setStoryPhase(musaPhase1);

            // Media toevoegen aan stroyPhase
            musaPhase1.setMedialist(List.of(musaVideo, musaAudio));

            // VRAAG 1
            QuizQuestion musaQuiz1 = new QuizQuestion();
            musaQuiz1.setQuestion("Wat was de vreeslijk bevel van farao?");
            musaQuiz1.setOptionsAnswer(List.of(
                    "alle meisjes moesten worden gedood",
                    "alle pasgeboren babyjongens van israëlieten doden",
                    "alle israëlieten doden",
                    "enekl vrouwen moesten worden gedood"
            ));
            musaQuiz1.setCorrectAnswer("alle pasgeboren babyjongens van israëlieten doden");
            musaQuiz1.setStoryPhase(musaPhase1);

            // VRAAG 2
            QuizQuestion musaQuiz2 = new QuizQuestion();
            musaQuiz2.setQuestion("wat heeft farao gezien in zijn droom waarvan hij bang voor was?");
            musaQuiz2.setOptionsAnswer(List.of(
                    "iemand die hem van zijn troon zou stoten",
                    "dat al zijn kinderen dood zouden gaan",
                    "terwijl hij slaapt, zal iamand hem met een mes neesteken ",
                    "iamand gaat hem vergiftigen"
            ));
            musaQuiz2.setCorrectAnswer("iemand die hem van zijn troon zou stoten");
            musaQuiz2.setStoryPhase(musaPhase1);

            // VRAAG 3
            QuizQuestion musaQuiz3 = new QuizQuestion();
            musaQuiz3.setQuestion("Waar werd Musa opgevoegd?");
            musaQuiz3.setOptionsAnswer(List.of(
                    "in een bouderij",
                    "in een bos",
                    "bij zijn oom",
                    "in paleis van farao"
            ));
            musaQuiz3.setCorrectAnswer("in paleis van farao");
            musaQuiz3.setStoryPhase(musaPhase1);

            // VRAAG 4
            QuizQuestion musaQuiz4 = new QuizQuestion();
            musaQuiz4.setQuestion("Wat heeft Allah gevraagt aan Musa?");
            musaQuiz4.setOptionsAnswer(List.of(
                    "neem de troon van farao",
                    "vorm een leger om de Egyptenaren te doden ",
                    "ga terug naar Egypte en bevrijd mijn volk uit de slavernij",
                    " ga terug naar Egypte en dood farao"
            ));
            musaQuiz4.setCorrectAnswer("ga terug naar Egypte en bevrijd mijn volk uit de slavernij");
            musaQuiz4.setStoryPhase(musaPhase1);

            // VRAAG 5
            QuizQuestion musaQuiz5 = new QuizQuestion();
            musaQuiz5.setQuestion("Waar vluchte Musa en zijn volk?");
            musaQuiz5.setOptionsAnswer(List.of(
                    "Arabische Zee",
                    "Rode Zee",
                    "Perzische Zee",
                    " Zwarte Zee"
            ));
            musaQuiz5.setCorrectAnswer("Rode Zee");
            musaQuiz5.setStoryPhase(musaPhase1);

            // Quiz toevoegen aan StoryPhase
            musaPhase1.setQuizQuestions(List.of(musaQuiz1,musaQuiz2,musaQuiz3,musaQuiz4,musaQuiz5));


            // Porfeet opslaan
            prophetRepository.save(musa);


            System.out.println("DATA LOADED SUCCESSFULLY");
        };
    }


}

