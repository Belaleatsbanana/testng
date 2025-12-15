package Runners;

public class TestRunner {

    static void main() {
        try {
            io.cucumber.core.cli.Main.run(
                    new String[]{
                            "--glue", "StepDefinitions",
                            "--plugin", "pretty",
                            "--plugin", "html:target/cucumber-report.html",
                            "--plugin", "json:target/cucumber-report.json",
                            "src/test/resources/features"
                    },
                    Thread.currentThread().getContextClassLoader()
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}