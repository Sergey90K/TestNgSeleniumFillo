import PageObject.RedmineObjForTask;
import PageObject.RedminePageForTask;
import org.testng.annotations.Test;

public class Task1 {
    RedminePageForTask redminePageForTask = new RedminePageForTask();
    RedmineObjForTask redmineObjForTask = new RedmineObjForTask();

    @Test (groups = {"regress,smokeTest"})
    public void homePageValidations(){
        redmineObjForTask.validationsOfReferences(redminePageForTask.homePageRefXpas(), redminePageForTask.homePageRefUrl());
    }
    @Test (groups = {"regress"})
    public void progectsValidations(){
        redmineObjForTask.validationsOfReferences(redminePageForTask.progectRefXpas(), redminePageForTask.progectRefUrl());
    }
    @Test (groups = {"regress,smokeTest"})
    public void helpValidations(){
        redmineObjForTask.validationsOfReferences(redminePageForTask.helpRefXpas(), redminePageForTask.helpRefUrl());
    }
    @Test (groups = {"smokeTest"})
    public void enterValidations(){
        redmineObjForTask.validationsOfReferences(redminePageForTask.enterRefXpas(), redminePageForTask.enterRefUrl());
    }
    @Test (groups = {"regress,smokeTest"})
    public void registrationsValidations(){
        redmineObjForTask.validationsOfReferences(redminePageForTask.regidtrationsRefXpas(), redminePageForTask.regidtrationsRefUrl());
    }
    @Test (groups = {"regress"})
    public void searchValidations(){
        redmineObjForTask.validationsOfReferences(redminePageForTask.searchRefXpas(), redminePageForTask.searchRefUrl());
    }

    @Test (groups = {"regress,smokeTest"})  // эта часть в задании не требуется, могу удалить из функционала за ненадобностью, дописывал ее для собственного интереса
    public void allRefInOneTest(){
        redmineObjForTask.allValidationsOfReferences(redminePageForTask.allRefXpas(), redminePageForTask.allRefUrl());
    }
}
