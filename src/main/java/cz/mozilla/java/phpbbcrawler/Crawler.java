package cz.mozilla.java.phpbbcrawler;

import cz.mozilla.java.phpbbcrawler.helpers.IsLinkToDomain;
import cz.mozilla.java.phpbbcrawler.helpers.RemoveFragment;
import cz.mozilla.java.phpbbcrawler.helpers.RemoveKeyFromQueryString;
import cz.mozilla.java.phpbbcrawler.helpers.UniqueQueue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

public class Crawler {

    private static final Logger log = LoggerFactory.getLogger(Crawler.class);

    public Set<String> crawl(String startUrl) throws MalformedURLException {
        final long start = System.currentTimeMillis();
        final String domain = new URL(startUrl).getHost();
        final UniqueQueue<String> toCrawl = new UniqueQueue<>(Collections.singletonList(startUrl));
        final Set<String> crawled = new HashSet<>();

        String url;
        while ((url = toCrawl.poll()) != null) {
            // Skip this url if crawled already
            if (!crawled.add(url)) {
                // log.info("{} has been crawled already.", url);
                continue;
            }

            try {
                // Load url and add all links for future crawling
                Document doc = Jsoup.connect(url).get();
                doc.select("a[href]").stream()
                        .map(a -> a.absUrl("href"))
                        .filter(new IsLinkToDomain(domain))
                        .filter(href -> Stream.of("index.php", "viewforum.php", "viewtopic.php").parallel().anyMatch(href::contains))
                        .map(new RemoveFragment())
                        .map(new RemoveKeyFromQueryString("sid"))
                        .filter(href -> !crawled.contains(href))
                        .sequential()
                        .forEach(toCrawl::offer);
            } catch (IOException e) {
                log.warn("Could not crawl {}.", url, e);
            }

            if (crawled.size()%100 == 0) {
                log.info(
                        "Crawled {} urls in {} seconds, {} enqueued to be crawled.",
                        crawled.size(),
                        (System.currentTimeMillis()-start)/1000,
                        toCrawl.size()
                );
            }
        }

        return crawled;
    }
}
