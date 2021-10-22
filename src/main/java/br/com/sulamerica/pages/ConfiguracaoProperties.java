package br.com.sulamerica.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfiguracaoProperties {
    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(".\\target\\classes\\config.properties");
        props.load(file);
        return props;
    }
}
