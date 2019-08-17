package tecolotl.web.alumno;

public class ActividadesModelo {

    private String languageLevel;
    private String learnersType;
    private String time;
    private String topic;
    private String language;
    private String idVideo;
    private String question;

    public ActividadesModelo(String languageLevel, String learnersType, String time, String topic, String language, String idVideo, String question) {
        this.languageLevel = languageLevel;
        this.learnersType = learnersType;
        this.time = time;
        this.topic = topic;
        this.language = language;
        this.idVideo = idVideo;
        this.question = question;
    }

    public ActividadesModelo() {
    }

    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }

    public String getLearnersType() {
        return learnersType;
    }

    public void setLearnersType(String learnersType) {
        this.learnersType = learnersType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
