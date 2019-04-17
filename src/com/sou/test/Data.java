package com.sou.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Data {
	private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3573.0 Safari/537.36";
	
	public static void get(String needSearch) throws IOException{
		int i = 1;
		String url = "http://pan.here325.com/s?q="+ needSearch + "&p=" + i;
		
		Document doc = Jsoup.connect(url).userAgent(userAgent).get();
		Elements name = doc.select("div.data-item div a");
		String titles[] = new String[name.size()];
		String outLinks[] = new String[name.size()];
		String innerUrls[] = new String[name.size()];
		
		int j = 0;
		for(Element e : name){
			outLinks[j] = e.attr("href");
			titles[j] = e.text();
			j++;
		}
		
		for(int k = 0; k < name.size(); k++){
			Document doc1 = Jsoup.connect(outLinks[k]).userAgent(userAgent).get();
			Elements innerUrl = doc1.select("body div div iframe");
			innerUrls[k] = innerUrl.attr("src");
		}
		
//		System.out.println("-------------------------------------");
//		for(int k = 0; k < name.size(); k++){
//			System.out.println(titles[k]);
//		}
//		System.out.println("-------------------------------------");
//		for(int k = 0; k < name.size(); k++){
//			System.out.println(innerUrls[k]);
//		}
	}
	
	public static void main(String[] args) throws IOException {
		get("java");
	}
}
