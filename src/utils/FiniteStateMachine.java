package utils;

public class FiniteStateMachine {
    public enum State {
        MAIN_MENU,
        CREATE_LEDGER,
        OPEN_LEDGER,
        DELETE_LEDGER,
        SHOW_LEDGER,
        CREATE_ENTRY,
        SHOW_ENTRY,
        DELETE_ENTRY,
        END_TRACKER,
        INVALID_STATE
    }

    private State currState;
    private State nextState;

    public FiniteStateMachine(State currState){
        this.currState = currState;
        this.nextState = State.INVALID_STATE;
    }
    public State getCurrState(){
        return this.currState;
    }
    public void setNextState(State state){
        this.nextState = state;
    }
    public void transitionState(State nexState){
        this.currState = nextState;
        setNextState(nexState);
    }
    public void transitionState(){
        this.currState = nextState;
        setNextState(State.INVALID_STATE);
    }
}
