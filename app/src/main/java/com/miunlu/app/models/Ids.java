package com.miunlu.app.models;

/**
 * Created by ivan.pico.martin
 */
/*
            "ids": {
                "trakt": 87254,
                "slug": "finding-dory-2016",
                "imdb": "tt2277860",
                "tmdb": 127380
            }*/
public class Ids {

    private Integer trakt;
    private String slug;
    private String imdb;
    private Integer tmdb;

    /**
     *
     * @return
     * The trakt
     */
    public Integer getTrakt() {
        return trakt;
    }

    /**
     *
     * @param trakt
     * The trakt
     */
    public void setTrakt(Integer trakt) {
        this.trakt = trakt;
    }

    /**
     *
     * @return
     * The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     *
     * @param slug
     * The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     *
     * @return
     * The imdb
     */
    public String getImdb() {
        return imdb;
    }

    /**
     *
     * @param imdb
     * The imdb
     */
    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    /**
     *
     * @return
     * The tmdb
     */
    public Integer getTmdb() {
        return tmdb;
    }

    /**
     *
     * @param tmdb
     * The tmdb
     */
    public void setTmdb(Integer tmdb) {
        this.tmdb = tmdb;
    }


}