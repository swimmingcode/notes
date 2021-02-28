package org.youyuan.jwt.service;

import org.springframework.web.multipart.MultipartFile;
import org.youyuan.jwt.vo.request.AddTextBookVO;
import org.youyuan.jwt.vo.response.TextBookVO;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/27 15:59
 */
public interface TextBookService {

    /**
     * 文件存储
     *
     * @param file
     * @return
     */
    String upload(MultipartFile file);

    /**
     * 添加教材
     *
     * @param addTextBookVO
     */
    void addTextBooks(AddTextBookVO addTextBookVO);

    /**
     * 修改教材
     *
     * @param addTextBookVO
     */
    void updateTextBook(TextBookVO addTextBookVO);

    /**
     * 查询教材
     *
     * @param id
     * @return
     */
    TextBookVO getTextBookById(Integer id);

    /**
     * 删除教材
     *
     * @param id
     */
    void deleteTextBook(Integer id);

    /**
     * 教材列表
     *
     * @param page  页数
     * @param size  每页大小
     * @param search 搜索参数
     * @return
     */
    List<TextBookVO> getTextBookList(Integer page, Integer size, String search);
}
