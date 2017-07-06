package cz.mozilla.java.phpbbcrawler.helpers;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.function.Function;

public class RemoveFragment implements Function<String, String> {

    private static final Logger log = LoggerFactory.getLogger(RemoveFragment.class);

    @Override
    public String apply(String url) {
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.setFragment(null);
            return uriBuilder.toString();
        } catch (URISyntaxException e) {
            log.warn("Cannot remove fragment from url {}.", url);
            return url;
        }
    }
}
