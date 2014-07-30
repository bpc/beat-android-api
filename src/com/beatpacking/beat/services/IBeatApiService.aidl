package com.beatpacking.beat.services;

/**
 * Created by Eugene on 7/25/14.
 */
interface IBeatApiService {
    void setHeadVisible(boolean b);
    boolean isHeadVisible();
    void setHeadLocation(int x, int y);
    int getHeadX();
    int getHeadY();

    void pause();
    void resume();

    boolean isAuthenticated();
}
