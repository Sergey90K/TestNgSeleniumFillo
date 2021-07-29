import PageObject.RedmineObjForTask;
import PageObject.RedminePageForTask;
import org.testng.annotations.Test;

public class Task2 {
    RedminePageForTask redminePageForTask = new RedminePageForTask();
    RedmineObjForTask redmineObjForTask = new RedmineObjForTask();

    @Test (groups = {"regress,smokeTest"})
public  void test(){
redmineObjForTask.testInputField(redminePageForTask.urlPage(), redminePageForTask.allFieldXpas(), redminePageForTask.okButton(), redminePageForTask.errorFild());
}
@Test (groups ={"regress"} )
    public void testSearchField(){
        redmineObjForTask.testSearchField(redminePageForTask.urlPage(),redminePageForTask.searchField(),redminePageForTask.testDataForSearch());
}
}
