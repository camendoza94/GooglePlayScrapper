package page;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import file.GooglePlayFile;
import object.App;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GooglePlay {

    private static Set<App> appList = new HashSet<>();

    public static void crawling() {
        Document doc;
        try {
            String MAIN_URL = "https://play.google.com/store/apps/category/FINANCE/collection/topselling_free";
            doc = Jsoup.connect(MAIN_URL).timeout(0).get();
            HashSet<String> hrefs = new HashSet<>();
            Elements links = doc.select("a.card-click-target");
            for (Element link : links) {
            	hrefs.add(link.attr("abs:href"));
            }
            for (String url: hrefs) {
            	subPage(url);
            }
            createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile() {
        new GooglePlayFile(appList);
    }

    private static void subPage(String url) throws IOException {
        try {
            Document doc = Jsoup.connect(url).get();
            System.out.println(url);
            pageApp(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private static void pageApp(Document doc) {
		App app = new App();
		app.setDescription(doc.select("[itemprop='description']").text());
		String ratingCount = doc.select("span.rating-count").text().replace(",", "");
		app.setRatingCount(Integer.parseInt(ratingCount));
		String averageRating = doc.select("div.score").text().replace(",",".");
		app.setAverageRating(Double.parseDouble(averageRating));
		app.setName(doc.select("div.id-app-title").text());
		String fiveStars = doc.select("div.five span.bar-number").text().replace(",", "");
		app.setFiveStarsRatings(Integer.parseInt(fiveStars));
		String fourStars = doc.select("div.four span.bar-number").text().replace(",", "");
        app.setFourStarsRatings(Integer.parseInt(fourStars));
        Elements changes = doc.select("div.recent-change");
        for (Element change : changes) {
        	app.addChange(change.text());
        }
        appList.add(app);
	}
}