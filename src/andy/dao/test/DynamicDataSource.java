package andy.dao.test;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Andy<andy_513@163.com>
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /** 
     *  
     * override determineCurrentLookupKey 
     * <p> 
     * Title: determineCurrentLookupKey 
     * </p> 
     * <p> 
     * Description: 自动查找datasource 
     * </p> 
     *  
     * @return 
     */  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return DBContextHolder.getDbType();  
    }  
}
