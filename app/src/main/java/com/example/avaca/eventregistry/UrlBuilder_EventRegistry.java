package com.example.avaca.eventregistry;

/**
 * Created by avaca on 1/9/2018.
 * Abrasct Class for constructor API url request
 */
//http://eventregistry.org/json/article?query=%7B%22%24query%22%3A%7B%22lang%22%3A%22eng%22%7D%7D&action=getArticles&resultType=articles&articlesSortBy=date&articlesCount=100&articlesArticleBodyLen=-1&articlesIncludeArticleLocation=true&articlesIncludeLocationGeoLocation=true&apiKey=6f4689b1-2f95-405a-8e3e-3430d291bc84
public class UrlBuilder_EventRegistry{
    //Add & When Constructing
String URL;

/*
    public UrlBuilder_EventRegistry(Luagauge lua,Cateorgy cateorgy){
    String urlLau = lua.toString();
    String urlCat = cateorgy.toString();
    URL = "http://eventregistry.org/json/article?query=%7B%22%24query%22%3A%7B%22%24and%22%3A%5B%7B%22categoryUri%22%3A%7B%22%24and%22%3A%5B%22dmoz%2F"
            + urlCat + "%22%5D%7D%7D%2C%7B%22lang%22%3A%22" + urlLau
            + "%22%7D%5D%7D%7D&action=getArticles&resultType=articles&articlesSortBy=rel&articlesCount=100&articlesArticleBodyLen=50&apiKey=6f4689b1-2f95-405a-8e3e-3430d291bc84";
}
public UrlBuilder_EventRegistry(Luagauge lua){
        String urlLau = lua.toString();
        URL = "http://eventregistry.org/json/article?query=%7B%22%24query%22%3A%7B%22lang%22%3A%22" + urlLau;

    }*/


    /**
     *
     * @param lua lua The lauguage Must be Short like eng for English use Enum if not fimilar
     * @param cateorgy Catogory again use Enum if not sure of Avaible perms
     */
    public UrlBuilder_EventRegistry(String lua,String cateorgy){
        String urlLau = lua;
        String urlCat = cateorgy;
        URL = "http://eventregistry.org/json/article?query=%7B%22%24query%22%3A%7B%22%24and%22%3A%5B%7B%22categoryUri%22%3A%7B%22%24and%22%3A%5B%22dmoz%2F"
                + urlCat + "%22%5D%7D%7D%2C%7B%22lang%22%3A%22" + urlLau
                + "%22%7D%5D%7D%7D&action=getArticles&resultType=articles&articlesSortBy=rel&articlesCount=100&articlesArticleBodyLen=50&articlesIncludeArticleLocation=true&articlesIncludeLocationGeoLocation=true&apiKey=6f4689b1-2f95-405a-8e3e-3430d291bc84";
    }
    /**
     *
     * @param lua The lauguage Must be Short like eng for English use Enum if not fimilar
     */
    public UrlBuilder_EventRegistry(String lua){
        String urlLau = lua;
        URL = "http://eventregistry.org/json/article?query=%7B%22%24query%22%3A%7B%22lang%22%3A%22" + urlLau + "%22%7D%7D&action=getArticles&resultType=articles&articlesSortBy=rel&articlesCount=100&articlesArticleBodyLen=50&articlesIncludeArticleLocation=true&articlesIncludeLocationGeoLocation=true&apiKey=6f4689b1-2f95-405a-8e3e-3430d291bc84";
    }


public String getURL() {
    return URL;
}

public void setURL(String URL) {
    this.URL = URL;
}

    // public String GetLauguage(Luagauge lua){
//     if (lua == Luagauge.eng){
//         return "eng";
//     }else if(lua == Luagauge.fra){
//         return "fra";
//     }else if(lua ==Luagauge.hrv){
//         return "hrv";
//     }else if(lua == Luagauge.ita){
//         return "ita";
//     }else if(lua == Luagauge.spa){
//         return "spa";
//     }
//     return "eng";
// }



}//End of URLBUILDER Class
//ENUM Classes may be useless as String array cannot be changed However will leave in for furture
enum Cateorgy{
    Business(),
    Computers(),
    Health(),
    Science(),
    Society

}


enum Luagauge{
    eng(),spa(),ita(),hrv(),fra()
}
