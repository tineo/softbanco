package com.softbanco.repositories;

import com.softbanco.entities.Record;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 * Record repository abstract interface.
 * 
 * @author Petr Smrcek <smrkous@gmail.com>
 */
@Local
public interface RecordRepository {
    public void create(Record r);
    public void delete(int id);
    public void updateContent(int id, String content);
    public List<Record> getAll();
}
