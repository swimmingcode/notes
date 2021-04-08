package org.youyuan.elasticsearch;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.youyuan.elasticsearch.bean.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ElasticsearchApplicationTests {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    //创建锁引
    @Test
    void contextLoads() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("youyuancode");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexRequest.toString());
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

    //添加文档
    @Test
    public void testAddDocument() throws IOException {
        User user = new User("youyuan", 24);
        IndexRequest request = new IndexRequest("youyuancode");
        request.id("1");
        //设置超时时间
        request.timeout("1s");
        //将数据放到json字符串
        request.source(JSONObject.toJSONString(user), XContentType.JSON);
        //发送请求
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);

        System.out.println("添加文档----------" + response.toString());
        System.out.println("添加文档----------" + response.status());
    }

    //测试文档是否存在
    @Test
    public void testExistDocument() throws IOException {
        GetRequest request = new GetRequest("youyuancode", "2");
        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //测试文档获取
    @Test
    public void testGetDocument() throws IOException {
        GetRequest request = new GetRequest("youyuancode", "1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println("测试读取文档-------" + response.getSourceAsString());
        System.out.println("测试读取文档-------" + response);
        //测试读取文档-------{"age":24,"name":"youyuan"}
        //测试读取文档-------{"_index":"youyuancode","_type":"_doc","_id":"1","_version":1,"_seq_no":0,"_primary_term":3,"found":true,"_source":{"age":24,"name":"youyuan"}}
    }

    //测试文档更新
    @Test
    public void testUpdateDocument() throws IOException {
        User user = new User("张三", 20);
        UpdateRequest request = new UpdateRequest("youyuancode", "1");
        request.timeout("1s");
        request.doc(JSON.toJSONString(user), XContentType.JSON);

        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response);
        System.out.println(response.status());
        //UpdateResponse[index=youyuancode,type=_doc,id=1,version=2,seqNo=1,primaryTerm=3,result=updated,shards=ShardInfo{total=2, successful=1, failures=[]}]
        //OK
    }

    //测试删除文档
    @Test
    public void testDeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("youyuancode", "1");
        request.timeout("1s");
        DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println("测试删除文档" + response.status());
    }

    //测试批量添加文档
    @Test
    void testBulkAddDocument() throws IOException {
        List<User> userlist=new ArrayList<User>();
        userlist.add(new User("cyx1",5));
        userlist.add(new User("cyx2",6));
        userlist.add(new User("cyx3",40));
        userlist.add(new User("cyx4",25));
        userlist.add(new User("cyx5",15));
        userlist.add(new User("cyx6",35));

        //批量操作的Request
        BulkRequest request = new BulkRequest();
        request.timeout("1s");

        //批量处理请求
        for (int i = 0; i < userlist.size(); i++) {
            request.add(
                    new IndexRequest("youyuancode")
                            .id(""+(i+1))
                            .source(JSON.toJSONString(userlist.get(i)),XContentType.JSON)
            );
        }
        BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        //response.hasFailures()是否是失败的
        System.out.println("测试批量添加文档-----"+response.hasFailures());
    }

    //测试查询文档
    @Test
    void testSearchDocument() throws IOException {
        SearchRequest request = new SearchRequest("youyuancode");
        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置了高亮
        sourceBuilder.highlighter();
        //term name为cyx1的
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "cyx1");
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        request.source(sourceBuilder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        System.out.println("测试查询文档-----"+JSON.toJSONString(response.getHits()));
        System.out.println("=====================");
        for (SearchHit documentFields : response.getHits().getHits()) {
            System.out.println("测试查询文档--遍历参数--"+documentFields.getSourceAsMap());
        }
    }




}
