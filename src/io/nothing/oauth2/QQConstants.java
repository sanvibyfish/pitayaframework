package io.nothing.oauth2;

public class QQConstants {
    public static final String CLIENT_ID = "100297082";

    public static final String CLIENT_SECRET = "c5df159fdcd3f2608b970e31847df63b";

    public static final String REDIRECT_URL = "http://localhost/Callback";
    
    public static final String AUTHORIZATION_CODE_SERVER_URL = "https://graph.qq.com/oauth2.0/authorize?client_id=" + CLIENT_ID
	+ "&response_type=code&redirect_uri=" + REDIRECT_URL
	+ "&state=test&display=mobile";
    
    public static final String TOKEN_SERVER_URL = "https://github.com/login/oauth/access_token";

}
