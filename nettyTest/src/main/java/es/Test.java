package es;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import java.net.InetAddress;
import java.util.concurrent.ExecutionException;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-09-03 9:45
 */
public class Test {
    private TransportClient client;

    public void start() {
        Settings settings = Settings.builder()
                .put("cluster.name", "online-app-logs-es")
                .put("xpack.security.transport.ssl.enabled", false)
                .put("xpack.security.user", "elastic:streamcenter")
                .build();
        try {
            client = new PreBuiltXPackTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(
                    InetAddress.getByName("10.20.180.117"), 9300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("start ~");
        Test instance = new Test();
        instance.start();
        instance.search();
        System.out.println("end~");
    }
    public void search(){
        try {
            SearchRequest searchRequest = new SearchRequest("sc-audit-log-*");
            searchRequest.types("log");
            SearchResponse response = client.search(searchRequest).get();
            System.out.println(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
