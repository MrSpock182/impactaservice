package br.com.studiotrek.impactaservice.request_impacta;

import br.com.studiotrek.impactaservice.util.Const;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Request implements Serializable {
    public static final String COOKIE = "cookie";
    public static final String RESPONSE = "response";

    private static final String UTF8 = "UTF-8";
    private Map<String, String> map;

    public Request() {
        map = new HashMap<>();
    }

    public Map<String, String> login(String rm, String senha) throws Exception {
        postBase(Const.URL_LOGIN, rm, senha, null, true);
        return map;
    }

    public String post(String urlBase, String cookie) throws Exception {
        return postBase(urlBase, null, null, cookie, false);
    }

    private String postBase(String urlBase, String rm, String senha, String cookie, Boolean login) throws Exception {
        URL url = new URL(urlBase);
        Map<String, Object> params = new LinkedHashMap<>();

        if(login) {
            params.put("desidentificacao", rm);
            params.put("dessenha", senha);
        }

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0)
                postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), UTF8));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), UTF8));
        }
        byte[] postDataBytes = postData.toString().getBytes(UTF8);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Cache-Control", "no-cache");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        if(!login) {
            conn.setRequestProperty("Cookie", cookie);
        }
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF8));

        StringBuilder retorno = new StringBuilder();
        for (int c; (c = in.read()) >= 0;) {
            retorno.append((char) c);
        }

        if(login) {
            map.put(COOKIE, conn.getHeaderField("Set-Cookie"));
            map.put(RESPONSE, retorno.toString());
        }

        return retorno.toString();
    }

}
