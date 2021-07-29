import PageObject.RedmineObjForTask;
import PageObject.RedminePageForTask;
import org.testng.annotations.Test;

public class Task1 {
    RedminePageForTask redminePageForTask = new RedminePageForTask();
    RedmineObjForTask redmineObjForTask = new RedmineObjForTask();

    @Test (groups = {"regress,smokeTest"})
    public void homePageValidations(){
        redmineObjForTask.validationsOfReferences(redminePageForTask.urlPage(), redminePageForTask.homePageRefXpas(), redminePageForTask.homePageRefUrl());
    }
    @Test (groups = {"regress"})
    public void progectsValidations(){
        redmineObjForTask.validationsOfReferences(redminePageForTask.urlPage(), redminePageForTask.progectRefXpas(), redminePageForTask.progectRefUrl());
    }
    @Test (groups = {"regress,smokeTest"})
    public void helpValidations(){
        redmineObjForTask.validationsOfReferences(redminePageForTask.urlPage(), redminePageForTask.helpRefXpas(), redminePageForTask.helpRefUrl());
    }
    @Test (groups = {"smokeTest"})
    public void enterValidations(){
        redmineObjForTask.validationsOfReferences(redminePageForTask.urlPage(), redminePageForTask.enterRefXpas(), redminePageForTask.enterRefUrl());
    }
    @Test (groups = {"regress,smokeTest"})
    public void registrationsValidations(){
        redmineObjForTask.validationsOfReferences(redminePageForTask.urlPage(), redminePageForTask.regidtrationsRefXpas(), redminePageForTask.regidtrationsRefUrl());
    }
    @Test (groups = {"regress"})
    public void searchValidations(){
        redmineObjForTask.validationsOfReferences(redminePageForTask.urlPage(), redminePageForTask.searchRefXpas(), redminePageForTask.searchRefUrl());
    }

    @Test (groups = {"regress,smokeTest"})
    public void allRefInOneTest(){
        redmineObjForTask.allValidationsOfReferences(redminePageForTask.urlPage(), redminePageForTask.allRefXpas(), redminePageForTask.allRefUrl());
    }
}
