import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        var url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        var address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(address).GET().build();

        HttpResponse<String> response = client.send(request,
                BodyHandlers.ofString());

        var body = response.body();

        var parser = new JsonParser();
        var parsedBody = parser.parse(body);

        var generator = new StickersGenerator();
        for (Map<String, String> n : parsedBody) {
            String imgUrl = n.get("image");

            InputStream stream = new URL(imgUrl).openStream();
            var title = n.get("title") + ".png";

            generator.create(stream, title);

            System.out.println(title);
            System.out.println();
        }

    }
}
