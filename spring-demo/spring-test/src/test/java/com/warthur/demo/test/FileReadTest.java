package com.warthur.demo.test;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class FileReadTest {

    @Test
    public void testReadFile() throws Exception {
        File file = new File(FileReadTest.class.getResource("/README.txt").getFile());

        if (file.exists() && file.canRead()) {
            showFileContent(file);
        }
        Assert.assertEquals(File.class, file.getClass());
    }

    private static void showFileContent(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));

        StringBuilder contentHolder = new StringBuilder();

        String lineContent = null;

        while ((lineContent = br.readLine()) != null) {
            contentHolder.append(lineContent);
        }

        br.close();

        System.out.println("content=" + contentHolder);

    }
}
