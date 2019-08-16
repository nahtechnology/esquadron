package tecolotl.web.alumno;

import tecolotl.web.alumno.ActividadesModelo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class ActividadesControlador {

    private List<ActividadesModelo> actividadesModeloLista;

    @PostConstruct
    public void init() {
        actividadesModeloLista = new ArrayList<>();
        actividadesModeloLista.add(new ActividadesModelo("A1", "Young learners: kids", "90", "Plans", "Be going to", "fMTHrvskSW8", "What do you normally do on holidays?"));
        actividadesModeloLista.add(new ActividadesModelo("A1", "Young learners: kids", "90", "Friends", "Adverbs of Frequency", "hxFldthKoso", "How frequently do you go to the woods?"));
        actividadesModeloLista.add(new ActividadesModelo("A1", "Young learners: kids", "90", "Demonstratives", "Demonstratives (this/that)", "KAVHS8d3evM", "What can you find outside?"));
        actividadesModeloLista.add(new ActividadesModelo("A1-A2", "Young learners: kids", "30", "Listen for details", "Present continuous, Questions and replies", "mL9hNsNSFTw", "What makes us family?"));
        actividadesModeloLista.add(new ActividadesModelo("A1", "Young learners: kids", "1:30", "Habits and routines", "Present simple", "Z4DDrBjEBHE", "What do you do before sleeping?"));
        actividadesModeloLista.add(new ActividadesModelo("A1-A2", "Primary School", "1:30", "Tsunamis", "Simple present", "Wx9vPv-T51I", "How are tsunamis formed?"));
        actividadesModeloLista.add(new ActividadesModelo("A2", "Young learners: kids", "1:30", "My favorite toy", "Superlative", "w1rKocEL1Eg", "Which is the best toy you have?"));
        actividadesModeloLista.add(new ActividadesModelo("A2", "Young learners: kids", "1:30", "Solar system", "Superlative", "libKVRa01L8", "What do you know about our solar system?"));
        actividadesModeloLista.add(new ActividadesModelo("A2", "Young learners: kids", "1:30", "Making Jokes", "Present Continuous", "3FKMUa7vCZU", "What makes you laugh?"));
        actividadesModeloLista.add(new ActividadesModelo("A2", "Young learners", "90", "Photosynthesis", "Infinitives for reasons", "JJxZH_Y5D4s", "How do plants help us?"));
        actividadesModeloLista.add(new ActividadesModelo("A2", "Young learners", "1:30", "Giving instructions and making requests.", "Imperative", "En7TapBA84M", "How do plants help us?"));
        actividadesModeloLista.add(new ActividadesModelo("A2", "Young learners: kids", "1:30", "Giving instructions and making requests.", "Imperative", "mFl8nzZuExE", "What makes people feel scared?"));
        actividadesModeloLista.add(new ActividadesModelo("A2", "Young learners: Teenagers", "1:30", "Water cycle", "Conditional 0", "zBnKgwnn7i4",
                "What’s your favorite kind of weather? Do you like rainy days? What do you usually do in those\n" +
                        "days?"));
        actividadesModeloLista.add(new ActividadesModelo("A2", "Young learners: kids", "1:30", "Superheroes", "Comparatives", "J3vDtZeo1YQ", "Who is you favorite superhero?"));
        actividadesModeloLista.add(new ActividadesModelo("A2", "Young learners: kids", "1:30", "Comparing people", "Comparatives", "J3vDtZeo1YQ", "Who are you?"));
        actividadesModeloLista.add(new ActividadesModelo("A2", "Young learners: kids", "90", "Music", "Can", "s19Fr-_WaXo", "Why do we have music?"));
        actividadesModeloLista.add(new ActividadesModelo("A1", "Young learners", "90", "Wishes and orders", "Imperatives", "JcMtWwiyzpU", "What did you wish in your last birthday?"));
        actividadesModeloLista.add(new ActividadesModelo("A1", "Young learners: kids", "1:30", "My vacation", "Adverbs of frequency", "g9WDeud275U", "What do you do on vacation?"));
        actividadesModeloLista.add(new ActividadesModelo("A2", "Young learners: kids", "1:30", "Describing yourself and others", "Adjectives", "DNHmujbuC74", "How does a scary creature look like?"));
        actividadesModeloLista.add(new ActividadesModelo("A2", "Young learners: kids", "1:30", "Describing tasks", "Adjectives", "0_1NU60qHWs", "What makes you nervous?"));

    }

    public List<ActividadesModelo> getActividadesModeloLista() {
        return actividadesModeloLista;
    }

    public void setActividadesModeloLista(List<ActividadesModelo> actividadesModeloLista) {
        this.actividadesModeloLista = actividadesModeloLista;
    }
}
