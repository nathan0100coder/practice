package practice.datastruc.obj;
 
/**
 * @author nathan
 */
public class Menu {
    private String id;  
    private String name;  
    private String pid;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    @Override
    public String toString() {
        return "Menu [id=" + id + ", name=" + name + ", pid=" + pid + "]";
    }  
    
}