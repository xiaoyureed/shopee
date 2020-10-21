package io.github.xiaoyureed.shopeesearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.xiaoyureed.shopeesearch.config.EsConfig;
import lombok.*;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                User.builder().age(11).name("xiii").gender("mix")), XContentType.JSON);

        IndexResponse usersIndexResp = client.index(usersIndexReq, EsConfig.COMMON_OPTIONS);
        System.out.println(usersIndexResp);

        // batch opts
//        BulkResponse bulkResp = client.bulk(new BulkRequest().add(usersIndexReq), EsConfig.COMMON_OPTIONS);
//        if (bulkResp.hasFailures()) {
//            // error log
//            List<String> ids = Arrays.stream(bulkResp.getItems()).map(BulkItemResponse::getId).collect(Collectors.toList());
//            System.out.println(ids);
//        }

    }

    @Test
    public void testEsSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest();

        searchRequest.indices("users");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(
                        QueryBuilders.matchAllQuery()
//                        QueryBuilders.matchQuery("name", "user2")
                )
//                        .from(1)
//                        .size(4)
                .aggregation(AggregationBuilders.terms("age_distribution").size(10).field("age"))
                .aggregation(AggregationBuilders.avg("age_avg").field("age"));
        System.out.println(searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResp = client.search(searchRequest, EsConfig.COMMON_OPTIONS);
        System.out.println(searchResp);

        List<User> collect = Arrays.stream(searchResp.getHits().getHits())
                .map(SearchHit::getSourceAsString)
                .map(json -> {
                    try {
                        return objectMapper.readValue(json, User.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        throw new RuntimeException();
                    }
                })
                .collect(Collectors.toList());
        System.out.println(collect);

//        searchResp.getAggregations().asList().forEach(aggregation -> {
////            String aggregationName = aggregation.getName();
////            aggregation.getMetadata()
//        });


    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    static class User {
        private String name;
        private String gender;
        private Integer age;
    }

}
