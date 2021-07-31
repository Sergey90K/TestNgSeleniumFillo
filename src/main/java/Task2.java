import PageObject.RedmineObjForTask;
import PageObject.RedminePageForTask;
import org.testng.annotations.Test;

public class Task2 {
    RedminePageForTask redminePageForTask = new RedminePageForTask();
    RedmineObjForTask redmineObjForTask = new RedmineObjForTask();

    @Test(groups = {"regress,smokeTest"})
    public void testUserField(){
        redmineObjForTask.inputOnlyOneField(redminePageForTask.userField(),redmineObjForTask.getUser(),redminePageForTask.okButton(),redminePageForTask.errorFild());
    }
    @Test(groups = {"regress"})
    public void testPasswordField(){
        redmineObjForTask.inputOnlyOneField(redminePageForTask.passwordField(),redmineObjForTask.getPassword(),redminePageForTask.okButton(),redminePageForTask.errorFild());
    }
    @Test(groups = {"regress"})
    public void testConfirmPasswordField(){
        redmineObjForTask.inputOnlyOneField(redminePageForTask.secondPasswordField(),redmineObjForTask.getConfirmPassword(),redminePageForTask.okButton(),redminePageForTask.errorFild());
    }
    @Test(groups = {"regress"})
    public void testNameField(){
        redmineObjForTask.inputOnlyOneField(redminePageForTask.nameField(),redmineObjForTask.getName(),redminePageForTask.okButton(),redminePageForTask.errorFild());
    }
    @Test(groups = {"regress"})
    public void testLastNameField(){
        redmineObjForTask.inputOnlyOneField(redminePageForTask.lastNameField(),redmineObjForTask.getLastName(),redminePageForTask.okButton(),redminePageForTask.errorFild());
    }
    @Test(groups = {"regress"})
    public void testEmailField(){
        redmineObjForTask.inputOnlyOneField(redminePageForTask.emailField(),redmineObjForTask.getEmail(),redminePageForTask.okButton(),redminePageForTask.errorFild());
    }
    @Test(groups = {"regress"})
    public void testIrcNicField(){
        redmineObjForTask.inputOnlyOneField(redminePageForTask.ircNickField(),redmineObjForTask.getIrcNick(),redminePageForTask.okButton(),redminePageForTask.errorFild());
    }




  //  @Test (groups = {"regress,smokeTest"})
//public  void test(){// больше не ктуальна,так как делает проверку для всех полей одновременно
//redmineObjForTask.testInputField(redminePageForTask.allFieldXpas(), redminePageForTask.okButton(), redminePageForTask.errorFild());
//}
@Test (groups ={"regress"} )
    public void testSearchField(){ // в задании не требуется, можно удалить из функционала
        redmineObjForTask.testSearchField(redminePageForTask.searchField(),redminePageForTask.testDataForSearch());
}
}
