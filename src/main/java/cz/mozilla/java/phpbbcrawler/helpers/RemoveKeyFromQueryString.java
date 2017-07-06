package cz.mozilla.java.phpbbcrawler.helpers;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RemoveKeyFromQueryString implements Function<String, String> {

    private static final Logger log = LoggerFactory.getLogger(RemoveKeyFromQueryString.class);

    private final String key;

    public RemoveKeyFromQueryString(String key) {
        this.key = key;
    }

    @Override
    public String apply(String url) {
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            List<NameValuePair> queryParams = uriBuilder.getQueryParams().stream()
                    .filter(pair -> !pair.getName().equals(key))
                    .collect(Collectors.toList());
            uriBuilder.removeQuery();
            if (!queryParams.isEmpty()) {
                uriBuilder.addParameters(queryParams);
            }
            return uriBuilder.toString();
        } catch (URISyntaxException e) {
            log.warn("Cannot remove key {} from url {}.", key, url);
            return url;
        }
    }
}
