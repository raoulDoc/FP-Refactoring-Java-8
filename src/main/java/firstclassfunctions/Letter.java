package firstclassfunctions;

public class Letter {

    private final String message;

    public Letter(String message) {
        this.message = message;
    }

    public Letter addDefaultHeader(){
        return new Letter("From her majesty:\n" + message);
    }

    public Letter checkSpelling(){
        return new Letter(message.replaceAll("FTW", "for the win"));
    }

    public Letter addDefaultFooter(){
        return new Letter(message + "\nKind regards");
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "message='" + message + '\'' +
                '}';
    }
}
