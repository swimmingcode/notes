package org.youyuan.jwt.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.youyuan.jwt.service.TextBookService;
import org.youyuan.jwt.utils.common.PageResponse;
import org.youyuan.jwt.utils.common.response.Response;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.common.response.ResponseFactory;
import org.youyuan.jwt.utils.exception.ExceptionFactory;
import org.youyuan.jwt.utils.jwt.annotation.AccessPermission;
import org.youyuan.jwt.vo.request.AddTextBookVO;
import org.youyuan.jwt.vo.response.TextBookExcel;
import org.youyuan.jwt.vo.response.TextBookVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Describe: 教材共享领域
 * @Author: youjiancheng
 * @date 2021/2/27 15:41
 */
@RestController
@RequestMapping("/api/v1.0/textBook")
@Slf4j
@Api(tags= "教材领域")
public class TextBookApi {

    @Autowired
    TextBookService textBookService;

    @Autowired
    RedisTemplate redisTemplate;

    @AccessPermission(roleName = "admin")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public Response<String> upload(@RequestParam("file") MultipartFile file) {
        //无文件系统 直接将文件存在项目中
        String str = textBookService.upload(file);
        return ResponseFactory.<String>productResult(ResponseCode.OK,str);
    }


    @AccessPermission(roleName = "admin")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "添加教材")
    @PostMapping("/add/book")
    public Response<Void> addTextBooks(@ApiParam(value = "教材实体类") @RequestBody AddTextBookVO addTextBookVO) {
        if (!redisTemplate.hasKey(addTextBookVO.getImageUrl())) {
            throw new ExceptionFactory(ResponseCode.BOOK_IMAGE_NOT_EXISTS);
        }
        String url = redisTemplate.opsForValue().get(addTextBookVO.getImageUrl()).toString();
        addTextBookVO.setImageUrl(url);
        textBookService.addTextBooks(addTextBookVO);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }

    @AccessPermission(roleName = "user")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "查询教材")
    @GetMapping("/add/book/{id}")
    public Response<TextBookVO> getTextBookById(@ApiParam(value = "教材ID") @PathVariable(value = "id") Integer id) {
        TextBookVO textBookVO = textBookService.getTextBookById(id);
        return ResponseFactory.<TextBookVO>productResult(ResponseCode.OK,textBookVO);
    }

    @AccessPermission(roleName = "admin")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "修改教材")
    @PutMapping("/update/book")
    public Response<Void> updateTextBook(@ApiParam(value = "教材实体类") @RequestBody TextBookVO textBookVO) {
        textBookService.updateTextBook(textBookVO);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }

    @AccessPermission(roleName = "admin")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "删除教材")
    @DeleteMapping("/delete/book/{id}")
    public Response<Void> deleteTextBook(@ApiParam(value = "教材ID") @PathVariable(value = "id") Integer id) {
        textBookService.deleteTextBook(id);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }


    @AccessPermission(roleName = "user")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "教材列表")
    @GetMapping("/list")
    public Response<PageResponse<TextBookVO>> getTextBookList(@ApiParam("页数") @RequestParam(value = "page",defaultValue = "1",required = false) Integer page,
                                                      @ApiParam("大小") @RequestParam(value = "size",defaultValue = "10",required = false) Integer size,
                                                      @ApiParam("查询参数") @RequestParam(value = "search" ,required = false) String search) {
        List<TextBookVO> textBookList = textBookService.getTextBookList(page,size,search);
        return ResponseFactory.<PageResponse<TextBookVO>>productResult(ResponseCode.OK, PageResponse.<TextBookVO>builder().total(textBookList.size()).list(textBookList).build());
    }


    @AccessPermission(roleName = "admin")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "下载教材列表")
    @GetMapping("/download/excel")
    public void downloadExcelTextBookList(HttpServletResponse response) throws IOException {
        List<TextBookExcel> textBookExcels = textBookService.getTextBookExcelList();
        //使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 将URI中的空格转成application/x-www-form-urlencoded符号
            String fileName = URLEncoder.encode("教材列表", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(),TextBookExcel.class).autoCloseStream(Boolean.FALSE).sheet("模板").doWrite(textBookExcels);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

}
