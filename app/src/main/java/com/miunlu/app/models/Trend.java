package com.miunlu.app.models;

/**
 * Created by ivan.pico.martin
 */
public class Trend {

    private Integer watchers;
    private Movie movie;


    public Trend() {
    }

    public Trend(Integer watchers, Movie movie) {
        this.watchers = watchers;
        this.movie = movie;
    }

    /**
     *
     * @return
     * The watchers
     */
    public Integer getWatchers() {
        return watchers;
    }

    /**
     *
     * @param watchers
     * The watchers
     */
    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    /**
     *
     * @return
     * The movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     *
     * @param movie
     * The movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }


}