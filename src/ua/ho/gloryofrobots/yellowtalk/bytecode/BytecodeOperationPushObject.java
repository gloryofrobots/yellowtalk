package ua.ho.gloryofrobots.yellowtalk.bytecode;

import ua.ho.gloryofrobots.yellowtalk.stobject.STContext;
import ua.ho.gloryofrobots.yellowtalk.stobject.STObject;
import ua.ho.gloryofrobots.yellowtalk.stobject.STScope;
import ua.ho.gloryofrobots.yellowtalk.stobject.STStack;

public class BytecodeOperationPushObject extends BytecodeOperation {

    @Override
    public
    void perform(int argument)  {
        STContext context = mRoutine.getContext();
        STObject name = mRoutine.getExecutable().getLiteral(argument);
        if(name.toString().equals("block2")) {
            int bdsm = 1;
            int x =bdsm;
        }
        STObject obj = context.lookup(name);
        if(obj == null) {
            obj = context.lookup(name);
            runtimeError("Uknown name %s", name.toString());
        }
        
        STStack stack = mRoutine.getStack();
        stack.push(obj);
    }

}
