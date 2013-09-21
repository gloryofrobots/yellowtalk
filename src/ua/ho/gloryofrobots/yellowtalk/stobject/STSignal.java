package ua.ho.gloryofrobots.yellowtalk.stobject;

import ua.ho.gloryofrobots.yellowtalk.Universe;
import ua.ho.gloryofrobots.yellowtalk.scheduler.Routine;
import ua.ho.gloryofrobots.yellowtalk.stobject.classprovider.BindingClassProvider;

public class STSignal extends STObject {
  
    private static final long serialVersionUID = 1L;

    class StackTrace {

        public void add(Routine routine) {
            
        }
        
    }
    
    public void addToTrace(Routine routine) {
        mTrace.add(routine);
    }
    
    StackTrace mTrace;
    STSignal() {
        mTrace = new StackTrace();
        transformToScopedObject();
        
    }
    
    public static STSignal create() {
        STSignal obj = new STSignal();
        obj.setClassProvider(new BindingClassProvider(obj) {
            @Override
            protected STClass _getSTClass() {
                return Universe.classes().Signal;
            }
        });
        
        return obj;
    }
}
