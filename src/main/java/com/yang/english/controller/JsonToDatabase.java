package com.yang.english.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JsonToDatabase {
    public static void main(String[] args) throws IOException, SQLException {
        String json = new String(Files.readAllBytes(Paths.get("C:\\Users\\13780\\IdeaProjects\\English\\src\\main\\java\\com\\yang\\english\\controller\\data.json")));

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/english?useUnicode=true&characterEncoding=UTF-8", "root", "137805")) {
            connection.setAutoCommit(false);

            // 插入到 audio 表
//            JsonObject audioObject = jsonObject.getAsJsonObject("data").getAsJsonObject("audio");
//            String insertAudio = "INSERT INTO audio (id, name, weigh, create_time, update_time, category_id, user_id, origin, delete_time, content, file_id, audio_url_cdn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            try (PreparedStatement audioStmt = connection.prepareStatement(insertAudio)) {
//                audioStmt.setInt(1, audioObject.get("id").getAsInt());
//                audioStmt.setString(2, audioObject.get("name").getAsString());
//                audioStmt.setLong(3, audioObject.get("weigh").getAsLong());
//                audioStmt.setString(4, audioObject.get("create_time").getAsString());
//                audioStmt.setString(5, audioObject.get("update_time").getAsString());
//                audioStmt.setInt(6, audioObject.get("category_id").getAsInt());
//                audioStmt.setInt(7, audioObject.get("user_id").getAsInt());
//                audioStmt.setString(8, audioObject.get("origin").getAsString());
//                // Handle null for delete_time
//                String deleteTime = audioObject.get("delete_time").isJsonNull() ? null : audioObject.get("delete_time").getAsString();
//                audioStmt.setString(9, deleteTime);
//                // Handle null for content
//                String content = audioObject.get("content").isJsonNull() ? null : audioObject.get("content").getAsString();
//                audioStmt.setString(10, content);
//                audioStmt.setInt(11, audioObject.get("file_id").getAsInt());
//                audioStmt.setString(12, audioObject.get("audio_url_cdn").getAsString());
//
//                audioStmt.execute();
//            }

            // 插入到 sentences 表
            JsonArray sentencesArray = jsonObject.getAsJsonObject("data").getAsJsonArray("sentences");
            String insertSentence = "INSERT INTO sentences (id, text, file_id, begin_position, end_position, trans_zh, audio_id, is_section_end, word_number, is_collect, audio_url_cdn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            for (JsonElement sentenceElement : sentencesArray) {
                JsonObject sentenceObject = sentenceElement.getAsJsonObject();
                try (PreparedStatement sentenceStmt = connection.prepareStatement(insertSentence)) {
                    sentenceStmt.setInt(1, sentenceObject.get("id").getAsInt());
                    sentenceStmt.setString(2, sentenceObject.get("text").getAsString());
                    sentenceStmt.setInt(3, sentenceObject.get("file_id").getAsInt());
                    sentenceStmt.setInt(4, sentenceObject.get("begin_position").getAsInt());
                    sentenceStmt.setInt(5, sentenceObject.get("end_position").getAsInt());
                    // Handle null for trans_zh
                    String transZh = sentenceObject.get("trans_zh").isJsonNull() ? null : sentenceObject.get("trans_zh").getAsString();
                    sentenceStmt.setString(6, transZh);
                    sentenceStmt.setInt(7, sentenceObject.get("audio_id").getAsInt());
                    sentenceStmt.setBoolean(8, sentenceObject.get("is_section_end").getAsInt() == 1);
                    sentenceStmt.setInt(9, sentenceObject.get("word_number").getAsInt());
                    sentenceStmt.setBoolean(10, sentenceObject.get("is_collect").getAsBoolean());
                    sentenceStmt.setString(11, sentenceObject.get("audio_url_cdn").getAsString());

                    sentenceStmt.execute();
                }
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
