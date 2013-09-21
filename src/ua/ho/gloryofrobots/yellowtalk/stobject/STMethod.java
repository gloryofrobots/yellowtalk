package ua.ho.gloryofrobots.yellowtalk.stobject;

import ua.ho.gloryofrobots.yellowtalk.Universe;
import ua.ho.gloryofrobots.yellowtalk.compilation.DuplicateVariableException;
import ua.ho.gloryofrobots.yellowtalk.scheduler.MethodRoutine;
import ua.ho.gloryofrobots.yellowtalk.scheduler.PrimitiveRoutine;
import ua.ho.gloryofrobots.yellowtalk.scheduler.Routine;
import ua.ho.gloryofrobots.yellowtalk.stobject.classprovider.BindingClassProvider;

public class STMethod extends STExecutableObject {

    private static final long serialVersionUID = 1L;

    protected STSymbol mComment;
    protected STSymbol mCategory;
    protected STSymbol mPrimitiveName = null;
    protected STSymbol mClassName;
    protected STClass mOwnerClass;
    private STSymbol mSelector;

    public void setOwnerClass(STClass owner) {
        mOwnerClass = owner;
    }

    public static STMethod create() {
        STMethod obj = new STMethod();
        obj.setClassProvider(new BindingClassProvider(obj) {
            @Override
            protected STClass _getSTClass() {
                return Universe.classes().Method;
            }
        });

        return obj;
    }

    public void setComment(STSymbol comment) {
        mComment = comment;
    }

    public void setCategory(STSymbol category) {
        mCategory = category;
    }

    public void setPrimitiveName(STSymbol primitive) {
        mPrimitiveName = primitive;
    }

    public STSymbol getPrimitiveName() {
        return mPrimitiveName;
    }

    public STPrimitive getPrimitive() {
        if (mOwnerClass == null) {
            return null;
        }

        STPrimitive primitive = mOwnerClass.getPrimitive(mPrimitiveName);
        return primitive;
    }

    public boolean hasPrimitive() {
        if (mPrimitiveName == null) {
            return false;
        }

        return true;
    }

    @Override
    public Routine createRoutine() {
        Routine routine;

        if (hasPrimitive() == false) {
            routine = new MethodRoutine(this);
        } else {
            routine = new PrimitiveRoutine(this);
        }

        return routine;
    }

    public void setClassName(STSymbol name) {
        mClassName = name;
    }

    public void setSelector(STSymbol name) {
        mSelector = name;
    }

    @Override
    public String toString() {
        String result = String.format(
                "<Method %s from %s>",
                (mSelector != null) ? mSelector.toString() : "",
                (mOwnerClass != null) ? mOwnerClass.toString() : "");

        return result;
    }
}
