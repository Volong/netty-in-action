package github.io.volong.chapter01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingIOExamples {

    public void serve(int portNumber) throws IOException {
        
        // 监听指定端口上的请求
        ServerSocket serverSocket = new ServerSocket(portNumber);
        
        // 对 accept 的调用将阻塞，直到一个连接被建立
        Socket accept = serverSocket.accept();
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), true);
        
        String request, response;
        
        while ((request = bufferedReader.readLine()) != null) {
            if ("Done".equals(request)) {
                break;
            }
            
            response = processRequest(request);
            printWriter.println(response);
        }
    }
    
    private String processRequest(String request){
        return "Processed";
    }
}
