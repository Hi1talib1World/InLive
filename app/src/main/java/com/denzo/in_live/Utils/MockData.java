package com.denzo.in_live.Utils;

public class MockData {
    public static final String HOME_JSON = "{\n" +
            "  \"slider\": [\n" +
            "    {\n" +
            "      \"name\": \"Welcome to In-Live\",\n" +
            "      \"image\": \"https://via.placeholder.com/800x450.png?text=Welcome+to+In-Live\",\n" +
            "      \"api_url\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Featured Channels\",\n" +
            "      \"image\": \"https://via.placeholder.com/800x450.png?text=Featured+Channels\",\n" +
            "      \"api_url\": \"\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"name\": \"Categories\",\n" +
            "      \"type\": \"cats\",\n" +
            "      \"content\": [\n" +
            "        {\n" +
            "          \"category_name\": \"Movies\",\n" +
            "          \"thumbnail\": \"https://via.placeholder.com/300x300.png?text=Movies\",\n" +
            "          \"type\": \"list\",\n" +
            "          \"api_url\": \"https://api.vidflix.net/v2/api/movies\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"category_name\": \"Sports\",\n" +
            "          \"thumbnail\": \"https://via.placeholder.com/300x300.png?text=Sports\",\n" +
            "          \"type\": \"cats\",\n" +
            "          \"api_url\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"category_name\": \"News\",\n" +
            "          \"thumbnail\": \"https://via.placeholder.com/300x300.png?text=News\",\n" +
            "          \"type\": \"cats\",\n" +
            "          \"api_url\": \"\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Live TV\",\n" +
            "      \"type\": \"cats\",\n" +
            "      \"content\": [\n" +
            "        {\n" +
            "          \"category_name\": \"HBO\",\n" +
            "          \"thumbnail\": \"https://via.placeholder.com/300x300.png?text=HBO\",\n" +
            "          \"type\": \"cats\",\n" +
            "          \"api_url\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"category_name\": \"CNN\",\n" +
            "          \"thumbnail\": \"https://via.placeholder.com/300x300.png?text=CNN\",\n" +
            "          \"type\": \"cats\",\n" +
            "          \"api_url\": \"\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static final String TV_CHANNELS_JSON = "{\n" +
            "  \"content\": [\n" +
            "    {\n" +
            "      \"cat_name\": \"General\",\n" +
            "      \"content\": [\n" +
            "        {\n" +
            "          \"name\": \"General Channel 1\",\n" +
            "          \"poster_url\": \"https://via.placeholder.com/300x200.png?text=General+1\",\n" +
            "          \"api_url\": \"https://api.vidflix.net/v2/api/playback/1\",\n" +
            "          \"is_series\": \"0\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"General Channel 2\",\n" +
            "          \"poster_url\": \"https://via.placeholder.com/300x200.png?text=General+2\",\n" +
            "          \"api_url\": \"https://api.vidflix.net/v2/api/playback/2\",\n" +
            "          \"is_series\": \"0\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"cat_name\": \"Entertainment\",\n" +
            "      \"content\": [\n" +
            "        {\n" +
            "          \"name\": \"Ent Channel 1\",\n" +
            "          \"poster_url\": \"https://via.placeholder.com/300x200.png?text=Ent+1\",\n" +
            "          \"api_url\": \"https://api.vidflix.net/v2/api/playback/3\",\n" +
            "          \"is_series\": \"0\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static final String MOVIES_LIST_JSON = "{\n" +
            "  \"is_pagination\": \"0\",\n" +
            "  \"content\": [\n" +
            "    {\n" +
            "      \"name\": \"Mock Movie 1\",\n" +
            "      \"poster_url\": \"https://via.placeholder.com/300x450.png?text=Mock+Movie+1\",\n" +
            "      \"api_url\": \"https://api.vidflix.net/v2/api/playback/4\",\n" +
            "      \"is_series\": \"0\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Mock Series 1\",\n" +
            "      \"poster_url\": \"https://via.placeholder.com/300x450.png?text=Mock+Series+1\",\n" +
            "      \"api_url\": \"https://api.vidflix.net/v2/api/series/1\",\n" +
            "      \"is_series\": \"1\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static final String MOVIE_DETAILS_JSON = "{\n" +
            "  \"name\": \"Mock Movie 1\",\n" +
            "  \"poster_url\": \"https://via.placeholder.com/300x450.png?text=Mock+Movie+1\",\n" +
            "  \"description\": \"This is a mock movie description.\",\n" +
            "  \"releaseDate\": \"2024-01-01\",\n" +
            "  \"genres\": \"Action, Adventure\",\n" +
            "  \"videos\": \"W3sgIm5hbWUiOiAiSERIUSAiLCAiZmlsZV91cmwiOiAiaHR0cHM6Ly93d3cubGVhcm5pbmduYmV0LmNvbS9zb3VyY2UvYWRhcHRpdmVfdGVzdC9vdXQvaGxzL3Rlc3QubTN1OCIsICJ0eXBlIjogImhscyIgfV0=\",\n" +
            "  \"timestamp\": 1603743472,\n" +
            "  \"is_series\": \"0\",\n" +
            "  \"is_live\": \"0\"\n" +
            "}";

    public static final String SERIES_DETAILS_JSON = "{\n" +
            "  \"name\": \"Mock Series 1\",\n" +
            "  \"poster_url\": \"https://via.placeholder.com/300x450.png?text=Mock+Series+1\",\n" +
            "  \"description\": \"This is a mock series description.\",\n" +
            "  \"releaseDate\": \"2024-01-01\",\n" +
            "  \"genres\": \"Drama\",\n" +
            "  \"episodes_data\": \"W3sgIm5hbWUiOiAiU2Vhc29uIDEiLCAiZXBpc29kZXMiOiBbIHsgIm5hbWUiOiAiRXBpc29kZSAxIiwgImFwaV91cmwiOiAiaHR0cHM6Ly9hcGkudmlkZmxpeC5uZXQvdjIvYXBpL3BsYXliYWNrLzUiLCAicG9zdGVyX3VybCI6ICJodHRwczovL3ZpYS5wbGFjZWhvbGRlci5jb20vMzAweDIwMC5wbmc/dGV4dD1FcisxIiB9IF0gfV0=\",\n" +
            "  \"timestamp\": 1603743472,\n" +
            "  \"is_series\": \"1\"\n" +
            "}";

    public static final String SEARCH_RESULTS_JSON = MOVIES_LIST_JSON;
}
