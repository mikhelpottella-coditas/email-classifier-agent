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
public class DataReader {


    private final ResourceLoader resourceLoader;


}

