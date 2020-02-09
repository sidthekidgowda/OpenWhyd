package com.openwhyd.util

import com.openwhyd.model.HotTrackRes
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object JsonUtil {

    fun firstNetworkCall(): HotTrackRes {
        val json = "{\n" +
                "  \"hasMore\": {\n" +
                "    \"skip\": 3\n" +
                "  },\n" +
                "  \"genre\": \"Hip hop\",\n" +
                "  \"tracks\": [\n" +
                "    {\n" +
                "      \"_id\": \"568ffabaf81b6dfc3b29d424\",\n" +
                "      \"uId\": \"511b77ff7e91c862b2aacd54\",\n" +
                "      \"uNm\": \"Inni Root\",\n" +
                "      \"text\": \"\",\n" +
                "      \"pl\": {\n" +
                "        \"name\": \"Hip Hop\",\n" +
                "        \"id\": 4\n" +
                "      },\n" +
                "      \"name\": \"Black Moon - Who Got Da Props?\",\n" +
                "      \"eId\": \"/yt/DPuCpt2kYUs\",\n" +
                "      \"ctx\": \"bk\",\n" +
                "      \"img\": \"https://i.ytimg.com/vi/DPuCpt2kYUs/default.jpg\",\n" +
                "      \"nbP\": 14,\n" +
                "      \"nbR\": 5,\n" +
                "      \"score\": 300,\n" +
                "      \"nbL\": 0,\n" +
                "      \"prev\": 0,\n" +
                "      \"trackId\": \"569027f44bf212908fb673c3\",\n" +
                "      \"rankIncr\": -300\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"5e089aba2417891f2b612d54\",\n" +
                "      \"uId\": \"5356db0471eaec19b56fe9ff\",\n" +
                "      \"uNm\": \"Muriel (µµ)\",\n" +
                "      \"text\": \"\",\n" +
                "      \"pl\": {\n" +
                "        \"name\": \"HipHop / Rap\",\n" +
                "        \"id\": 8\n" +
                "      },\n" +
                "      \"name\": \"DA BREAK - Burning [Clip Officiel]\",\n" +
                "      \"eId\": \"/yt/uDOpPh_bAi8\",\n" +
                "      \"ctx\": \"bk\",\n" +
                "      \"img\": \"https://i.ytimg.com/vi/uDOpPh_bAi8/default.jpg\",\n" +
                "      \"src\": {\n" +
                "        \"id\": \"https://www.youtube.com/watch?time_continue=17&v=uDOpPh_bAi8&feature=emb_logo\",\n" +
                "        \"name\": \"DA BREAK - Burning [Clip Officiel] - YouTube\"\n" +
                "      },\n" +
                "      \"nbP\": 1,\n" +
                "      \"score\": 100,\n" +
                "      \"nbL\": 0,\n" +
                "      \"nbR\": 1,\n" +
                "      \"prev\": 100,\n" +
                "      \"trackId\": \"5e08c8765b78425153c5b5f7\",\n" +
                "      \"rankIncr\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"5dfbf2f82417891f2b6129f7\",\n" +
                "      \"uId\": \"5b554bb5a08223d7e2073aa6\",\n" +
                "      \"uNm\": \"ＨEＦE░ＨEEＴＲ♢Ｃ░\",\n" +
                "      \"text\": \"\",\n" +
                "      \"pl\": {\n" +
                "        \"name\": \"Swaggagascar 2 - [Mixtape]\",\n" +
                "        \"id\": 4\n" +
                "      },\n" +
                "      \"name\": \"ＨEＦE░ＨEEＴＲ♢Ｃ░ - \uD835\uDC6D\uD835\uDC68\uD835\uDC74\uD835\uDC76\uD835\uDC7C\uD835\uDC7A \uD835\uDD8B\uD835\uDD8A\uD835\uDD86\uD835\uDD99 \uD835\uDD6E\uD835\uDD97\uD835\uDD9E\uD835\uDD95\uD835\uDD99\uD835\uDD8E\uD835\uDD88\",\n" +
                "      \"eId\": \"/sc/weznilez/famous-feat-cryptic#https://api.soundcloud.com/tracks/707449834/stream\",\n" +
                "      \"ctx\": \"bk\",\n" +
                "      \"img\": \"https://i1.sndcdn.com/artworks-000653067202-96kl31-large.jpg\",\n" +
                "      \"order\": 5,\n" +
                "      \"lov\": [\n" +
                "        \"5b554bb5a08223d7e2073aa6\"\n" +
                "      ],\n" +
                "      \"score\": 100,\n" +
                "      \"nbP\": 0,\n" +
                "      \"nbL\": 1,\n" +
                "      \"nbR\": 1,\n" +
                "      \"prev\": 100,\n" +
                "      \"trackId\": \"5dfd7fba5b78425153701428\",\n" +
                "      \"rankIncr\": 0\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return moshi.adapter(HotTrackRes::class.java).fromJson(json)!!
    }

    fun getSecondNetworkCall(): HotTrackRes {
        val json = "{\n" +
                "  \"hasMore\": {\n" +
                "    \"skip\": 6\n" +
                "  },\n" +
                "  \"genre\": \"All\",\n" +
                "  \"tracks\": [\n" +
                "    {\n" +
                "      \"_id\": \"5e3a8f3452dbe65b7a275bc3\",\n" +
                "      \"uId\": \"52e94f9c7e91c862b2b4a61a\",\n" +
                "      \"uNm\": \"Hip Hop 4 Soul\",\n" +
                "      \"text\": \"\",\n" +
                "      \"name\": \"Koma - Avec c'qu'on vit feat. Morad\",\n" +
                "      \"eId\": \"/yt/Y2-uKgEiKB0\",\n" +
                "      \"ctx\": \"bk\",\n" +
                "      \"img\": \"https://i.ytimg.com/vi/Y2-uKgEiKB0/default.jpg\",\n" +
                "      \"src\": {\n" +
                "        \"id\": \"https://www.youtube.com/watch?v=Y2-uKgEiKB0\",\n" +
                "        \"name\": \"Koma - Avec c&#39;qu&#39;on vit feat. Morad - YouTube\"\n" +
                "      },\n" +
                "      \"nbP\": 1,\n" +
                "      \"score\": 200,\n" +
                "      \"nbL\": 0,\n" +
                "      \"nbR\": 2,\n" +
                "      \"prev\": 200,\n" +
                "      \"trackId\": \"5e3dab987853a6bfddbe539c\",\n" +
                "      \"rankIncr\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"5e3852fd52dbe65b7a275b3b\",\n" +
                "      \"uId\": \"544c39c3e04b7b4fca803438\",\n" +
                "      \"uNm\": \"Stefanos\",\n" +
                "      \"text\": \"\",\n" +
                "      \"pl\": {\n" +
                "        \"name\": \"Avant Garde\",\n" +
                "        \"id\": 29\n" +
                "      },\n" +
                "      \"name\": \"Whistling Arrow - Magician\",\n" +
                "      \"eId\": \"/yt/mNok8p_00l8\",\n" +
                "      \"ctx\": \"bk\",\n" +
                "      \"img\": \"https://i.ytimg.com/vi/mNok8p_00l8/default.jpg\",\n" +
                "      \"nbP\": 2,\n" +
                "      \"nbR\": 2,\n" +
                "      \"score\": 200,\n" +
                "      \"nbL\": 0,\n" +
                "      \"prev\": 200,\n" +
                "      \"trackId\": \"5e3dab147853a6bfddbe5003\",\n" +
                "      \"rankIncr\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"5e38507952dbe65b7a275b39\",\n" +
                "      \"uId\": \"55f7ee1a4bf212908fb4d96f\",\n" +
                "      \"uNm\": \"Constance de M\",\n" +
                "      \"text\": \"\",\n" +
                "      \"pl\": {\n" +
                "        \"name\": \"beau\",\n" +
                "        \"id\": 13\n" +
                "      },\n" +
                "      \"name\": \"'Midsommar' [2019] Soundtrack by Bobby Krlic\",\n" +
                "      \"eId\": \"/yt/JWaKI-_eIDQ\",\n" +
                "      \"ctx\": \"bk\",\n" +
                "      \"img\": \"https://i.ytimg.com/vi/JWaKI-_eIDQ/default.jpg\",\n" +
                "      \"lov\": [\n" +
                "        \"51ffab157e91c862b2af9030\"\n" +
                "      ],\n" +
                "      \"nbP\": 6,\n" +
                "      \"nbR\": 2,\n" +
                "      \"score\": 200,\n" +
                "      \"nbL\": 1,\n" +
                "      \"prev\": 200,\n" +
                "      \"trackId\": \"5e3939697853a6bfdda80124\",\n" +
                "      \"rankIncr\": 0\n" +
                "    }\n" +
                "  ]\n" +
                "}"

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return moshi.adapter(HotTrackRes::class.java).fromJson(json)!!
    }
}