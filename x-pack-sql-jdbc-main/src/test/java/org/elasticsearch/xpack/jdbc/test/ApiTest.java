package org.elasticsearch.xpack.jdbc.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.Index;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ApiTest {

    public static void main(String[] args) throws Exception {
        String address = "jdbc:es://http://127.0.0.1:9200";
        Properties connectionProperties = new Properties();
        try {
            Connection connection = DriverManager.getConnection(address, connectionProperties);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT userHead FROM user");
            while (results.next()) {
                System.out.println(results.getString("userHead"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_init() throws IOException{

        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder( new HttpHost("127.0.0.1",9200,"http")));

        //创建请求
        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        IndexRequest indexRequest = new IndexRequest("user");
        User user_01 = new User();
        user_01.setId(1l);
        user_01.setUserId("122334");
        user_01.setUserNickName("yly");
        user_01.setUserHead("01_50");
        user_01.setUserPassword("123456");
        indexRequest.source(JSONObject.toJSONString(user_01), XContentType.JSON);
        restHighLevelClient.index(indexRequest,RequestOptions.DEFAULT);
    }

}
