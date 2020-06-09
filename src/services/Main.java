package services;

import parsers.FactParser;
import parsers.RuleParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Application application = new Application();
        application.runApp();
    }
}
