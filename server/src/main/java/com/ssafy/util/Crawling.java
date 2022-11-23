package com.ssafy.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
	public ArrayList<News> arr = new ArrayList<>();

	// 네이버 뉴스 다운로드
	public List<News> getNews() {
		StringBuilder html = new StringBuilder();

		List<News> news = new ArrayList<>();
		try {
			String address = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid2=260&sid1=101&page=1";
			// 네이버뉴스 사이트에 접속하게 해주는 객체 url 생성, 그안에 네이버뉴스 주소인 address를 넣어준다.
			URL url = new URL(address);
			// 사이트에 접속해줌. 엔터의 역할을 해준다.
			URLConnection con = url.openConnection();
			// 연결이되면 데이터를 받아오는데 받을때 아스키코드로 받아오는걸 inputStreamReader, BufferedReader로 감싸줘서
			// 가변적으로 받아온걸 바꿔준다.
			BufferedReader download = new BufferedReader(new InputStreamReader(con.getInputStream(), "EUC-KR"));

			// 데이터를 받아오는데 더이상 부을 값이 없어서 line에 null값이 되게되면 나오게 된다.
			while (true) {
				String line = download.readLine();
				if (line == null)
					break;
				html.append(line);
			}
			download.close();

			Document doc = Jsoup.parse(url.openStream(), "EUC-KR", address);
			Elements articleTitle = doc.select("ul[class=type06_headline] > li");

			for (int i = 0; i < articleTitle.size(); i++) {
				Element element = articleTitle.get(i);
				News post = new News();
				Elements e = element.select("dl > dt > a > img");
				String eUrl = element.select("dl > dt > a").attr("href");
				String content = element.select("dl > dd > span").first().text();
				String company = element.select("dl > dd > span").next().first().text();
				String regTime = element.select("dl > dd > span").next().next().text();

				post.setNewsNo(i + 1);
				post.setImg(e.attr("src"));
				post.setTitle(e.attr("alt"));
				post.setUrl(eUrl);
				post.setContent(content);
				post.setCompany(company);
				post.setCreatedDate(regTime);

				news.add(post);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return news;
	}
}
