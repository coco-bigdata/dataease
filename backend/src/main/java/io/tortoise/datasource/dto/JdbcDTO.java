package io.tortoise.datasource.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JdbcDTO {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String dataBase;
    private String dataSourceType = "jdbc";
}
