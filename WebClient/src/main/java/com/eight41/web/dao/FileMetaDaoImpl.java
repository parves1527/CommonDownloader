package com.eight41.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.eight41.web.model.FileMeta;

public class FileMetaDaoImpl implements FileMetaDao
{
    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean add(FileMeta fileMeta)
    {
        String sql = "insert into file_meta"
                + "(uri, size, file_name, file_type, download_path, status)"
                + " values(?,?,?,?,?,?)";

        jdbcTemplate.update(sql, new Object[]
        {
            fileMeta.getUri(), fileMeta.getSize(), fileMeta.getFileName(),
            fileMeta.getFileType(), fileMeta.getDownloadPath(), fileMeta.getStatus()
        });
        
        return true;
    }

    @Override
    public FileMeta get(int id)
    {
        String sql = "select * from file_meta where id='" + id + "'";

        List<FileMeta> fileMetas = jdbcTemplate.query(sql, new FileMetaMapper());

        return fileMetas.size() > 0 ? fileMetas.get(0) : null;
    }

}

class FileMetaMapper implements RowMapper<FileMeta>
{
    @Override
    public FileMeta mapRow(ResultSet rs, int arg1) throws SQLException
    {
        FileMeta fileMeta = new FileMeta();
        fileMeta.setId(rs.getInt("id"));
        fileMeta.setUri(rs.getString("uri"));
        fileMeta.setSize(rs.getInt("size"));
        fileMeta.setFileName(rs.getString("file_name"));
        fileMeta.setFileType(rs.getString("file_type"));
        fileMeta.setDownloadPath(rs.getString("download_path"));
        fileMeta.setStatus(rs.getString("status"));

        return fileMeta;
    }
}
