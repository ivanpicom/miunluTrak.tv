package models;

/**
 * Created by ivan.pico.martin
 */
public class Trend {

    private Integer watchers;
    private Movie movie;

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

/*[
    {
        "watchers": 38,
        "movie": {
            "title": "Finding Dory",
            "year": 2016,
            "ids": {
                "trakt": 87254,
                "slug": "finding-dory-2016",
                "imdb": "tt2277860",
                "tmdb": 127380
            }
        }
    },
    {
        "watchers": 37,
        "movie": {
            "title": "Star Trek Beyond",
            "year": 2016,
            "ids": {
                "trakt": 118174,
                "slug": "star-trek-beyond-2016",
                "imdb": "tt2660888",
                "tmdb": 188927
            }
        }
    },
    {
        "watchers": 30,
        "movie": {
            "title": "Jack Reacher: Never Go Back",
            "year": 2016,
            "ids": {
                "trakt": 222913,
                "slug": "jack-reacher-never-go-back-2016",
                "imdb": "tt3393786",
                "tmdb": 343611
            }
        }
    },
    {
        "watchers": 26,
        "movie": {
            "title": "Sausage Party",
            "year": 2016,
            "ids": {
                "trakt": 137261,
                "slug": "sausage-party-2016",
                "imdb": "tt1700841",
                "tmdb": 223702
            }
        }
    },
    {
        "watchers": 24,
        "movie": {
            "title": "Bad Moms",
            "year": 2016,
            "ids": {
                "trakt": 226615,
                "slug": "bad-moms-2016",
                "imdb": "tt4651520",
                "tmdb": 376659
            }
        }
    },
    {
        "watchers": 19,
        "movie": {
            "title": "Independence Day: Resurgence",
            "year": 2016,
            "ids": {
                "trakt": 33179,
                "slug": "independence-day-resurgence-2016",
                "imdb": "tt1628841",
                "tmdb": 47933
            }
        }
    },
    {
        "watchers": 18,
        "movie": {
            "title": "Suicide Squad",
            "year": 2016,
            "ids": {
                "trakt": 193079,
                "slug": "suicide-squad-2016",
                "imdb": "tt1386697",
                "tmdb": 297761
            }
        }
    },
    {
        "watchers": 14,
        "movie": {
            "title": "Nine Lives",
            "year": 2016,
            "ids": {
                "trakt": 202888,
                "slug": "nine-lives-2016",
                "imdb": "tt4383594",
                "tmdb": 322240
            }
        }
    },
    {
        "watchers": 13,
        "movie": {
            "title": "Ghostbusters",
            "year": 2016,
            "ids": {
                "trakt": 28880,
                "slug": "ghostbusters-2016",
                "imdb": "tt1289401",
                "tmdb": 43074
            }
        }
    },
    {
        "watchers": 13,
        "movie": {
            "title": "The BFG",
            "year": 2016,
            "ids": {
                "trakt": 166311,
                "slug": "the-bfg-2016",
                "imdb": "tt3691740",
                "tmdb": 267935
            }
        }
    }
]*/