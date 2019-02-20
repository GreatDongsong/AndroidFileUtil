package com.dawson.androidfileutil;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rick on 15/6/11.
 */
public final class Constants {
    public static final String WECHAT_MINIPROGRAM_APPNAME = "weixin_mini_program";
    public static final String WECHAT_APP_ID = "wxe0214065c1e9863e";
    public static final String WECHAT_MINIPROGRAM_ID = "gh_a87ea019a68d";

    public static final String TEST_APPID_QQOPEN = "1105886239";
    public static final String TENCENT_WIFI_PRODUCT_ZIPP2_ID = "a3b1a178-e432-4f80-9cb5-552a2128733b:8629ba3a54c34ae8b6e297db28e48c45";
    public static final String TENCENT_WIFI_PRODUCT_COCO_ID = "0573aca0e0a611e882c43d0d24698636:3557754375354c1d90c36fe126b45aaa";

    public static final String TENCENT_WIFI_DEVOEM = "LIBRATONE";
    public static final String TENCENT_WIFI_DEVTYPE = "SPEAKER";

    public static final String WECHAT_APP_SECRET = "5517fe662b151ff1649b32cc0dc0bc97";
    public static final String DOUBAN_APIKEY = "02866a151bfea94507933e3f53fbfbcf";
    public static final String DOUBAN_CLIENT_SECRET = "11bcb179d9311edb";
    public static final String redirect_uri = "http://www.libratone.com";
    public static final String DOUBAN_REDHEARD_CHANNELID = "-3";
    public static final String DOUBAN_MYPRIVATE_CHANNELID = "0";
    public static final String DOUBAN_CLIENT = "s:mobile|y:android|f:603|m:Douban|d:-1081994751|e:google";
    public static final String DOUBAN_APP_NAME = "radio_libratone";
    public static final String DOUBAN_VERSION = "1";
    public static final String DOUBAN_PT = "0";
    public static final String CONTROL_TYPE_LIKE = "r";
    public static final String CONTROL_TYPE_DISLIKE = "u";
    //The first request list, or the first request list after switching megahertz
    public static final String CONTROL_TYPE_GET = "n";
    public static final String CONTROL_TYPE_SKIP = "s";
    //For the current list when the play is finished (or about to play)
    public static final String CONTROL_TYPE_P = "p";
    //Report a song playback complete, only report used
    public static final String CONTROL_TYPE_E = "e";
    //No longer play
    public static final String CONTROL_TYPE_b = "b";

    public static final String IO_FAILURE = "i/o failure";
    public static Resources resources = MyApplication.getContext().getResources();

    public static final int MAX_BTCHANNLE_DATA_LENGHT = 70;
    public static final int MAX_BTCHANNLE_SINGLE_DATA_LENGHT = 14;

    public static final String SEARCH_URL = "asp/browsexml/Search.asp?sSearchtype=3&search=";

    public static boolean TEST_MODE = true;

    private static final String TERM_URL_DEFAULT = "http://assets.libratone.com/app/en/terms/";
    private static final String TERM_URL_ZH = "http://libratone.com.cn/terms/TermsAndConditions.html";

    private static final String POLICY_URL_DEFAULT = "http://assets.libratone.com/app/en/privacy/";
    private static final String POLICY_URL_ZH = "http://libratone.com.cn/terms/PrivacyStatement.html";

    //below just the same
    private static final String RENT_URL_DEFAULT = "http://libratone.com.cn/event/lease-guide/Android.html";
    private static final String RENT_URL_ZH = "http://libratone.com.cn/event/lease-guide/Android.html";

    //redirect link for filter out this link
    public static final String RENT_URL_ZH_REDIRECT = "http://libratone.com.cn/event/lease-guide/Android.html";

    //add for webview init parameter
    public static final String WEBVIEW_ACCESS_URL = "source_url";
    public static final String WEBVIEW_ACCESS_DEVICEMODE = "device_mode";
    public static final String WEBVIEW_TITLE = "title_info";
    public static final String WEBVIEW_RETURN_HOMEPAGE = "back_homepage";
    //used only for whether right close button is displayed and function.
    public static final String WEBVIEW_CLOSE_ENABLE = "close_enable";

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 100;
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_PHONE_STATE = 200;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 300;

    //used for whether adjust font size to max
    public static final String WEBVIEW_FONTMAX_ENABLE = "fond_adjust";
    public static final String JAVASCRIPT = "javascript";

    //add for avs login
    public static final String CHALLENGE_METHOD = "S256";
    public static final String ALEXA_ALL_SCOPE = "alexa:all";
    public static final String DEVICE_SERIAL_NUMBER = "deviceSerialNumber";
    public static final String PRODUCT_INSTANCE_ATTRIBUTES = "productInstanceAttributes";
    public static final String PRODUCT_ID = "productID";

    //ai service name;
    public static final String AI_TYPE_WIFI = "wifi";
    public static final String AI_TYPE_MOBILE = "mobile";

    public static final String AI_NAME_AVS_WIFI = "avs";
    public static final String AI_NAME_TENCENT_WIFI = "tencent";
    public static final String AI_NAME_BAIDU_WIFI = "baidu";
    public static final String AI_NAME_BAIDU_MOBILE = "baidu";

    //add for avs timeout
    public static final int MSG_REMOTE_EXECUTE_TIMEOUT_ID = 0x00010001;
    //add this timeout for device no result.
    public static final int MSG_REMOTE_DEVICE_EXECUTE_TIMEOUT_ID = 0x00010002;
    public static final int TVS_APP_TO_WECHAT_EXECUTE_AUTHORIZE_TIMEOUT = 5000;
    public static final int DEFAULT_REMOTE_EXECUTE_TENCENT_TIME = 8000;
    public static final int AVS_APP_TO_AMAZON_EXECUTE_AUTHORIZE_TIMEOUT = 10000;
    public static final int AVS_DEVICE_TO_AMAZON_EXECUTE_AUTHORIZE_TIMEOUT = 20000;

    public static final int MAX_WIFI_PRODUCT_NAME_LENGTH = 32;
    public static final int MAX_BT_PRODUCT_NAME_LENGTH = 30;

    //add for error message
    public static final int MESSAGE_FAIL_CHANNEL_INVALID = 1;

    //note: wifi device version is float.
    public static final float WIFI_LUCY_SUPPORTED_PROTOCOL_VERSION = 1.0f;
    //only used for bt device or type c device.
    public static final int HEADSET_SUPPORTED_PROTOCOL_VERSION = 1;

    public static final String EMPTY_DSN_TENCENT = "000000";

    public static final String DEFAULT_CHINA_FAVORITE = "{\n" +
            "    \"channel_1\": {\n" +
            "        \"channel_id\": 1,\n" +
            "        \"channel_identity\": \"77\",\n" +
            "        \"channel_name\": \"静谧时光\",\n" +
            "        \"channel_type\": \"doubanfm\"\n" +
            "    },\n" +
            "    \"channel_2\": {\n" +
            "        \"channel_id\": 2,\n" +
            "        \"channel_identity\": \"155\",\n" +
            "        \"channel_name\": \"指尖轻挑\",\n" +
            "        \"channel_type\": \"doubanfm\"\n" +
            "    },\n" +
            "    \"channel_3\": {\n" +
            "        \"channel_id\": 3,\n" +
            "        \"channel_identity\": \"257\",\n" +
            "        \"channel_name\": \"心跳节奏\",\n" +
            "        \"channel_type\": \"doubanfm\"\n" +
            "    },\n" +
            "    \"channel_4\": {\n" +
            "        \"channel_id\": 4,\n" +
            "        \"channel_identity\": \"260744\",\n" +
            "        \"channel_name\": \"晚安妈妈睡前故事\",\n" +
            "        \"channel_type\": \"xmly\"\n" +
            "    },\n" +
            "    \"channel_5\": {\n" +
            "        \"channel_id\": 5,\n" +
            "        \"channel_identity\": \"2823309\",\n" +
            "        \"channel_name\": \"虎嗅·商业有味道\",\n" +
            "        \"channel_type\": \"xmly\"\n" +
            "    }\n" +
            "\n" +
            "}";

    public static final String DEFAULT_FOREIGN_FAVORITE = "{\n" +
            "    \"bitmap\": 31,\n" +
            "    \"channel_1\": {\n" +
            "        \"channel_id\": 1,\n" +
            "        \"channel_identity\": \"26128\",\n" +
            "        \"channel_name\": \"BBC Radio 6 Music\",\n" +
            "        \"channel_type\": \"vtuner\"\n" +
            "    },\n" +
            "    \"channel_2\": {\n" +
            "        \"channel_id\": 2,\n" +
            "        \"channel_identity\": \"3158\",\n" +
            "        \"channel_name\": \"BBC Radio 4\",\n" +
            "        \"channel_type\": \"vtuner\"\n" +
            "    },\n" +
            "    \"channel_3\": {\n" +
            "        \"channel_id\": 3,\n" +
            "        \"channel_identity\": \"13417\",\n" +
            "        \"channel_name\": \"Radio Paradise\",\n" +
            "        \"channel_type\": \"vtuner\"\n" +
            "    },\n" +
            "    \"channel_4\": {\n" +
            "        \"channel_id\": 4,\n" +
            "        \"channel_identity\": \"36322\",\n" +
            "        \"channel_name\": \"Bossa Jazz Brasil\",\n" +
            "        \"channel_type\": \"vtuner\"\n" +
            "    },\n" +
            "    \"channel_5\": {\n" +
            "        \"channel_id\": 5,\n" +
            "        \"channel_identity\": \"24570\",\n" +
            "        \"channel_name\": \"Chinese Music World\",\n" +
            "        \"channel_type\": \"vtuner\"\n" +
            "    }\n" +
            "}";

    private Constants() {
    }

    public static final class Device {
        public static final int BT_BATTERY_LOW_POWER = 20;
        public static final int WIFI_BATTERY_LOW_POWER = 40;
        public static final int WIFI_SIGNAL_STRENGTH = 40;
        public static final int WIFI_DELTAN_BATTERY_LOW_POWER = 30;
    }

    public static final class XIMALAYA {
        public static final String app_key = "bddf76da60724867370d423bf61b9826";
        public static final String app_secret = "d5cbc89abb408896e0a04c1a14f32449";
        public static final String client_os_type = "4";
        public static final String serverAuthenticateStaticKey = "fIBumJji";
    }

    public static final class ITEM {
        public static final String CHANNEL_ITEM = "CHANNEL_ITEM";
        public static final String PLAYER_ITEM = "PLAYER_ITEM";
        public static final String SOUNDSPACE_ITEM = "SOUNDSPACE_ITEM";

        //add for save channel for common user.
        public static final String CHANNEL_USER_UPDATE = "CHANNEL_USER_UPDATE";
        public static final String CHANNEL_ACTION = "CHANNEL_ACTION";
        public static final String SOURCE_CHANNEL = "SOURCE_CHANNEL";
        public static final String SOURCE_SUPPOTR = "SOURCE_SUPPOTR";
        public static final String IGNORE_DEVICE_EVENT = "IGNORE_DEVICE_EVENT";
    }

    public static final class CHANNEL {
        public static final String CHANNEL = "channel";
        public static final String VTUNER = "vtuner";
        public static final String XIMALAYA = "xmly";
        public static final String DOUBAN = "doubanfm";
        public static final String NAPSTER = "napster";
        public static final String AUDIOGUM_NAPSTER = "audiogum_napster";//for favourites
        public static final String TIDAL = "tidal";
        public static final String AUDIOGUM_TIDAL = "audiogum_tidal";//for favourites
        public static final String AUDIOGUM = "audiogum";
        //add for baidu
        public static final String BAIDU_PREFIX = "baidu";
        public static final String BAIDU_BILL = "baidu_bill";
        public static final String BAIDU_ARTIST = "baidu_artist";
        public static final String BAIDU_GEDAN = "baidu_gedan";
        public static final String BAIDU_RADIO = "baidu_radio";
        //add for spotify
        public static final String SPOTIFY = "spotify";
        //add for kaishu
        public static final String KAISHU_ALBUM = "kaishu";
        //alexa
        public static final String AMAZON_PREFIX = "AVS_";

        //when music service is invalid, this will return from AG server.
        public static final String CHANNEL_ALL_INVALID = "all";


        //below define come from source docs. please refer to
        //LBT_SW_Delta_Audio Source And Playback Capability Definition_v0.5_20160121
        public static final byte CHANNEL_VTUNER_TYPE = (byte) 27;
        public static final byte CHANNEL_XIMALAYA_TYPE = (byte) 28;
        public static final byte CHANNEL_DOUBAN_TYPE = (byte) 29;

        public static final byte CHANNEL_BAIDU_RADIO_TYPE = (byte) 38;
        public static final byte CHANNEL_BAIDU_BILL_TYPE = (byte) 39;
        public static final byte CHANNEL_BAIDU_ARTIST_TYPE = (byte) 40;
        public static final byte CHANNEL_BAIDU_GEDAN_TYPE = (byte) 41;
        public static final byte CHANNEL_KAISHU_TYPE = (byte) 42;

    }


    public static final class MyMusicError {
        public static final int BAIDU_MUSIC_NET_WORK_ERROR = -902;
        public static final int BAIDU_MUSIC_NET_WORK_ERROR_1 = -900;
    }

    public static final class LogConstant {
        public static final String LOG_TYPE_CRASH_DUMP = "crashdump";
        public static final String LOG_TYPE_LOG = "log";
        public static final String LOG_DIRECT = "log";
        public static final String NORMAL_LOG_DIRECT_PREFIX = "NormalLog";
        public static final String PLAY_LOG_DIRECT_PREFIX = "PlayLog.roll";
        public static final String APP_FUNCTION_LOG_DIRECT_PREFIX = "AppFunctionLog.roll";
        public static final String AUTH_LOG_DIRECT_PREFIX = "AuthLog";
        public static final String CRASH_LOG_PREFIX = "CrashLog";
        public static final String LOG_NAME_AT_SYMBOL = "ATSYMBOL";
        public static final String NAVIGATION_LOG_DIRECT_PREFIX = "NavigationLog";
        public static final String OTA_LOG_DIRECT_PREFIX = "OTALog";
        public static final String FAVORITE_DIRECT = "favorite";
        public static final String FAVORITE_LOG_PREFIX = "FavoriteLog";
    }

    public static final class LogConstants {
        //@link https://www.audiogum.cn/developers/docs/analytics.html#actions
        public static final class Play {
            //start, Playback from a source started
            public static final String ACTION_START = "start";
            //stop, Playback from a source stopped or paused
            public static final String ACTION_STOP = "stop";
            //resume, Playback from a source resumed (after pause/stop)
            public static final String ACTION_RESUME = "resume";
            //begin, An item (song/track/chapter etc) from a source started
            public static final String ACTION_BEGIN = "begin";
            //complete, An item from a source has completed
            public static final String ACTION_COMPLETE = "complete";
            //skipnext, User has skipped forward
            public static final String ACTION_NEXT = "skipnext";
            //skipprev, User has skipped backward/rewound
            public static final String ACTION_PRE = "skipprev";
            //seek, User has moved offset in currently playing item
            public static final String ACTION_SEEK = "seek";
        }

        public static final class Auth {
            public static final String ACTION_SIGNIN = "signin";
            public static final String ACTION_SIGNUP = "signup";
            public static final String ACTION_SIGNOUT = "signout";
            public static final String SERVICE_DOUBAN = "douban";
            public static final String SERVICE_AUDIOGUM = "audiogum";
            public static final String SERVICE_WECHAT = "wechat";
            public static final String SERVICE_FACEBOOK = "facebook";
            public static final String SERVICE_GOOGLE = "google";
            public static final String RESULT_FAIL = "fail";
            public static final String RESULT_SUCCESS = "success";
        }

        public static final class Navigation {
            public static final String SOURCE_CONTROL_LIST = "list";
            public static final String SOURCE_CONTROL_BUTTON = "button";

            public static final String SOURCE_TAG_SYSTEMABLUM = "systemablum";
            public static final String SOURCE_TAG_SYSTEMCAMERA = "systemcamera";
            public static final String SOURCE_TAG_REGISTEREDPRODUCTS = "registeredproducts";
            public static final String SOURCE_TAG_NOTIFYCATION = "notifycation";
            public static final String SOURCE_TAG_PROFILESETTING = "profilesetting";
            public static final String SOURCE_TAG_REGISTERPRODUCT = "registerproduct";
            public static final String SOURCE_TAG_VIEWINTRODUCTVEDIO = "viewintroductvedio";
            public static final String SOURCE_TAG_TOPRODUCTAPGE = "toproductapge";
            public static final String SOURCE_TAG_TOGROUPPAGE = "togrouppage";
            public static final String SOURCE_TAG_TOSPOTIFYAPP = "tospotifyapp";
            public static final String SOURCE_TAG_HELP = "help";
            public static final String SOURCE_TAG_ADDPRODUCT = "addproduct";
            public static final String SOURCE_TAG_SWITCHSOURCE = "switchsource";
            public static final String SOURCE_TAG_PRODUCTSETTING = "productsetting";
            public static final String SOURCE_TAG_CHECKWIFISIGNAL = "checkwifisignal";
            public static final String SOURCE_TAG_CHECKBATTERYLEVEL = "checkbatterylevel";
            public static final String SOURCE_TAG_TOFAVOURITES = "tofavourites";
            public static final String SOURCE_TAG_ADDCOLLECTION = "addcollection";
            public static final String SOURCE_TAG_ADDFAVOURITES = "addfavourites";
            public static final String SOURCE_TAG_LINKPRODUCT = "linkproduct";
            public static final String SOURCE_TAG_UNLINKPRODUCT = "unlinkproduct";
            public static final String SOURCE_TAG_TOUSBPAGE = "tousbpage";
            public static final String SOURCE_TAG_TALKTHROUGH = "talkthrough";
            public static final String SOURCE_TAG_GROUPSETTING = "groupsetting";
            public static final String SOURCE_TAG_UPGRADEPRODUCT = "upgradeproduct";
            public static final String SOURCE_TAG_EDITUSBPLAYLIST = "editusbplaylist";
            public static final String SOURCE_TAG_SWITCHROOMMODE = "switchroommode";
            public static final String SOURCE_TAG_DO_NOT_DISTURB = "donotdisturb";
            public static final String SOURCE_TAG_CONFIGURESLEEPTIME = "configuresleeptime";
            public static final String SOURCE_TAG_SWITCHVOICEMODE = "switchvoicemode";
            public static final String SOURCE_TAG_CHANGEWIFI = "changewifi";
            public static final String SOURCE_TAG_EDITPRODUCTNAME = "editproductname";
            public static final String SOURCE_TAG_EDITPRODUCTCOLOR = "editproductcolor";
            public static final String SOURCE_TAG_RESETPRODUCTSETTING = "resetproductsetting";
            public static final String SOURCE_TAG_SLEEPPRODUCT = "sleepproduct";
            public static final String SOURCE_TAG_ENABLEBTSELECTSLAVEGUIDE = "enablebtselectslaveguide";
            public static final String SOURCE_TAG_DISABLEBTSELECTSLAVEGUIDE = "disablebtselectslaveguide";
            public static final String SOURCE_TAG_TOGGLESTEREO = "togglestereo";
            public static final String SOURCE_TAG_SEARCHMUSIC = "searchmusic";
            public static final String SOURCE_TAG_EXITAPP = "exitapp";
            public static final String SOURCE_TAG_APPINBACKGROUND = "appinbackground";
            public static final String SOURCE_TAG_CHANGEPASSWORD = "changepassword";
            public static final String SOURCE_TAG_FORGETPASSSWORD = "forgetpasssword";
            public static final String SOURCE_TAG_ENABLEHINT = "enablehint";
            public static final String SOURCE_TAG_DISABLEHINT = "disablehint";
            public static final String SOURCE_TAG_DISABLEDOUBANSERVICE = "disabledoubanservice";
            public static final String SOURCE_TAG_ENABLEDOUBANSERVICE = "enabledoubanservice";
            public static final String SOURCE_TAG_SAVESETTING = "savesetting";
            public static final String SOURCE_TAG_LIST_CLICK = "listclick";
        }

        public static final class AppFunction {
            public static final String FUNCTION_AVS_AUTH = "avs authorize";
            public static final String FUNCTION_TVS_AUTH = "tvs authorize";
            public static final String FUNCTION_LAUNCH_AD = "launch AD";
            public static final String FUNCTION_MULTI_ROOM = "multi room";

            public static final String STAGE_START = "start";
            public static final String STAGE_ON_GOING = "on going";
            public static final String STAGE_COMPLETE = "complete";
        }
    }

    public static final class PageType {
        public static final int SPEAKER_PAGE_WHITE = 0;
        public static final int SPEAKER_PAGE_BLACK = 1;
        public static final int USB_PAGE = 2;
    }
}
