package Zookeeper.test;

import Zookeeper.Client.Client;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    public void test(){
        Client client=new Client();
        try {
            client.getConnect();
            client.handleBussiness();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
