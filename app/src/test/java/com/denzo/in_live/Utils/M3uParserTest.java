package com.denzo.in_live.Utils;

import com.denzo.in_live.Model.M3uChannel;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class M3uParserTest {

    @Test
    public void testParse() throws IOException {
        String m3uContent = "#EXTM3U\n" +
                "#EXTINF:-1 tvg-id=\"id1\" tvg-name=\"Channel 1\" tvg-logo=\"logo1.png\",Channel 1 Name\n" +
                "http://example.com/stream1.m3u8\n" +
                "#EXTINF:-1,Channel 2\n" +
                "http://example.com/stream2.m3u8";

        InputStream inputStream = new ByteArrayInputStream(m3uContent.getBytes(StandardCharsets.UTF_8));
        List<M3uChannel> channels = M3uParser.parse(inputStream);

        assertEquals(2, channels.size());
        
        assertEquals("Channel 1", channels.get(0).getName());
        assertEquals("http://example.com/stream1.m3u8", channels.get(0).getUrl());
        assertEquals("logo1.png", channels.get(0).getLogoUrl());
        assertEquals("id1", channels.get(0).getTvgId());

        assertEquals("Channel 2", channels.get(1).getName());
        assertEquals("http://example.com/stream2.m3u8", channels.get(1).getUrl());
    }
}
