package com.example.videoplay.html;

import com.example.videoplay.model.DotaHeroes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huangwy on 2015/11/27.
 * <img src="http://dota.tgbus.com/dxyimages/dota45/kunnka.jpg" alt="船长" title="海军上将">
 */
public class Html {
    public static List<DotaHeroes> getHeroesHtmlDota(String html) {
        List<DotaHeroes> lists = new ArrayList<DotaHeroes>();
        Document parse = Jsoup.parse(html);
        Elements tavernbody = parse.select("div.tavernbody");//获取所有class=tavernbody的div标签
        for (Element holder : tavernbody) {//获取所有模块下的英雄
            Elements children = holder.children();
            for (Element child : children) {//获取模块中的
                Elements lis = child.getElementsByTag("li");
                for (Element li : lis) {
                    Element img = li.getElementsByTag("img").first();
                    String content_url = li.select("a").attr("href").trim();
                    String img_url = img.attr("src").trim();
                    String alt = img.attr("alt").trim();
                    String title = img.attr("title").trim();
                    DotaHeroes heroes = new DotaHeroes();
                    heroes.setDetail_url(content_url);
                    heroes.setIcon_url(img_url);
                    heroes.setTitle(title);
                    lists.add(heroes);
                }
            }
        }
        return lists;
    }

    public static List<DotaHeroes> getHeroesHtmlDota2(String html) {
        int type = 0;
        List<DotaHeroes> lists = new ArrayList<DotaHeroes>();
        Document parse = Jsoup.parse(html);
        Elements tavernbody = parse.select("div.tavernbody");//获取所有class=tavernbody的div标签
        for (Element holder : tavernbody) {//获取所有模块下的英雄
            Elements lis = holder.getElementsByTag("li");
            for (Element li : lis) {
                Element img = li.getElementsByTag("img").first();
                String content_url = li.select("a").attr("href").trim();
                String img_url = img.attr("src").trim();
                String alt = img.attr("alt").trim();
                String title = img.attr("title").trim();

                DotaHeroes heroes = new DotaHeroes();
                heroes.setDetail_url(content_url);
                heroes.setIcon_url(img_url);
                heroes.setTitle(title);
                heroes.setType(type++);

                lists.add(heroes);
            }
        }
        return lists;
    }
}
