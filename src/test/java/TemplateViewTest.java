import it.uniroma2.ispw.view.cli.TemplateView;

import java.util.List;

class TemplateViewTest extends TemplateView {


    @Override
    public void control() {

    }

    @Override
    protected List<String> getOptions() {
        return List.of("1 ", "2 ", "3 ");
    }

    @Override
    protected String getHeader() {
        return "ciao";
    }

    public static void main(String[]args){
        TemplateViewTest test = new TemplateViewTest();
        int choice=test.userChoice();
        System.out.println("user option ="+choice);
    }
}