package io.github.xiaoyureed.shopeesearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.xiaoyureed.shopeesearch.config.EsConfig;
import lombok.Builder;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ShopeeSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testEsInsert() throws IOException {
        IndexRequest usersIndexReq = new IndexRequest("users");

        usersIndexReq.id("1").source(objectMapper.writeValueAsString(
                User.builder().age(11).gender("male").name("hello xy").build()), XContentType.JSON);

        IndexResponse usersIndexResp = client.index(usersIndexReq, EsConfig.COMMON_OPTIONS);
        System.out.println(usersIndexResp);
    }

    @Test
    public void testEsSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest();

        searchRequest.indices("users");
        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));

        SearchResponse searchResp = client.search(searchRequest, EsConfig.COMMON_OPTIONS);
        System.out.println(searchResp);
    }

    @Data
    @Builder
    static class User {
        private String name;
        private String gender;
        private Integer age;
    }

}
