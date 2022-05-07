package practice.datastruc.json;

import java.util.List;

/**
 * @author nathan
 */
public class ResultInfo {
    public String total;
    public String records;
    public String page;
    public List<Cell> rows;
    
    static class Cell{
        public List<String> cell;
        public String id;
    }
    
    
}