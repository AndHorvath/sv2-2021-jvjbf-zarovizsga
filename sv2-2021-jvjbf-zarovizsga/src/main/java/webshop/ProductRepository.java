package webshop;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Objects;

public class ProductRepository {

    // --- attributes ---------------------------------------------------------

    private JdbcTemplate jdbcTemplate;

    // --- constructors -------------------------------------------------------

    public ProductRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // --- getters and setters ------------------------------------------------

    public JdbcTemplate getJdbcTemplate() { return jdbcTemplate; }

    // --- public methods -----------------------------------------------------

    public long insertProduct(String productName, int price, int stock) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> getPreparedStatement(connection, productName, price, stock), keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    //language=SQL
    public Product findProductById(long id) {
        return jdbcTemplate.queryForObject(
            "select * from products where id = ?",
            (resultSet, rowNumber) -> getProductFromResult(resultSet, id), id
        );
    }

    //language=SQL
    public void updateProductStock(long id, int amount) {
        jdbcTemplate.update(
            "update products set stock = stock - ? where id = ?", amount, id
        );
    }

    // --- private methods ----------------------------------------------------

    private PreparedStatement getPreparedStatement(
        Connection connection, String productName, int price, int stock) throws SQLException {

        PreparedStatement preparedStatement = createPreparedStatementForSave(connection);
        parametrizeSaveUpdate(preparedStatement, productName, price, stock);
        return preparedStatement;
    }

    //language=SQL
    private PreparedStatement createPreparedStatementForSave(Connection connection) throws SQLException {
        return connection.prepareStatement(
            "insert into products (product_name, price, stock) values (?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        );
    }

    private void parametrizeSaveUpdate(
        PreparedStatement preparedStatement, String productName, int price, int stock) throws SQLException {

        preparedStatement.setString(1, productName);
        preparedStatement.setInt(2, price);
        preparedStatement.setInt(3, stock);
    }

    private Product getProductFromResult(ResultSet resultSet, long id) throws SQLException {
        return new Product(
            id,
            resultSet.getString("product_name"),
            resultSet.getInt("price"),
            resultSet.getInt("stock")
        );
    }
}