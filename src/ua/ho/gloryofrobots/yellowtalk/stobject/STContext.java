package ua.ho.gloryofrobots.yellowtalk.stobject;

import ua.ho.gloryofrobots.yellowtalk.Universe;
import ua.ho.gloryofrobots.yellowtalk.stobject.classprovider.BindingClassProvider;

public class STContext extends STObject {
    private static final long serialVersionUID = 1L;
    STObject mReceiver;
    
    private STContext() {
        transformToScopedObject();
    }
    
    public static STContext create() {
        STContext obj = new STContext();
        obj.setClassProvider(new BindingClassProvider(obj) {
            @Override
            protected STClass _getSTClass() {
                return Universe.classes().Context;
            }
        });
        return obj;
    }
    
    public STObject getReceiver() {
        return mReceiver;
    }
    
    public void setReceiver(STObject mReceiver) {
        this.mReceiver = mReceiver;
    }
    
    public void assign(STObject varName, STObject value) {
        mScope.assign(varName, value);
    }

    public STObject lookup(STObject varName) {
        STObject result = mScope.lookup(varName);
        
        if(result == null) {
            return Universe.image().lookup(varName);
        }
        
        return result;
    }
    
    public void pushScope(STScope scope) {
        scope.append(mScope);
        setScope(scope);
    }
}
