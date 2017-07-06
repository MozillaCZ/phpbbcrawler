package cz.mozilla.java.phpbbcrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Start URL expected as the first argument.");
            System.exit(1);
        }

        String startUrl = args[0];
        Crawler c = new Crawler();
        String output = null;
        try {
            output = c.crawl(startUrl).stream().collect(Collectors.joining("\n"));
        } catch (MalformedURLException e) {
            System.err.printf("Given start url \"%s\" is malformed.%n", startUrl);
            System.exit(1);
        }

        try {
            Path outputDir = Files.createDirectories(Paths.get(".", "out"));
            Path outputFile = Paths.get(outputDir.toString(), "output.txt");
            Files.newBufferedWriter(Files.createFile(outputFile), StandardCharsets.UTF_8).write(output);
        } catch (IOException e) {
            System.err.println("Error writing output.");
            System.exit(2);
        }
    }
}
