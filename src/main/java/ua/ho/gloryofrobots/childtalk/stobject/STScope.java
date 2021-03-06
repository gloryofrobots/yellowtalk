package ua.ho.gloryofrobots.childtalk.stobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ua.ho.gloryofrobots.childtalk.bootstrap.ImageSuite;
import ua.ho.gloryofrobots.childtalk.compilation.DuplicateVariableException;
import ua.ho.gloryofrobots.childtalk.stobject.classprovider.BindingClassProvider;

public class STScope extends STInternalDictionary {
    private static final long serialVersionUID = 1L;
    private STScope next = null;
    
    protected STScope() {
        super();
    }
    
    public static STScope create() {
        STScope obj = new STScope();
        obj.setClassProvider(new BindingClassProvider(obj) {
           
            private static final long serialVersionUID = 1L;

            @Override
            protected STClass _getSTClass() {
                return ImageSuite.image().classes().Scope;
            }
        });
        
        return obj;
    }
    
    
    @SuppressWarnings (value="unchecked")
    public <T extends STObject> 
    T getAndCast(STObject key) {
        STObject value = at(key);
        T obj = null;
        try {
            obj = (T) value;    
        } catch(ClassCastException e) {
            return null;
        } 
        
        return obj;
    }
    
    public void append(STScope scope) {
        next = scope;
        //scope.setPrevious(this);
    }
    
//    private void setPrevious(STScope prev) {
//        previous = prev;
//    }
    
    public boolean assign(STObject key, STObject value) {
         STScope scope = this;
         while(scope != null) {
             if(scope.has(key)) {
                 scope.put(key, value);
                 return true;
             }
             scope = scope.next;
         }
         
         return false;
    }
    
    public STObject lookup(STObject key) {
        STScope scope = this;
        while(scope != null) {
            STObject  value = scope.at(key);
            if(value != null) {
                return value;
            }
            scope = scope.next;
        }
        
        return null;
    }
    
    public void putUnique(STObject name, STObject object)
            throws DuplicateVariableException {
        if (has(name)) {
            throw new DuplicateVariableException(name.toString());
        }
        
        put(name, object);
    }
    
    public List<STObject> asList() {
        ArrayList<STObject> copy = new  ArrayList<STObject>(mData);
        return copy;
    }
    
    public STScope copySelf() {
        STScope clone = new STScope();
        for (Map.Entry<STObject, Integer> item : mBinding.entrySet()) {
            STObject key = item.getKey();
            STObject value = mData.get(item.getValue());
            clone.put(key, value);
        }
        
        return clone;
    }
    
    @Override
    public String toString() {
        String result = "";
        for(STObject obj : mData) {
            result += obj.toString() + "\n";
        }
        
        return result;
    }
}
