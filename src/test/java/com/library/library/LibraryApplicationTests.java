//package com.library.library;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.sql.DataSource;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@SpringBootTest
//@Slf4j
//class LibraryApplicationTests {
////
////    @Autowired
////    private DataSource dataSource;
////
////    @Test
////    void applicationCanConnectToDatabase() {
////        assertThat(dataSource).isNotNull();
////
////        try {
////            Connection connection = dataSource.getConnection();
////            assertThat(connection).isNotNull();
////            assertThat(connection.getCatalog()).isEqualTo("library");
////        } catch (SQLException e) {
////            log.info("Error occurred when connecting to db --> {}", e.getMessage());
////        }
////    }
//
//}
