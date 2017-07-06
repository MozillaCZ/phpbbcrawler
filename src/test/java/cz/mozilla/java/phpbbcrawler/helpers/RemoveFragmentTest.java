package cz.mozilla.java.phpbbcrawler.helpers;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveFragmentTest {

    @Test
    public void removesFragment() {
        // Arrange
        String url = "https://example.com/path/to/file.txt?query=string#fragment";

        // Act
        String result = Stream.of(url).map(new RemoveFragment()).findAny().get();

        // Assert
        assertThat(result, is("https://example.com/path/to/file.txt?query=string"));
    }

}
