package commands;

public class StringCommand extends Command {

    private final static String STRING_PREFIX = "string: ";
    private int repeat;

    public StringCommand(String message, int repeat) {
        super.message = message;
        this.repeat = repeat;
    }

    @Override
    public String getDecorated(){
        String decoratedStr = STRING_PREFIX + message;
        return repeat == 0? decoratedStr: decoratedStr + " (x" + ++repeat + ")";
    }


    @Override
    public Command getAccumulated(Command other) {
        return new StringCommand((String) message, repeat + 1);
    }

    @Override
    public Boolean equalsCommand(Command other) {
        return other instanceof StringCommand && message.equals(other.message) ;
    }

}
