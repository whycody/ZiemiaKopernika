package pl.ziemiakopernika.ziemiakopernika.question;

import pl.ziemiakopernika.ziemiakopernika.arrange.answer.ArrangeAnswerRowPresenter;
import pl.ziemiakopernika.ziemiakopernika.choose.answer.ChooseAnswerPresenter;

public interface FragmentInitializer {

    void setChooserAnswerPresenter(ChooseAnswerPresenter chooserAnswerPresenter);

    void setArrangeAnswerRowPresenter(ArrangeAnswerRowPresenter arrangeAnswerRowPresenter);
}
