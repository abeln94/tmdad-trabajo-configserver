package es.unizar.tmdad.carlosabel;

import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/reload")
    @ResponseBody
    public String refresh() {

        String[] machines = new String[]{
            "https://carlos-abel-tmdad-trabajo.herokuapp.com/",
            "https://carlos-abel-tmdad-trabajo-2.herokuapp.com/",
            "https://carlos-abel-tmdad-trabajo-3.herokuapp.com/",
            "https://carlos-abel-tmdad-trabajo-4.herokuapp.com/"
        };

        for (String machine : machines) {
            try {
                System.out.println("Refreshing machine " + machine);
                HttpsURLConnection connection = (HttpsURLConnection) new URL(machine + "refresh").openConnection();
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(100 * 1000);
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    System.out.println("Oh oh, couldn't refresh machine " + machine + " (" + responseCode + ")");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return "refreshed";
    }

    @GetMapping("/start")
    @ResponseBody
    public String start() {

        String[] machines = new String[]{
            "https://carlos-abel-tmdad-trabajo.herokuapp.com/",
            "https://carlos-abel-tmdad-trabajo-2.herokuapp.com/",
            "https://carlos-abel-tmdad-trabajo-3.herokuapp.com/",
            "https://carlos-abel-tmdad-trabajo-4.herokuapp.com/"
        };

        for (String machine : machines) {
            try {
                System.out.println("Pinging machine " + machine);
                HttpsURLConnection connection = (HttpsURLConnection) new URL(machine).openConnection();
                connection.setRequestMethod("HEAD");
                connection.setConnectTimeout(100 * 1000);
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    System.out.println("Oh oh, couldn't akawe machine " + machine + " (" + responseCode + ")");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return "started";
    }

}
