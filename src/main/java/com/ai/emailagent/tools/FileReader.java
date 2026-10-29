package com.ai.emailagent.tools;

import com.networknt.schema.AbsoluteIri;
import com.networknt.schema.resource.ResourceLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class FileReader {


    private final ResourceLoader resourceLoader;

    @Tool(description = """
            this tool helps you to read all the emails that are available.
            """)
    public StringBuilder readFile() {

        System.out.printf("tool call is happened");

        String dirPath = "src/main/resources/static/emails";

        StringBuilder output = new StringBuilder(""" 
                """);
        // Traverse the directory and process files
        try (Stream<Path> paths = Files.walk(Paths.get(dirPath))) {

            paths
                    .filter(Files::isRegularFile)  // ensure it is a file
                    .forEach(file->{
                        InputStream inputStream = null;
                        try {
                            inputStream = resourceLoader.getResource((AbsoluteIri) file).getInputStream();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        reader.close();
                        output.append(content.toString().trim());

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });// prints the file path


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("tool call as been done!!");
        return output;
    }
}

