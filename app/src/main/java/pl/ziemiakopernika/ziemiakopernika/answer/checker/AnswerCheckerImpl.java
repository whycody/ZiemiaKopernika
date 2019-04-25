package pl.ziemiakopernika.ziemiakopernika.answer.checker;

import java.util.ArrayList;

import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;

public class AnswerCheckerImpl implements AnswerChecker {

    private SetOfQuestions setOfQuestions;

    public AnswerCheckerImpl(SetOfQuestions setOfQuestions){
        this.setOfQuestions = setOfQuestions;
    }

    @Override
    public boolean answerIsCorrect(int numberOfQuestion) {
        if(setOfQuestions.getQuestions().get(numberOfQuestion).getTypeOfQuestion() == 0) {
            int correctAnswer = setOfQuestions.getAnswers().get(numberOfQuestion).getSetOfAnswers().get(0);
            int choosedAnswer = setOfQuestions.getAnswers().get(numberOfQuestion).getChoosedAnswer();
            return choosedAnswer == correctAnswer;
        }else{
            ArrayList<Integer> answersList = new ArrayList<>();
            answersList.add(0);
            answersList.add(1);
            answersList.add(2);
            answersList.add(3);
            return setOfQuestions.getAnswers().get(numberOfQuestion).getChoosedAnswers().equals(answersList);
        }
    }
}
