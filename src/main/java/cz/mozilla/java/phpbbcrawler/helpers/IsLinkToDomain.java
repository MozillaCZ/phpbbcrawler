package cz.mozilla.java.phpbbcrawler.helpers;

import cz.mozilla.java.phpbbcrawler.Crawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Predicate;

public class IsLinkToDomain implements Predicate<String> {

    private static final Logger log = LoggerFactory.getLogger(Crawler.class);

    private final String domain;

    public IsLinkToDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public boolean test(String url) {
        try {
            return new URL(url).getHost().equals(domain);
        } catch (MalformedURLException e) {
            // log.warn("Could not enqueue link {}.", url);
            return false;
        }
    }
}
