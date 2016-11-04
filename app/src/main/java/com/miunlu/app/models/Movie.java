package com.miunlu.app.models;

/**
 * Created by ivan.pico.martin
 */

public class Movie {

    private String title;
    private Integer year;
    private Ids ids;

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     *
     * @param year
     * The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     *
     * @return
     * The ids
     */
    public Ids getIds() {
        return ids;
    }

    /**
     *
     * @param ids
     * The ids
     */
    public void setIds(Ids ids) {
        this.ids = ids;
    }


}