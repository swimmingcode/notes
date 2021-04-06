package org.youyuan.elasticsearch;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ElasticsearchApplicationTests {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    //创建锁引
    @Test
    void contextLoads() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("youyuancode");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexRequest);
    }

    //查询锁引 判断锁引是否存在
    @Test
    public void query() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("youyuancode");
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    //删除锁引
    @Test
    public void delete() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("youyuancode");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

}
