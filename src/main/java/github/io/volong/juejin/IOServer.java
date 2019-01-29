package github.io.volong.juejin;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.ws.handler.MessageContext.Scope;

public class IOServer {
    
    public static void main(String[] args) throws IOException {
        
        
        new Thread(() -> {
            while (true) {
                try (ServerSocket serverSocket = new ServerSocket(8000)) {
                    
                    // 1. 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();
                    
                    // 2. 每个连接都创建新的线程
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        }).start();
    }

}
