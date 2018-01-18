package pl.jkan.ecommerce.sales.infrastructure.productcatalog;

import org.springframework.jdbc.core.JdbcTemplate;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;
import pl.jkan.ecommerce.sales.domain.productcatalog.ProductCatalog;

import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLProductCatalog implements ProductCatalog {

    private JdbcTemplate sql;

    public SQLProductCatalog(JdbcTemplate sql) {
        this.sql = sql;
    }

    @Override
    public Product load(Identifier id) {
        return sql.query(
                "SELECT id, stock_qty, price FROM ProductCatalog WHERE id = ?",
                new Object[] { id.toString() },
                (rs, rowNum) -> new Product(
                        new Identifier(rs.getString("id")),
                        rs.getInt("stock_qty"),
                        rs.getDouble("price")
                )
            ).stream()
            .findFirst()
            .get()
        ;
    }

    @Override
    public void add(Product product) {

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                product.getId().toString(),
                product.getStockQty(),
                product.getPrice()
        });

        sql.batchUpdate("INSERT INTO ProductCatalog(id, stock_qty, price) VALUES (?,?,?)", list) ;
    }

}
