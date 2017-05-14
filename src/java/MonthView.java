import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
@ConversationScoped
public class MonthView implements Serializable{
    private String month;
    private List<String> months;  

     
    @PostConstruct
    public void init() {
        months = new ArrayList<String>();
        months.add("Januar");
        months.add("Februar");
        months.add("März");
        months.add("Mai");
        months.add("Juni");
        months.add("Juli");
        months.add("August");
        months.add("September");
        months.add("Oktober");
        months.add("November");
        months.add("Dezember");
    }
 
    public String getMonth(){
        return month;
    }
    
    
    public void setMonth(String month){
        this.month = month;
    }
    
    
    public List<String> getMonths() {
        return months;
    }
}