package andy.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author andy<andy_513@163.com>
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSouce();
	}

}
