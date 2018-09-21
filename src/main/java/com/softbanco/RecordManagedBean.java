package com.softbanco;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.softbanco.entities.Record;
import com.softbanco.repositories.RecordRepository;
import org.primefaces.event.RowEditEvent;

/**
 * Managed Bean for JSF.
 * 
 * @author Petr Smrcek <smrkous@gmail.com>
 */

@ManagedBean
@RequestScoped
public class RecordManagedBean 
{
    @EJB
    private RecordRepository recordRepository;

    private String content;
    private int id;
    private List<Record> recordList;

    public RecordManagedBean()
    {
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String create()
    {
        Record r = new Record();
        r.setContent(content);
        recordRepository.create(r);
        updateList();
        return "index";
    }
    
    public void delete(int id)
    {
        recordRepository.delete(id);
        updateList();
    }
    
    public void onRowEdit(RowEditEvent event) {  
        Record r = (Record)event.getObject();
        recordRepository.updateContent(r.getId(),r.getContent());
    }  

    public List<Record> getList()
    {
        if (recordList == null) {
            updateList();
        }
        return recordList;
    }
    
    private void updateList() {
        recordList = recordRepository.getAll();
    }
}
