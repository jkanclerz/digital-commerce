package pl.jkan.ecommerce.sales.readmodel.productcatalog;

import org.springframework.jdbc.core.JdbcTemplate;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.productcatalog.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SQLProductFinder implements ProductFinder {

    private JdbcTemplate sql;

    public SQLProductFinder(JdbcTemplate sql) {
        this.sql = sql;
    }

    public List<ProductDto> all() {

        return new ArrayList<>(sql.query(
                "SELECT * FROM ProductProjection",
                (rs, rowNum) -> new ProductDto(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getString("description")
                )
        ));
    };
}
