package myapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

    private final String USER_AGENT = "Mozilla/5.0";

    private String ip = "";

    PrintWriter out;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = "";

        out = resp.getWriter();

        try {
//            sendGet(url);

            sendGet1(url);
        } catch (Exception ex) {
            Logger.getLogger(HelloServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        out = resp.getWriter();
        out.println("Hello, bew world");
    }

    // HTTP GET request
    private void sendGet(String url) throws Exception {

        out.println("Start Get");

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        con.setUseCaches(false);
        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        out.println("\nSending 'GET' request to URL : " + url);
        out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        out.println(response.toString());

    }

    // HTTP GET request
    private void sendGet1(String url) throws Exception {

//		String url = "http://www.google.com/search?q=developer";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

        out.println("\nSending 'GET' request to URL : " + url);
        out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        out.println(result.toString());

    }

}
