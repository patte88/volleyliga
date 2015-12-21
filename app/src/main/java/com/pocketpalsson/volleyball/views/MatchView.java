package com.pocketpalsson.volleyball.views;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.pocketpalsson.volleyball.models.MatchModel;
import com.pocketpalsson.volleyball.utilities.CustomBus;

public interface MatchView extends MvpView {
    void setMatchModel(MatchModel match);

    CustomBus getBus();
}