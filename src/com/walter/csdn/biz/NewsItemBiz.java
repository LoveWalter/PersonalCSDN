package com.walter.csdn.biz;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.walter.csdn.bean.CommonException;
import com.walter.csdn.bean.News;
import com.walter.csdn.bean.NewsDto;
import com.walter.csdn.bean.NewsItem;
import com.walter.csdn.csdn.DataUtil;
import com.walter.csdn.csdn.URLUtil;

public class NewsItemBiz {
	public List<NewsItem> getNewsItems(int newsType, int currentPage)
			throws CommonException {
		String urlStr = URLUtil.generateUrl(newsType, currentPage);
		String htmlStr = DataUtil.doGet(urlStr);

		List newsItems = new ArrayList();
		NewsItem newsItem = null;

		Document doc = Jsoup.parse(htmlStr);
		Elements units = doc.getElementsByClass("unit");
		for (int i = 0; i < units.size(); i++) {
			newsItem = new NewsItem();
			newsItem.setNewsType(newsType);

			Element unit_ele = units.get(i);

			Element h1_ele = unit_ele.getElementsByTag("h1").get(0);
			Element h1_a_ele = h1_ele.child(0);
			String title = DataUtil.ToDBC(h1_a_ele.text());
			String href = h1_a_ele.attr("href");

			newsItem.setLink(href);
			newsItem.setTitle(title);

			Element h4_ele = unit_ele.getElementsByTag("h4").get(0);
			Element ago_ele = h4_ele.getElementsByClass("ago").get(0);
			String date = ago_ele.text();

			newsItem.setDate(date);

			Element dl_ele = unit_ele.getElementsByTag("dl").get(0);
			Element dt_ele = dl_ele.child(0);
			try {
				Element img_ele = dt_ele.child(0);
				String imgLink = img_ele.child(0).attr("src");
				newsItem.setImgLink(imgLink);
			} catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {
			}
			Element content_ele = dl_ele.child(1);
			String content = DataUtil.ToDBC(content_ele.text());
			newsItem.setContent(content);
			newsItems.add(newsItem);
		}

		return newsItems;
	}

	public NewsDto getNews(String urlStr) throws CommonException {
		NewsDto newsDto = new NewsDto();
		List newses = new ArrayList();
		String htmlStr = DataUtil.doGet(urlStr);
		Document doc = Jsoup.parse(htmlStr);

		Element detailEle = doc.select(".left .detail").get(0);

		Element titleEle = detailEle.select("h1.title").get(0);
		News news = new News();
		news.setTitle(titleEle.text());
		news.setType(1);
		newses.add(news);

		Element summaryEle = detailEle.select("div.summary").get(0);
		news = new News();
		news.setSummary(summaryEle.text());
		newses.add(news);

		Element contentEle = detailEle.select("div.con.news_content").get(0);
		Elements childrenEle = contentEle.children();

		for (Element child : childrenEle) {
			Elements imgEles = child.getElementsByTag("img");

			if (imgEles.size() > 0) {
				for (Element imgEle : imgEles) {
					if (!imgEle.attr("src").equals("")) {
						news = new News();
						news.setImageLink(imgEle.attr("src"));
						newses.add(news);
					}
				}
			}
			imgEles.remove();

			if (!child.text().equals("")) {
				news = new News();
				news.setType(3);
				try {
					if (child.children().size() == 1) {
						Element cc = child.child(0);
						if (cc.tagName().equals("b")) {
							news.setType(5);
						}
					}
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
				news.setContent(child.outerHtml());
				newses.add(news);
			}
		}
		newsDto.setNewses(newses);
		return newsDto;
	}
}
