package com.yang.english.controller;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping(value="/upload")
public class UploadController {

    private String accessKey ="AKID5z0xiIk5QxTvQsCSZmuz0thO0VxKZjru";
    private String secretKey="30vlVFxfL5nS1TmEap1AEC6ypbbaPCCA";
    private String bucket="ap-nanjing";
    private String bucketName="english-1309979517";
    private String path="https://english-1309979517.cos.ap-nanjing.myqcloud.com";
    private String qianzui="avatar";

    @RequestMapping(value = "/tengxun", method = RequestMethod.POST)
    @ResponseBody
    public Object Upload(@RequestParam(value = "file") MultipartFile file) {
        if (file == null) {
            return new UploadMsg(0, "文件为空", null);
        }
        String oldFileName = file.getOriginalFilename();
        String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + eName;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(bucket));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = this.bucketName;

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = null;
        try {
            localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            // 指定要上传到 COS 上的路径
            String key = "/" + this.qianzui + "/" + newFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            return new UploadMsg(1, "上传成功", this.path + putObjectRequest.getKey());
        } catch (IOException e) {
            return new UploadMsg(-1, e.getMessage(), null);
        } finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }
    }
    @GetMapping("getAllavatar")

    public List<String> getAllavatar() {
        String perName="https://english-1309979517.cos.ap-nanjing.myqcloud.com/";
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucketName);
        listObjectsRequest.setPrefix("avatar/");
        listObjectsRequest.setDelimiter("/");
        listObjectsRequest.setEncodingType("url");
        listObjectsRequest.setMaxKeys(1000);
        ObjectListing objectListing = null;
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(bucket));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        List<String> allavatar;
        do {
            try {
                objectListing = cosclient.listObjects(listObjectsRequest);
            } catch (CosServiceException e) {
                e.printStackTrace();
                return null;
            } catch (CosClientException e) {
                e.printStackTrace();
                return null;
            }
            // common prefix表示表示被delimiter截断的路径, 如delimter设置为/, common prefix则表示所有子目录的路径
            List<String> commonPrefixs = objectListing.getCommonPrefixes();
            allavatar = new ArrayList<>();
            // object summary表示所有列出的object列表
            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
            log.info("commonPrefixs:{}", commonPrefixs);
            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
                // 文件的路径key
                String key = cosObjectSummary.getKey();
                // 文件的etag
                String etag = cosObjectSummary.getETag();
                // 文件的长度
                long fileSize = cosObjectSummary.getSize();
                // 文件的存储类型
                String storageClasses = cosObjectSummary.getStorageClass();

                if (key.contains("avatar/")) {
                    if (key.matches("avatar/$")) {

                    }
                    else allavatar.add(perName+key);
                }

            }

            String nextMarker = objectListing.getNextMarker();
            listObjectsRequest.setMarker(nextMarker);
        }
        while (objectListing.isTruncated());
        return allavatar;
    }
    private class UploadMsg {
        public int status;
        public String msg;
        public String path;

        public UploadMsg() {
            super();
        }

        public UploadMsg(int status, String msg, String path) {
            this.status = status;
            this.msg = msg;
            this.path = path;
        }
    }

}
