package http_connection;
import java.io.*;
import java.net.*;

public class Client {
    
    public static void main(String[] args) {
        
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            // easier and nicer way to implement thread with a lambda function
            // note that this lambda is to override the run function in Runnable interface, and the run function takes no argument, so we cannot place any argument in the parentheses on the left side of the arrow
            // if we want to pass any parameters, we need the parameter needs to be effectively final (cannot be changed afterwards) 
            new Thread(() -> getValue(finalI)).start();

            // this is equivalent to
            // new Thread(new Runnable(){
            //     @Override
            //     public void run() {
            //         getValue(finalI);
            //     }
            // }).start();

            // We can also do the following. having a code block on the right side of the lambda arrow
            // However, it is nicer to have that code block as a separate function as shown above.
            // new Thread(() -> {
            //     URL url = new URL(String.format("http://localhost:5000/?param=%d", i));
            //     HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //     // following code
            // }).start();
        }
    }

    private static void getValue(int i) {
        try {
            URL url = new URL(String.format("http://localhost:5000/?param=%d", i));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.connect();

            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            StringBuilder stb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stb.append(line);
            }

            System.out.println(stb.toString());
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
