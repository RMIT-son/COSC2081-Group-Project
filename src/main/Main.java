package main;

import main.InterfaceUtils.Interface;
import org.fusesource.jansi.AnsiConsole;

public class Main {
    public static void main(String[] args) {
        Interface.run();
        AnsiConsole.systemUninstall();
    }
}
