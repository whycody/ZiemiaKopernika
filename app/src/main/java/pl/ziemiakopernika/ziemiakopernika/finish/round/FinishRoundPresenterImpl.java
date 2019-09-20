package pl.ziemiakopernika.ziemiakopernika.finish.round;

import android.app.Activity;

import pl.ziemiakopernika.ziemiakopernika.main.MainPresenterImpl;
import pl.ziemiakopernika.ziemiakopernika.model.SetOfRounds;

public class FinishRoundPresenterImpl implements FinishRoundPresenter {

    private SetOfRounds setOfRounds;
    private Activity activity;
    private FinishRoundView view;

    FinishRoundPresenterImpl(Activity activity, FinishRoundView view){
        this.activity = activity;
        setOfRounds = getSetOfRounds();
        this.view = view;
    }

    private SetOfRounds getSetOfRounds(){
        return (SetOfRounds)activity.getIntent().getSerializableExtra(MainPresenterImpl.ROUND_SET);
    }

    @Override
    public void onCreate() {
        view.setRoundText("Runda " + (setOfRounds.getNumOfRound()+1) + "/" +
                setOfRounds.getSetOfQuestions().size() + " ukończona");
    }

    @Override
    public void continueGameBtnClicked() {
        setOfRounds.setNumOfRound(setOfRounds.getNumOfRound()+1);
        activity.setResult(Activity.RESULT_OK);
        activity.finish();
    }

    @Override
    public void leaveGameBtnClicked() {
        activity.setResult(Activity.RESULT_CANCELED);
        activity.finish();
    }
}
