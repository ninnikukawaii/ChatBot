package service.quiz;

public class Question {
    private String question;
    private String answer;

    public Question(String question, String answer)
    {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getAnswer()
    {
        return answer;
    }

    @Override
    public boolean equals(Object object){
        if (object instanceof Question){
            return ((Question) object).question.equals(this.question) &&
                    ((Question) object).answer.equals(this.answer);
        }
        return false;
    }
}
