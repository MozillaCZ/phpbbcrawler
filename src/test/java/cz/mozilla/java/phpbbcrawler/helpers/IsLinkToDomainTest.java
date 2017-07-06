package cz.mozilla.java.phpbbcrawler.helpers;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IsLinkToDomainTest {

    @Test
    public void keepsTheRightDomain() {
        // Arrange
        String url = "https://example.com/path/to/file.txt?query=string#fragment";

        // Act
        Optional<String> result = Stream.of(url).filter(new IsLinkToDomain("example.com")).findAny();

        // Assert
        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(url));
    }

    @Test
    public void filtersDifferentDomain() {
        // Arrange
        String url = "https://another.example.com/path/to/file.txt?query=string#fragment";

        // Act
        Optional<String> result = Stream.of(url).filter(new IsLinkToDomain("example.com")).findAny();

        // Assert
        assertThat(result.isPresent(), is(false));
    }

}
