package com.jibjab;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class PlistFontRenamer {

    private String stripExtension (String str) {
        if (str == null) return null;
        int pos = str.lastIndexOf(".");
        if (pos == -1) return str;
        return str.substring(0, pos);
    }

    private void editFile(File file, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
        String contents = new String(encoded, encoding);
        contents = contents.replaceAll("GLCGaramondBold", "1592GLCGaramondBold");
        contents = contents.replaceAll("GLCGaramondNormal", "1592GLCGaramondNormal");
        String outPath = "destFiles/" + file.getName();
        try(  PrintWriter out = new PrintWriter(outPath)){
            out.println( contents );
        }
        Process p = Runtime.getRuntime().exec("plutil -convert xml1 " + outPath + " -o destFiles/" + stripExtension(file.getName()) + ".plist");
        return;
    }

    private void loopThroughFiles(){
        File dir = new File("sourceFiles");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Do something with child
                System.out.println(child.getName());
                try{
                    editFile(child, StandardCharsets.UTF_8);
                }catch(Exception e){
                    System.err.println(e.getMessage());
                }

            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
    }

    public static void main(String[] args){
        PlistFontRenamer me = new PlistFontRenamer();
        me.loopThroughFiles();
    }
}
