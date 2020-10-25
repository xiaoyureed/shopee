package io.github.xiaoyureed.shopeesearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.xiaoyureed.shopeecommon.util.StringUtils;
import io.github.xiaoyureed.shopeesearch.config.EsConfig;
import lombok.Data;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@SpringBootTest
class ShopeeSearchApplicationTests {

    @Qualifier("esClient")
    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testEsInsert() throws IOException {
        IndexRequest usersIndexReq = new IndexRequest("users");

        User userInsert = new User();
        userInsert.setGender("female");
        userInsert.setAge(11);
        userInsert.setName("xy");

        usersIndexReq.id("1").source(objectMapper.writeValueAsString(userInsert), XContentType.JSON);

        IndexResponse usersIndexResp = client.index(usersIndexReq, EsConfig.COMMON_OPTIONS);
        System.out.println(usersIndexResp);


    }

    @Test
    void testDelete() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("users");
        // 必须指定 id, 只能指定一个 id
        deleteRequest.id("1").id("2");// 只会删除 2
        client.delete(deleteRequest, EsConfig.COMMON_OPTIONS);
    }

    @Test
    void deleteAllUsers() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();

        // 若指定 id 不存在, 则忽略, 不会报错
        LongStream.rangeClosed(1, 10).forEach(id -> bulkRequest.add(new DeleteRequest("users").id(String.valueOf(id))));

        client.bulk(bulkRequest, EsConfig.COMMON_OPTIONS);
    }

    @Test
    void testBatchInsert() throws IOException {
        BulkRequest bulk = new BulkRequest();

        final Integer[] ages = {11, 21, 20};
        // 种子相同的 random 生成的随机数序列也会相同, 所以这里指定系统时间为种子, 保证每次种子不同
        final Random random = new Random(System.currentTimeMillis());

        LongStream.rangeClosed(1, 10).mapToObj(id -> {
            User ret = new User();
            ret.setId(id);
            ret.setAge(ages[random.nextInt(3)]);
            ret.setName(StringUtils.genChineseNameJianHan(true, 2));
            ret.setGender(new Random().nextInt() % 2 == 0 ? "male" : "female");
            return ret;
        }).forEach(u -> bulk.add(new IndexRequest("users")
                .id(u.getId().toString())
                .source(json(u), XContentType.JSON)));

//         batch opts
        BulkResponse bulkResp = client.bulk(bulk, EsConfig.COMMON_OPTIONS);

        if (bulkResp.hasFailures()) {
            // error log
            List<String> ids = Arrays.stream(bulkResp.getItems())
                    .map(BulkItemResponse::getId).collect(Collectors.toList());
            System.out.println(ids);
        }
    }

    private String json(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("json error", e);
        }
    }

    private <T> T obj(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("error or deserialization json string", e);
        }
    }

    @Test
    void testEsSearchQuery() throws IOException {
        SearchResponse resp = client.search(new SearchRequest()
                .indices("users")
                .source(new SearchSourceBuilder()
                        .query(
//                                QueryBuilders.matchAllQuery()
                                QueryBuilders.matchQuery("age", 10)
                        )), EsConfig.COMMON_OPTIONS);

        System.out.println(json(resp));
        List<User> collect = Arrays.stream(resp.getHits().getHits())
                .map(SearchHit::getSourceAsString)
                .map(json -> obj(json, User.class))
                .collect(Collectors.toList());
        System.out.println(json(collect));
    }


    @Test
    void testEsSearchAgg() throws IOException {
        SearchResponse resp = client.search(
                new SearchRequest("users")
                        .source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery())
                                // 只能对 数字字段进行分布情况聚合
                                // 对 string 类型字段不行, 如 gender
                                .aggregation(AggregationBuilders.terms("age_distribution").field("age"))
                                // 平均值
                                .aggregation(AggregationBuilders.avg("age_avg").field("age"))),
                EsConfig.COMMON_OPTIONS);
        System.out.println(json(resp));
        Aggregations aggs = resp.getAggregations();
        Terms ageDistAgg = aggs.get("age_distribution");
        ageDistAgg.getBuckets().forEach(bucket -> {
            Number keyAsNumber = bucket.getKeyAsNumber();
            long docCount = bucket.getDocCount();
            System.out.println(String.format("age=%d, docCount=%d", keyAsNumber.longValue(), docCount));
        });
        Avg ageAvgAgg = aggs.get("age_avg");
        // age_avg = 11.0
        System.out.println(String.format("%s = %s", ageAvgAgg.getName(), ageAvgAgg.getValueAsString()));
    }

    @Test
    public void testEsSearchAggregation() throws IOException {
        SearchRequest searchRequest = new SearchRequest();

        searchRequest.indices("users");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(
                        QueryBuilders.matchAllQuery()// 查询所有
//                        QueryBuilders.matchQuery("age", 10)// 指定字段要求
                )
//                        .from(1) // 从 1 开始, 默认 0
//                        .size(4) // 查 4 条, 默认 10
                // 对前面的结果聚合分析, 类似 SQL 的 group by 等等
                .aggregation(AggregationBuilders
                        // 进行分布情况聚合, 并指定聚合数据结果在 json 中的 key
                        .terms("gender_distribution") // 性别分布
                        .size(10) // 聚合多少条数据
                        .field("gender")) // 对哪个字段聚合
                // 平均值聚合
                .aggregation(AggregationBuilders.avg("age_avg").field("age"));
        // 打印请求 dsl
        System.out.println(searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResp = client.search(searchRequest, EsConfig.COMMON_OPTIONS);
        System.out.println(searchResp);

        // 收集查询结果
        List<User> collect = Arrays.stream(searchResp.getHits().getHits())
                .map(SearchHit::getSourceAsString)
                .map(json -> obj(json, User.class))
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    @Data
    static class User {
        private Long id;
        private String name;
        private String gender;
        private Integer age;
    }

}
