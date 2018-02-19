package br.com.studiotrek.impactaservice.access_error.regra;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.Serializable;

public class AccessErrorRegra implements Serializable {

    public void parseHtml(String html) throws Exception {

        Document doc = Jsoup.parse(html);
        Element element = doc.getElementById("form-login");

        if(element == null) {
            throw new Exception("Error");
        }
    }

}
