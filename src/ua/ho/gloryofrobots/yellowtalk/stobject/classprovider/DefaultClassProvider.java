package ua.ho.gloryofrobots.yellowtalk.stobject.classprovider;

import ua.ho.gloryofrobots.yellowtalk.stobject.STClass;

public class DefaultClassProvider implements ClassProvider {
    private STClass mClass;
    
    public DefaultClassProvider(STClass _class) {
        mClass = _class;
    }
    
    @Override
    public STClass getSTClass() {
        return mClass;
    }
    
}