package com.gh.base;

import com.gh.pojo.base.BaseEntity;
import com.gh.utils.PagingResult;
import com.gh.utils.Search;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 持久层基类
 */
public abstract class BaseMybatisDAO {
    @Autowired
    @Qualifier("sessionTemplate")
    SqlSessionTemplate sqlSessionTemplate;

    public <T extends BaseEntity> T find(String sqlid,T t)throws SQLException{

        return  sqlSessionTemplate.selectOne(sqlid,t);
    }
    public <T extends BaseEntity> T find(String sqlid,Integer id)throws SQLException{

        return  sqlSessionTemplate.selectOne(sqlid,id);
    }
    /**
     * 分页查询指定SQL语句的数据
     *
     * @param countSqlId 总数查询SQL语句ID
     * @param sqlId      SQL语句ID
     * @param search     查询
     * @return 分页查询结果PagingResult
     */
    public <T extends BaseEntity> PagingResult<T> findForPage(String countSqlId, String sqlId, Search search) throws SQLException {
        RowBounds rowBounds = new RowBounds(search.getFirstRowNum(), search.getRows());

        return null;
    }

    /**
     * 分页查询指定SQL语句的数据，不查询总的数据量
     *
     * @param sqlId SQL语句ID
     * @param page  页码
     * @param rows  每页记录数
     * @return 分页查询结果PagingResult
     */
    public <T extends BaseEntity> PagingResult<T> findForPage(String countSqlId, String sqlId, int page, int rows, Map<String, Object> params) throws SQLException {
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<T> data = this.sqlSessionTemplate.selectList(sqlId, params, rowBounds);
        return new PagingResult<>(cont(countSqlId,params), data, page, rows);
    }

    /**
     * 获取指定查询语句的总数
     *
     * @param SqlId 获取指定查询语句的 sqlid
     * @return 记录的总数
     * @throws SQLException
     */
    protected long cont(String SqlId) throws SQLException {

        return this.sqlSessionTemplate.selectOne(SqlId);
    }

    /**
     * 获取指定查询语句的总数
     *
     * @param SqlId  获取指定查询语句的 sqlid
     * @param params
     * @return 记录的总数
     * @throws SQLException
     */
    protected long cont(String SqlId, Map<String, Object> params) throws SQLException {
        return this.sqlSessionTemplate.selectOne(SqlId, params);
    }

    /**
     * 插入数据
     * @param sqlid
     * @param t
     * @param <T>
     * @return
     * @throws SQLException
     */
    protected <T extends BaseEntity>  Integer save(String sqlid,T t)throws SQLException{
        return this.sqlSessionTemplate.insert(sqlid,t);
    }
  protected <T extends  BaseEntity> Integer EditModfiy(String sqlid,T t) throws SQLException{
        return this.sqlSessionTemplate.update(sqlid,t);
  }
  protected Integer deleteById(String sqlid,Integer id)throws SQLException{
      return this.sqlSessionTemplate.delete(sqlid,id);
  }


}
