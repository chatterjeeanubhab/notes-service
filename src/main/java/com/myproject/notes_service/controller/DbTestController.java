package com.myproject.notes_service.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
@RestController
public class DbTestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/check-db")
    public String checkDatabase() {
        try {
            Connection connection = dataSource.getConnection();
            return "Connected to database: " + connection.getCatalog();
        } catch (Exception e) {
            return "Connection failed: " + e.getMessage();
        }
    }
    @GetMapping("/hi")
    public String checkDbConnection() {
        return "Hello, World!";
    }
}