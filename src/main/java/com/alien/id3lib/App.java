package com.alien.id3lib;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World! " + Charset.defaultCharset().name() + " 測試 " + args[0] );
        for (Map.Entry<String, Charset> e:Charset.availableCharsets().entrySet()) {
            System.out.println(" - " + e.getKey());
        }

    }
}
