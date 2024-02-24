package com.yang.english.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import com.yang.english.entiy.Song;
import com.yang.english.entiy.SongDTO;
import com.yang.english.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/api/songs") // Base URL for all handlers
@Slf4j
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/getAll")
    public List<SongDTO> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/getById/{id}")

    public ResponseEntity<Object> getSongById(@PathVariable Long id) throws FileNotFoundException {
        Song song=songService.getSongById(id);
        String lyric=song.getLyricsFilePath();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建HttpGet请求
            HttpGet request = new HttpGet(lyric);

            // 执行请求并获取响应
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                // 获取响应实体
                HttpEntity entity = response.getEntity();

                // 确保响应实体不为空
                if (entity != null) {
                    // 读取响应内容
                    ContentType contentType = ContentType.getOrDefault(entity);
                    // 获取字符集，如果未明确指定，则默认使用UTF-8
                    Charset charset = contentType.getCharset() != null ? contentType.getCharset() : Consts.UTF_8;
                    String content = EntityUtils.toString(entity, charset);
//                    String content = EntityUtils.toString(entity);

                    // 创建JSON响应体
                    String jsonResponse = "{ \"content\": \"" + escapeJson(content) + "\" }";

                    // 返回JSON响应
                    return ResponseEntity.ok().body(jsonResponse);
                } else {
                    throw new RuntimeException("No response entity");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
    // 其他请求处理方法
    // 简单的JSON转义方法，用于处理特殊字符
    private String escapeJson(String input) {
        return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
