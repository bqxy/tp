package utils;

public class exceptions {
    public static class EmptyContentException extends Exception{
        public EmptyContentException(String command) {
            super("The title of a " + command + " cannot be empty");
        }
    }
    public static class InvalidIndexException extends Exception {
        public InvalidIndexException(int index) {
            super("Invalid index " + index + " provided!");
        }
        public InvalidIndexException(String input) {
            super("\"" + input + "\" is not a number!");
        }
    }
    public static class InvalidParamArgument extends Exception {

        public InvalidParamArgument(String paramType) {
            super("No param argument provided for param /" + paramType);
        }

        public InvalidParamArgument(String paramType, boolean exist) {
            super("Param /" + paramType + " specified more than once!");
        }

        public InvalidParamArgument(String paramType, String paramArgument) {
            super("Invalid param argument " + paramArgument + " for param /"
                    + paramType);
        }
    }
    public static class InvalidParamType extends Exception {
        public InvalidParamType(String paramType) {
            super("Invalid param /" + paramType);
        }
    }
}
