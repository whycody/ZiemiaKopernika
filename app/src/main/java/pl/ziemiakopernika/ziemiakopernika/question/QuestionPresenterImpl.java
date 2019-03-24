package pl.ziemiakopernika.ziemiakopernika.question;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import pl.ziemiakopernika.ziemiakopernika.choose.answer.ChooseAnswerFragment;
import pl.ziemiakopernika.ziemiakopernika.main.MainPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfQuestions;

public class QuestionPresenterImpl implements QuestionPresenter{

    private Activity activity;
    private QuestionView questionView;
    private SetOfQuestions setOfQuestions;
    private SharedPreferences sharedPreferences;
    private int numberOfCoins, numberOfQuestion;
    private boolean fiftyFiftyBtnActivated, addSecondsBtnActivated;

    private static final String COINS = "coins";

    QuestionPresenterImpl(Activity activity, QuestionView questionView){
        this.activity = activity;
        this.questionView = questionView;
        this.setOfQuestions = getSetOfQuestions();
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        numberOfCoins = getNumberOfCoins();
    }

    private SetOfQuestions getSetOfQuestions(){
        return (SetOfQuestions)activity.getIntent().getSerializableExtra(MainPresenterImpl.QUESTION_SET);
    }

    private int getNumberOfCoins(){
        return sharedPreferences.getInt(COINS, 50);
    }

    @Override
    public void onCreate() {
        questionView.setQuestion(setOfQuestions.getQuestions().get(numberOfQuestion).getQuestion());
        questionView.setCoinsNumber(numberOfCoins);
        applyFragment();
        setButtonsActivated();
    }

    private void applyFragment(){
        if(setOfQuestions.getQuestions().get(numberOfQuestion).getTypeOfQuestion() == 0){
            ChooseAnswerFragment chooseAnswerFragment = new ChooseAnswerFragment();
            chooseAnswerFragment.setSetOfQuestions(setOfQuestions);
            chooseAnswerFragment.setNumberOfQuestion(numberOfQuestion);
            chooseAnswerFragment.setQuestionPresenter(this);
            questionView.applyFragment(chooseAnswerFragment);
        }
    }

    private void setButtonsActivated(){
        if(numberOfCoins>=3 && numberOfCoins<5){
            questionView.setAddSecondsBtnActivated(true);
            questionView.setFiftyFiftyBtnActivated(false);
            setButtonsActivatedBooleans(true, false);
        }else if(numberOfCoins>=5){
            questionView.setAddSecondsBtnActivated(true);
            questionView.setFiftyFiftyBtnActivated(true);
            setButtonsActivatedBooleans(true, true);
        }else{
            questionView.setAddSecondsBtnActivated(false);
            questionView.setFiftyFiftyBtnActivated(false);
            setButtonsActivatedBooleans(false, false);
        }
    }

    private void setButtonsActivatedBooleans(boolean addSecondsBtnActivated, boolean fiftyFiftyBtnActivated){
        this.addSecondsBtnActivated = addSecondsBtnActivated;
        this.fiftyFiftyBtnActivated = fiftyFiftyBtnActivated;
    }

    @Override
    public void onFiftyFiftyBtnClicked() {
        if(fiftyFiftyBtnActivated){
            questionView.setFiftyFiftyBtnActivated(false);
            numberOfCoins = numberOfCoins - 5;
            saveNumberOfCoins(numberOfCoins);
        }
    }

    @Override
    public void onAddSecondsBtnClicked() {
        if(addSecondsBtnActivated){
            questionView.setAddSecondsBtnActivated(false);
            numberOfCoins = numberOfCoins - 3;
            saveNumberOfCoins(numberOfCoins);
        }
    }

    @Override
    public void onAnswerChoosed(boolean correct) {
        Toast.makeText(activity, "Czy to prawda, że wybrałeś dobrą odpowiedź? " + correct, Toast.LENGTH_SHORT).show();
    }

    private void saveNumberOfCoins(int numberOfCoins){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(COINS, numberOfCoins);
        editor.apply();
    }
}
