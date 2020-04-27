package br.com.brlima.cursomc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URLUtils {

    private URLUtils() {
        // Construtor vazio
    }

    public static List<Long> decodeLongList(String s) {
        return Arrays.asList(s.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
    }

    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
