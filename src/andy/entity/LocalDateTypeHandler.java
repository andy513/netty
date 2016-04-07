package andy.entity;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * @author Andy<andy_513@163.com>
 */
public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType) throws SQLException {
		ps.setDate(i, Date.valueOf(parameter));
	}

	@Override
	public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Date date = rs.getDate(columnName);
		if (date != null) {
			return date.toLocalDate();
		}
		return null;
	}

	@Override
	public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Date date = rs.getDate(columnIndex);
		if (date != null) {
			return date.toLocalDate();
		}
		return null;
	}

	@Override
	public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Date date = cs.getDate(columnIndex);
		if (date != null) {
			return date.toLocalDate();
		}
		return null;
	}

}
