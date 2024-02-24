package com.yang.english.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class CSVToMySQL {
    public static void main(String[] args) {
        // 数据库URL、用户名和密码
        String url = "jdbc:mysql://localhost:3306/english?useUnicode=true&characterEncoding=UTF-8";
        String user = "root";
        String password = "137805";

        // CSV文件路径
        String csvFilePath = "C:\\Users\\13780\\IdeaProjects\\English\\src\\main\\java\\com\\yang\\english\\controller\\words_converted_with_isok.csv";

        // SQL查询用于插入数据
        String insertSQL = "INSERT INTO CET4 (Word, N, V, ADJ, ADV, PREP, CONJ, PRON, INTERJ, VI, VT, isOK, PronunciationURL, UKPhonetic, USPhonetic) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            // 读取CSV文件
            String lineText = null;
            lineReader.readLine(); // 跳过头部

            while ((lineText = lineReader.readLine()) != null) {
                try {
                    String[] data = lineText.split(",", -1);

                    // 设置单词和词性数据
                    for (int i = 1; i <= data.length; i++) {
                        preparedStatement.setString(i, data[i - 1]);
                    }

                    // 设置PronunciationURL, UKPhonetic, USPhonetic字段
                    for (int i = data.length + 1; i <= 15; i++) {
                        preparedStatement.setString(i, ""); // 设置这些字段为空字符串
                    }

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    System.err.println("Error inserting line: " + lineText);
                    System.err.println(e.getMessage());
                }
            }

            System.out.println("Data has been inserted into the table.");

        } catch (IOException ex) {
            System.err.println("Error reading the CSV file: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Database error: " + ex.getMessage());
        }
    }
}
