package cz.mozilla.java.phpbbcrawler.helpers;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveKeyFromQueryStringTest {

    @Test
    public void removesKey() {
        // Arrange
        String url = "https://example.com/path/to/file.txt?query=string&key=value#fragment";

        // Act
        String result = Stream.of(url).map(new RemoveKeyFromQueryString("key")).findAny().get();

        // Assert
        assertThat(result, is("https://example.com/path/to/file.txt?query=string#fragment"));
    }

    @Test
    public void removesOnlyKey() {
        // Arrange
        String url = "https://example.com/path/to/file.txt?key=value#fragment";

        // Act
        String result = Stream.of(url).map(new RemoveKeyFromQueryString("key")).findAny().get();

        // Assert
        assertThat(result, is("https://example.com/path/to/file.txt#fragment"));
    }

}
