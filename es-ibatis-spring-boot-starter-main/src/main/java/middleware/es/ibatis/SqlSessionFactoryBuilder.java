package middleware.es.ibatis;

import middleware.es.autoconfigure.ESIBatisProperties;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SqlSessionFactoryBuilder {

    public DefaultSqlSessionFactory build(ESIBatisProperties esiBatisProperties) throws Exception {
        Configuration configuration = new Configuration();
        configuration.setConnection(connection(esiBatisProperties));
        configuration.setMapperElement(mapperElement(esiBatisProperties.getMappers()));
        return new DefaultSqlSessionFactory(configuration);
    }

    private Connection connection(ESIBatisProperties esiBatisProperties) {
        try {
            return DriverManager.getConnection(esiBatisProperties.getAddress(), new Properties());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取SQL语句信息
    private Map<String, XNode> mapperElement(String resource) {
        Map<String, XNode> map = new HashMap<>();
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element root = document.getRootElement();

            //命名空间
            String namespace = root.attributeValue("namespace");

            // SELECT
            List<Element> selectNodes = root.selectNodes("select");
            for (Element node : selectNodes) {
                String id = node.attributeValue("id");
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getText();

                // ? 匹配
                Map<Integer, String> parameter = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                for (int i = 1; matcher.find(); i++) {
                    String g1 = matcher.group(1);
                    String g2 = matcher.group(2);
                    parameter.put(i, g2);
                    sql = sql.replace(g1, "?");
                }

                XNode xNode = new XNode();
                xNode.setNamespace(namespace);
                xNode.setId(id);
                xNode.setParameterType(parameterType);
                xNode.setResultType(resultType);
                xNode.setSql(sql);
                xNode.setParameter(parameter);

                map.put(namespace + "." + id, xNode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return map;
    }

}
