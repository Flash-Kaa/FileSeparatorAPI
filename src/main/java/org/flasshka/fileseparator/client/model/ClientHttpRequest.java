package org.flasshka.fileseparator.client.model;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class ClientHttpRequest {
    private final static URI SERVER_URI;

    static {
        try {
            SERVER_URI = new URI("http://localhost:8443/api/v1");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String sendAndGetJson(URI fileUri) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httppost = new HttpPost(SERVER_URI);

            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("file", new FileBody(new File(fileUri)))
                    .build();

            httppost.setEntity(reqEntity);

            try (CloseableHttpResponse response = httpclient.execute(httppost)) {
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    return new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
                } else {
                    return String.format("Error. Code: \\d", status);
                }
            }
        }
    }
}
