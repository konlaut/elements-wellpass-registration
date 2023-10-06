import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElementsAutomation {

    private final static String ELEMENTS_MAIL = System.getenv("ELEMENTS_EMAIL");
    private final static String ELEMENTS_PASSWORD = System.getenv("ELEMENTS_PASSWORD");
    private final static String BASE_URL = "https://slots.elements.com/";
    private final static String LOGIN_URL_TEMPLATE = BASE_URL+"?action=login&send=1&email=%s&password=%s";
    private final static String STUDIO_URL_TEMPLATE = BASE_URL+"sheet.php?studio_id=%s";
    private final static String REGISTER_URL_TEMPLATE = BASE_URL+"sheet.php%s";
    private final static String STUDIO_IDS = System.getenv("ELEMENTS_STUDIOS");

    public static void main(String[] args) {
        List<String> studio_ids = getStudioIDs(STUDIO_IDS);

        Connection session;
        session = Jsoup.newSession().userAgent(HttpConnection.DEFAULT_UA);
        getLogin(session);

        for(String studio_id : studio_ids){
            registerInStudio(studio_id,session);
        }

    }
    private static void registerInStudio(String studio_id, Connection session){
        final String STUDIO_URL = String.format(STUDIO_URL_TEMPLATE,studio_id);

        Document studio_page;
        try {
            studio_page = session.newRequest().url(STUDIO_URL).get();
            Elements links = studio_page.select("a[href*=action=register]");

            if (!links.isEmpty()){
                String register_URL = links.attr("href");
                clickButton(register_URL,session);
            }

            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void clickButton (String button_URL, Connection session){
        String register_URL = String.format(REGISTER_URL_TEMPLATE,button_URL);
        try {
            session.newRequest().url(register_URL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getLogin(Connection session){
        final String LOGIN_URL = String.format(LOGIN_URL_TEMPLATE, ELEMENTS_MAIL,ELEMENTS_PASSWORD);
        try {
            session.newRequest().url(LOGIN_URL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static List<String> getStudioIDs(String studio_ids){
        List<String> studio_ids_list = new ArrayList<>();
        if (studio_ids.contains(",") && studio_ids.length()>2){
            studio_ids_list = Arrays.asList(studio_ids.split(","));
        }
        else {
            studio_ids_list.add(studio_ids);
        }
        return studio_ids_list;
    }
}
