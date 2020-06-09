package services;

import parsers.FactParser;
import parsers.RuleParser;

import java.io.IOException;

public class Application {

    public void runApp() throws IOException {
        FactParser factParser = new FactParser();
        RuleParser ruleParser = new RuleParser();
        ESProvider esProvider = new ESProvider(factParser, ruleParser);
        esProvider.collectAnswers();
        String result = esProvider.evaluate();
        if (result.equalsIgnoreCase("")){
            result = "No matches found";
        }
        System.out.println("\nThe perfect car for you will be: " + result);
    }
}
