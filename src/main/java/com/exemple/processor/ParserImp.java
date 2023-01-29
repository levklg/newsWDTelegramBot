package com.exemple.processor;

import com.exemple.processor.Parser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ParserImp implements Parser {


    public ParserImp() {

    }

    @Override
    public Map<String, String> parsingNika() {
        HashMap<String, String> returnHashMap = new HashMap<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://nikatv.ru/news/lenta")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                    .timeout(5000)
                    .cookie("cookiename", "val234")
                    .cookie("anothercookie", "ilove")
                    .referrer("http://google.com")
                    .header("headersecurity", "xyz123")
                    .get();
            Elements resultLinks = doc.select("div.b-news-teaser-v4__title");
            Elements element = resultLinks.select("a[href]");
            var lt = element.eachText();
            var lh = element.eachAttr("href");

            for (int i = 0; i < lt.size(); i++) {
                returnHashMap.put(lt.get(i), "https://nikatv.ru" + lh.get(i));
            }


        } catch (IOException e) {
            System.out.println("No response from nikatv.ru");
            System.out.println(e);

        }


        return returnHashMap;
    }

    @Override
    public Map<String, String> parsingRbc() {
        HashMap<String, String> returnHashMap = new HashMap<>();
        List<String> rbcSelectorList = new LinkedList<String>();
        rbcSelectorList.add("div.main__big");
        rbcSelectorList.add("div.main__feed");
        Document doc = null;
        try {
            doc = Jsoup.connect("http://rbc.ru")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                    .timeout(5000)
                    .cookie("cookiename", "val234")
                    .cookie("anothercookie", "ilovejsoup")
                    .referrer("http://google.com")
                    .header("headersecurity", "xyz123")
                    .get();

            Elements resultLinks;
            Elements element;

            List<String> listText = new LinkedList<>();
            List<String> listHref = new LinkedList<>();
            for (var selector : rbcSelectorList) {
                resultLinks = doc.select(selector);
                element = resultLinks.select("a[href]");

                var lt = element.eachText();
                var lh = element.eachAttr("href");

                listText.addAll(lt);
                listHref.addAll(lh);
            }

            for (int i = 0; i < listText.size(); i++) {
                returnHashMap.put(listText.get(i), listHref.get(i));
            }

        } catch (IOException e) {
            System.out.println("No response from rbc.ru");
            System.out.println(e);
        }


        return returnHashMap;
    }


    public Map<String, String> parsingKaluga24() {
        //// Защита от DDOS атак при парсинге ошибка 403
        HashMap<String, String> returnHashMap = new HashMap<>();
        Document doc = null;
        try {
            Connection conn = Jsoup.connect("https://kaluga24.tv/home/news").timeout(5000);
            conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.header("Accept-Encoding", "gzip, deflate, sdch");
            conn.header("Accept-Language", "zh-CN,zh;q=0.8");
            conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

            doc = conn.get();

        } catch (IOException e) {
            System.out.println("No response from kaluga24.tv");
            System.out.println(e);
        }

        Elements resultLinks;
        Elements element;

        resultLinks = doc.select("div.news-one__main-inner");
        element = resultLinks.select("a[href]");
        List<String> listText = new LinkedList<>();
        List<String> listHref = new LinkedList<>();
        for (var e : element) {
            listText.add(e.text());
            listHref.add(e.attr("href"));
        }

        for (int i = 0; i < listText.size(); i++) {
            returnHashMap.put(listText.get(i), listHref.get(i));
        }


        return returnHashMap;
    }

    @Override
    public Map<String, String> parsingKP40() {
        HashMap<String, String> returnHashMap = new HashMap<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.kp40.ru/news/")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                    .timeout(5000)
                    .cookie("cookiename", "val234")
                    .cookie("anothercookie", "ilove")
                    .referrer("http://google.com")
                    .header("headersecurity", "xyz123")
                    .get();
            doc.select("div.news_info").remove();
            doc.select("div.nrli-rublink").remove();
            Elements resultLinks = doc.select("div.nrlif-body.col.-order-1.-order-sm-1");//"div.news-list"
            Elements element = resultLinks.select("a[href]");

            var lt = element.eachText();
            var lh = element.eachAttr("href");

            for (int i = 0; i < lt.size(); i++) {
                returnHashMap.put(lt.get(i), "https://www.kp40.ru/news/" + lh.get(i));
            }

        } catch (IOException e) {
            System.out.println("No response from www.kp40.ru");
            System.out.println(e);
        }

        return returnHashMap;
    }

    @Override
    public Map<String, String> parsingKalugaPoisk() {
        HashMap<String, String> returnHashMap = new HashMap<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.kaluga-poisk.ru/news")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                    .timeout(5000)
                    .cookie("cookiename", "val234")
                    .cookie("anothercookie", "ilove")
                    .referrer("http://google.com")
                    .header("headersecurity", "xyz123")
                    .get();
            Elements resultLinks;
            Elements element;
            doc.select("div.info").remove();
            resultLinks = doc.select("div.frontend-news-list");
            resultLinks = doc.select("div.row.new.frontend-news-list");
            element = resultLinks.select("a[href]");
            var lt = element.eachText();
            var lh = element.eachAttr("href");

            for (int i = 0; i < lt.size(); i++) {
                returnHashMap.put(lt.get(i), lh.get(i));
            }

        } catch (IOException e) {
            System.out.println("No response from kaluga-poisk.ru");
            System.out.println(e);
        }

        return returnHashMap;
    }

    @Override
    public Map<String, String> parsingZnamKaluga() {

        HashMap<String, String> returnHashMap = new HashMap<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://znamkaluga.ru/category/materialy/news/")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                    .timeout(5000)
                    .cookie("cookiename", "val234")
                    .cookie("anothercookie", "ilove")
                    .referrer("http://google.com")
                    .header("headersecurity", "xyz123")
                    .get();
            Elements resultLinks = doc.select("div.read-title");
            Elements element = resultLinks.select("a[href]");
            var lt = element.eachText();
            var lh = element.eachAttr("href");
            for (int i = 0; i < lt.size(); i++) {
                returnHashMap.put(lt.get(i), lh.get(i));
            }
        } catch (IOException e) {
            System.out.println("No response from znamkaluga.ru.ru");
            System.out.println(e);
        }
        return returnHashMap;
    }

}
