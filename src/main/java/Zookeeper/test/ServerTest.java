package Zookeeper.test;

import Zookeeper.Server.Server;
import org.junit.jupiter.api.Test;


public class ServerTest {
    @Test
        public void test(){
        Server server=new Server();
        server.registServerInfo("====serve 01r===");
        server.startServer();
    }
}
