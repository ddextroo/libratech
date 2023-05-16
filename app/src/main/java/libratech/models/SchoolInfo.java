
package libratech.models;


public class SchoolInfo {
    
    public SchoolInfo() {
        
    }
    
    public String[] getSchoolName() {
        
        String[] schoolNames = {
            "CTU - Dexter Campus",
            "CTU - Gwapo Campus",
            "CTU - Cavite Campus",
        };
        
        return schoolNames;
    }
    public int[] getSchoolID() {
        
        int[] schoolIDs = {
            123,
            456,
            789,
        };
        
        return schoolIDs;
    }
}
