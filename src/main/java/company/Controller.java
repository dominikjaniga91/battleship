package company;

import com.google.gson.Gson;
import company.model.GameManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


@WebServlet("/battleship")
public class Controller extends HttpServlet {


    private GameManager gameManager = new GameManager();
    private Gson gson = new Gson();

    public Controller() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


        try {

            String playerShot = null;
            JSONObject jsonObject = readJsonObjectFromRequest(request);
            String action = String.valueOf(jsonObject.get("name"));
            if(jsonObject.has("id")){
                playerShot = String.valueOf(jsonObject.get("id"));
            }

            switch (action) {
                case "prepare": {
                    startTheGame(response);
                    break;
                }
                case "shot": {
                    sendResponse(response, gameManager.playTheGameAndReturnResult(playerShot));
                    break;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // create computer ang player ships and return location of player ships
    private void startTheGame(HttpServletResponse response) throws IOException {

        LinkedList<String> list = gameManager.prepareTheGame();

        JSONObject data = new JSONObject();
        data.put("id", new JSONArray(list));

        String jsonString = this.gson.toJson(data);
        response.setContentType("application/json");
        response.setHeader("Accept", "application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.write(jsonString);
        out.flush();
        out.close();
    }

    // read action tha should be perform or player shot (field id)
    private JSONObject readJsonObjectFromRequest(HttpServletRequest request) throws  IOException, JSONException{

        StringBuilder builder = new StringBuilder();
        String line;

            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

        return new JSONObject(builder.toString());
    }

    private void sendResponse(HttpServletResponse response, HashMap<String, String> responseMessage) throws IOException {

        JSONObject data = new JSONObject();
        data.put("message", responseMessage);

        String jsonString = this.gson.toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.write(jsonString);
        out.flush();
        out.close();
    }


}