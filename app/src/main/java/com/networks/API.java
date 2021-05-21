package com.networks;


/**
 * Created by Cbc-03 on 08/10/16.
 */
public interface API {
//------------------------------staging/live SERVER-------------------------------------------------

    //STAGING SERVER
    String BASE_URL = "https://itunes.apple.com/";

    //LIVE SERVER
    // String BASE_URL = "";

    String API_DATA = BASE_URL + "search?term=Michael+jackson";

}