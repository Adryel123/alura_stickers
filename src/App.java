import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

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

        parsedBody.forEach(n -> {
            System.out.println(n.get("title"));
            String nota = n.get("imDbRating");
            System.out.println(nota);
        });
    }
}
