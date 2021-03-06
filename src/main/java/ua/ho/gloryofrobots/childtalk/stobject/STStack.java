package ua.ho.gloryofrobots.childtalk.stobject;

import ua.ho.gloryofrobots.childtalk.bootstrap.ImageSuite;
import ua.ho.gloryofrobots.childtalk.stobject.classprovider.BindingClassProvider;

public class STStack extends STCollection  {
    private static final long serialVersionUID = 1L;
    
    private int mIndex;

    protected STStack(int size) {
        super();
        init();
    }
    
    public static STStack create() {
        STStack obj = new STStack(DEFAULT_SIZE);
        obj.setClassProvider(new BindingClassProvider(obj) {
            
            private static final long serialVersionUID = 1L;

            @Override
            protected STClass _getSTClass() {
               
                return ImageSuite.image().classes().Stack;
            }
        });
        return obj;
    }
    
    public static STStack create(int size) {
        STStack obj = new STStack(size);
        obj.setSTClass(ImageSuite.image().classes().Stack);
        return obj;
    }
    
    public void init() {
        mIndex = 0;
    }
    
    public void push(STObject obj) {
        put(mIndex, obj);
        
        mIndex++;
    }

    public STObject pop() {
        mIndex -= 1;
        STObject obj = at(mIndex);
        return obj;
    }

    public STObject peek() {
        if (mIndex == 0) {
            return null;
        }
        
        int index = getCurrentIndex();
        STObject res = at(index);
        return res;
    }
    
    public int getCurrentIndex() {
        return mIndex - 1;
    }
    
    public int getCurrentPosition() {
        return mIndex;
    }

    public void setIndex(int position) {
        mIndex = position;
    }

    
    public STObject getFromEnd(int shift) {
        return at(mIndex - shift);
    }
    
    public String toString() {
        String data = "<STStack ";
        for(int i = 0; i < mIndex; i++) {
            STObject obj = at(i);
            data += obj.toString() + " ";
        }
        data += ">";
        return data;
    }

    public void flushCollection(STCollection collection) {
        collection.foreach(new ForeachFunction() {
            
            @Override
            public boolean call(STObject obj) {
                push(obj);
                return true;
            }
        });
    }
}
