package indi.wkq.superseatandroid.model

import indi.wkq.superseatandroid.constant.MyURL
import indi.wkq.superseatandroid.model.response.JsonData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author  calesq
 * @date    2021/4/29
 */
interface ApiService {
    @Headers(
        "Content-Type: application/json;charset=UTF-8",
        "Connection: keep-alive",
        "Keep-Alive: timeout=5, max=1000",
        "Host: seat.lib.whu.edu.cn:8443",
        "User-Agent: doSingle/11 CFNetwork/976 Darwin/18.2.0"
    )
    @GET(MyURL.PATH_LOGIN)
    fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<JsonData>


    @Headers(
        "Content-Type: application/json;charset=UTF-8",
        "Connection: keep-alive",
        "Keep-Alive: timeout=5, max=1000",
        "Host: seat.lib.whu.edu.cn:8443",
        "User-Agent: doSingle/11 CFNetwork/976 Darwin/18.2.0"
    )
    @GET(MyURL.PATH_USR_INFO)
    fun getUserInfo(@Query("token") token: String): Call<JsonData>

    @Headers(
        "Content-Type: application/json;charset=UTF-8",
        "Connection: keep-alive",
        "Keep-Alive: timeout=5, max=1000",
        "Host: seat.lib.whu.edu.cn:8443",
        "User-Agent: doSingle/11 CFNetwork/976 Darwin/18.2.0"
    )
    @GET(MyURL.PATH_RESERVATIONS)
    fun reservations(@Query("token") token: String): Call<JsonData>

    @Headers(
        "Content-Type: application/json;charset=UTF-8",
        "Connection: keep-alive",
        "Keep-Alive: timeout=5, max=1000",
        "Host: seat.lib.whu.edu.cn:8443",
        "User-Agent: doSingle/11 CFNetwork/976 Darwin/18.2.0"
    )
    @GET(MyURL.PATH_HISTORY)
    fun getHistory(
        @Path("page") page: Int,
        @Path("pageSize") pageSize: Int,
        @Query("token") token: String
    ): Call<JsonData>

    @Headers(
        "Content-Type: application/json;charset=UTF-8",
        "Connection: keep-alive",
        "Keep-Alive: timeout=5, max=1000",
        "Host: seat.lib.whu.edu.cn:8443",
        "User-Agent: doSingle/11 CFNetwork/976 Darwin/18.2.0"
    )
    @GET(MyURL.PATH_LAYOUT_BY_DATE)
    fun getSeatsInRoom(
        @Path("roomId") roomId: String,
        @Path("date") date: String,
        @Query("token") token: String
    ): Call<JsonData>

}