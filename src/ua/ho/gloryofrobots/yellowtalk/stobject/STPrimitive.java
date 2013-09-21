package ua.ho.gloryofrobots.yellowtalk.stobject;

import ua.ho.gloryofrobots.yellowtalk.Universe;
import ua.ho.gloryofrobots.yellowtalk.inout.SignalSuite;
import ua.ho.gloryofrobots.yellowtalk.scheduler.Routine;


public abstract class STPrimitive extends STObject {
   
    private static final long serialVersionUID = 1L;
    
    
    
    public boolean execute(Routine routine) {
        STObject receiver = routine.getContext().getReceiver();
        STStack stack = routine.getStack();
        
        STObject result = onExecute(routine, receiver, stack);
        if(result == null) {
            return false;
        }
        //we don`t need write result to stack
        if(result == Universe.objects().NIL) {
            routine.complete();
            return true;
        }
        
        routine.compliteWithResult(result);
        return true;
    }
    
    public void primitiveError(Routine routine, String format, Object... args) {
        SignalSuite.error(format, args);
        //SignalSuite.raiseError(routine, format, args);
    }
    
    protected abstract STObject onExecute(Routine routine, STObject receiver, STStack stack);
}
