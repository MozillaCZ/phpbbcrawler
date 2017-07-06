package cz.mozilla.java.phpbbcrawler.helpers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UniqueQueueTest {

    @Test
    public void keepsOrder() {
        // Arrange
        UniqueQueue<String> q = new UniqueQueue<>();

        // Act
        q.offer("3");
        q.offer("5");
        q.offer("1");

        // Assert
        assertThat(q.poll(), is("3"));
        assertThat(q.poll(), is("5"));
        assertThat(q.poll(), is("1"));
    }

    @Test
    public void isUnique() {
        // Arrange
        UniqueQueue<String> q = new UniqueQueue<>();

        // Act
        q.offer("3");
        q.offer("5");
        q.offer("5");
        q.offer("3");
        q.offer("3");
        q.offer("1");

        // Assert
        assertThat(q.poll(), is("3"));
        assertThat(q.poll(), is("5"));
        assertThat(q.poll(), is("1"));
    }

    @Test
    public void returnsNullIfEmpty() {
        // Arrange
        UniqueQueue<String> q = new UniqueQueue<>();

        // Assert
        assertThat(q.poll(), is(nullValue()));
    }

}
