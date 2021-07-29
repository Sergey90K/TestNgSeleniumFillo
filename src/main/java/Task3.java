import PageObject.RedmineObjForTask;
import PageObject.RedminePageForTask;
import com.codoid.products.exception.FilloException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Task3 {
    RedminePageForTask redminePageForTask = new RedminePageForTask();
    RedmineObjForTask redmineObjForTask = new RedmineObjForTask();

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod( ) throws FilloException {
                    String[] outerData1 = new String[7];
                    String[] outerData2 = new String[7];
                    String[] outerData3 = new String[7];
                    String[] outerData4 = new String[7];
                    String[] outerData5 = new String[7];
                    String[] outerData6 = new String[7];

        String[][] data =  redmineObjForTask.readDataFromExcel();
                    for(int i=0; i<7; i++){
                       outerData1[i] = data[i][0];
                    }
                     for(int i=0; i<7; i++){
                         outerData2[i] = data[i][1];
                     }
                     for(int i=0; i<7; i++){
                     outerData3[i] = data[i][2];
                     }
                     for(int i=0; i<7; i++){
                        outerData4[i] = data[i][3];
                     }
                     for(int i=0; i<7; i++){
                     outerData5[i] = data[i][4];
                     }
                     for(int i=0; i<7; i++){
                        outerData6[i] = data[i][5];
                     }
        return new Object[][]{{outerData1}, {outerData2}, {outerData3}, {outerData4},{outerData5},{outerData6}};


    }
    @Test(dataProvider = "data-provider", groups = {"regress,smokeTest"})
    public void testMethod(String [] data){
       redmineObjForTask.addValueInField(data,redminePageForTask.urlPage(), redminePageForTask.allFieldXpas(),redminePageForTask.okButton(),redminePageForTask.errorFild());
    }
}
